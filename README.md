# Quarkus Function

## Bootstrap the project

With Maven Archetype:

```shell
mvn archetype:generate -B -DarchetypeGroupId=io.quarkus -DarchetypeArtifactId=quarkus-azure-functions-http-archetype  -DarchetypeVersion=2.14.0.Final -DjavaVersion=11 -DgroupId=com.targa.labs.dev -DartifactId=azure-quarkus-func  -Dversion=1.0.0-SNAPSHOT  -DappName=azure-resources-handler  -DappRegion=northeurope -Dfunction=azure-resources-handler -DresourceGroup=helloworld-rg
```

With Quarkus Maven plugin:

```shell
mvn io.quarkus:quarkus-maven-plugin:2.14.0.Final:create \
  -DplatformVersion=2.14.0.Final \
  -DprojectGroupId=pg.home \
  -DprojectArtifactId=quarkus-azfa \
  -DclassName="io.azfa.function.CarResource" \
  -Dpath="/cars" \
  -Dextensions="resteasy, resteasy-jackson, undertow, reactive-routes, azure-functions-http"
```

```shell
mvn io.quarkus:quarkus-maven-plugin:2.14.0.Final:create \
  -DplatformVersion=2.14.0.Final \
  -DprojectGroupId=pg.home \
  -DprojectArtifactId=data-access \
  -DclassName="io.azfa.function.CarResource" \
  -Dpath="/cars" \
  -Dextensions="hibernate-orm-panache, jdbc-mysql"
```

## Setup the Azure environment

### Login to Azure 

First, sign in to your Azure account using the Azure CLI:

```shell
az login
````

Make sure you are using the right subscription with:

```shell
az account show
````

### Setting Up the Environment Variables

To create the Azure environment with Azure CLI, first start by setting a

```shell
PROJECT="quarkus-azf-cars"
RESOURCE_GROUP="rg-${PROJECT}"
LOCATION="northeurope"
TAG="${PROJECT}"

MYSQL_DB="mysql-${PROJECT}"
MYSQL_DB_SCHEMA="db-${PROJECT}"
MYSQL_DB_ADMIN=adminazfcars
MYSQL_DB_PWD=adminazfcars

APP_INSIGHTS="appi-${PROJECT}"
FUNCTION_APP="func-${PROJECT}"
````

### Create the Resource Group

```shell
az group create \
  --name "$RESOURCE_GROUP" \
  --location "$LOCATION" \
  --tags system="$TAG"
````

### Create the Azure Database for MySQL

Create the database with the following command:

```shell
az mysql flexible-server create \
  --resource-group "$RESOURCE_GROUP" \
  --location "$LOCATION" \
  --tags system="$TAG" \
  --name "$MYSQL_DB" \
  --database-name "$MYSQL_DB_SCHEMA" \
  --admin-user "$MYSQL_DB_ADMIN" \
  --admin-password "$MYSQL_DB_PWD" \
  --public-access all \
  --tier "Burstable" \
  --sku-name "Standard_B1ms" \
  --storage-size 32 \
  --version "5.7"
````

Create the table and add some data:

```shell
az mysql flexible-server execute \
  --name "$MYSQL_DB" \
  --admin-user "$MYSQL_DB_ADMIN" \
  --admin-password "$MYSQL_DB_PWD" \
  --database-name "$MYSQL_DB_SCHEMA" \
  --file-path "data-access/createDB.sql"
````

Execute an SQL statements directly in the database with the following command:

```shell
az mysql flexible-server execute \
  --name "$MYSQL_DB" \
  --admin-user "$MYSQL_DB_ADMIN" \
  --admin-password "$MYSQL_DB_PWD" \
  --database-name "$MYSQL_DB_SCHEMA" \
  --querytext "select * from cars"
````

To get the connection string to the database, use the following command:

```shell
MYSQL_CONNECTION_STRING=$(
  az mysql flexible-server show-connection-string \
    --server-name "$MYSQL_DB" \
    --admin-user "$MYSQL_DB_ADMIN" \
    --admin-password "$MYSQL_DB_PWD" \
    --database-name "$MYSQL_DB_SCHEMA" \
    --query "connectionStrings.jdbc" \
    --output tsv
)

echo "$MYSQL_CONNECTION_STRING"
````

## Create the Application Insights

If you do not create an Application Insights resource, the Azure Functions runtime will create one for you.
If you pref to create a separate one, use the following command:

```shell
az monitor app-insights component create \
  --app "${APP_INSIGHTS}" \
  --resource-group "$RESOURCE_GROUP" \
  --location "$LOCATION" \
  --tags system="$TAG" \
  --application-type function
```

In the `pom.xml` file make to uncomment `<appInsightsInstance>${app.insights.name}</appInsightsInstance>`

## Deploy the function

We can deploy the function with the Azure Function Maven plugin.
The plugin is configured in the `quarkus-azfa/pom.xml` file.
Make sure you have the right region configured in the properties.

```xml
<properties>
  <function.app.name>fun-quarkus-azf-cars</function.app.name>
  <function.app.region>northeurope</function.app.region>
  <function.resource.group>rg-quarkus-azf-cars</function.resource.group>
  <function.name>quarkus-azf-cars</function.name>
  <app.insights.name>appi-quarkus-azf-cars</app.insights.name>
</properties>
```

Package the function with the following command under the `quarkus-azfa` folder:

```shell 
mvn clean package
```

You should see the function packaged into a JAR file and the JSON configuration files under `target/azure-functions/fun-quarkus-azf-cars`.
Then, use the following Maven command under the `quarkus-azfa` folder:

```shell
mvn azure-functions:deploy
```

WARNING: If you get the following message in the portal (_Your app is currently in read only mode because you are running from a package file. To make any changes update the content in your zip file and WEBSITE_RUN_FROM_PACKAGE app setting_) then you need to redeploy the function and restart the function app from the portal.

To get the URL of the deployed function, use the following command:

```shell 
az functionapp function show \
  --name "${FUNCTION_APP}" \
  --function-name "${PROJECT}" \
  --resource-group "$RESOURCE_GROUP" 
```

### Azure China deployment

To support Azure China deployment, several parameters need to be updated in `pom.xml`:
Parameter region should be one of the Azure China Region. For example: `chinanorth2`

```xml
<plugin>
  <groupId>com.microsoft.azure</groupId>
  <artifactId>azure-functions-maven-plugin</artifactId>
  <version>${azure.functions.maven.plugin.version}</version>
  <configuration>
    <resourceGroup>${functionResourceGroup}</resourceGroup>
    <appName>${functionAppName}</appName>
    <region>chinanorth2</region>
```

Parameter environment should be `AzureChina` and provide a service principle with contributor role for the function.

```xml
<plugin>
  <groupId>com.microsoft.azure</groupId>
  <artifactId>azure-functions-maven-plugin</artifactId>
  <version>${azure.functions.maven.plugin.version}</version>
  <configuration>
    <resourceGroup>${functionResourceGroup}</resourceGroup>
    <appName>${functionAppName}</appName>
    <region>${functionAppRegion}</region>
    <runtime>
      <!-- runtime os, could be windows, linux or docker-->
      <os>windows</os>
      <javaVersion>11</javaVersion>
    </runtime>
    <appSettings>
      <property>
        <name>FUNCTIONS_EXTENSION_VERSION</name>
        <value>~3</value>
      </property>
    </appSettings>
    <auth>
      <type>service_principal</type>
      <client></client>
	  <tenant></tenant>
      <key></key>
      <environment>AzureChina</environment>
  </auth>
  </configuration>
</plugin>
```

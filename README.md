# QuarkusFunction

With Maven Archetype:

```
mvn archetype:generate -B -DarchetypeGroupId=io.quarkus -DarchetypeArtifactId=quarkus-azure-functions-http-archetype  -DarchetypeVersion=2.14.0.Final -DjavaVersion=11 -DgroupId=com.targa.labs.dev -DartifactId=azure-quarkus-func  -Dversion=1.0.0-SNAPSHOT  -DappName=azure-resources-handler  -DappRegion=northeurope -Dfunction=azure-resources-handler -DresourceGroup=helloworld-rg
```

With Quarkus Maven plugin:

```
mvn io.quarkus:quarkus-maven-plugin:2.14.0.Final:create \
  -DplatformVersion=2.14.0.Final \
  -DprojectGroupId=pg.home \
  -DprojectArtifactId=quarkus-azfa \
  -DclassName="io.quarkus.GreetingResource" \
  -Dpath="/cars" \
  -Dextensions="resteasy, resteasy-jackson, undertow, reactive-routes, azure-functions-http"
```

```
mvn io.quarkus:quarkus-maven-plugin:2.14.0.Final:create \
  -DplatformVersion=2.14.0.Final \
  -DprojectGroupId=pg.home \
  -DprojectArtifactId=data-access \
  -DclassName="io.quarkus.GreetingResource" \
  -Dpath="/cars" \
  -Dextensions="hibernate-orm-panache, jdbc-mysql"
```

```
For development environment deployment, use mvn command:

mvn azure-functions:deploy

To support Azure China deployment, several parameters need to be updated in pom.xml:

1. Parameter region should be one of the Azure China Region. For example: chinanorth2 
 <plugin>
        <groupId>com.microsoft.azure</groupId>
        <artifactId>azure-functions-maven-plugin</artifactId>
        <version>${azure.functions.maven.plugin.version}</version>
        <configuration>
          <resourceGroup>${functionResourceGroup}</resourceGroup>
          <appName>${functionAppName}</appName>
          <region>chinanorth2</region> .........

2. Parameter environment should be "AzureChina" and provide a service principle with contributor role for the function.

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

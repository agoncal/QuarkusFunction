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

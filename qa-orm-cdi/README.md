# qa-orm-cdi

CDI integration for the qa-orm library.

##### Requirements
* Java 8+
* Maven

##### Quick Start

Add the QuakeArts.com maven repository

```
<repositories>
    <repository>
        <id>YOUR-PROJECT-NAME-mvn-repo</id>
        <url>https://raw.github.com/kwakutwumasi/Quakearts-Webtools/mvn-repo/</url>
    </repository>
</repositories>

```

then add the dependency

```
<dependency>
	<groupId>com.quakearts.webtools</groupId>
	<artifactId>qa-orm-cdi</artifactId>
	<version>1.0.1</version>
</dependency>

```

This library provides a producer for _com.quakearts.webapp.orm.DataStore_ instances. Due to the nature of some CDI integrations, the _DataStore_ returned is a proxy that creates the DataStore the first time any of the API methods are called. This _DataStore_ is not suitable for use in _@java.inject.Singleton_ or application scoped injection targets. If this cannot be avoided, the _DataStore_ should be injected as part of method parameters. Alternatively the _com.quakearts.webapp.orm.DataStoreFactory_ can be injected and used to obtain _DataStore_ instances.
<br /><br />
Injection should be done using the qualifier _@com.quakearts.webapp.orm.cdi.annotation.DataStoreHandle_ for _DataStore_ and the qualifier _@com.quakearts.webapp.orm.cdi.annotation.DataStoreFactoryHandle_ for _DataStoreFactory_.

```java
	@Inject @DataStoreFactoryHandle DataStoreFactory factory;
	
	@Inject @DataStoreHandle DataStore dataStore;
```
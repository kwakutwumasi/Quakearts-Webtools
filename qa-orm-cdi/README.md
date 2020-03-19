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
	<version>1.1.0</version>
</dependency>

```

This library provides a producer for _com.quakearts.webapp.orm.DataStore_ instances. Due to the nature of some CDI integrations, the _DataStore_ returned is a proxy that creates the DataStore the first time any of the API methods are called. This _DataStore_ is suitable for use in scope including _@java.inject.Singleton_ and application scoped injection targets. The _DataStore_ can also be injected as part of method parameters. For use cases that require the _com.quakearts.webapp.orm.DataStoreFactory_, it can be injected and used to obtain _DataStore_ instances.
<br /><br />
Injection should be done using the qualifier _@com.quakearts.webapp.orm.cdi.annotation.DataStoreHandle_ for _DataStore_ and the qualifier _@com.quakearts.webapp.orm.cdi.annotation.DataStoreFactoryHandle_ for _DataStoreFactory_.
<br />
Some ORM libraries, like Hibernate, EclipseLink etc, have the capability of binding the lifecycle of their ORM handles to a _javax.transaction.Transaction_. This means that getting a _DataStoreHandle_ that is not synchronized with a _Transaction_ will lead to unspecified errors when injected into the field of a Singleton or application scoped injection targets. To help with these types of _DataStore_ implementations, a special implementation of _DataStore_ has been provided that takes care of creating and destroying _DataStore_ handles within a transaction. In order to get this _DataStore_, use the _@com.quakearts.webapp.orm.cdi.annotation.RequiresTransaction_ annotation.

```java
	//Get a DataStoreFactory
	@Inject @DataStoreFactoryHandle DataStoreFactory factory;
	
	//Get a DataStore
	@Inject @DataStoreHandle DataStore dataStore;
	
	//Get a DataStore bound to the lifecycle of a Transaction
	@Inject @DataStoreHandle @RequiresTransaction DataStore dataStore;
	
```
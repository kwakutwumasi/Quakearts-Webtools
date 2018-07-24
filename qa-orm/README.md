# qa-orm

An Abstraction of ORM/NoSQL data storage and retrieval. This library can be used to abstract away the backing storage for applications. It is especially suited for CRUD apps. Data storage libraries can implement the _com.quakearts.webapp.orm.DataStore_ interface and provide a _com.quakearts.webapp.orm.DataStoreFactory_ that can be searched and registered using the [Java Service Locator mechanism](https://docs.oracle.com/javase/8/docs/api/java/util/ServiceLoader.html).

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
	<artifactId>qa-orm</artifactId>
	<version>2.3.1</version>
</dependency>

```

Implementers can then implement the _com.quakearts.webapp.orm.DataStore_ interface and provide a _com.quakearts.webapp.orm.DataStoreFactory_. Then a file named 'com.quakearts.webapp.orm.DataStoreFactory' should be placed in the _META-INF/services_ folder of the implementation. It must contain only one line: the name of a class that extends _DataStoreFactory_. This will make it possible for the _DataStoreFactory_ to find the factory and register it to provide an implementation of _com.quakearts.webapp.orm.DataStore_.
<br /><br />
The _DataStore_ can be used by applications to abstract away the actual data storage mechanism. This is great especially for commercial/open source applications that can plug-in different storage backends. It is also possible to offer different storage options for different sized hardware; a small IOT device can use a simple, NoSQL storage option, while a datacenter server can use a large relational database.

###### Using com.quakearts.webapp.orm.DataStore

An instance of _com.quakearts.webapp.orm.DataStore_ can be obtained by calling the _getDataStore()_ function on an instance of _com.quakearts.webapp.orm.DataStoreFactory_.

```java
	DataStore dataStore = DataStoreFactory.getInstance().getDataStore();
```

The _get()_ method retrieves data using a unique identifier:

```java
	Personnel personnel = dataStore.get(Personnel.class, 1);
```

The _save()_ method stores new data to the data store.

```
	Personnel personnel = ... ;
	dataStore.save(personnel);
```

The _update()_ method replaces data in the data store with the new data.

```java
	Personnel personnel = ... ;
	dataStore.update(personnel);
```

The _saveOrUpdate()_ method first checks the data store for the data. If it finds the data, it replaces it with the new data. Otherwise it stores the new data. This is useful for situations where the user is uncertain of the status of data in the data store.

```java
	Personnel personnel = ... ;
	dataStore.saveOrUpdate(personnel);
```

The _delete()_ method removes data from the data store.

```java
	Personnel personnel = ... ;
	dataStore.delete(personnel);
```

The _find()_ method is a fluid API for searching the data store. Parameters are specified using the fluid methods:

```java
	List<ORMTest> list = dataStore.find(ORMTest.class)
			.filterBy("testValue").withAValueEqualTo("Test Value")
			.filterBy("testVariable").withAValueLike("Test Variable")
			.filterBy("testRangeBetween").withValues().between(0).and(1)
			.filterBy("testRangeFrom").withValues().startingFrom(0)
			.filterBy("testRangeTo").withValues().upTo(1)
			.filterBy("testChoices").withAValueEqualToOneOf("Choice1","Choice2","Choice3")
			.useAResultLimitOf(3)
			.orderBy(new QueryOrder("testOrder1", false))
			.orderBy(new QueryOrder("testOrder2", true))
			.thenList();
```

For these API methods every search parameter must match the data. This is analogous to using the 'and' operator to join each search term in SQL. The fluid API can also be terminated with _thenGetFirst()_ which returns a java.util.Optional. It may or may not contain the first element of the list from the search, depending on the result of the search.
<br /><br />
_list()_ comes in two methods: one to list all objects of a certain class, and another to list data matching the parameters passed to it. The latter is a more advanced than the _find()_ method. It makes it possible to create complex 'and'/'or' search paramters. This is done using the _com.quakearts.webapp.orm.query.helper.ParameterMapBuilder_ 

```java
	List<SalesPart> salesParts = dataStore.list(SalesPart.class, 	
		ParameterMapBuilder.createParameters().disjoin().add("product.name", "Altima")
					.add("product.id", 2).conjoin()
					.add("product.brand.name", "Audi")
					.add("product.brand.id", 1).build());
```

The _ParameterMapBuilder_ is a fluid api for building a _java.util.Map_ of search parameters. The _disjoin()_ method creates a disjoint set of parameters, i.e. return data that matches one or more the parameters. This is analogous to using the 'or' operator to join each search term in SQL. The _conjoin()_ method creates a conjoined set of parameters, i.e. every search parameter must match the data, just like using the 'and' operator to join each search term in SQL. This is the default behavior.
<br /><br />
The rest of the methods deal with data store related tasks. _refresh()_ can be used to fetch the latest version of data from the data store. This is useful for usage instances where the data object may get out of sync with the data in the data store. _flushBuffers()_ is useful for forcing the data store to push its cached data to the underlying storage. _getConfigurationProperty()_ returns configuration parameters. _executeFunction()_ exposes data store specific API to users.

```java
	dataStore.executeFunction((connector)->{
		DataStoreConnection dataStoreConnection = connector.getConnection(DataStoreConnection.class);
		dataStoreConnection.doSomethingWithIt();
		...
	})
```
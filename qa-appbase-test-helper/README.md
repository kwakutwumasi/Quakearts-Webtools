# qa-appbase-test-helper
A collection of abstract classes for use as parent classes for appbase tests

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
	<groupId>com.quakearts.webtools.test</groupId>
	<artifactId>qa-appbase-test-helper</artifactId>
	<version>1.0.2</version>
</dependency>

```

There are four test classes that can be implemented:

* com.quakearts.webtools.test.AppBaseCDIBaseTest - a base class for tests that require CDI services in qa-appbase
* com.quakearts.webtools.test.AppBaseFullBaseTest - a base class for tests that require the full qa-appbase services
* com.quakearts.webtools.test.AppBaseTransactionManagerTestBase - a base for tests that require transaction manager services
* com.quakearts.webtools.test.AppDatasourceBeanTestBase - a base class for tests that require Datasource services

The classes take care of startup and tear down of the necessary services in order to ensure the environment perfectly matches running conditions. 
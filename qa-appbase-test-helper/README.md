# qa-appbase-test-helper
A collection of JUnit4 runners for use as with qa-appbase app tests

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
	<version>2.0.1</version>
</dependency>

```

There are four test Runners that can be used:

* com.quakearts.webtools.test.CDIRunner - a runner for tests that require CDI 2.0 services in qa-appbase
* com.quakearts.webtools.test.AllServicesRunner - a runner for tests that require the full qa-appbase services
* com.quakearts.webtools.test.TransactionManagerRunner - a base for tests that require transaction manager services
* com.quakearts.webtools.test.DataSourceRunner - a runner for tests that require data source services
* com.quakearts.webtools.test.EmbeddedWebServerRunner - a runner for tests that require Servlet 3+/4+ services

The classes take care of startup and tear down of the necessary services in order to ensure the environment perfectly matches running conditions. 
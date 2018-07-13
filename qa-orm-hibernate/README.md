# qa-orm-hibernate

An implementation of qa-orm for the Hibernate Library.

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
	<artifactId>qa-orm-hibernate</artifactId>
	<version>2.3.1</version>
</dependency>

```

This library automatically registers a _com.quakearts.webapp.orm.DataStoreFactory_ instance that installs the implementation.
<br /><br />
See the documentation for Hibernate for details of how to configure and deploy instances.

###### qa-appbase JTA

This library provides a _org.hibernate.engine.transaction.jta.platform.spi.JtaPlatform_ implementation for qa-appbase: _com.quakearts.webapp.hibernate.appbase.AppBaseJTAPlatform_.

###### Non JTA platforms

This library provides a _org.hibernate.context.spi.CurrentSessionContext_ for non-JTA environments. It creates  _org.hibernate.Session_ objects and makes them available to the _org.hibernate.SessionFactory_'s _getCurrentSession()_ call.

###### com.quakearts.webapp.orm.DataStoreConnection

The _com.quakearts.webapp.orm.DataStoreConnection_ provided in a call to the _executeFunction()_ returns two connection types: _java.sql.Connection_ and _org.hibernate.Session_.
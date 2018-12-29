# qa-beansupport

A library for updating a Java Bean with another bean (of the same class) using the properties of the latter.
The updater operates in two modes: ignore null and empty (the default), or not. In the first case, null or empty properties are ignored. In the second case, null or empty values are copied, effectively deleting the properties. Additionally another mode, treat zero as empty, makes primitive number fields treat the value zero as an empty field. When turned off, primitive values will always be copied regardless of the value.
<br /><br />
This class works using handlers that know how to treat special objects. The handlers implement _com.quakearts.webapp.beansupport.emtpyhandler.BeanEmptyHandler_. The updater comes out of the box with support for Java primitives, Java Collections and Java Maps support, as well as for Java java.lang.String.
<br />
Implement _com.quakearts.webapp.beansupport.emtpyhandler.BeanEmptyHandler_ for custom types and call the static method _registerEmptyHandlers()_ to register it.

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
	<artifactId>qa-beansupport</artifactId>
	<version>2.3.2</version>
</dependency>

```

The updater is started using a constructor method and uses fluid APIs for configuration. Once created the _com.quakearts.webapp.beansupport.BeanUpdater_ instance can be used to update a bean:

```java
	BeanUpdater<PersonBean> updater = new BeanUpdater().dontIgnoreNullAndEmpty();
	
	updater.update(sourceBean, destinationBean);
```

If you need to know which elements have been updated, use the _

The _BeanUpdater_ is thread safe.
# qa-weldjsf-cdi

This is a port of the Weld JSF CDI Extension from org.jboss to work with qa-appbase. It provides support for using CDI with JSF. CDI beans and context can be used with JSF Facelet pages.

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
	<artifactId>qa-weldjsf-cdi</artifactId>
	<version>1.0.1</version>
</dependency>

```

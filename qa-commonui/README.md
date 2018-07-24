# qa-commonui

A java.sql.Timestamp converter, and a filename validator

##### Requirements
* Java 8+
* Maven
* A Servlet Container (Tomcat, Jetty, Wildfly, Undertow, etc)

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
	<artifactId>qa-commonui</artifactId>
	<version>2.2.1</version>
</dependency>

```

##### Additional Details

In order to get full auto-completion in Eclipse, install the Quake Arts Metadata Plugin from the [Quakearts-Update-Site](https://github.com/kwakutwumasi/Quakearts-Update-Site) project
<br />
All tags and their attributes have been documented and auto-completion values have been added to ease typing.
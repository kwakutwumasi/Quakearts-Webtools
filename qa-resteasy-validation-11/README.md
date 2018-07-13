# qa-resteasy-validation-11

This is a port of the RestEasy Validation Extension from org.jboss to work with qa-appbase. It provides support for using Bean Validation in RestEasy JAX-RS components. Validation can be done in Web Resources and Providers.

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
	<artifactId>qa-resteasy-validation-11</artifactId>
	<version>1.0.1</version>
</dependency>

```
# qa-codegenerator-annotations

Annotations for use with [Quakearts-Code-Generator-Plugin](https://github.com/kwakutwumasi/Quakearts-Code-Generator-Plugin).

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
	<artifactId>qa-codegenerator-annotations</artifactId>
	<version>1.0.0</version>
</dependency>

```

There are four annotations: 

* com.quakearts.webapp.codegeneration.annotations.CodeGeneratorProperties - a collection of CodeGeneratorProperty instances
* com.quakearts.webapp.codegeneration.annotations.CodeGeneratorProperty - A generator property that can be used by the template engine for additional generation capabilites
* com.quakearts.webapp.codegeneration.annotations.Order - When a field, a bean getter or a bean setter method is annotated, the number value guarantees that the property will be rendered before properties annotated with a higher numerical value and all other properties
* com.quakearts.webapp.codegeneration.annotations.Skip - When a field, a bean getter or a bean setter method is annotated, the property is skipped and will not be generated.



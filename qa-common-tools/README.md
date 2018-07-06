# qa-common-tools

A framework for writing command line utilities in Java. The framework handles parameter parsing. Metadata annotations allow developers to write documentation. The framework takes care of transforming the metadata into usage printouts. This frees up the developer and allows her to concentrate on writing the command execution code.

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
	<artifactId>qa-common-tools</artifactId>
	<version>1.0.0</version>
</dependency>

```

Developers need only implement the _com.quakearts.utilities.Command_ interface. The command can be run by calling

```
java -cp ... com.quakearts.utilities.CommandMain your.Command
```

Commands can be packaged into uberjars. This makes it easier to run the command by calling

```
java -jar youruber.jar your.Command
```

A utility class is available for generating MS-DOS and *nix Bash Shell scripts. Simply pass the name of your uberjar and a folder where you wish to save the scripts:

```
java -cp ... com.quakearts.utilities.GenerateBatchFiles -name youtuber.jar -folder ../scripts
```

The scripts can be run as normal OS commands. See the documentation for your OS for more details.

Be sure to make your uber jar an executable jar by adding a MANIFEST.MF file to the META-INF folder of your jar with Main-Class and Classpath entries. See [the jar deployment trail](https://docs.oracle.com/javase/tutorial/deployment/jar/index.html) on the Java tutorial site for more information on executable jars.
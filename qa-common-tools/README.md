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
	<groupId>com.quakearts.utilities</groupId>
	<artifactId>qa-common-tools</artifactId>
	<version>1.0.1</version>
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

##### Specifying command line information using annotations

There are two annotation classes. The main annotation is _com.quakearts.utilities.annotation.CommandMetadata_. This documents general information about the command. The other is _com.quakearts.utilities.annotation.CommandParameterMetadata_ for documenting parameters.
<br />
_CommandMetadata_ has 5 attributes:

```
value - The name of the command.

description - The command description to print out

parameters - A set of parameter metadata to print out

additionalInfo - Additional information, such us expected output, links, etc. Analogous to the information printed at the end of *nix Man Pages

examples - Example commands to help the user. Analogous to the information printed at the end of *nix Man Pages

```

It is placed on the class declaration of the command implementation :

```java

@CommandMetadata(value="testExecutor", parameters={
		@CommandParameterMetadata("v"),
		@CommandParameterMetadata(value = "f", required = true),
		@CommandParameterMetadata(value = "test1", alias="alias1", format = "format1"),
		@CommandParameterMetadata("test2"),
		@CommandParameterMetadata(value = "test3", format = "format2", description = "description1")
})
public class TestCommandExecutor extends CommandBase { ... }

```

The _CommandParameterMetadata_ has 7 attributes:

```
value - The parameter name

alias - An alias for the parameter name

format - The description of the expected format of the parameter value

description - The description of the parameter

required - A boolean value indicating that the command is required

linkedParameters - A semicolon separated list of parameters required by the presence of this parameter 

canOmitName - A boolean value indicating that the parameter name can be omitted. For use with a single parameter, usually the last parameter and the target of the command

```
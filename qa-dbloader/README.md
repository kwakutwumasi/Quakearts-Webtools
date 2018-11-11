# qa-dbloader

A classloader that uses JPA to load and store class files. Useful for projects that may require dynamic deployment of plugin-like operations. Due to the nature of the file storage, it is not suitable for large jar files. Using large jar files requires large amounts of heap storage, and is not the most efficient way of deploying large dynamic systems. An OSGI environment like Eclipse would be better suited.

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
	<artifactId>qa-dbloader</artifactId>
	<version>2.2.2</version>
</dependency>

```

The _com.quakearts.tools.classloaders.DBJarClassLoader_ can be instantiated in two ways:

```java
	DBJarClassLoader loader = new DBJarClassLoader();
```

or

```java
	DBJarClassLoader loader = new DBJarClassLoader("mydomain");
```

where 'mydomain' is the name of the domain to pass to _com.quakearts.webapp.orm.DataStore_.
<br /><br />
The _DBJarClassLoader_ can be used as a thread context classloader or in standalone mode. In standalone mode simply call the _getDBJarClass(String)_ method with the name of the class to load.

```java
	Class<?> loadedClass = loader.getDBJarClass("my.custom.Plugin");
```

##### com.quakearts.tools.classloaders.utils.JarFileStorer

A convenience class _com.quakearts.tools.classloaders.utils.JarFileStorer_ is provided to quickly store jar files. It unzips the jar file, catalogs each resource entry and stores them in the database. In the event that a duplicate resource is detected, it compares the two and registers the newest file. It also contains data store maintenance procedures to clean out jar files that are obsolete.
<br /><br />
To create the _JarFileStorer_, use one of the two constructors:

```java
	JarFileStorer storer = new JarFileStorer();
```

or 

```java
	JarFileStorer storer = new JarFileStorer("mydomain");
```

where 'mydomain' is the name of the domain to pass to _com.quakearts.webapp.orm.DataStore_.
<br /><br />
To store a jar file, read the jar bytes into a byte array, and pass it to the _storeJarFile()_ method.

```java
	InputStream inputStream = ...;
	byte[] jarbytes = new byte[inputStream.available()];
	inputStream.read(jarbytes);
	storer.storeJarFile(jarbytes,"my-jarfile.jar");
```

The _JarFileStorer_ can also operate on multiple _DataStore_s. In such instances, the store to use is passed as part of the variables:

```java
	DataStore mydataStore = ...;
	InputStream inputStream = ...;
	byte[] jarbytes = new byte[inputStream.available()];
	inputStream.read(jarbytes);
	storer.storeJarFile(jarbytes,"my-jarfile.jar",mydataStore);
```


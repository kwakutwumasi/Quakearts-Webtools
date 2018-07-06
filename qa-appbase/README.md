# qa-appbase

This library forms the basis of the QuakeArts.com application platform. It is a custom integration of Java Transaction API (JTA), Java Connector Architecture (JCA), Context and Dependency Injection (CDI), a Servlet Container, and Java Naming and Directory Interface (JNDI), all part of the Jakarta Enterprise Edition (JEE) Standard (formally Java Enterprise Edition). It is similar to the Spring Boot Platform.

#### Requirements

* Java 8+
* Maven (for building from source)

#### Quick Start

##### Maven
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
	<artifactId>qa-appbase</artifactId>
	<version>1.1</version>
</dependency>

```

You can also use one of the three maven archetypes created for quickly bootstrapping a qa-appbase project:

* qa-appbase-archetype
* qa-appbase-jsf-orm-archetype
* qa-appbase-resteasy-orm-archetype

They can be viewed in the [repository root](/kwakutwumasi/Quakearts-Webtools)

#### Current Implementations

qa-appbase has been designed in a modular fashion to easily swap out implementations of the JEE standard modules (JTA/JCA/CDI/Servlet/JNDI). Currently the server uses the following implementations:

* JTA: Atomikos JTA 4.0.4
* JCA: Atomikos Datasource Beans/Custom Code
* CDI: Jboss Weld 2.4.2.Final
* Servlet: Tomcat Embedded 8.5.9
* JNDI: Apache Naming/Custom Code (integrated with Tomcat Embedded)

#### Configuration

Configuration is done through a configuration file that can be passed in at the command line. If no command line file is specified, a file named default.configuration is searched for at the root of the classpath.

The configuration file is a simple Java Properties file (plain/XML) that contains the following property keys:

```

cdi.spi.class=
java.tm.spi.class=
embedded.ws.spi.class=
jndi.spi.class=
datasource.spi.class=

```

Each property key holds the name of the SPI class that bootstraps the implementation of the JEE module.

* cdi.spi.class -> CDI
* java.tm.spi.class -> JTA
* embedded.ws.spi.class -> Servlet Container
* jndi.spi.class -> JNDI
* datasource.spi.class -> JCA 

You may use the default implementations or swap out one for your own.
The default implementations are: 

```
cdi.spi.class=com.quakearts.appbase.spi.impl.WeldContextDependencySpiImpl
java.tm.spi.class=com.quakearts.appbase.spi.impl.AtomikosJavaTransactionManagerSpiImpl
embedded.ws.spi.class=com.quakearts.appbase.spi.impl.TomcatEmbeddedServerSpiImpl
jndi.spi.class=com.quakearts.appbase.spi.impl.JavaNamingDirectorySpiImpl
datasource.spi.class=com.quakearts.appbase.spi.impl.AtomikosBeanDatasourceProviderSpiImpl
```

####Running the application

The application is run by using the following command:

```
java -cp ... com.quakearts.appbase.Main your.main.Class
```

where _your.main.Class_ is replaced by the name of your main startup class. The startup class needs only one method

```java
	public void init(){
		///Your initializing code here
	}
```

The code within it will be called during bootstrapping after all the standard modules are started and are ready to be interacted with. (Servlet applications may not have been fully initialized, however).

It is important to note the CDI plays an important role within qa-appbase. As such the main class must be within a valid Bean Archive. See CDI documentation for more details.

The application can be shutdown by terminating the VM. It can also be shutdown by calling 

```
java -cp ... com.quakearts.appbase.Main
```

More documentation on running the application can be found by running 

```
java -cp ... com.quakearts.appbase.Shutdown
```

_com.quakearts.appbase.Shutdown_ works by sending a kill signal to the process. The port it send the kill signal on defaults to port 9999. This can be changed within the application using the _shutdown.port_ property key. In that case the first argument to shutdown command should be the new port.

This does not work if the _-dontwaitinmain_ parameter is passed when starting the application

####Specific implementation notes

##### Tomcat

The embedded Tomcat Servlet container uses the following folder structure

```
	[application root]/
	└───webservers
		└──[web server name]/
			├──conf/
			|	└──server.config.json
			└──webapps/
				└──[webapp name]
					└──META-INF
						└──webapp.config.json
```

* _[application root]_ is the root of your application, the folder from which the application is launched. 
* _[web server name]_ can be replaced with any name you wish to give the server. 
* The _conf/_ folder contains a JSON file _server.config.json_ which contains server configuration parameters such as port, connectors, encryption, etc. Examples can be found in the _webservers/_ folder of the project
* The _webapps/_ folder contains exploded servlet wars. 
* _[webapp name]_ corresponds to the context root of the application; e.g. _weather-application/_ would mean the application is available at http://localhost/weather-application. 
* _webapp.config.json_ is a special file that enables you to expose libraries/folders in your main application classpath to scanning by the Tomcat Servlet container. Examples can be found in the _webservers/_ folder of the project.

Multiple servers can be created as specified. The _conf/server.config.json_ must be used to specify different ports for each of the servers. 
Multiple servers are useful for instances where you would like to add an administrative application that is available only on a specified port. This makes it possible to run micro-services that can be managed at the node level.

For Servlet applications that do not have any web application files, the _server.config.json_ and _webapp.config.json_ files can be placed at the root of the classpath. In such a case they must be named _main-server.config.json_ and _main-webapp.config.json_. The _web.xml_ file, if any, can also be placed at the root of the classpath and renamed _main-web.xml_. All files are optional, and in the absence of any, the defaults will be used. However at least one of _main-server.config.json_ or _main-web.xml_ is required to signal the intent to launch a web application.

For applications that must be served from the root of the application context (example http://localhost/) the name of the web application must be _webapp/_. This does not apply if no web application files are required. in such instances the the root of the application context is default.

For more information on the server.config.json and webapp.config.json see the code and documentation for _com.quakearts.appbase.spi.impl.TomcatEmbeddedServerSpiImpl_.

##### Atomikos

The Atomikos JCA implmentation uses a custom JSON file to configure SQL Datasources. There are two ways of configuring datasources:

1. Drop a file with an extension _'ds.json'_ into the _atomikos/datasources_ folder at the root of the application
2. Create a file named _default.ds.json_ and place it at the root of the classpath

Examples of configuration files can be found in _atomikos/datasources_ folder of the project.

For more information on ds.json files see the code and documentation for _com.quakearts.appbase.spi.impl.AtomikosBeanDatasourceProviderSpiImpl_
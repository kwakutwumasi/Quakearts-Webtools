# qa-exception

An exception handling API inspired by the following [Dzone article](https://dzone.com/articles/exception-handling-in-real-life-applications).
<br /><br />
This API makes it possible to build libraries with plugable exception handling mechanisms. In the event that the library serves multiple frontends (a web front end, a RESTful service frontend, and a custom socket frontend, for example) a custom handler can be registered to handle each domain. This also makes it possible to update/improve exception handling without having to rewrite each try-catch block; applications are closer to the spirit of the SOLID design principles.
<br /><br />
The API makes it easy to handle exceptions thrown by a specific class. Convenience methods auto-detect the class where the exception occurred, and passes it on the the handler factory to find a handler registered to handle exceptions thrown by the specific class. The API also makes provisions for passing additional variables that may be necessary for proper exception handling, such as context objects and external resources.

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
	<artifactId>qa-exception</artifactId>
	<version>1.0.1</version>
</dependency>

```

The _com.quakearts.common.exceptionhandler.ExceptionHandlerUtil_ is the best entry point for using this library. It contains convenience methods that can be called within the catch block of a try-catch statement.

```java
	try {
		.....
	} catch (MyException e) {
		ExceptionHandlerUtil.handleExceptionForThisClass(e, "My custom paramter 1", myCustomParameter2);
	}
```

The _handleExceptionForThisClass()_ method handles exception using the calling class as the related class. This makes it possible to create handlers specifically for a class. The optional parameter array allows users to pass in resources necessary for proper exception handling.
<br />
The _handleExceptionWithRelatedClass()_ method can be used in instances where a proxy class is being used. The
_handleException()_ is a catch all method for usages that do not fit the other two scenarios. 
<br />
For all methods, if a handler for the specified class/exception is not found, an attempt will be made to find a handler whose registered exceptionClass and relatedClass are parents of the exception parameter and its related class, if any.

##### Creating handlers

All handlers must implement _com.quakearts.common.exceptionhandler.ExceptionHandler_. It is a functional interface, which makes it easy to use lambda expressions to create. In such instances the _registerExceptionHandler()_ method should be used to register the handler. Alternatively, if a concrete class is created, it can be annotated with _@com.quakearts.common.exceptionhandler.annotations.Handler_ for auto-discovery. Auto-discovery can be triggered during application bootup by calling the _getInstance()_ method of _com.quakearts.common.exceptionhandler.ExceptionHandlerFactory_.
<br /><br />
The API comes with a default implementation of _ExceptionHandlerFactory_. Custom factories can be provided by extending this class and calling _setInstance()_ before any call to _getInstance()_. If _setInstance()_ is called twice, or if an instance has already been registered, a configuration exception is thrown.
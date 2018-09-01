# qa-restsecurity

A CDI enabled library for adding security to applications. This library makes it possible to annotate methods as secured and provides attributes to list the roles allowed to access the method. It also provides a mechanism for caching credentials to speed up authentication. This is great for simple REST applications and microservices.

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
	<artifactId>qa-restsecurity</artifactId>
	<version>1.0.2</version>
</dependency>

```

JAAS is used for authentication. To speed implementation, a custom login context replaces the standard login context. This module has been tested to be 50% faster than the standard implementation. It achieves this by casting the context to _javax.security.auth.spi.LoginModule_ rather than using reflection. For modules that do not implement _LoginModule_, it switches to a wrapper that uses method call sites. This gives it a 10-20% boost. The authenticator looks for a file _login.config_, a JAAS configuration file, at the root of the classpath. See [JAAS Configuration documentation](https://docs.oracle.com/javase/7/docs/api/javax/security/auth/login/Configuration.html) for details on the configuration format.
<br /><br />
To create custom authentication, extend the _com.quakearts.webapp.security.rest.JAASAuthenticatorBase_. It provides five methods to perform authentication. Each method needs to be called in a specific order. The _init()_ methods are called first. Depending on which init method is called, one of the _authenticateVia*()_ methods should be called to complete authentication. The two init methods serve different purposes:

```java
	protected void init(String username, String password, String remoteHost, int remotePort,
			Map<String, String> requestHeaders, String host, int port, String application, String applicationContext) {...}
```

is used for authentication requiring an identity and credentials. When this call returns the class is ready for authentication. To complete authentication call _	authenticateViaUsernameAndPassword(String contextName)_, where _contextName_ is the JAAS context to use. 

```java
	protected void init(byte[] credentials, String remoteHost, int remotePort,
			Map<String, String> requestHeaders, String host, int port, String application, String applicationContext) {...}
```

is used for authentication requiring some kind of token. The token is sent as a byte array value to preserve formatting. When this call returns the class is ready for authentication. To complete authentication call _authenticateViaByteCredentials(String contextName)_, where _contextName_ is the JAAS context to use. 
<br />
A third authentication method _authenticate(CallbackHandler handler, String contextName)_ can be used to directly provide the callbacks required by the login modules.

The _authenticateViaUsernameAndPassword()_ uses the standard username/password callbacks 
_javax.security.auth.callback.NameCallback_/_javax.security.auth.callback.PasswordCallback_ for the login modules. The _authenticateViaByteCredentials()_ method uses _com.quakearts.webapp.security.auth.callback.TokenCallback_ from the [qa-auth](https://github.com/kwakutwumasi/Quakearts-Webtools/tree/master/qa-auth/) library. See the [JAAS Reference Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/security/jaas/JAASRefGuide.html) for more information on login modules.

###### com.quakearts.webapp.security.rest.filter.AuthenticationFilter

The _com.quakearts.webapp.security.rest.filter.AuthenticationFilter_ is a _javax.servlet.Filter_ that can be installed to provide security for web resources. It can be used for RESTful webservices and microservices. The filter has three initial parameters:

```
	requireAuthorization - a string that evaluates to the boolean value 'true' or 'false'. If true, authorization is required for every call. It is false by default
	
	contextName - the JAAS context to use in authenticating
	
	errorWriterClass - a class that implements the com.quakearts.webapp.security.rest.filter.AuthenticationErrorWriter. This is used when sending an authentication error
	
```

There are two ways of configuring the web filter for authentication.
1. **Using web.xml** <br /><br />
You can configure the filter using the web.xml. See [Servlet 3.0 documentation](https://javaee.github.io/tutorial/webapp005.html) for more information.

```
 <filter>
		<filter-name>AuthenticationFilter</filter-name>
		<filter-class>com.quakearts.webapp.security.rest.filter.AuthenticationFilter</filter-class>
		<init-param>
			<param-name>requireAuthorization</param-name>
			<param-value>true</param-value>
		</init-param>
		<init-param>
			<param-name>contextName</param-name>
			<param-value>JAASContext</param-value>
		</init-param>
		<init-param>
			<param-name>errorWriterClass</param-name>
			<param-value>com.my.ErrorWriterClass</param-value>
		</init-param>
	</filter>
```
2. **Using annotations** <br /><br />
You can configure the filter using _javax.servlet.annotation.WebFilter_ and _javax.servlet.annotation.WebInitParam_ by subclassing _com.quakearts.webapp.security.rest.filter.AuthenticationFilter_. See documentation of the annotations for more information.

```java
	import javax.servlet.annotation.WebFilter;
	import javax.servlet.annotation.WebInitParam;
	import com.quakearts.webapp.security.rest.filter.AuthenticationFilter;
	
	@WebFilter(urlPatterns="/*",
		initParams={@WebInitParam(name="contextName",value="JAASContext")})
	public class MyFilter extends AuthenticationFilter(){}
```

###### com.quakearts.webapp.security.rest.cache.AuthenticationCacheService

The library provides a service interface for plugging in an authentication cache. The cache allows temporary storage of an authenticated subject to match against the provided credentials. This makes it possible to improve authentication performance especially when an expensive external call is required (such as to a data store). To activate cache storage implement _com.quakearts.webapp.security.rest.cache.AuthenticationCacheService_, then create a file named _com.quakearts.webapp.security.rest.cache.AuthenticationCacheService_ in the _META-INF/services_ folder of your class folder or jar, with a single line entry of the name of your implementation. For more information on the [Java ServiceLoader](https://docs.oracle.com/javase/9/docs/api/java/util/ServiceLoader.html), see Java Online documentation.

###### com.quakearts.webapp.security.rest.SecurityContextStorageService

The library provides a service interface for changing the storage mechanism for _com.quakearts.webapp.security.rest.SecurityContext_. The default implementation uses a _java.util.concurrent.ThreadLocal_ variable to store the context. This can create problems in asynchronous environments. You may override this behavior by implementing _com.quakearts.webapp.security.rest.SecurityContextStorageService_, then create a file named _com.quakearts.webapp.security.rest.SecurityContextStorageService_ in the _META-INF/services_ folder of your class folder or jar, with a single line entry of the name of your implementation. For more information on the [Java ServiceLoader](https://docs.oracle.com/javase/9/docs/api/java/util/ServiceLoader.html), see Java Online documentation.

# qa-resttools

This library provides a base for building REST clients. The library is intended to provide basic HTTP request processing, freeing up the implementers to build fluid HTTP clients for specific endpoints. The HTTP request can be abstracted away for developers.

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
	<artifactId>qa-resttools</artifactId>
	<version>1.0.2</version>
</dependency>

```

Extend the _com.quakearts.rest.client.HttpClient_ to create an HTTP client. _HttpClient_ provides two methods to send the http request:

```
	protected void sendRequest(String, String, HttpVerb, String){}

	protected void sendRequest(String, String, HttpVerb, String, Map<String, List<String>>){}

```

The second is the same as the first, except an additional parameter for adding HTTP headers.
<br /><br />
The HTTP client has several properties:

```
	host - the HTTP host
	
	port - the HTTP port
	
	followRedirects - a string that evaluates to the boolean value 'true' or 'false'. Allow redirect calls to be automatically followed
	
	username - the BASIC authentication user name
	
	password - the BASIC authentication password
	
	userAgent - the User Agent string to add to the HTTP headers
	
	defaultCookie - the default cookie to send along with HTTP request
	
	secured - a string that evaluates to the boolean value 'true' or 'false'
	
	matchHostname - a string that evaluates to the boolean value 'true' or 'false'.
```

Configuration of the _HTTPClient_ is done by an implementation of _com.quakearts.rest.client.HttpClientBuilder_. This is to separate the creation of the client from it's operation.
<br /><br />
Implementers should abstract away the http calls by providing simple methods that handle the conversion of inputs into HTTP calls and converting the responses back to the required output.
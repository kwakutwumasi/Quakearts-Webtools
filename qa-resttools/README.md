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
	<version>1.0.4</version>
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
	
	connectTimeout - a decimal that evaluates to the number of seconds to wait before timing out an attempt to connect to a server
	
	readTimeout - a decimal that evaluates to the number of seconds to wait before timing out an attempt to read from a connection to a server
```

Configuration of the _HTTPClient_ is done by an implementation of _com.quakearts.rest.client.HttpClientBuilder_. This is to separate the creation of the client from it's operation.
<br /><br />
Implementers should abstract away the http calls by providing simple methods that handle the conversion of inputs into HTTP calls and converting the responses back to the required output.

##### _com.quakearts.rest.client.HttpClient#processStream()_

Implementers have the option of plugging in their own input stream handler. This it done by overriding the _com.quakearts.rest.client.HttpClient#processStream()_ method. The _java.io.InputStream_ instance is passed to the method untouched and can be read in a manner that is memory efficient.

##### _com.quakearts.rest.client.HttpObjectClient_

_com.quakearts.rest.client.HttpObjectClient_ provides methods for creating clients that can convert HTTP requests and responses from and to Objects instances. 

The abstract _writeValueAsString(Object object)_ method must be implemented to convert an object instance to a String. This String is passed as the request value of the HTTP request.

The abstract _createConverter(Class)_ method must be implemented to return a converter that can translate HTTP response body content to instances of the specified class. The _com.quakearts.rest.client.HttpObjectClient.Converter<R>_ interface is a functional interface, and can be implemented with a lambda function.

The abstract _nonSuccessResponseUsing(HttpResponse)_ method must be implemented to return _com.quakearts.rest.client.exception.HttpClientException_ or a subclass of it in the event of an HTTP response code greater than 299 or less then 200.

The _execute_ methods can be called by user defined methods to conveniently convert an object into an HTTP request and convert the response to an expected class. The object instance and the expected class is passed along with other parameters necessary for executing the HTTP Request and processing the response.
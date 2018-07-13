# qa-crypto

A library providing a configurable cryptographic resource. This library has support for CDI injection of the CryptoService resource factory.

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
	<artifactId>qa-crypto</artifactId>
	<version>3.0</version>
</dependency>

```

The _com.quakearts.security.cryptography.factory.CryptoService_ can be injected using CDI. It must be qualified by _@com.quakearts.security.cryptography.cdi.CryptoServiceHandle_ annotation.

```java
	@Inject @CryptoServiceHandle CryptoService service;
```

Alternatively in non CDI environments an instance of _CryptoService_ can be obtained using the _getInstance()_ method of _com.quakearts.security.cryptography.factory.CryptoServiceImpl_.

```java
	CryptoService service = CryptoServiceImpl.getInstance();
```

The _CryptoService_ can be used to create a _com.quakearts.security.cryptography.CryptoResource_, which in turn can be used for symmetric encryption operations. The resource is thread safe.

```java
	CryptoResource resource = service.getResource("myresource");
```

The name of the resource passed to the _getResource(String)_ is appended to the string '.crypto.properties', and used to find a properties file at the root of the classpath. The file must contain two properties:

```
crypto.instance - The cryptographic algorithm to use. It must be a valid algorithm supplied by a JCE registered cryptographic service provider.

crypto.key.provider.class - The name of a class implementing the com.quakearts.security.cryptography.provider.KeyProvider interface.

```

The properties file may contain other parameters used to configure the _KeyProvider_.

##### The com.quakearts.security.cryptography.provider.impl.KeystoreKeyProvider

An implementation of _KeyProvider_ is provided in the form of _com.quakearts.security.cryptography.provider.impl.KeystoreKeyProvider_. This provider uses the Java Keystore API to load keys. This provider should be sufficient for most use cases. However in high security environments, a custom _KeyProvider_ that loads the keys from a more secure location should be used. Simply implement the _getKey()_ method to return the key from secure storage.
<br /><br />
The _KeystoreKeyProvider_ is configured using the following properties:

```
store.file - The filename and location of the Java keystore file containing the keys

key.storeType - The Java Key Store type

key.alias - The alias of the signing keys

store.pass - The password for the keystore file

key.pass - The password for the key

```

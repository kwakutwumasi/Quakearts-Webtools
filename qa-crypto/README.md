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
	<version>3.1</version>
</dependency>

```

The _com.quakearts.security.cryptography.factory.CryptoService_ can be injected using CDI.

```java
	@Inject CryptoService service;
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

crypto.provider - The name of the cryptographic provider to use. It must be a valid registered cryptographic service provider.

crypto.key.provider.class - The name of a class implementing the com.quakearts.security.cryptography.provider.KeyProvider interface.

```

The properties file may contain other parameters used to configure the _KeyProvider_.

The _com.quakearts.security.cryptography.CryptoResource_ can also be injected using CDI. It must be qualified with _@com.quakearts.security.cryptography.cdi.CryptoResourceHandle_ and the instance indicated by setting the name as the value of the _@com.quakearts.security.cryptography.cdi.CryptoInstance_ annotation.

```java
	@Inject @CryptoResourceHandle @CryptoInstance("myinstance") CryptoResource resource;
```

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

##### The JPA Entity Attribute Converters

The project also includes entity attribute converters designed for JPA. They can also be used integrated with any data storage, with no dependency on JPA specific objects other then the interface that describes them. They depend on QA-ORM library for retrieving configuration information, and must be used with an implementation for the data storage library of choice.

_com.quakearts.security.cryptography.jpa.EncryptedBytesConverter_ and _com.quakearts.security.cryptography.jpa.EncryptedStringConverter_ can be used for the default data store. If there are multiple data stores in use, or if the default store is not used, _com.quakearts.security.cryptography.jpa.EncryptedValueConverter_ must be used for the entity attribute. _com.quakearts.security.cryptography.jpa.EncryptedValue_ should be the entity attribute class. The _setDataStoreName(String)_ method should be used to specify the data store holding the configuration information.

For more information on data store usage and configuration, see the QA-ORM library documentation.

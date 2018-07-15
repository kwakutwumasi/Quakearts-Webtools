# qa-auth
A collection of JAAS modules for authentication.

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
	<artifactId>qa-auth</artifactId>
	<version>2.0.2</version>
</dependency>

```

See JAAS documentation for more information on how to setup JAAS modules for authentication. The modules can be used in stand-alone mode as well, for users that may not want to use the older, less popular JAAS for authentication.

###### Using in stand-alone mode

The module can be started by creating a new instance: 

```java
	LoginModule module = new LoginModule();
```

The module can be instantiated by passing in a _javax.security.auth.Subject_, an implementation of _javax.security.auth.callback.CallbackHandler_, a _java.util.Map_ for holding shared state information (in the case where multiple modules will be called), and a _java.util.Map_ containing parameters for configuring the module:

```java
	Subject subject = new Subject();
	CallbackHandler callback handler = ...;
	Map sharedState = new HashMap();
	Map options = new HashMap();
	module.initialize(subject,handler,sharedState,options);
```

The callback handler will be used to interact with the client to provide necessary information for authentication. See documentation for further details.

Once the module has been successfully initialized, the authentication can begin by calling the login method:

```java
	module.login();
```

Login is successful iff the _login()_ method returns true;

The subject can be populated with roles and other necessary information by calling the _commit()_ method of the module:

```java
	module.commit();
```

To clear the Subject of all roles and authentication state, call the _logout()_ method:

```java
	module.logout();
```

The _abort()_ method removes authentication state that may have been placed on the subject in the event of an error. This ensures that the Subject does not possess state that allows them to bypass security controls when authentication has not been completed.

Note: the modules are not thread safe. Each module authenticates and holds data on an authenticated subject. It is not possible to share modules across threads. If you reuse an existing module security bugs may occur.

##### Modules

All modules can be found under the com.quakearts.webapp.security.auth package. All configuration options are java.lang.Strings.

###### DatabaseLoginModule

This login module connects to a database using JCA and executes a query to authenticate a subject. The query takes the form of a java.sql.PreparedStatement with two parameters: username as parameter 1 and password as parameter 2. If the query returns a result, the subject is verified. An optional role query is executed to load a result set of roles to be added to the subject.

This module uses two call backs : _javax.security.auth.callback.NameCallback_ and _javax.security.auth.callback.PasswordCallback_.

Configuration options:

```
database.authenticationquery - The query to execute. It must be a valid java.sql.PreparedStatement query that takes two parameters. The first is the username, the second is the password. Ex. SELECT * FROM user.table WHERE username = ? AND password= ?

database.jndiname - The JNDI name of the java.sql.DataSource to obtain connections from

database.rolename - This parameter is for use in JAAS modules that are integrated with Jboss/Wildfly's authentication system. All roles are grouped within a java.security.acl.Group with a specific name. This defaults to Roles

require_password_change - A string that evaluates to the Boolean value of true or false. If true the password will be compared with the value in the default_password option to 
determine if the user needs to change their password

default_password - The default password value. Note that this must be a valid hash value if password hashing is turned on. Also note that this will not work if username_as_salt is set to true since the hashed passwords would not be the same for different users. In such instances two DatabaseLoginModule's can be used, the first set to hash with the username, and the second that will not hash with the user name. Whichever one succeeds is used to authenticate the user

change_password_role - The role to assign a subject that requires a password change. All other roles will be ignored if a password change is required

database.defaultroles - A comma separated list of the default roles to assign to all authenticated subjects

database.rolesquery - The query for retrieving user roles. It must be a valid java.sql.PreparedStatement query that takes one parameter. Ex. SELECT * FROM user.roles.table WHERE username = ?

database.rolescolumns - The list of columns returned by the database.rolesquery, spearated by commas

result_orientation_potrait - A string that evaluates to the Boolean value of true or false. Determines the type of the result set returned by database.rolesquery. A value of true means multiple rows are returned. In such an instance the database.rolescolumns must return only one column. If false it is assumed a single row is returned. The database.rolescolumns is parsed and the row is iterated over using the columns

database.usehash - A string that evaluates to the Boolean value of true or false. A value of true sets the module to hash passwords before comparing them to the stored value

hash_algorithm - The JCE hash algorithm to use

hash_iterations - The number of hash iterations, to increase difficulty

salt_value - a value to append to the password. Salts make it harder to compare brute forced dictionary hashes to the stored value, in the event that the user table is compromised and password values are leaked

username_as_salt - Adds the username as a salt. Using the username as salt makes it difficult for passwords to be replaced in a database with a known hash

max_try_attempts - The maximum number of tries for wrong passwords

lockout_time - The time, in milliseconds, to lockout a subject after exceeding max_try_attempts

use_first_pass - A string that evaluates to the Boolean value of true or false. If true, and multiple modules are used, it will attempt to use the username and password from the previous module, if any, rather than perform a callback

load_profile_only - A string that evaluates to the Boolean value of true or false. If true, authentication will not be performed. The module will skip straight to loading roles
```

###### DatabaseServerLoginModule

This login module connects to a database using JCA with the supplied username and password. If a connection can be made, the user is viewed as authenticated. An optional role query is executed to load a result set of roles to be added to the subject.

This module uses two call backs : _javax.security.auth.callback.NameCallback_ and _javax.security.auth.callback.PasswordCallback_.

Configuration options:

```
database.rolename - This parameter is for use in JAAS modules that are integrated with Jboss/Wildfly's authentication system. All roles are grouped within a java.security.acl.Group with a specific name. This defaults to Roles

database.jndiname - The JNDI name of the java.sql.DataSource to obtain connections from

database.defaultroles - A comma separated list of the default roles to assign to all authenticated subjects

database.rolesquery - The query for retrieving user roles. It must be a valid java.sql.PreparedStatement query that takes one parameter. Ex. SELECT * FROM user.roles.table WHERE username = ?

database.rolescolumns - The list of columns returned by the database.rolesquery, spearated by commas

result_orientation_potrait - A string that evaluates to the Boolean value of true or false. Determines the type of the result set returned by database.rolesquery. A value of true means multiple rows are returned. In such an instance the database.rolescolumns must return only one column. If false it is assumed a single row is returned. The database.rolescolumns is parsed and the row is iterated over using the columns.

max_try_attempts - The maximum number of tries for wrong passwords

lockout_time - The time, in milliseconds, to lockout a subject after exceeding max_try_attempts

database.case_sensitivity - one of the values none,upper,lower. Some databases may require passwords in upper or lower case in order to authenticate. A value of upper will convert the password to uppercase before attempting to authentic. The same is true for lower. None leaves the password as is

authenticate_only - A string that evaluates to the Boolean value of true or false. 

use_first_pass - A string that evaluates to the Boolean value of true or false. If true, and multiple modules are used, it will attempt to use the username and password from the previous module, if any, rather than perform a callback.

```

###### DirectoryLoginModule

This login module connects to an LDAP server to authenticate the user. It is capable of loading the user's profile from the LDAP server using attributes bound to the user DN. The profile must consist of 9 attributes relating to the following (in this order): first name, last name, email address, unit, department, branch, position, salary grade/level/staff position, staff number. The module can use two methods to verify the user: for LDAP servers that store the passwords as part of the attributes, the module can compare the password to the stored password; otherwise an attempt to bind to the ldap server using the username and password is made.

This module uses two call backs : _javax.security.auth.callback.NameCallback_ and _javax.security.auth.callback.PasswordCallback_.

Configuration options:

```
directory.rolename - This parameter is for use in JAAS modules that are integrated with Jboss/Wildfly's authentication system. All roles are grouped within a java.security.acl.Group with a specific name. This defaults to Roles

ldap.server - the LDAP server URL or IP address

ldap.port - the port if the default ports are not used (389/636)

ldap.allow.anonymousbind - A string that evaluates to the Boolean value of true or false. If true, the module will attempt to bind anonymously to the server. If false, the module will expect ldap.search.dn and ldap.search.dn properties to be present, and will use them to bind to the server

ldap.ssl.use - A string that evaluates to the Boolean value of true or false. If true, the module will attempt to make a secure connection to the server. The default port for ssl connections is 636

ldap.keystore - The keystore location holding the list of certificates to trust. This setting only takes effect if a systemwide trust store has not been setup.

ldap.search.dn - The user name/dn for a user with read access to the directory tree holding user information

ldap.search.acc - the password for the user specified in ldap.search.dn

ldap.compare.use - A string that evaluates to the Boolean value of true or false. If true, the module will attempt to compare the password to an attribute in the users profile found in the ldap.password.param.

ldap.password.param - The name of the password attribute to compare to

directory.attributes - a list of nine attributes corresponding to the following: first name, last name, email address, unit, department, branch, position, salary grade/level/staff position, staff number. The attributes must be listed in this order and separated by ';'. If empty or less than 9, none of the attributes specified will be loaded.

directory.defaultroles - A comma separated list of the default roles to assign to all authenticated subjects

ldap.search.baseDN - the base of the directory tree to start the search from

ldap.filter - a filter for ignoring certain branches in the directory tree

use_first_pass - A string that evaluates to the Boolean value of true or false. If true, and multiple modules are used, it will attempt to use the username and password from the previous module, if any, rather than perform a callback.

max_try_attempts - The maximum number of tries for wrong passwords

lockout_time - The time, in milliseconds, to lockout a subject after exceeding max_try_attempts

```

###### IpAddressFilterLoginModule

This login module uses the subject's IP address to authenticate the subject.

This module does not use call backs. It expects that the servlet container would have populated the javax.security.jacc.PolicyContext object with a javax.servlet.http.HttpServletRequest object with which it will retrieve and verify the IP address.

Configuration options:

```
rolename - This parameter is for use in JAAS modules that are integrated with Jboss/Wildfly's authentication system. All roles are grouped within a java.security.acl.Group with a specific name. This defaults to Roles

defaultroles - A comma separated list of the default roles to assign to all authenticated subjects

use_first_pass - A string that evaluates to the Boolean value of true or false. If true, and multiple modules are used, it will attempt to use the username and password from the previous module, if any, rather than perform a callback.

max_try_attempts - The maximum number of tries for wrong passwords

lockout_time - The time, in milliseconds, to lockout a subject after exceeding max_try_attempts

properties.file - The filename and location of a properties file containing the list of IP addresses to allow. The value is a ';' separated list of roles to assign the authenticated subject

```

###### JWTLoginModule

This login module attempts to validate a JWT token using one of the validation algorithms. If used in conjunction with another login module, and the previous module successfully authenticated the user, it will generate a JWT token and return it as a special java.security.Principal of type com.quakearts.webapp.security.auth.JWTPrincipal. The module supports three algorithm validation/generation algorithms: HMAC, RSA and ESS.

This module uses two call backs : _com.quakearts.webapp.security.auth.callback.TokenCallback_ and _javax.security.auth.callback.PasswordCallback_. If the _TokenCallBack_ callback fails, it is expected that the JWT token will be found in the _PasswordCallback_.

Configuration options:

```
issuer - The issuer name for the JWT token

audience - The audience for the JWT token

algorithm - The signing algorithm to use in generation/verification. Must be one of HS256,HS384,HS512,RS256,RS384,RS512,ES256,ES384, or ES512

file - Required if the algorithm is prefixed with ES or RS. The filename and location of the Java keystore file containing the signing keys for the RSA or ESS signing algorithms.

storeType - Required if the algorithm is prefixed with ES or RS. The Java Key Store type

alias - Required if the algorithm is prefixed with ES or RS. The alias of the signing keys

password - Required if the algorithm is prefixed with ES or RS. The password for the keystore file

rolesgroupname - This parameter is for use in JAAS modules that are integrated with Jboss/Wildfly's authentication system. All roles are grouped within a java.security.acl.Group with a specific name. This defaults to Roles

secret - Required if the algorithm is prefixed with HS. The secret with which the JWT signature is hashed for verification

validity - The number of time periods the JWT token is valid for, from the time of issue

validity.period - The unit of time 'validity' is expressed in 

activate.after - The number of time periods that must precede JWT token activation, from the time of issue

activate.after.period - The unit of time the 'activate.after' is expressed in 

additional.claims - Key pair values of additional claims

grace.period - the time in seconds to add as a grace period to make up for possible time differences and network lag.

```

###### LoadProfileLoginModule

This login module connects to a database using JCA and executes a query to load the profile of a subject. The query takes the form of a java.sql.PreparedStatement with one parameters, the username. This login module is to be used in conjunction with other login modules. It exists as a way to load roles from a single datasource, in the event that authentication occurs on a different datasource, or as a way to share role storage across different authentication modules.

This module uses no call backs.

Configuration options:

```
database.jndiname - The JNDI name of the java.sql.DataSource to obtain connections from

database.rolename - This parameter is for use in JAAS modules that are integrated with Jboss/Wildfly's authentication system. All roles are grouped within a java.security.acl.Group with a specific name. This defaults to Roles

require_password_change - A string that evaluates to the Boolean value of true or false. If true the password will be compared with the value in the default_password option to 
determine if the user needs to change their password

change_password_role - The role to assign a subject that requires a password change. All other roles will be ignored if a password change is required

database.defaultroles - A comma separated list of the default roles to assign to all authenticated subjects

database.rolesquery - The query for retrieving user roles. It must be a valid java.sql.PreparedStatement query that takes one parameter. Ex. SELECT * FROM user.roles.table WHERE username = ?

database.rolescolumns - The list of columns returned by the database.rolesquery, spearated by commas

result_orientation_potrait - A string that evaluates to the Boolean value of true or false. Determines the type of the result set returned by database.rolesquery. A value of true means multiple rows are returned. In such an instance the database.rolescolumns must return only one column. If false it is assumed a single row is returned. The database.rolescolumns is parsed and the row is iterated over using the columns

```

###### WebServiceLoginModule

This login module is deprecated

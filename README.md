# Quakearts-Webtools V1.9 LTS Branch
 My Utility Servlet 3.0, JAX-RS, CDI, JTA, JSF Tag Libraries and JCA classes for web application development <br />
 <br />
 This branch is for long term support of deployed client applications
 <br />
qa-appbase: A custom integration of JTA, JCA, CDI, Servlet 3 Integration, and JNDI<br />
qa-appbase-archetype: A maven archetype for qa-appbase applications<br />
qa-appbase-jsf-orm-archetype: A maven archetype for qa-appbase applications using Hibernate and JSF Reference Implementation<br />
qa-appbase-reasteasy-orm-archetype: A maven archetype for qa-appbase applications using JBoss RestEasy and Hibernate<br />
qa-auth: JAAS LoginModules for qa-appbase Tomcat, Wildfly and any JEE Server that supports JAAS<br />
qa-beansupport: Tools for merging two beans, one being the update of the other<br />
qa-boot: Twitter Bootstrap JSF Tag Library implementation<br />
qa-classpathscanner: A tool for scanning class path for annotated classes<br />
qa-codegenerator-annotations: Annotations for CRUD Code Generation. Used by the QA Code Generators Eclipse Plugin<br />
qa-common: JSF Tag Library with common tools for ORM, Property File Handling, Security, etc<br />
qa-common-ext: Extensions to the qa-common library that are dependent on the outdated JBoss Gravel Tag Library<br />
qa-common-tools:Command line app building utility. Useful for Java based devops utilities<br />
qa-commonui: Common JSF UI tags for application development<br />
qa-crypto: Cryptographic Service for use with JPA<br />
qa-dbloader: Database Classloader for use with any RDBMS. Uses JPA and provides utility classes to quickly load and instantiate classes stored in a database. Useful for dynamic projects<br />
qa-exception: An Exception handling library, inspired by a Dzone article<br />
qa-logging: A logging servlet for Servers that use logging frameworks like JBoss Logging, SLF4J, Log4j etc. Reads and parses log files, pumps logs through websockets to a web interface<br />
qa-message-broker: An implementation of two way messaging Queue for inter-thread async communication<br />
qa-orm: An abstraction of data access. Abstracted to work with JPA as well as other Object-Relational Mapping, and Object-NoSql Mapping libraries.<br />
qa-orm-cdi: CDI producers for qa-orm interfaces<br/>
qa-orm-hibernate: An implementation of qa-orm for Hibernate JPA<br />
qa-orm-hibernate-web: Additional classes for Hibernate JPA for working with JSF<br />
qa-resteasy-cdi: Integration classes for CDI and Jboss RestEasy. Designed for qa-appbase<br />
qa-resteasy-validation-11:Integration classes for Bean Validation and Jboss RestEasy. Designed for qa-appbase<br />
qa-restsecurity: Common tools for providing Bearer and Basic authorization to REST Resources. Includes CDI Interceptors for decorating JAX-RS resources<br />
qa-resttools: Http REST client tools<br />
qa-syshub: An integration application, designed for processing, transforming and transmitting large data sets. Designed to work on a scheduler, or to be triggered by external elements<br />
qa-syshub-webapp: A web interface for managing Syshub<br />
qa-testtools: Tools for mocking data and for mocking web-services. Designed to make tests independent of live web-services. Also good for distributing mocked web-services for developers to code against<br />
qa-weldjsf-cdi: Integration classes for Weld CDI and JSF. Designed for qa-appbase<br />

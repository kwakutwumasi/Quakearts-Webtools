# Quakearts-JSF-Webtools
My JSF Tag Libraries and Utility classes for web application development.

qa-appbase: A custom integration of JTA, JCA, CDI, Servlet 3 Integration, and JNDI
qa-auth: JAAS LoginModules for Tomcat, Wildfly and any JEE Server that supports JAAS
qa-beansupport: JPA tools for concatenating strings
qa-boot: Twitter Bootstrap JSF Tag Library implementation
qa-classpathscanner: A tool for scanning class path for annotated classes
qa-common: JSF Tag Library with common tools for Hibernate, Property File Handling, Security, etc
qa-common-ext: Extensions to the qa-common library that are dependent on the outdated JBoss Gravel Tag Library
qa-common-tools:Command line app building utility. Useful for Java based devops utilities
qa-commonui: Common JSF UI tags for application development
qa-crypto: Cryptographic Service for use with JPA
qa-dbloader: Database Classloader for use with any RDBMS. Uses JPA and provides utility classes to quickly load and instantiate classes stored in a database. Useful for dynamic projects
qa-exception: An Exception handling library, inspired by a Dzone article
qa-logging: A looging servlet for Wildfly Servers
qa-orm: An abstraction of data access. Built to work with JPA as well as other Object Relational Mapping libraries, and NoSql databases.
qa-orm-hibernate: An implementation of qa-orm for JPA/Hibernate
qa-orm-hibernate-web: Additional classes for working with JSF
qa-resteasy-cdi: Integration classes for Java SE CDI and Jboss ReastEasy. Designed for qa-appbase
qa-restsecurity: Common tools for providing Bearer and Basic authorization to REST Resources. Includes CDI Interceptors for decorating JAX-RS resources
qa-resttools: Http REST client tools
qa-syshub: An integration application, designed for processing, transforming and transmitting large data sets. Designed to work on a scheduler, or to be triggered by external elements
qa-syshub-webapp: A web interface for managing Syshub
qa-testtools: Tools for mocking data and for mocking web-services. Designed to make tests independent of live web-services. Also good for distributing mocked web-services for developers to code against
qa-weldjsf-cdi: Integration classes for Java SE CDI and JSF. Designed for qa-appbase
qa-workflow: JBPM3 JSF Tag enhancements
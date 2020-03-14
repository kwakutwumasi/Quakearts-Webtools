# qa-orm-hibernate-web

This library provides Servlet/JSF integration for qa-orm-hibernate. The integration is in the form of 
* _com.quakearts.webapp.facelets.hibernate.phase.HibernatePhaseHelper_, an implementation of _javax.faces.event.PhaseListener_ that starts and stops transactions, making it possible to use the  _getCurrentSession()_ call of _org.hibernate.SessionFactory_. 
* an implementation of _org.hibernate.context.spi.CurrentSessionContext_ for JSF in the form of _com.quakearts.webapp.hibernate.facelets.FaceletsSessionHelper_. 
* an implementation of _javax.servlet.Filter_ in the form of _com.quakearts.webapp.hibernate.servlet.ServletSessionFilter_. This can be used in non-JTA environments to close all open _org.hibernate.context.spi.CurrentSessionContext_s.

##### Requirements
* Java 8+
* Maven
* A Servlet Container (Tomcat, Jetty, Wildfly, Undertow, etc)

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
	<artifactId>qa-orm-hibernate-web</artifactId>
	<version>1.1.0</version>
</dependency>

```
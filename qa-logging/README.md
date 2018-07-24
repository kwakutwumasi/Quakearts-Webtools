# qa-logging

This is a custom remote logging view web application. It is designed to plug into the logging subsystem and append logs to a web interface. It is designed to work with the old Jboss AS servers (now called Wildfly).

##### Requirements
* Java 8+
* Maven
* JBoss AS 4+

##### Quick Start

Download and build the project. Copy the war files to web application deployment folders, then modify the logging configuration settings:

###### AS 7+
Add the following to standalone.xml under profile > subsystem :

```
<handler class="com.quakearts.webapp.logging.javautillogger.WebLogHandler" name="WEB">
  <error-manager>
     <only-once/>
  </error-manager>
  <level name="FINEST"/>
</handler>
```

Then add the following to root-logger > handlers

```
<handler-ref name="WEB"/> 
```

###### AS 6
Add the following to deploy/jboss-logging.xml :

```
<handler class="com.quakearts.webapp.logging.javautillogger.WebLogHandler" name="WEB">
  <error-manager>
     <only-once/>
  </error-manager>
  <level name="FINEST"/>
</handler>
```

Then add the following to root-logger > handlers

```
<handler-ref name="WEB"/>  
```

###### AS 4-5
Add the following to conf/jboss-log4j.xml :

```
<appender name="WEB" class="com.quakearts.webapp.logging.log4j.WebLogAppender">
</appender>
```

Then add the following to root > appenders

```
 <appender-ref ref="WEB"/>
```
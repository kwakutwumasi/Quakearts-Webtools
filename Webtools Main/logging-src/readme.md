AS 7+
Add the following to standalone.xml under profile > subsystem :

<handler class="com.quakearts.webapp.logging.javautillogger.WebLogHandler" name="WEB">
  <error-manager>
     <only-once/>
  </error-manager>
  <level name="FINEST"/>
</handler>

Then add the following to root-logger > handlers
<handler-ref name="WEB"/> 

AS 6
Add the following to deploy/jboss-logging.xml :

<handler class="com.quakearts.webapp.logging.javautillogger.WebLogHandler" name="WEB">
  <error-manager>
     <only-once/>
  </error-manager>
  <level name="FINEST"/>
</handler>

Then add the following to root-logger > handlers
<handler-ref name="WEB"/>  

AS 4-5
Add the following to conf/jboss-log4j.xml :

<appender name="WEB" class="com.quakearts.webapp.logging.log4j.WebLogAppender">
</appender>

Then add the following to root > appenders
 <appender-ref ref="WEB"/>
# qa-syshub

The SysHub Message Processing Interface is a multipurpose message processing engine. It can be customized to a variety of message processing scenarios. It provides advanced features such as asynchronous calls, multithreaded processing and nonlinear pipelines. It is designed to reduce message processing delays significantly. It can be used to provide services such as message translation, integration between systems using different data formats, transaction notification, etc. It is trivial to scale message processing. A simple round robin load balancer can coordinate the processing on multiple nodes.

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
	<groupId>com.quakearts.syshub</groupId>
	<artifactId>qa-syshub</artifactId>
	<version>1.1.0</version>
</dependency>

```

Implement the _com.quakearts.syshub.core.DataSpooler_, _com.quakearts.syshub.core.MessageFormatter_ and _com.quakearts.syshub.core.Messenger_ interfaces to create a processing agent. 
<br />
The _DataSpooler_ has two methods: _prepare()_ and _update()_. The _prepare()_ method is the first to be called in the processing chain. It returns a _com.quakearts.syshub.core.CloseableIterator_ that is used to loop over data. _CloseableIterator_ has two methods: _hasNext()_ and _next()_. _hasNext()_ returns true if the call to _next()_ will return data. The _next()_ method does the data retrieval. If an unrecoverable error occurs during this operation, an exception is thrown and processing ends.
<br />
The _com.quakearts.syshub.core.Result_ object returned from the _CloseableIterator_ is passed to the _formatData()_ method of the _com.quakearts.syshub.core.MessageFormatter_. It returns a _com.quakearts.syshub.core.Message_ object.
<br />
The _Message_ object is given to the _sendMessage()_ of _com.quakearts.syshub.core.Messenger_.
<br />
The _update()_ method of _DataSpooler_ is called with the _Message_ to end the processing cycle. 
<br />
<br />
_Messenger_ provides two methods for maintenance operations: _isCompatibleWith(MessageFormatter)_ is called during _ProcessingAgent_ creation to determine if the _MessageFormatter_ can format messages for this messenger. The _confirmDelivery(ProcessingLog)_ method is called to check if a logged message has been successfully delivered.
<br />

The sequence diagram below shows the sequence of calls for a typical execution cycle:

![sequence_diagram.png](https://raw.github.com/kwakutwumasi/Quakearts-Webtools/master/qa-syshub/images/sequence_diagram.png)

<br />

_ProcessingAgent_s can be created using _com.quakearts.syshub.agent.builder.ProcessingAgentBuilder_. The _ProcessingAgentBuilder_ is a fluid API. All the parameters necessary for assembling and deploying the _com.quakearts.syshub.agent.ProcessingAgent_ can be set using the API:

```java
	ProcessingAgent processingAgent = new ProcessingAgentBuilder().name("Test ProcessingAgentBuilder")
		.maxDataSpoolerWorkers(10)
		.maxFormatterWorkers(15)
		.queueSize(30)
		.keepAliveTime(120)
		.corePoolSize( 5)
		.maximumPoolSize(45)
		.resendCapable(true)
		.dataSpooler(TestDataSpooler.class, "Test ProcessingAgentBuilder Module 1")
		.addBinaryParameter("test1", "value".getBytes())
		.messageFormatter(TestFormatter1.class,  "Test ProcessingAgentBuilder Module 2")
		.addBooleanParameter("test2", true)
		.messenger(TestMessenger1.class, "Test ProcessingAgentBuilder Module 2")
		.addStringParameter("test3", "value", ParameterType.PASSWORD)
		.map(agentModule1).map(agentModule2)
		.build()
```

The built agent can process data by calling the _processData()_ method:

```java

	try {
		processingAgent.processData();
	} catch (ProcessingException e) {
		....
	}
```

There are several _com.quakearts.syshub.core.runner.AgentRunner_s that can be configured to initiate processing:

* _com.quakearts.syshub.core.runner.impl.LoopedAgentRunner_ will run the _ProcessingAgent_ in an infinite loop
* _com.quakearts.syshub.core.runner.impl.ScheduledAgentRunner_ will run the _ProcessingAgent_ on a schedule
* _com.quakearts.syshub.core.runner.impl.TriggeredAgentRunner_ will run the _ProcessingAgent_ when the configured _com.quakearts.syshub.core.runner.AgentTrigger_ is triggered.
<br />

A maven archetype _com.quakearts.syshub:qa-syshub-archetype:1.0.0_ is available to create the maven project skeleton. It includes _qa-syshub-webapp_; this web application can be used to configure the _ProcessingAgent_. The configured agent will be stored in the configured database, and subsequently re-deployed when the system is restarted. See its documentation for more details.

##### Configuring storage in the archetype

The archetype sets up an Apache Derby internal database to hold the configuration properties of _ProcessingAgent_s, as well as the process logs and other convenience data. This can be changed to any relational data store. All that is needed are the appropriate drivers and the configuration of the data source. See _qa-appbase_ documentation for more information on setting data sources. The datasource configuration file can be found in the _src/main/resources/_ folder of the project under the name _default.ds.json_.
<br />
NoSQL data stores can also be used. Support for this is however limited, and requires creating an implementation of _qa-orm_ for the NoSQL data store. The dependency on _qa-orm-hibernate_ and _qa-orm-hibernate-web_ need to be removed. The library uses a special _DataStore_ under the domain name _system_. See _qa-orm_ documentation for more information on multiple _DataStore_ domains.

##### Additional services in the archetype application

There are additional services provided for use in data processing. These services can be injected via CDI injection annotations.

* _com.quakearts.syshub.core.utils.CacheManager_ provides a cache for temporary storage of operational data
* _com.quakearts.syshub.core.utils.SystemDataStoreManager_ provides access to the data store.
* _com.quakearts.syshub.core.utils.VariableCacheManager_ is a persistent cache for storing operational data. The data is guaranteed to survive reboots of the system.
* _com.quakearts.syshub.core.utils.MessageLogger_ provides access to the log system to record transmitted messages and their status. These logs can be viewed in _qa-syshub-webapp_.

##### ProcessingAgent Properties

The _ProcessingAgent_ has properties that can be used to fine tune its execution.

```
	maxDataSpoolerWorkers - the maximum number of DataSpoolerWorkers to create. These workers handle the retrieval of data for processing. Limiting their size can save some memory in memory constrained environments.
	
	maxFormatterWorkers - the maximum number of FormatterWorkers to create. These workers handle the transformation of data, and its onward transmission. Limiting their size can save some memory in memory constrained environments.
	
	queueSize - the maximum number of queued processes in the ExecutorService used to run the processing threads
	
	keepAliveTime - the maximum amount of time in seconds to keep idle processing threads alive in the ExecutorService used to run the processing threads
	
	corePoolSize - the fixed number of processing threads in the ExecutorService used to run the processing threads
	
	maximumPoolSize - the maximum number of processing threads in the ExecutorService used to run the processing threads
	
	resendCapable - indicates that the processing agent supports retransmission of messages. When true, logged messages can be resent. This is useful in situations where message delivery is unreliable (like email or SMS) and there may be a need to retransmit messages.
	
```

##### Configuration Metadata

The library provides annotations that can be used by _qa-syshub-webapp_ to build web forms for configuring _ProcessingAgent_: _@com.quakearts.syshub.core.metadata.annotations.ConfigurationProperty_, and  _@com.quakearts.syshub.core.metadata.annotations.ConfigurationProperties_. _ConfigurationProperties_ is used to hold more than one _ConfigurationProperty_. The annotations support defining numeric, boolean, string and binary properties. It also provides types for class, file, JNDI, email, endpoint addresses, CRON strings, password, and lists of strings. 

```java
	@ConfigurationProperties({
		@ConfigurationProperty(type=ParameterType.FILE, value="test.file"),
		@ConfigurationProperty(type=ParameterType.JNDINAME, value="test.jndiname")})
	public class TestFormatter1 extends RandomErrorThrower implements MessageFormatter {
		...
	}
```

Some implementations may not need to provide configuration metadata, but still need to be discovered by the scanners used to populate the module drop-downs in _qa-syshub-webapp_. In such instances, annotate the module with _@com.quakearts.syshub.core.metadata.annotations.Autoconfigured_.

##### Custom startup actions

For users that wish to provide custom startup logic during, inheriting _com.quakearts.syshub.SysHubMain_ is the starting point. The main method can then be replaced in the pom.xml settings for _appassembler-maven-plugin_. Replace _com.quakearts.syshub.SysHubMain_ in the '&lt;commandLineArgument&gt;' elements with your inherited class.

##### Running your application

You can test your applications by creating a _ProcessingAgent_ using _ProcessingAgentBuilder_. 
<br /><br />
To run the full application, package the application using the 'mvn package' command, then run the appropriate batch file for your OS from the _/target/_ folder of the project. A test batch file named _webapptest_ is provided for debugging the full application. It opens a JDWP socket on port 4000 for remote debuggers.

### Appendix

##### Architecture

The diagram below shows the structure of modules/classes within the application. Adjacent modules have a dependency on one another.

![syshub_diagram.png](https://raw.github.com/kwakutwumasi/Quakearts-Webtools/master/qa-syshub/images/syshub_diagram.png)

The system is comprised of:
<br />

**DataSpooler**: Responsible for pulling data and creating a universally understandable format for onward processing by MessageFormatters
<br />
**MessageFormatter**: Responsible for formatting data into a format that can be transmitted to message endpoints. Each message endpoint may have different formats. As such each MessageFormatter is mapped to a Messenger
<br />
**Messenger**: Responsible for transmitting formatted messages to endpoints. Optionally endpoint processing results can be received and stored along with message logs. It can also send the response to the DataSpooler in the event that message processing results need to be reported. The messenger supports asynchronous reporting of message processing results.
<br />
**ProcessingAgent**: Responsible for co-ordinating the activities of the DataSpooler, MessageFormatter and Messenger. It provides multi-threaded processing and process pipelining for efficient processing of data. Each data processing stage that can be processed asynchronously is handed to a configurable number of execution threads to ensure that independent processes do not wait for each other to complete.
<br />
**AgentRunner**: Responsible for starting the ProcessingAgent’s processing thread. This layer is invoked by the AgentTrigger
<br />
**AgentTrigger**: the AgentTrigger is responsible for signaling when processing must begin. There are three supported trigger types:

* Externally Triggered – Meant for processes that receive data for processing from external systems over a network connection
* Schedule Triggered – Meant for processes that are executed on a one-time or repeatable schedule
* Loop Triggered – Meant for processes that are processed over a loop
<br />

**ProcessingAgentBuilder**: Responsible for assembling a ProcessingAgent from configuration information. Configurations can be stored on the database and uploaded as a file.
<br />
**AgentDeployer**: Responsible for reading configured ProcessingAgent’s and deploying. It can also deploy new ProcessingAgent’s at runtime. The lifecycle of processing Agents are controlled by the AgentDeployer
<br />
**ModuleFactories**: Responsible for the instantiation of AgentTrigger, DataSpooler, MessageFormatter and Messengers
<br />
**MessageLogger**: Responsible for logging all messages that have been processed, including messages that were not processed due to errors.
<br />
**Management Interface**: Provides a web based interface for managing the ProcessingAgents

<br />
The thread sequence diagram shows how multiple threads handle the cycle of data retrieval, formatting and transmission:

![thread_sequence_diagram.png](https://raw.github.com/kwakutwumasi/Quakearts-Webtools/master/qa-syshub/images/thread_sequence_diagram.png)

<br />

Explore the code and in line documentation for more information on how it works. The main class passed as the _qa-appbase_ startup class is _com.quakearts.syshub.SysHubMain_. Its a good place to start to understand the application.
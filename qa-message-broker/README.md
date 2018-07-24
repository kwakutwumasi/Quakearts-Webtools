# qa-message-broker

This is a two way message passing library for multi-threaded applications. It is designed to support asynchronous processing. It is able to handle processing failures for individual messages. It features a timeout mechanism to ensure processing does not wait indefinitely. The library is CDI enabled.

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
	<artifactId>qa-message-broker</artifactId>
	<version>1.0.1</version>
</dependency>

```

_com.quakearts.utils.messagebroker.MessageBroker_ handles the communication between threads. The two communicating threads need to get a message broker from the _com.quakearts.utils.messagebroker.MessageBrokerRegistry_ implementation, using a shared name, or by a parent thread passing it on to the child thread. The two threads may then communicate using the four message exchange methods:

```java

	public class MessageSender implements Runnable {
		MessageBrokerRegistry registry = ...;
		
		public void run(){
			MessageBroker<Message> broker = registry.getMessageBroker("SHARED_BROKER");
			Message message = ...;
			broker.sendForProcessing(message);
			Message response = broker.retrieveResponse();
		}
	}
	
	public class MessageReciever implements Runnable {
		MessageBrokerRegistry registry = ...;
		
		public void run(){
			MessageBroker<Message> broker = registry.getMessageBroker("SHARED_BROKER");
			Message message = broker.retrieveForProcessing();
			Message response = ... ;
			broker.sendResponse(response);
		}
	}

	public class MultiThreadedApp {
		public static void main(String[] args){
			new Thread(new MessageSender()).start();
			new Thread(new MessageReciever()).start();
		}
	}

```

_MessageBrokerRegistry_ has five methods for creating a message broker. A call to _getMessageBroker()_ retrieves a message broker with the ID if it has already been created. If no message broker with the ID exists, it will create a message broker using the default parameters.
<br />
The other four _createMessageBroker()_ methods allow a user to configure broker capacity, blocking timeout, clean up daemon interval, and maximum age for stale messages. The create methods must be called by only one thread, preferably at boot time to ensure there are no conflicts.

###### CDI Environments

In CDI environments, simply inject an instance of the _MessageBrokerRegistry_ to obtain broker instances: 

```java
	@Inject MessageBrokerRegistry registry;
```

###### Non-CDI Environments

In non-CDI environments, simply create an new instance of the _com.quakearts.utils.messagebroker.MessageBrokerRegistryImpl_, and store it in a central location. You can retrieve it using any preferred mechanism to obtain broker instances: 

```java
	private static MessageBrokerRegistry registry;
	
	public static MessageBrokerRegistry getRegistry(){
		if(registry == null)
			registry = new MessageBrokerRegistryImpl();
		
		return registry;
	}
```

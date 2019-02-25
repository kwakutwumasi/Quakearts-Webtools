package com.quakearts.utilities.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNot.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.quakearts.utils.messagebroker.MessageBroker;
import com.quakearts.utils.messagebroker.MessageBrokerRegistry;
import com.quakearts.utils.messagebroker.MessageBrokerRegistryImpl;
import com.quakearts.utils.messagebroker.exception.MessageBrokerException;

public class TestMessageBroker {
	
	static MessageBrokerRegistry brokerRegistry;
	static String REGISTRYID = "TEST";
	static ExecutorService service;
	
	@BeforeClass
	public static void init() {
		LoggerFactory.getLogger(TestMessageBroker.class).info("Starting");
		brokerRegistry = new MessageBrokerRegistryImpl();
		brokerRegistry.createMessageBroker(REGISTRYID, 2, 1, TimeUnit.SECONDS,
						10, TimeUnit.SECONDS, 5, TimeUnit.SECONDS);
		service = Executors.newFixedThreadPool(4);
	}
	
	@AfterClass
	public static void shutdown() {
		service.shutdown();
	}

	@Test
	public void testSendAndRetrieve() throws Exception {
		MessageBroker<String> broker = brokerRegistry.getMessageBroker(REGISTRYID);
		String testMessage = UUID.randomUUID().toString();		
		Future<String> receiveFuture = service.submit(()->{
			return broker.retrieveForProcessing();
		});
				
		broker.sendForProcessing(testMessage);
		assertThat(receiveFuture.get(), is(testMessage));
		
		receiveFuture = service.submit(()->{
			return broker.retrieveResponse();
		});
		
		broker.sendResponse(testMessage);
		assertThat(receiveFuture.get(), is(testMessage));
		
		String testMessage2 = UUID.randomUUID().toString();		
		
		receiveFuture = service.submit(()->{
			return broker.retrieveForProcessing();
		});
		
		broker.sendForProcessing(testMessage2);
		assertThat(receiveFuture.get(), is(testMessage2));
		
		receiveFuture = service.submit(()->{
			return broker.retrieveResponse();
		});
		
		broker.sendResponse(testMessage2);
		assertThat(receiveFuture.get(), is(testMessage2));
	}
	
	@Test
	public void testSendForProcessingAndRetrieveForProcessingTimeout() throws Exception {
		String testMessage = UUID.randomUUID().toString();
		MessageBroker<String> broker = brokerRegistry.getMessageBroker(REGISTRYID);
		//Test timeout works
		try {
			broker.sendForProcessing(testMessage);
			fail("Did not throw exception after timeout");
		} catch (MessageBrokerException e) {}
		
		try {
			broker.retrieveForProcessing();
			fail("Did not throw exception after timeout");
		} catch (MessageBrokerException e) {}
		
		String testMessage2 = UUID.randomUUID().toString();
		//Test if retrieve after timeout works, and that next message doesn't pick previous message
		Future<Boolean> sendFuture = service.submit(()->{
			broker.sendForProcessing(testMessage2);
			return true;
		});
		
		Future<String> receiveFuture = service.submit(()->{
			return broker.retrieveForProcessing();
		});
		
		if(sendFuture.get()) {
			assertThat(receiveFuture.get(), is(testMessage2));
			assertThat(testMessage, is(not(testMessage2)));
		}
	}
	
	@Test
	public void testSendResponseAndRetrieveResponseTimeout() throws Exception {
		String testMessage = UUID.randomUUID().toString();
		MessageBroker<String> broker = brokerRegistry.getMessageBroker(REGISTRYID);
		//Test timeout works
		try {
			broker.sendResponse(testMessage);
			fail("Did not throw exception after timeout");
		} catch (MessageBrokerException e) {}
		
		try {
			broker.retrieveResponse();
			fail("Did not throw exception after timeout");
		} catch (MessageBrokerException e) {}
		String testMessage2 = UUID.randomUUID().toString();
		//Test if retrieve after timeout works, and that next message doesn't pick previous message
		Future<Boolean> sendFuture = service.submit(()->{
			broker.sendResponse(testMessage2);
			return true;
		});
		
		Future<String> receiveFuture = service.submit(()->{
			return broker.retrieveResponse();
		});
		
		if(sendFuture.get()) {
			assertThat(receiveFuture.get(), is(testMessage2));
			assertThat(testMessage, is(not(testMessage2)));
		}
	}

	@Test
	public void testCallOrder() throws Exception {
		String testMessage = UUID.randomUUID().toString();
		MessageBroker<String> broker = brokerRegistry.getMessageBroker(REGISTRYID);
		
		//Test timeout works
		try {
			broker.sendForProcessing(testMessage);
			fail("Did not throw exception after timeout");
		} catch (MessageBrokerException e) {}
		
		//Test if retrieve after timeout works, and that next message doesn't pick previous message
		Future<Boolean> sendFuture = service.submit(()->{
			broker.sendForProcessing(testMessage);
			return true;
		});
		
		Future<String> receiveFuture = service.submit(()->{
			return broker.retrieveForProcessing();
		});
		
		try {
			sendFuture.get();
			fail("Send did not timeout");
		} catch (ExecutionException e) {}

		try {
			receiveFuture.get();
			fail("Retrieve did not timeout");
		} catch (ExecutionException e) {}
		
		try {
			broker.retrieveForProcessing();
			fail("Did not throw exception after timeout");
		} catch (MessageBrokerException e) {}
		
		//Test timeout works
		try {
			broker.sendResponse(testMessage);
			fail("Did not throw exception after timeout");
		} catch (MessageBrokerException e) {}
		//Test if retrieve after timeout works, and that next message doesn't pick previous message
		sendFuture = service.submit(()->{
			broker.sendResponse(testMessage);
			return true;
		});
		
		receiveFuture = service.submit(()->{
			return broker.retrieveResponse();
		});
		
		try {
			sendFuture.get();
			fail("Retrieve did not timeout");
		} catch (ExecutionException e) {}

		try {
			receiveFuture.get();
			fail("Retrieve did not timeout");
		} catch (ExecutionException e) {}
		
		try {
			broker.retrieveResponse();
			fail("Did not throw exception after timeout");
		} catch (MessageBrokerException e) {}

	}
}

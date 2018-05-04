package com.quakearts.utilities.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.quakearts.utils.messagebroker.MessageBroker;
import com.quakearts.utils.messagebroker.MessageBrokerRegistry;
import com.quakearts.utils.messagebroker.MessageBrokerRegistryImpl;

public class TestMessageBroker {
	
	static MessageBrokerRegistry brokerRegistry;
	static String REGISTRYID = "TEST";
	static ExecutorService service;
	
	@BeforeClass
	public static void init() {
		brokerRegistry = new MessageBrokerRegistryImpl();
		brokerRegistry.createMessageBroker(REGISTRYID, 2, 1, TimeUnit.SECONDS,
						1, TimeUnit.SECONDS, 5, TimeUnit.SECONDS);
		service = Executors.newFixedThreadPool(4);
	}
	
	@AfterClass
	public static void shutdown() {
		service.shutdown();
	}

	@Test
	public void testSendAndRetrieve() throws Exception {
		String message1 = UUID.randomUUID().toString(),
				message2 = UUID.randomUUID().toString();
		
		Future<?> future1 = service.submit(()->{
			try {
				Thread.sleep(500);
				MessageBroker<String> broker = brokerRegistry
						.getMessageBroker(REGISTRYID);
				broker.sendForProcessing(message1);
				String retrievedMessage = broker.retrieveResponse();
				assertThat(retrievedMessage, is(message2));
			} catch (Exception e) {
				fail(e.getMessage());
			}
		});
		
		Future<?> future2 = service.submit(()->{
			try {
				Thread.sleep(500);
				MessageBroker<String> broker = brokerRegistry
						.getMessageBroker(REGISTRYID);
				String retrievedMessage = broker.retrieveForProcessing();
				assertThat(retrievedMessage, is(message1));
				broker.sendResponse(message2);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		});
		
		future1.get();
		future2.get();
	}
	
	@Test
	public void testSendForProcessingTimeout() throws Exception {
		String message1 = UUID.randomUUID().toString(),
				message2 = UUID.randomUUID().toString(),
				message3 = UUID.randomUUID().toString();
		
		Future<?> future1 = service.submit(()->{
			try {
				MessageBroker<String> broker = brokerRegistry
						.getMessageBroker(REGISTRYID);
				broker.sendForProcessing(message1);
				broker.sendForProcessing(message2);
				try {
					broker.sendForProcessing(message3);
					fail("Did not timeout");
				} catch (Exception e) {
				}
			} catch (Exception e) {
				fail(e.getClass()+" "+e.getMessage());
			}
		});
		
		Future<?> future2 = service.submit(()->{
			try {
				MessageBroker<String> broker = brokerRegistry
						.getMessageBroker(REGISTRYID);
				Thread.sleep(1100);
				String retrievedMessage = broker.retrieveForProcessing();
				assertThat(retrievedMessage, is(message1));
				retrievedMessage = broker.retrieveForProcessing();
				assertThat(retrievedMessage, is(message2));
				retrievedMessage = broker.retrieveForProcessing();
				assertThat(retrievedMessage, is(nullValue()));
			} catch (Exception e) {
				fail(e.getClass()+" "+e.getMessage());
			}
		});
		
		future1.get();
		future2.get();
	}
	
	@Test
	public void testRetrieveForProcessingTimeout() throws Exception {
		String message1 = UUID.randomUUID().toString(),
				message2 = UUID.randomUUID().toString(),
				message3 = UUID.randomUUID().toString();
		
		Future<?> future1 = service.submit(()->{
			try {
				MessageBroker<String> broker = brokerRegistry
						.getMessageBroker(REGISTRYID);
				broker.sendForProcessing(message1);
				Thread.sleep(1100);
				broker.sendForProcessing(message2);
				broker.sendForProcessing(message3);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		});
		
		Future<?> future2 = service.submit(()->{
			try {
				MessageBroker<String> broker = brokerRegistry
						.getMessageBroker(REGISTRYID);
				String retrievedMessage = broker.retrieveForProcessing();
				assertThat(retrievedMessage, is(message1));
				retrievedMessage = broker.retrieveForProcessing();
				assertThat(retrievedMessage, is(nullValue()));
				retrievedMessage = broker.retrieveForProcessing();
				assertThat(retrievedMessage, is(message3));
			} catch (Exception e) {
				fail(e.getMessage());
			}
		});

		future1.get();
		future2.get();
	}
	
	@Test
	public void testSendResponseTimeout() throws Exception {
		String message1 = UUID.randomUUID().toString(),
				message2 = UUID.randomUUID().toString(),
				message3 = UUID.randomUUID().toString();
		
		Future<?> future1 = service.submit(()->{
			try {
				MessageBroker<String> broker = brokerRegistry
						.getMessageBroker(REGISTRYID);
				broker.sendResponse(message1);
				broker.sendResponse(message2);
				try {
					broker.sendResponse(message3);
					fail("Did not timeout");
				} catch (Exception e) {
				}
			} catch (Exception e) {
				fail(e.getClass()+" "+e.getMessage());
			}
		});
		
		Future<?> future2 = service.submit(()->{
			try {
				MessageBroker<String> broker = brokerRegistry
						.getMessageBroker(REGISTRYID);
				Thread.sleep(1100);
				String retrievedMessage = broker.retrieveResponse();
				assertThat(retrievedMessage, is(message1));
				retrievedMessage = broker.retrieveResponse();
				assertThat(retrievedMessage, is(message2));
				retrievedMessage = broker.retrieveResponse();
				assertThat(retrievedMessage, is(nullValue()));
			} catch (Exception e) {
				fail(e.getClass()+" "+e.getMessage());
			}
		});
		
		future1.get();
		future2.get();

	}
	
	@Test
	public void testRetrieveResponseTimeout() throws Exception {
		String message1 = UUID.randomUUID().toString(),
				message2 = UUID.randomUUID().toString(),
				message3 = UUID.randomUUID().toString();
		
		Future<?> future1 = service.submit(()->{
			try {
				MessageBroker<String> broker = brokerRegistry
						.getMessageBroker(REGISTRYID);
				broker.sendResponse(message1);
				Thread.sleep(1100);
				broker.sendResponse(message2);
				broker.sendResponse(message3);
			} catch (Exception e) {
				fail(e.getClass()+" "+e.getMessage());
			}
		});
		
		Future<?> future2 = service.submit(()->{
			try {
				MessageBroker<String> broker = brokerRegistry
						.getMessageBroker(REGISTRYID);
				String retrievedMessage = broker.retrieveResponse();
				assertThat(retrievedMessage, is(message1));
				retrievedMessage = broker.retrieveResponse();
				assertThat(retrievedMessage, is(nullValue()));
				retrievedMessage = broker.retrieveResponse();
				assertThat(retrievedMessage, is(message3));
			} catch (Exception e) {
				fail(e.getClass()+" "+e.getMessage());
			}
		});
		
		future1.get();
		future2.get();

	}
	
	/*
	 * test send and retrieve for processing after timeout
	 * test send and retrieve response after timeout
	 */
	

}

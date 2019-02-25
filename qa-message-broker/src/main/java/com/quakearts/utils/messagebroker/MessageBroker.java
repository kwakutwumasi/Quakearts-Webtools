/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.utils.messagebroker;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.utils.messagebroker.exception.MessageBrokerException;

/**A class for exchanging messages between threads.
 * @author kwakutwumasi-afriyie
 *
 * @param <M>
 */
public class MessageBroker<M> {

	private static final String TIMED_OUT_RESPONSE = "Timed out waiting for response";
	private static final String RETRIEVING_MESSAGE = "Retrieving message '{}'";
	private static final String TIMED_OUT_MESSAGE = "Timed out waiting for message";
	private static final String RETRIEVAL_TIMEOUT = "Retrieval timeout";
	private static final Logger log = LoggerFactory.getLogger(MessageBroker.class);
	private Map<String, BrokerObject<M>> incomingTickets = new ConcurrentHashMap<>();
	private Map<String, BrokerObject<M>> outgoingTickets = new ConcurrentHashMap<>();
	private Deque<String> incomingTicketQueueIn;
	private Deque<String> incomingTicketQueueOut; 
	private Deque<String> outgoingTicketQueueIn;
	private Deque<String> outgoingTicketQueueOut;
	private long timeout;
	private long timeoutInMillis;
	private TimeUnit timeoutTimeUnit;
	private Duration maxAge;
	private ExecutorService retrievalExecutor;
	
	private TimerTask cleanUpTask = new TimerTask() {
		@Override
		public void run() {	
			incomingTickets.entrySet().parallelStream()
			.forEach(entry->{
				if(entry.getValue().getAge().compareTo(maxAge)>0) {
					incomingTickets.remove(entry.getKey());
				}
			});
			outgoingTickets.entrySet().parallelStream()
			.forEach(entry->{
				if(entry.getValue().getAge().compareTo(maxAge)>0) {
					outgoingTickets.remove(entry.getKey());
				}
			});
		}
	};
	
	MessageBroker() {
		this(10, 60, TimeUnit.SECONDS);
	}
	
	MessageBroker(int capacity){
		this(capacity, 60, TimeUnit.SECONDS);
	}

	MessageBroker(long timeout, TimeUnit timeoutTimeUnit){
		this(10, timeout, timeoutTimeUnit);
	}

	MessageBroker(int capacity, long timeout, TimeUnit timeoutTimeUnit){
		this(capacity, timeout, timeoutTimeUnit, 3, TimeUnit.HOURS, 5, TimeUnit.MINUTES);
	}
	
	MessageBroker(int capacity, long timeout, TimeUnit timeoutTimeUnit,
			long deamonInterval, TimeUnit deamonIntervalUnit, long maxAgeLong,
			TimeUnit maxAgeUnit){
		retrievalExecutor = Executors.newFixedThreadPool(capacity);
		incomingTicketQueueIn = new ArrayDeque<>();
		incomingTicketQueueOut = new ArrayDeque<>();
		outgoingTicketQueueIn = new ArrayDeque<>();
		outgoingTicketQueueOut = new ArrayDeque<>();
		this.timeout = timeout;
		this.timeoutTimeUnit = timeoutTimeUnit;
		timeoutInMillis = getFrom(timeout, timeoutTimeUnit).toMillis();
		this.maxAge = getFrom(maxAgeLong, maxAgeUnit);
		long interval = getFrom(deamonInterval, deamonIntervalUnit).toMillis();
		Timer timer = new Timer(true);
		timer.schedule(cleanUpTask, interval, interval);
		log.trace("MessageBroker created");
	}
	
	Duration getFrom(long time, TimeUnit timeUnit) {
		switch (timeUnit) {
		case DAYS:
			return Duration.ofDays(time);
		case HOURS:
			return Duration.ofHours(time);
		case MINUTES:
			return Duration.ofMinutes(time);
		case SECONDS:
			return Duration.ofSeconds(time);
		case MICROSECONDS:
			throw new UnsupportedOperationException("No duration in microseconds");
		case MILLISECONDS:
			return Duration.ofMillis(time);
		case NANOSECONDS:
			throw new UnsupportedOperationException("No duration in nanoseconds");
		}
		throw new UnsupportedOperationException("No duration in "+timeUnit);		
	}
	
	/**Send a message for processing
	 * @param message the message object
	 * @throws MessageBrokerException if the broker capacity 
	 * has been reached.
	 */
	public void sendForProcessing(M message) throws MessageBrokerException {
		log.trace("Adding message '{}' to response queue.", returnDescription(message));
		BrokerObject<M> brokerObject = new BrokerObject<>(message);
		String ticket = getIncomingTicketIn();
		log.trace("Putting message '{}' with ticket {} into incomingTickets", returnDescription(message), ticket);
		incomingTickets.put(ticket, brokerObject);
		if(!brokerObject.hasBeenPicked(timeoutInMillis)) {
			incomingTickets.remove(ticket);
			throw new MessageBrokerException("Message was not picked");
		}
	}

	private Object returnDescription(M message) {
		return message!=null? message.toString():"null";
	}
	
	/**Retrieve a message sent for processing.
	 * @return the message the message object
	 * @throws MessageBrokerException if the retrieval operation times out
	 */
	public M retrieveForProcessing() throws MessageBrokerException {
		log.trace("Retrieving message...");
		BrokerObject<M> object;
		String brokerID = getIncomingTicketOut();
		Future<BrokerObject<M>> future = retrievalExecutor
				.submit(()->processTicket(incomingTickets, brokerID, "incoming queue"));
		try {
			object = future.get(timeout, timeoutTimeUnit);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new MessageBrokerException(TIMED_OUT_MESSAGE, e);
		} catch(ExecutionException | TimeoutException e) {
			throw new MessageBrokerException(TIMED_OUT_MESSAGE, e);
		}
		
		if(object!=null && !object.isStale()) {
			log.trace(RETRIEVING_MESSAGE, returnDescription(object.getMessage()));
			return object.getMessage();
		} else {
			log.trace(RETRIEVAL_TIMEOUT);
			throw new MessageBrokerException(TIMED_OUT_RESPONSE);
		}
	}
	
	private synchronized String getIncomingTicketIn() {
		log.trace("Obtaining incomingTicketQueueIn ticket");
		String ticketId = incomingTicketQueueIn.poll();
		if(ticketId == null) {
			log.trace("Generating incomingTicketQueueOut ticket");
			ticketId = UUID.randomUUID().toString();
			incomingTicketQueueOut.offer(ticketId);
			log.trace("Generated incomingTicketQueueOut ticket {}", ticketId);
		}
		log.trace("Returning incomingTicket ticket {}", ticketId);
		return ticketId;
	}
	
	private synchronized String getIncomingTicketOut() {
		log.trace("Obtaining incomingTicketQueueOut ticket");
		String ticketId = incomingTicketQueueOut.poll();
		if(ticketId == null) {
			log.trace("Generating incomingTicketQueueIn ticket");
			ticketId = UUID.randomUUID().toString();
			incomingTicketQueueIn.offer(ticketId);
			log.trace("Generated incomingTicketQueueIn ticket {}", ticketId);
		}
		log.trace("Returning incomingTicket ticket {}", ticketId);
		return ticketId;
	}
	
	/**Send a response upon process completion
	 * @param message the response message
	 * @throws MessageBrokerException if the broker capacity has been reached
	 */
	public void sendResponse(M message) throws MessageBrokerException {
		log.trace("Adding message '{}' to response queue.", returnDescription(message));
		BrokerObject<M> brokerObject = new BrokerObject<>(message);
		String ticket = getOutgoingTicketIn();
		log.trace("Putting message '{}' with ticket {} into incomingTickets", returnDescription(message), ticket);
		outgoingTickets.put(ticket, brokerObject);
		if(!brokerObject.hasBeenPicked(timeoutInMillis)) {
			outgoingTickets.remove(ticket);
			throw new MessageBrokerException("Message was not picked");
		}
	}
	
	/**Retrieve a response to the processed message
	 * @return the response message
	 * @throws MessageBrokerException
	 */
	public M retrieveResponse() throws MessageBrokerException {
		log.trace("Retrieving response...");
		BrokerObject<M> object;
		String brokerID = getOutgoingTicketOut();
		Future<BrokerObject<M>> future = retrievalExecutor.submit(()->processTicket(outgoingTickets, brokerID, "outgoing queue"));
		try {
			object = future.get(timeout, timeoutTimeUnit);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new MessageBrokerException(TIMED_OUT_RESPONSE, e);
		} catch (ExecutionException | TimeoutException e) {
			throw new MessageBrokerException(TIMED_OUT_RESPONSE, e);
		}		
		if(object!=null && !object.isStale()) {
			log.trace(RETRIEVING_MESSAGE, returnDescription(object.getMessage()));
			return object.getMessage();
		} else {
			log.trace(RETRIEVAL_TIMEOUT);
			throw new MessageBrokerException(TIMED_OUT_RESPONSE);
		}
	}

	private synchronized String getOutgoingTicketIn() {
		log.trace("Obtaining outgoingTicketQueueIn ticket");
		String ticketId = outgoingTicketQueueIn.poll();
		if(ticketId == null) {
			log.trace("Generating outgoingTicketQueueOut ticket");
			ticketId = UUID.randomUUID().toString();
			outgoingTicketQueueOut.offer(ticketId);
			log.trace("Generated outgoingTicketQueueOut ticket {}", ticketId);
		}
		log.trace("Returning outgoingTicket ticket {}", ticketId);
		return ticketId;
	}
	
	private synchronized String getOutgoingTicketOut() {
		log.trace("Obtaining outgoingTicketQueueOut ticket");
		String ticketId = outgoingTicketQueueOut.poll();
		if(ticketId == null) {
			log.trace("Generating outgoingTicketQueueIn ticket");
			ticketId = UUID.randomUUID().toString();
			outgoingTicketQueueIn.offer(ticketId);
			log.trace("Generated outgoingTicketQueueIn ticket {}", ticketId);
		}
		log.trace("Returning outgoingTicket ticket {}", ticketId);
		return ticketId;
	}
	
	private BrokerObject<M> processTicket(Map<String, BrokerObject<M>> tickets, 
			String brokerID, String traceDescription) {
		log.trace("Processing ticket {} for {}", brokerID, traceDescription);
		long start = System.currentTimeMillis();
		BrokerObject<M> object;
		do {
			object = tickets.remove(brokerID);
			if(object!=null) {
				object.pick();
				return object;
			}
		} while (System.currentTimeMillis()-start<timeoutInMillis
				&& !Thread.interrupted());
		
		return null;
	}
}

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
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.quakearts.utils.messagebroker.exception.MessageBrokerException;

public class MessageBroker<M> {
	private Map<String, BrokerObject<M>> incomingTickets = new ConcurrentHashMap<>(),
			outgoingTickets = new ConcurrentHashMap<>();
	private BlockingQueue<BrokerObject<M>> incoming;
	private BlockingQueue<BrokerObject<M>> outgoing;
	private Deque<String> incomingTicketQueueIn, incomingTicketQueueOut, 
						  outgoingTicketQueueIn, outgoingTicketQueueOut;
	private long timeout;
	private TimeUnit timeoutTimeUnit;
	private Duration maxAge;
	private TimerTask cleanUpTask = new TimerTask() {
		Consumer<BrokerObject<M>> consumer = (object)->{
			if(object.getAge().compareTo(maxAge)>0) {
				incomingTickets.remove(object.getBrokerID());
			}
		};

		@Override
		public void run() {	
			incomingTickets.values().parallelStream()
			.forEach(consumer);
			outgoingTickets.values().parallelStream()
			.forEach(consumer);
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
		incoming = new ArrayBlockingQueue<>(capacity);
		outgoing = new ArrayBlockingQueue<>(capacity);
		incomingTicketQueueIn = new ArrayDeque<>();
		incomingTicketQueueOut = new ArrayDeque<>();
		outgoingTicketQueueIn = new ArrayDeque<>();
		outgoingTicketQueueOut = new ArrayDeque<>();
		this.timeout = timeout;
		this.timeoutTimeUnit = timeoutTimeUnit;
		this.maxAge = getFrom(maxAgeLong, maxAgeUnit);
		long interval = getFrom(deamonInterval, deamonIntervalUnit).toMillis();
		Timer timer = new Timer(true);
		timer.schedule(cleanUpTask, interval, interval);
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
	
	public void sendForProcessing(M message) throws MessageBrokerException {
		send(incoming, new BrokerObject<>(message, getIncomingTicketIn()));
	}
	
	public M retrieveForProcessing() throws MessageBrokerException {
		BrokerObject<M> object;
		String brokerID = getIncomingTicketOut();
		processTickets(incomingTickets, incoming);
		object = incomingTickets.remove(brokerID);
		if(object!=null) {
			return object.getMessage();
		} else
			return null;
	}
	
	private synchronized String getIncomingTicketIn() {
		String ticketId = incomingTicketQueueIn.poll();
		if(ticketId == null) {
			ticketId = UUID.randomUUID().toString();
			incomingTicketQueueOut.offer(ticketId);
		}
		return ticketId;
	}
	
	private synchronized String getIncomingTicketOut() {
		String ticketId = incomingTicketQueueOut.poll();
		if(ticketId == null) {
			ticketId = UUID.randomUUID().toString();
			incomingTicketQueueIn.offer(ticketId);
		}
		return ticketId;
	}
	
	public void sendResponse(M message) throws MessageBrokerException {
		send(outgoing, new BrokerObject<>(message, getOutgoingTicketIn()));
	}
	
	public M retrieveResponse() throws MessageBrokerException {
		BrokerObject<M> object;
		String brokerID = getOutgoingTicketOut();
		processTickets(outgoingTickets, outgoing);
		object = outgoingTickets.remove(brokerID);		
		if(object!=null) {
			return object.getMessage();
		} else {
			return null;
		}
	}

	private synchronized String getOutgoingTicketIn() {
		String ticketId = outgoingTicketQueueIn.poll();
		if(ticketId == null) {
			ticketId = UUID.randomUUID().toString();
			outgoingTicketQueueOut.offer(ticketId);
		}
		return ticketId;
	}
	
	private synchronized String getOutgoingTicketOut() {
		String ticketId = outgoingTicketQueueOut.poll();
		if(ticketId == null) {
			ticketId = UUID.randomUUID().toString();
			outgoingTicketQueueIn.offer(ticketId);
		}
		return ticketId;
	}
	
	private void send(BlockingQueue<BrokerObject<M>> queue, BrokerObject<M> object) throws MessageBrokerException {
		try {
			if(!queue.offer(object, timeout, timeoutTimeUnit))
				throw new MessageBrokerException("Unable to process the transaction: timeout");
		} catch (InterruptedException e) {
			throw new MessageBrokerException("Unable to process the transaction", e);
		}		
	}

	private void processTickets(Map<String, BrokerObject<M>> tickets, 
			BlockingQueue<BrokerObject<M>> queue) throws MessageBrokerException {
		BrokerObject<M> object;
		object = receive(queue);
		if(object!=null) {
			tickets.put(object.getBrokerID(), object);
			if(!queue.isEmpty()) {
				for(int i=queue.size(); i>0;i--) {
					object = receive(queue);
					tickets.put(object.getBrokerID(), object);
				}
			}
		}
	}

	private BrokerObject<M> receive(BlockingQueue<BrokerObject<M>> queue) throws MessageBrokerException {
		try {			
			return queue.poll(timeout, timeoutTimeUnit);
		} catch (InterruptedException e) {
			throw new MessageBrokerException("Unable to process the transaction", e);
		}
	}
	
	
}

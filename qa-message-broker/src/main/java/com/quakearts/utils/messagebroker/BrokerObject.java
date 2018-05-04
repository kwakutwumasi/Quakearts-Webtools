package com.quakearts.utils.messagebroker;

import java.time.Duration;
import java.time.Instant;

public class BrokerObject<M> {
	private M message;
	private String brokerID;
	private Instant birthday = Instant.now();
	
	public BrokerObject(M message, String brokerID) {
		this.message = message;
		this.brokerID = brokerID;
	}
	
	public M getMessage() {
		return message;
	}
	
	public String getBrokerID() {
		return brokerID;
	}
	
	public Duration getAge() {
		return Duration.between(birthday, Instant.now());
	}
}
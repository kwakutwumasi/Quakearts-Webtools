package com.quakearts.syshub.test.helper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;

import com.quakearts.appbase.Main;
import com.quakearts.syshub.agent.event.ProcessingEvent;

@Singleton
public class ErrorObserver {

	private BlockingQueue<Throwable> queue = new ArrayBlockingQueue<>(1);
	
	public BlockingQueue<Throwable> getQueue() {
		return queue;
	}
	
	public void handleProcessingEvent(@Observes ProcessingEvent event) {
		Main.log.info("Handling "+event.getException().getClass().getName());
		if(event.getAgentConfiguration().getAgentName().equals("ErrorThrowingModule")) {
			try {
				queue.put(event.getException());
			} catch (InterruptedException e) {
			}
		}
	}
}

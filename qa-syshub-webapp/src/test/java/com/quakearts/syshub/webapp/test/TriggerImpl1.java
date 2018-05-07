package com.quakearts.syshub.webapp.test;

import javax.inject.Singleton;

@Singleton
public class TriggerImpl1 implements Trigger1 {

	@Override
	public synchronized void fire() {
		notifyAll();
	}
	
	@Override
	public synchronized void prepare() {
		try {
			wait();
		} catch (InterruptedException e) {
		}
	}

}

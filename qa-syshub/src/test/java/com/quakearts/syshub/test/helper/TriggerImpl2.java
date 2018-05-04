package com.quakearts.syshub.test.helper;

import javax.inject.Singleton;

@Singleton
public class TriggerImpl2 implements Trigger2 {

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

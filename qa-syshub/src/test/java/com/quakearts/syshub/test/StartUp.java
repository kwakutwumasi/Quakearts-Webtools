package com.quakearts.syshub.test;

import com.quakearts.appbase.Main;

public class StartUp {
	
	private static StartUp startUp = new StartUp();
	
	public static StartUp getStartUp() {
		return startUp;
	}
	
	public StartUp() {
	}
	
	public synchronized void initiateSystem(){
		Main.main(new String[] {TestSysHubMain.class.getName(),"-dontwaitinmain"});
		try {
			wait();
		} catch (InterruptedException e) {
		}
	}
	
	public synchronized void startUpComplete() {
		notifyAll();
	}
}

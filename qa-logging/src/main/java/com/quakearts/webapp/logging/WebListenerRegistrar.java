package com.quakearts.webapp.logging;

public interface WebListenerRegistrar {

	public void registerListener(WebListener listener);

	public boolean unregisterListener(WebListener listener);

}
package com.quakearts.webapp.logging.monitor;

import javax.servlet.ServletConfig;

public interface SystemMonitor {
	public enum State{
		UP,
		DOWN,
		QUERYERROR;
	}
	
	public enum Type{
		WEBAPP,
		WEBSERVICE,
		SERVERSERVICE;
	}
	
	public State getState(MonitorTarget target);
	public void setProperties(ServletConfig config);
}

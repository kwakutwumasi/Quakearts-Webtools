package com.quakearts.webapp.logging.monitor;

import com.quakearts.webapp.logging.monitor.SystemMonitor.Type;

public class MonitorTarget {
	private String objectIdentifier,displayName;
	private Type type;
	
	public MonitorTarget(String objectIdentifier, Type type, String displayName) {
		this.objectIdentifier = objectIdentifier;
		this.type = type;
		this.displayName=displayName;
	}
	public String getObjectIdentifier() {
		return objectIdentifier;
	}
	public void setObjectIdentifier(String objectIdentifier) {
		this.objectIdentifier = objectIdentifier;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}

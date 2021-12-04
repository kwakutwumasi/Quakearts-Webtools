package com.quakearts.microservices.dockerinit.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProcessesFile {
	@JsonIgnore
	private Map<String, ProcessMetadata> properties = new HashMap<>();
	
	@JsonAnyGetter
	public Map<String, ProcessMetadata> getProperties() {
		return properties;
	}
	
	@JsonAnySetter
	public void setProperties(String key, ProcessMetadata process) {
		this.properties.put(key, process);
	}
}

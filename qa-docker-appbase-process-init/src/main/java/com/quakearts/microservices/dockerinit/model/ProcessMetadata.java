package com.quakearts.microservices.dockerinit.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quakearts.microservices.dockerinit.ProcessDeployer;
import com.quakearts.microservices.dockerinit.exception.InitException;

public class ProcessMetadata {
	private String workingDirectory;
	private String jarFile;
	private List<String> properties;
	private List<String> arguments;
	private int killTimeout = 30;
	
	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public String getJarFile() {
		return jarFile;
	}

	public List<String> getProperties() {
		return properties;
	}
	
	public List<String> getArguments() {
		return arguments;
	}
	
	public int getKillTimeout() {
		return killTimeout;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(jarFile, workingDirectory);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ProcessMetadata))
			return false;
		ProcessMetadata other = (ProcessMetadata) obj;
		return Objects.equals(jarFile, other.jarFile) && Objects.equals(workingDirectory, other.workingDirectory);
	}
	
	@Override
	public String toString() {
		try {
			return ProcessDeployer.getMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new InitException("Unable to marshall ProcessMetadata", e);
		}
	}
}

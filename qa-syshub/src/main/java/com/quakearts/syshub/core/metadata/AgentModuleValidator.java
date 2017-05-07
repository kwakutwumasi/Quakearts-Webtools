package com.quakearts.syshub.core.metadata;

import com.quakearts.syshub.exception.ConfigurationException;

public interface AgentModuleValidator {
	void validate() throws ConfigurationException;
}

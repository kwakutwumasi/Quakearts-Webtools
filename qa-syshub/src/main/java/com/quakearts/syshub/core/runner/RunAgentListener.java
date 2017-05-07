package com.quakearts.syshub.core.runner;

import com.quakearts.syshub.exception.ProcessingException;

public interface RunAgentListener {
	void runAgent() throws ProcessingException;
}

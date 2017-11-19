package com.quakearts.utilities;

import java.util.Map;

import com.quakearts.utilities.exception.CommandParameterException;

public interface Command {
	void setCommandParametersMap(Map<String, CommandParameter> commandParameterMap);
	String printUsage();
	void execute() throws CommandParameterException;
}

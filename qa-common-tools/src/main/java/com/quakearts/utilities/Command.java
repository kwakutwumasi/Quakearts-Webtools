package com.quakearts.utilities;

import java.util.List;
import java.util.Map;

import com.quakearts.utilities.exception.CommandParameterException;

public interface Command {
	void setCommandParameters(List<CommandParameter> commandParameters);
	void setCommandParametersMap(Map<String, CommandParameter> commandParameterMap);
	String printUsage();
	void execute() throws CommandParameterException;
}

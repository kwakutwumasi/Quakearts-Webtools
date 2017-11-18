package com.quakearts.utilities;

import java.util.List;

import com.quakearts.utilities.exception.CommandParameterException;

public interface Command {
	void setCommandParameters(List<CommandParameter> commandParameters);
	String printUsage();
	void execute() throws CommandParameterException;
}

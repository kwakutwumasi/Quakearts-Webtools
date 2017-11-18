package com.quakearts.utilities.exception;

public class CommandParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String commandParameterName;

	public CommandParameterException() {
	}

	public CommandParameterException(String message, String commandParameterName) {
		super(message);
		this.commandParameterName = commandParameterName;
	}

	public CommandParameterException(Throwable cause, String commandParameterName) {
		super(cause);
		this.commandParameterName = commandParameterName;
	}

	public CommandParameterException(String message, String commandParameterName, Throwable cause) {
		super(message, cause);
		this.commandParameterName = commandParameterName;
	}

	public CommandParameterException(String message, String commandParameterName, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.commandParameterName = commandParameterName;
	}

	public String getCommandParameterName() {
		return commandParameterName;
	}
}

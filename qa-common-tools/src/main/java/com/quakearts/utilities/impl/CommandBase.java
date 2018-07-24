package com.quakearts.utilities.impl;

import java.util.Map;

import com.quakearts.utilities.Command;
import com.quakearts.utilities.CommandParameter;

/**A base class for commands. It stores the command parameter map for retrieval when executing.
 * The parameters can be obtained by calling the protected {@link #getCommandParametersMap()} method.
 * @author kwakutwumasi-afriyie
 *
 */
public abstract class CommandBase implements Command {
	private Map<String, CommandParameter> commandParametersMap;
	
	@Override
	public void setCommandParametersMap(Map<String, CommandParameter> commandParametersMap) {
		this.commandParametersMap = commandParametersMap;
	}
	
	/**Internal getter for classes extending this abstract method
	 * @return a {@linkplain Map} of {@linkplain CommandParameter}s
	 */
	protected Map<String, CommandParameter> getCommandParametersMap() {
		return commandParametersMap;
	}
}

package com.quakearts.utilities;

import java.util.Map;

import com.quakearts.utilities.annotation.CommandMetadata;
import com.quakearts.utilities.annotation.CommandParameterMetadata;
import com.quakearts.utilities.exception.CommandParameterException;

/**The interface to be extended by command classes.
 * @author kwakutwumasi-afriyie
 *
 */
public interface Command {
	static final String SPECIALINSTRUCTIONS = "Parameters can be prefixed with - or \\. Single letter parameters with no value can be grouped together in curly brackets {}\n";
	static final String HELPTEXT = "\nTo show this help message use the -help parameter.\n";
	/**This method is called by the main class to pass parsed command line variables.
	 * @param commandParameterMap a {@linkplain Map} of {@linkplain CommandParameter}s
	 */
	void setCommandParametersMap(Map<String, CommandParameter> commandParameterMap);
	/**Print the command usage information
	 * @return a String describing the command, parameter options and output
	 */
	default String printUsage() {
		CommandMetadata metadata = this.getClass().getAnnotation(CommandMetadata.class);
		StringBuilder builder = new StringBuilder("Usage: ");
		if(metadata!=null) {			
			builder.append(metadata.value());
			for(CommandParameterMetadata parameterMetadata:metadata.parameters()) {
				builder.append(!parameterMetadata.required() || parameterMetadata.canOmitName()?" (":" ").append("-")
				.append(parameterMetadata.value());
				if(!parameterMetadata.format().isEmpty())
					builder.append(parameterMetadata.canOmitName() && parameterMetadata.required()?")":"")
					.append(" [").append(parameterMetadata.format()).append("]");
				
				builder.append(!parameterMetadata.required() || (parameterMetadata.format().isEmpty() 
						&& parameterMetadata.canOmitName())?")":"");
			}
			builder.append("\n");
			
			if(!metadata.description().isEmpty())
				builder.append("\n").append(metadata.description()).append("\n");
			
			if(metadata.parameters().length>0)
				builder.append("\n").append(SPECIALINSTRUCTIONS).append("\n");
			
			for(CommandParameterMetadata parameterMetadata:metadata.parameters()) {
				if(!parameterMetadata.description().isEmpty()
						|| parameterMetadata.required()
						|| !parameterMetadata.format().isEmpty()
						|| !parameterMetadata.alias().isEmpty()) {
					builder.append(parameterMetadata.value());
					if(!parameterMetadata.alias().isEmpty())
						builder.append("|(").append(parameterMetadata.alias()).append(")");
					
					if(parameterMetadata.required() 
							|| !parameterMetadata.format().isEmpty() 
							|| !parameterMetadata.description().isEmpty())
						builder.append(":\t");
					
					if(parameterMetadata.required())
						builder.append("{required} ");
					
					if(!parameterMetadata.format().isEmpty())
						builder.append("[").append(parameterMetadata.format()).append("] ");
					
					if(!parameterMetadata.description().isEmpty())
						builder.append(parameterMetadata.description());

					builder.append("\n");
				}
			}
			
			if(!metadata.additionalInfo().isEmpty())
				builder.append("\n").append(metadata.additionalInfo()).append("\n");
			
			if(!metadata.examples().isEmpty())
				builder.append("\nExamples:\n").append(metadata.examples()).append("\n");
			
			builder.append(HELPTEXT);
		} else {
			builder.append("No usage specified");
		}
		return builder.toString();
	}
	/**This method is called by the main class to execute the command
	 * @throws CommandParameterException
	 */
	void execute() throws CommandParameterException;
}

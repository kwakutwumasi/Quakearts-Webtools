package com.quakearts.utilities.impl;

import java.util.List;

import com.quakearts.utilities.Command;
import com.quakearts.utilities.CommandParameter;
import com.quakearts.utilities.annotation.CommandMetadata;
import com.quakearts.utilities.annotation.CommandParameterMetadata;

public abstract class CommandBase implements Command {
	private static final String SPECIALINSTRUCTIONS = "Parameters can be prefixed with - or \\. Single letter parameters with no value can be grouped together in curly brackets {}\n";
	private List<CommandParameter> commandParameters;
		
	@Override
	public void setCommandParameters(List<CommandParameter> commandParameters) {
		this.commandParameters = commandParameters;
	}
	
	protected List<CommandParameter> getCommandParameters() {
		return commandParameters;
	}
	
	@Override
	public String printUsage() {
		CommandMetadata metadata = this.getClass().getAnnotation(CommandMetadata.class);
		StringBuilder builder = new StringBuilder("Usage: ");
		if(metadata!=null) {			
			builder.append(metadata.value());
			for(CommandParameterMetadata parameterMetadata:metadata.parameters()) {
				builder.append(" -").append(!parameterMetadata.required()?"(":"")
				.append(parameterMetadata.value());
				if(!parameterMetadata.format().isEmpty())
					builder.append(" [")
					.append(parameterMetadata.format()).append("]");
				builder.append(!parameterMetadata.required()?")":"");
			}
			builder.append("\n");
			
			if(!metadata.descritpion().isEmpty())
				builder.append("\n").append(metadata.descritpion()).append("\n");
			
			if(metadata.parameters().length>0)
				builder.append("\n").append(SPECIALINSTRUCTIONS).append("\n");
			
			for(CommandParameterMetadata parameterMetadata:metadata.parameters()) {
				if(!parameterMetadata.description().isEmpty()
						|| parameterMetadata.required()
						|| !parameterMetadata.format().isEmpty()
						|| !parameterMetadata.alias().isEmpty()) {
					builder.append(parameterMetadata.value());
					if(!parameterMetadata.alias().isEmpty())
						builder.append(" \\ ").append(parameterMetadata.alias());
					
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
				builder.append(metadata.additionalInfo()).append("\n");
			
			if(!metadata.examples().isEmpty())
				builder.append(metadata.examples()).append("\n");
		} else {
			builder.append("No usage specified");
		}
		return builder.toString();
	}
}

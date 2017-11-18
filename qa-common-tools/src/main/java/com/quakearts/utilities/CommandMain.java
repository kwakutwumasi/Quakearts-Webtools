package com.quakearts.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quakearts.utilities.annotation.CommandMetadata;
import com.quakearts.utilities.annotation.CommandParameterMetadata;
import com.quakearts.utilities.exception.CommandParameterException;
import com.quakearts.utilities.impl.CommandParameterImpl;

public class CommandMain {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		if(args.length < 1)
			throw new RuntimeException("Invalid invocation of Command. No arguments passed");
		
		Class<Command> commandClass;
		try {
			commandClass = (Class<Command>) Class.forName(args[0]);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Invalid invocation of Command. Class "+args[0]+" was not found.");
		} catch (ClassCastException e) {
			throw new RuntimeException("Invalid invocation of Command. Class "+args[0]+" was not an implementation of "+Command.class.getName()+".");
		}
		
		List<CommandParameter> commandParameters = new ArrayList<>();
		Map<String, CommandParameter> commandParametersMap = new HashMap<>();
		
		CommandParameter parameter = null;
		boolean skipfirst = false;
		for(String arg:args) {
			if(!skipfirst) {
				skipfirst = true;
				continue;
			}
				
			
			if(arg.length()>1 && (arg.startsWith("-")
				||arg.startsWith("\\")
				||(arg.startsWith("{") && arg.endsWith("}")))) {
				String prefix = arg.substring(0,1);
				switch (prefix) {
				case "-":
				case "\\":
					parameter = new CommandParameterImpl(arg.substring(1));					
					commandParameters.add(parameter);
					commandParametersMap.put(parameter.getName(), parameter);
					break;
				case "{":
					if(arg.length()>3) {
						String parts = arg.substring(1,arg.length()-1);
						for(char c:parts.toCharArray()) {
							parameter = new CommandParameterImpl(new Character(c).toString());
							commandParameters.add(parameter);
							commandParametersMap.put(parameter.getName(), parameter);
						}
					}
					break;
				default:
					break;
				}
			} else {
				if(parameter == null) {
					parameter = new CommandParameterImpl("no-name");
					commandParameters.add(parameter);
				}
				parameter.setValue(arg);
				continue;
			}
		}
		
		try {
			Command command = commandClass.newInstance();

			CommandMetadata metadata = commandClass.getAnnotation(CommandMetadata.class);
			if(metadata != null) {
				for(CommandParameterMetadata parameterMetadata : metadata.parameters()) {
					if(!commandParametersMap.containsKey(parameterMetadata.value())) {
						if(parameterMetadata.required()) {
							System.err.println("Missing required parameter: "+parameterMetadata.value());
							System.out.println(command.printUsage());
							return;
						} else if(!parameterMetadata.linkedParameters().isEmpty()) {
							String[] linkedParameters = parameterMetadata.linkedParameters().split(";");
							for(String linkedParameter:linkedParameters) {
								if(commandParametersMap.containsKey(linkedParameter)) {
									System.err.println("Missing required parameter: "+parameterMetadata.value());
									System.out.println(command.printUsage());
									return;								
								}
							}
						}
					}
				}
			}
			
			if(!commandParameters.isEmpty())
				command.setCommandParameters(commandParameters);
			
			if(!commandParametersMap.isEmpty())
				command.setCommandParametersMap(commandParametersMap);
			
			try { 
				command.execute();
			} catch (CommandParameterException e) {
				System.err.println("Invalid parameter "+e.getCommandParameterName()
					+(e.getMessage()!=null?". "+e.getMessage():"")+
					(e.getCause()!=null?"Cause: "+e.getCause().getMessage():""));
				System.out.println(command.printUsage());
			}
		} catch (InstantiationException e) {
			throw new RuntimeException("Invalid invocation of Command. Class "+args[0]+" cannot be instantiated.");
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Invalid invocation of Command. Class "+args[0]+" cannot be accessed.");
		}
	}
}

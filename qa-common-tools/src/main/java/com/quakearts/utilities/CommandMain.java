package com.quakearts.utilities;

import java.util.HashMap;
import java.util.Map;

import com.quakearts.utilities.annotation.CommandMetadata;
import com.quakearts.utilities.annotation.CommandParameterMetadata;
import com.quakearts.utilities.exception.CommandParameterException;
import com.quakearts.utilities.impl.CommandParameterImpl;

public class CommandMain {
	public CommandMain() {
	}

	private void processAndExecute(String[] commandLineArguments) {
		if(commandLineArguments.length < 1)
			throw new RuntimeException("Invalid invocation of Command. No arguments passed");
		
		Map<String, CommandParameter> withCommandParameterMap = getCommandParametersFrom(commandLineArguments);
		
		Class<Command> withCommandClass = getCommandClassNamed(commandLineArguments[0]);
				
		Command command = getCommand(withCommandClass, withCommandParameterMap);
		
		if(command != null)
			try {
				checkCommandParameter(withCommandClass, withCommandParameterMap)
					.thenExecute(command);
			} catch (CommandParameterException e) {
				System.err.println("Invalid parameter '"+e.getCommandParameterName()
				+(e.getMessage()!=null?"'. "+e.getMessage():"")+
				(e.getCause()!=null?". Cause: "+e.getCause().getMessage():"")+
				"\n"+command.printUsage());
			}
	}
	
	@SuppressWarnings("unchecked")
	protected Class<Command> getCommandClassNamed(String className){
		Class<Command> commandClass;
		try {
			commandClass = (Class<Command>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Invalid invocation of Command. Class "+className+" was not found.");
		} catch (ClassCastException e) {
			throw new RuntimeException("Invalid invocation of Command. Class "+className+" was not an implementation of "+Command.class.getName()+".");
		}
		
		return commandClass;
	}
	
	protected Map<String, CommandParameter> getCommandParametersFrom(String[] args){
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
					commandParametersMap.put(parameter.getName(), parameter);
					break;
				case "{":
					if(arg.length()>3) {
						String parts = arg.substring(1,arg.length()-1);
						for(char c:parts.toCharArray()) {
							parameter = new CommandParameterImpl(new Character(c).toString());
							commandParametersMap.put(parameter.getName(), parameter);
						}
					}
					break;
				default:
					break;
				}
			} else {
				if(parameter == null) {
					parameter = new CommandParameterImpl(CommandParameter.DEFAULT);
					commandParametersMap.put(parameter.getName(), parameter);
				}
				parameter.setValue(arg);
				continue;
			}
		}
		
		return commandParametersMap;
	}
	
	protected Command getCommand(Class<Command> commandClass, Map<String, CommandParameter> commandParametersMap) {
		Command command;
		try {
			command = commandClass.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException("Invalid invocation of Command. Class "+commandClass.getName()+" cannot be instantiated.");
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Invalid invocation of Command. Class "+commandClass.getName()+" cannot be accessed.");
		}
		
		if(commandParametersMap.size()==1 && commandParametersMap.containsKey("help")) {
			System.out.println(command.printUsage());
			return null;
		}
		
		command.setCommandParametersMap(commandParametersMap);
		
		return command;
	}
	
	protected CommandMain checkCommandParameter(Class<Command> commandClass, Map<String, CommandParameter> commandParametersMap) throws CommandParameterException {
		CommandMetadata metadata = commandClass.getAnnotation(CommandMetadata.class);
		if(metadata != null) {
			for(CommandParameterMetadata parameterMetadata : metadata.parameters()) {
				if(commandParametersMap.containsKey(parameterMetadata.alias()))
					commandParametersMap.put(parameterMetadata.value(), 
							commandParametersMap.get(parameterMetadata.alias()));
				
				if(!commandParametersMap.containsKey(parameterMetadata.value())) {
					if(parameterMetadata.required() && !parameterMetadata.canOmitName()) {
						throw new CommandParameterException("The parameter is required", parameterMetadata.value());
					} else if(!parameterMetadata.linkedParameters().isEmpty()) {
						String[] linkedParameters = parameterMetadata.linkedParameters().split(";");
						for(String linkedParameter:linkedParameters) {
							if(commandParametersMap.containsKey(linkedParameter)) {
								throw new CommandParameterException("The parameter is required", parameterMetadata.value());
							}
						}
					}
				}
			}
		}	
		
		return this;
	}
	
	protected void thenExecute(Command command) throws CommandParameterException {
		command.execute();
	}
	
	public static void main(String[] args) {
		new CommandMain().processAndExecute(args);
	}
}

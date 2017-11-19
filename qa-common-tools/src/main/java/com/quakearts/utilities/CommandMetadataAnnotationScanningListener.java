package com.quakearts.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.quakearts.classannotationscanner.listener.ClassAnnotationScanningListener;
import com.quakearts.utilities.annotation.CommandMetadata;

public class CommandMetadataAnnotationScanningListener implements ClassAnnotationScanningListener {
	private static String JARPART = "java -jar %1$s %2$s", WINDOWSFORMAT = "@echo off\n"+JARPART+" %%*", 
			NIXBASHFORMAT = "#!/bin/bash\n"+JARPART+" \"$@\""; 

	private String mainjar;
	
	public CommandMetadataAnnotationScanningListener(String mainjar) {
		this.mainjar = mainjar;
	}

	@Override
	public String[] getAnnotationsToListenFor() {
		return new String[]{CommandMetadata.class.getName()};
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handle(String className, String annotation) {		
		Class<Command> commandClass;
		try {
			commandClass = (Class<Command>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Invalid invocation of Command. Class "+className+" was not found.");
		} catch (ClassCastException e) {
			throw new RuntimeException("Invalid invocation of Command. Class "+className+" was not an implementation of "+Command.class.getName()+".");
		}
		
		CommandMetadata metadata = commandClass.getAnnotation(CommandMetadata.class);
		if(metadata != null) {
			if(mainjar!=null) {
				File windowsBatchFile = new File(metadata.value()+".bat");
				if(!windowsBatchFile.exists()) {
					try(FileOutputStream windowsBatchFileOutputStream = new FileOutputStream(windowsBatchFile)){
						windowsBatchFileOutputStream.write(String.format(WINDOWSFORMAT, mainjar, className).getBytes());
						windowsBatchFileOutputStream.flush();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
				File nixBashFile = new File(metadata.value());
				if(!nixBashFile.exists()) {
					try(FileOutputStream nixBashFileOutputStream = new FileOutputStream(nixBashFile)){
						nixBashFileOutputStream.write(String.format(NIXBASHFORMAT, mainjar, className).getBytes());
						nixBashFileOutputStream.flush();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}
}

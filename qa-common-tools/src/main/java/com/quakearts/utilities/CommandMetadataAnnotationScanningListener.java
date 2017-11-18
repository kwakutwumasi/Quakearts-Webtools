package com.quakearts.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.quakearts.classannotationscanner.listener.ClassAnnotationScanningListener;
import com.quakearts.utilities.annotation.CommandMetadata;

public class CommandMetadataAnnotationScanningListener implements ClassAnnotationScanningListener {
	private static String JARPART = "java -jar %1$s %2$s", WINDOWSFORMAT = "@echo off\n"+JARPART+" %%*", 
			NIXBASHFORMAT = "#!/bin/bash\n"+JARPART+" \"$@\""; 

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
			String jarName = null;
			File currentDirectory = new File(".");
			for(File fileFolder:currentDirectory.listFiles()) {
				if(fileFolder.isFile() && fileFolder.getName().startsWith("common-tools")) {
					jarName = fileFolder.getName();
					break;
				}
			}

			currentDirectory = new File("."+File.separator+"target"); //when testing...
			if(currentDirectory.exists() && currentDirectory.isDirectory())
				for(File fileFolder:currentDirectory.listFiles()) {
					if(fileFolder.isFile() && fileFolder.getName().startsWith("common-tools")) {
						jarName = fileFolder.getName();
						break;
					}
				}
			if(jarName!=null) {
				File windowsBatchFile = new File(metadata.value()+".bat");
				if(!windowsBatchFile.exists()) {
					try(FileOutputStream windowsBatchFileOutputStream = new FileOutputStream(windowsBatchFile)){
						windowsBatchFileOutputStream.write(String.format(WINDOWSFORMAT, jarName, className).getBytes());
						windowsBatchFileOutputStream.flush();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
				File nixBashFile = new File(metadata.value());
				if(!nixBashFile.exists()) {
					try(FileOutputStream nixBashFileOutputStream = new FileOutputStream(nixBashFile)){
						nixBashFileOutputStream.write(String.format(NIXBASHFORMAT, jarName, className).getBytes());
						nixBashFileOutputStream.flush();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}
}

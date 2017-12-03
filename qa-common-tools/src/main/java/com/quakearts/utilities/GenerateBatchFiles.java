package com.quakearts.utilities;

import java.util.Map;

import com.quakearts.classannotationscanner.ClasspathScanner;

public class GenerateBatchFiles extends CommandMain {
	
	public GenerateBatchFiles() {
	}
	
	private void processAndScan(String args[]) {
		Map<String, CommandParameter> parameterMap = getCommandParametersFrom(args);
		processAndScan(parameterMap);
	}
	
	private void processAndScan(Map<String, CommandParameter> commandParametersMap) {	
		ClasspathScanner classpathScanner = new ClasspathScanner(commandParametersMap.containsKey("package")?
				commandParametersMap.get("package").getValue():"com.quakearts.utilities");
		classpathScanner.addAnnotationListener(new CommandMetadataAnnotationScanningListener(
				commandParametersMap.containsKey("name")?commandParametersMap.get("name").getValue():null,
				commandParametersMap.containsKey("folder")?commandParametersMap.get("folder").getValue():"."));
		classpathScanner.scan();		
	}
	
	public static void main(String[] args) {
		new GenerateBatchFiles().processAndScan(args);
	}
}

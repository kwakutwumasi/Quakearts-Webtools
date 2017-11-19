package com.quakearts.utilities;

import java.util.Map;

import com.quakearts.classannotationscanner.ClasspathScanner;

public class GenerateBatchFiles extends CommandMain {
	
	public GenerateBatchFiles() {
	}
	
	private void processAndScan(String args[]) {
		processAndScan(withCommandParameters(args));
	}
	
	private void processAndScan(Map<String, CommandParameter> commandParametersMap) {	
		ClasspathScanner classpathScanner = new ClasspathScanner(commandParametersMap.containsKey("package")?
				commandParametersMap.get("package").getValue():"com.quakearts.utilities");
		classpathScanner.addAnnotationListener(new CommandMetadataAnnotationScanningListener(commandParametersMap.containsKey("jar-name")?
				commandParametersMap.get("jar-name").getValue():"qa-common-tools.1.0.0.jar"));
		classpathScanner.scan();		
	}
	
	public static void main(String[] args) {
		new GenerateBatchFiles().processAndScan(args);
	}
}

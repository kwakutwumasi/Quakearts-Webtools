package com.quakearts.utilities;

import com.quakearts.classannotationscanner.ClasspathScanner;

public class GenerateBatchFiles {
	public static void main(String[] args) {
		ClasspathScanner classpathScanner = new ClasspathScanner("com.quakearts.utilities");
		classpathScanner.addAnnotationListener(new CommandMetadataAnnotationScanningListener(args != null&&args.length>0?args[0]:"common-tools"));
		classpathScanner.scan();
	}
}

package com.quakearts.tools.test.generator.bootstrap;

import java.util.HashSet;
import java.util.Set;

import com.quakearts.classannotationscanner.ClasspathScanner;
import com.quakearts.classannotationscanner.Filter;
import com.quakearts.classannotationscanner.Scanner;
import com.quakearts.classannotationscanner.listener.ClassAnnotationScanningListener;

public final class GeneratorBootstrap {

	private GeneratorBootstrap() {
	}
	
	private static Boolean scanned = false;
	private static final GeneratorBootstrap instance = new GeneratorBootstrap();
	private static final ClassAnnotationScanningListener GENERATORANNOTATIONLISTENER = new GeneratesAnnotationScanningListener(),
			USEGENERATORANNOTATIONLISTENER = new UseGeneratorClassAnnotationScanningListener(),
			COLLECTIONFACTORYANNOTATIONLISTENER = new CollectionFactoryAnnotationListener();
	private static Set<String> excludes = new HashSet<>();
	
	public static void init(){
		synchronized (scanned) {
			if(!scanned){
				instance.bootstrapGenerators("com.quakearts.tools.test");
				scanned=true;
			}
		}
	}
	
	public static GeneratorBootstrap getInstance() {
		return instance;
	}
	
	public GeneratorBootstrap exclude(String property, Class<?> fromClass){
		synchronized (excludes) {
			excludes.add(fromClass.getName()+"#"+property);
		}
		return this;
	}
	
	public static boolean excludes(String property, Class<?> fromClass){
		synchronized (excludes) {
			return excludes.contains(fromClass.getName()+"#"+property);
		}
	}
	
	public void bootstrapGenerators(String... packageNames){
		Scanner scanner = new ClasspathScanner(packageNames);
		addScanAnnotationListeners(scanner);
		scanner.scan();
	}
	
	public void bootstrapGenerators(){
		Scanner scanner = new ClasspathScanner();
		addScanAnnotationListeners(scanner);
		scanner.scan();
	}

	public void bootstrapGenerators(Filter filter){
		Scanner scanner = new ClasspathScanner(filter);
		addScanAnnotationListeners(scanner);
		scanner.scan();
	}

	public static void addScanAnnotationListeners(Scanner scanner) {
		scanner.addAnnotationListener(GENERATORANNOTATIONLISTENER);
		scanner.addAnnotationListener(USEGENERATORANNOTATIONLISTENER);
		scanner.addAnnotationListener(COLLECTIONFACTORYANNOTATIONLISTENER);
	}
}

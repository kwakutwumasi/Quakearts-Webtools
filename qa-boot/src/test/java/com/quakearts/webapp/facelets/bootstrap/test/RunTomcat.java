package com.quakearts.webapp.facelets.bootstrap.test;

import java.io.File;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class RunTomcat {
	
	public static void main(String[] args) {
		System.setProperty("catalina.base", System.getProperty("user.dir"));
		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir(System.getProperty("user.dir"));
		tomcat.getConnector().setPort(8180);
		tomcat.addWebapp("", new File("test-webapp").getAbsolutePath());
		try {
			tomcat.start();
			tomcat.getServer().await();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
	
}

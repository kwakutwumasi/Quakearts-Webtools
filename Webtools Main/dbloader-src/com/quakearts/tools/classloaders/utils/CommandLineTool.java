package com.quakearts.tools.classloaders.utils;

import java.io.File;
import java.io.FileInputStream;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CommandLineTool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if(args.length < 3){
			printUsage();
			return;
		}
		
		String jarFileLocation=null,config=null;
		
		int i=0;
		for(String arg:args){
			if(i % 2==1){
				if(arg.equalsIgnoreCase("-jar"))
					jarFileLocation = args[i+1];
				if(arg.equalsIgnoreCase("-config"))
					config = args[i+1];
			}else{
				continue;
			}
		}
		
		if(jarFileLocation==null || jarFileLocation.isEmpty()){
			printUsage();
			return;
		}
		
		try{
			SessionFactory factory;
			Configuration configuration;
			if(config!=null)
				configuration = new Configuration().configure(config);
			else
				configuration = new Configuration().configure();
			
			factory = configuration.buildSessionFactory();
			
			Session session = factory.openSession();
			session.beginTransaction();
			FileInputStream fis=null; 
			try {
				JarFileStorer storer = new JarFileStorer();
				
				fis = new FileInputStream(jarFileLocation);
				byte[] jarBytes = new byte[fis.available()];
				
				fis.read(jarBytes);
				
				
				storer.storeJarFile(jarBytes,jarFileLocation.lastIndexOf(File.separator)!=-1?
						jarFileLocation.substring(jarFileLocation.lastIndexOf(File.separator)+1):
						jarFileLocation, session);
				
				session.getTransaction().commit();
			} catch (Exception e) {
				session.getTransaction().rollback();
			} finally{
				try {
					fis.close();
				} catch (Exception e2) {
				}
			}
		}catch (Exception e) {
			System.out.println("Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles storing jar file "+jarFileLocation);
			e.printStackTrace();
		}
	}

	private static void printUsage() {
		System.out.println("Usage: -jar (jar file name) [-config (hibernate configuration file name)]");
	}
}

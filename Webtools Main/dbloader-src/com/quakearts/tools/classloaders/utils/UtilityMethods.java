package com.quakearts.tools.classloaders.utils;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

public class UtilityMethods {
	private UtilityMethods() {
	}
	
	private static final InitialContext icx = startInitialContext();
	private static InitialContext startInitialContext(){
		InitialContext icx;
		try {
			icx = new InitialContext();
			return icx;
		} catch (NamingException e) {
			return null;
		}
    }
	
	public static UserTransaction getTransaction() throws NamingException{
		return (UserTransaction) icx.lookup("java:comp/UserTransaction");
	}
	
	public static ZipEntry findZipEntry(String entryFileName,ZipInputStream jarStream) throws IOException{
		ZipEntry zipEntry=null;
		while((zipEntry = jarStream.getNextEntry())!=null){
			if(zipEntry.isDirectory())
				continue;

			if(zipEntry.getName().equals(entryFileName)){
				break;
			}
		}
		return zipEntry;
	}
}

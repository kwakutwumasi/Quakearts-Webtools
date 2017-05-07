package com.quakearts.syshub.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializationUtil {
	private SerializationUtil() {
	}
	
	private static final Logger log = LoggerFactory.getLogger(SerializationUtil.class);
	
	private static final SerializationUtil instance = new SerializationUtil();
	
	public static SerializationUtil getInstance() {
		return instance;
	}
	
	public byte[] toByteArray(Serializable object){
		if(object==null)
			return null;
		
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(object);
			byte[] ret = bos.toByteArray();
			bos.close();
			return ret;			
		} catch (Exception e) {
			log.error("Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage()+". Exception occured whiles attempting to serialize object of class "+object.getClass().getName());
			return null;
		}
	}
	
	public Object toObject(byte[] bytes) throws IOException, ClassNotFoundException{
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Object obj = ois.readObject();
		bis.close();
		return obj;
	}
		

}

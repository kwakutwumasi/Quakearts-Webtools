/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.syshub.core.utils.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.syshub.core.utils.Serializer;

@Singleton
public class SerializerImpl implements Serializer {
	public SerializerImpl() {
	}
	
	private static final Logger log = LoggerFactory.getLogger(SerializerImpl.class);
	/* (non-Javadoc)
	 * @see com.quakearts.syshub.core.utils.Serializer#toByteArray(java.io.Serializable)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see com.quakearts.syshub.core.utils.Serializer#toObject(byte[])
	 */
	@Override
	public Object toObject(byte[] bytes) throws IOException, ClassNotFoundException{
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Object obj = ois.readObject();
		bis.close();
		return obj;
	}
		

}

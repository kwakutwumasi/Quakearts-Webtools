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
package com.quakearts.syshub.core.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;

public abstract class ByteMessageBase {
	private byte[] message=null;
	private byte[] headerbites=new byte[100];
	private byte[] bodybites=new byte[400];
	private byte[] footerbites=new byte[100];
	private boolean readonly=false;
	private boolean secure;
	private int messageLength=0,headercount=0,bodycount=0,footercount=0;
	private String messengerId;
	private final String id=this.hashCode()+"-"+new Date().getTime();
	private List<String> recipients = new ArrayList<String>();
	private Map<String,Serializable> message_props = new HashMap<String,Serializable>();
	private transient Object messageStatus;

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		else if (obj == null)
			return false;
		else if(obj instanceof MessageByteImpl){
			if(this.id == ((Message<?>)obj).getId())
				return true;
			else
				return false;
		} else
			return false;
	}
	
	protected void addRecipientToList(String newrecipient) {
		if(recipients==null)
			recipients = new ArrayList<String>();
		
		if(newrecipient!=null)
			this.recipients.add(newrecipient);
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#appendHeader(byte[])
	 */
	protected void appendHeaderBytes(byte[] bytes) throws ProcessingException {
		if(!readonly){
			int newcount = headercount + bytes.length;
			if(newcount>headerbites.length){
				byte[] newheaderbites = new byte[newcount+100];
				System.arraycopy(headerbites, 0, newheaderbites, 0, headercount);
				headerbites = newheaderbites;
			}
			System.arraycopy(bytes, 0, headerbites, headercount, bytes.length);
			headercount = newcount;
			messageLength+=bytes.length;

		} else
			throw new ProcessingException("Message is in read only mode");
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#appendBody(byte[])
	 */
	protected void appendBodyBytes(byte[] bytes) throws ProcessingException {
		if(!readonly){
			int newcount = bodycount + bytes.length;
			if(newcount>bodybites.length){
				byte[] newbodybites = new byte[newcount+300];
				System.arraycopy(bodybites, 0, newbodybites, 0, bodycount);
				bodybites = newbodybites;
			}
			System.arraycopy(bytes, 0, bodybites, bodycount, bytes.length);
			bodycount = newcount;
			messageLength+=bytes.length;
		} else
			throw new ProcessingException("Message is in read only mode");
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#appendFooter(byte[])
	 */
	protected void appendFooterBytes(byte[] bytes) throws ProcessingException {
		if(!readonly){
			int newcount = footercount + bytes.length;
			if(newcount>footerbites.length){
				byte[] newfooterbites = new byte[newcount+100];
				System.arraycopy(footerbites, 0, newfooterbites, 0, footercount);
				footerbites = newfooterbites;
			}
			System.arraycopy(bytes, 0, footerbites, footercount, bytes.length);
			footercount = newcount;        		
			messageLength+=bytes.length;
		} else
			throw new ProcessingException("Message is in read only mode");
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#getBytes()
	 */
	protected byte[] getBytes() {
		if(!readonly){
			readonly = true;
			setMessage();
		}
		return message;
	}
	
	protected void addPropertyForMessage(String name, Serializable prop) {
		if(message_props == null)
			message_props = new HashMap<String,Serializable>();
		
		message_props.put(name,prop);
	}
	
	protected void setMessage() {
		if(message==null){ 
			if(messageLength > 0){
				message = new byte[messageLength];
				System.arraycopy(headerbites, 0, message, 0, headercount);
				System.arraycopy(bodybites, 0, message, headercount, bodycount);
				System.arraycopy(footerbites, 0, message, headercount+bodycount, footercount);
			}else
				message = new byte[0];
			
			headerbites=null;
			bodybites=null;
			footerbites=null;
		}
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#toString()
	 */
	@Override
	public String toString(){
		try {
			setMessage();
			if(secure){
				return  "*********REDACTED*********";				
			} else
				return new String(message);
		} catch (Exception e){
			return "";
		}
	}

	public Serializable getProperty(String name){
		if(message_props==null)
			return null;
		
		return message_props.get(name);
	}

	public int length(){
		return messageLength;
	}

	public String getId() {
		return id;
	}

	public void setMessengerId(String source) {
		this.messengerId = source;
	}

	public String getMessengerId() {
		return messengerId;
	}
	
	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	public boolean isSecure() {
		return secure;
	}
	
	public synchronized <S> S getMessageStatus(Class<S> expectedType) 
			throws InterruptedException, ConfigurationException {
		if(messageStatus == null)
			wait();
		
		if(messageStatus != null 
				&& expectedType.isAssignableFrom(messageStatus.getClass()))
			throw new ConfigurationException("Invalid return type for messenger. Expected "+expectedType.getName()
			+". messageStatus is "+messageStatus.getClass().getName());
		
		return expectedType.cast(messageStatus);
	}
	
	public synchronized void setMessageStatus(Object messageStatus) {
		this.messageStatus = messageStatus;
		notifyAll();
	}
	
	public String[] getRecipients(){
		if(recipients!=null){
			
			String[] recipientArray = recipients.toArray(new String[recipients.size()]);
			return recipientArray;
		} else {
			return null;
		}
	}

	private void readObject(ObjectInputStream aStream) throws IOException, ClassNotFoundException {
		aStream.defaultReadObject();
	}

	private void writeObject(ObjectOutputStream aStream) throws IOException {
		setMessage();
		aStream.defaultWriteObject();
	}
	
}

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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;

public abstract class ByteMessageBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1899851507231298284L;
	private byte[] message=null;
	private byte[] headerbites=new byte[100];
	private byte[] bodybites=new byte[400];
	private byte[] footerbites=new byte[100];
	private boolean readonly=false;
	private boolean secure;
	private int messageLength=0,headercount=0,bodycount=0,footercount=0;
	private String messengerId;
	private String id;
	private List<String> recipients = new ArrayList<String>();
	private Map<String,Serializable> message_props = new HashMap<String,Serializable>();
	private transient Object messageStatus;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if(message == null) {
			result = prime * result + Arrays.hashCode(bodybites);
			result = prime * result + Arrays.hashCode(footerbites);
			result = prime * result + Arrays.hashCode(headerbites);
		} else {
			result = prime * result + Arrays.hashCode(message);
		}
		
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message_props == null) ? 0 : message_props.hashCode());
		result = prime * result + (readonly ? 1231 : 1237);
		result = prime * result + ((recipients == null) ? 0 : recipients.hashCode());
		result = prime * result + (secure ? 1231 : 1237);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		ByteMessageBase other = (ByteMessageBase) obj;

		if(message == null) {
			if (!Arrays.equals(bodybites, other.bodybites))
				return false;
			if (!Arrays.equals(footerbites, other.footerbites))
				return false;
			if (!Arrays.equals(headerbites, other.headerbites))
				return false;
		} else {
			if (!Arrays.equals(message, other.message))
				return false;
		}
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		if (message_props == null) {
			if (other.message_props != null)
				return false;
		} else if (!message_props.equals(other.message_props))
			return false;
		if (readonly != other.readonly)
			return false;
		if (recipients == null) {
			if (other.recipients != null)
				return false;
		} else if (!recipients.equals(other.recipients))
			return false;
		if (secure != other.secure)
			return false;
		return true;
	}

	protected void addRecipientToList(String newrecipient) {		
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
		if(id == null)
			id = UUID.randomUUID().toString();
		
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
		return recipients.toArray(new String[recipients.size()]);
	}
}

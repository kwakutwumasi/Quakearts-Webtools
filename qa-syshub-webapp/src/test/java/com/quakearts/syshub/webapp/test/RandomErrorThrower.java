package com.quakearts.syshub.webapp.test;

import java.text.MessageFormat;
import java.util.Random;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.syshub.core.event.ParameterEvent;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentModule;

public abstract class RandomErrorThrower {
	private Random random = new Random();
		
	protected void throwErrorOrNot() throws ProcessingException {
		if(Math.abs(random.nextInt()%10) == 1) {
			throw new ProcessingException("Random Exception");
		}
	}
	
	protected Random getRandom() {
		return random;
	}
	
	public abstract AgentModule getAgentModule();
	
	public void handleParameterChanged(ParameterEvent changeEvent) {
		String value;
		try {
			value = CryptoResource.byteAsHex(changeEvent.getSource().getBase64Bytes());
		} catch (ConfigurationException e) {
			value = "";
		}
		
		System.out.println(MessageFormat.format("Event Type:{0}\nName: {1}\nParameter Type: {2}\n"
				+ "Boolean Value: {3}\nNumeric Value: {4}\nString Value: {5}\n"
				+ "Base 64 Value: {6}\nEncrypted Value: {7}\nModule Name: {8}\nAgent Name: {9}\nThis Agent Name:{10}\n", 
				changeEvent.getEventType(),
				changeEvent.getSource().getName(),
				changeEvent.getSource().getParameterType(),
				changeEvent.getSource().getBooleanValue(),
				changeEvent.getSource().getNumericValue(),
				changeEvent.getSource().getStringValue(),
				value, changeEvent.getSource().getEncryptedValue()==null?"null":
				changeEvent.getSource().getEncryptedValue().getStringValue(),
				changeEvent.getSource().getAgentModule()==null?"NONE":changeEvent.getSource().getAgentModule().getModuleName(),
				changeEvent.getSource().getAgentConfiguration().getAgentName(),
				getAgentModule()!=null? getAgentModule().getModuleName():""));
	}
}

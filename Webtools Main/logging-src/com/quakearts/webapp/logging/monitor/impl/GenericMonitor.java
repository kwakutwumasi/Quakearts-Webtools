package com.quakearts.webapp.logging.monitor.impl;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.servlet.ServletConfig;

import com.quakearts.webapp.logging.monitor.MonitorTarget;
import com.quakearts.webapp.logging.monitor.SystemMonitor;

public abstract class GenericMonitor implements SystemMonitor {

	@Override
	public State getState(MonitorTarget monitorTarget) {
		if(monitorTarget.getType()==Type.WEBAPP || monitorTarget.getType()==Type.WEBSERVICE){
			HttpURLConnection connection = null;
			try {
				if(monitorTarget.getObjectIdentifier().startsWith("https")){
					HttpsURLConnection sConnection = (HttpsURLConnection) (new URL(monitorTarget.getObjectIdentifier()).openConnection());
					sConnection.setHostnameVerifier(new HostnameVerifier() {
						
						@Override
						public boolean verify(String hostname, SSLSession session) {
							return true;
						}
					});
					
					connection = sConnection;
				}else
					connection = (HttpURLConnection) (new URL(monitorTarget.getObjectIdentifier()).openConnection());
				
				connection.connect();
				if(connection.getResponseCode()<400)
					return State.UP;
				else
					return State.DOWN;
			} catch (Exception e) {
				System.err.println("["+this.getClass().getName()+"] Exception of type " + e.getClass().getName()
						+ " was thrown. Message is " + e.getMessage()+". Exception occured whiles getting getting state.");
				return State.QUERYERROR;
			} finally {
				try {
					connection.disconnect();
				} catch (Exception e2) {
				}
			}
		} else
			return getServiceState(monitorTarget.getObjectIdentifier());
	}

	@Override
	public void setProperties(ServletConfig config) {
	}
	
	abstract protected State getServiceState(String name);

}

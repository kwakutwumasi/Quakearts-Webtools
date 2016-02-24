package com.quakearts.webapp.facelets.tag.listener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.net.ssl.HttpsURLConnection;

import com.quakearts.webapp.facelets.util.ObjectExtractor;
import com.quakearts.webapp.facelets.util.WebPageBean;

public class HttpURLConnectionListener implements ActionListener {
	private final ValueExpression urlExpression, methodExpression, cookieExpression, targetExpression;
	
	public HttpURLConnectionListener(ValueExpression urlExpression,
			ValueExpression methodExpression, ValueExpression cookieExpression, ValueExpression targetExpression) {
		this.urlExpression = urlExpression;
		this.methodExpression = methodExpression;
		this.cookieExpression = cookieExpression;
		this.targetExpression = targetExpression;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
        HttpURLConnection con;
        FacesContext context = FacesContext.getCurrentInstance();
        String url = ObjectExtractor.extractString(urlExpression, context.getELContext()), 
        method = methodExpression!=null?ObjectExtractor.extractString(methodExpression, context.getELContext()):"GET";
		Collection cookie = cookieExpression!=null?ObjectExtractor.extractCollection(methodExpression, context.getELContext()):null;

        try {
        	con = (HttpURLConnection) (new URL(url)).openConnection();

        	con.setRequestMethod(method);
        	con.setDoOutput(true);
        	HttpsURLConnection.setFollowRedirects(true);
        	con.setUseCaches(false);
        	if(cookie!=null){
        		StringBuffer cookieBuffer = new StringBuffer();
        		boolean isFirst=true;
        		for(Object cookieObj:cookie){
        			if(!isFirst)
        				cookieBuffer.append(",");
        			else
        				isFirst=false;
        			cookieBuffer.append(cookieObj);
        		}
        		con.setRequestProperty("Cookie",cookieBuffer.toString());
        	}
        	
            con.setRequestProperty("User-Agent", this.getClass().getName() );

            con.connect();
        	if(con.getResponseCode()>=200 && con.getResponseCode()<=204){//connection to server was ok. go ahead and and authenticate
        		
        		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        		
        		StringBuffer contentBuffer = new StringBuffer();
        		String line;
        		while((line = reader.readLine())!=null){
        			contentBuffer.append(line);
        		}
        		con.getResponseCode();
        		con.getResponseMessage();
        		
        		String cookieString = con.getHeaderField("set-cookie"); //get the cookie from the last connection.
        		if(cookieString!=null){
        			ArrayList cookieList = new ArrayList();
        			Object[] cookieArray = cookieString.split(";");
        			for(Object obj:cookieArray){
        				cookieList.add(obj);
        			}
        			cookieExpression.setValue(context.getELContext(), cookieList);
        		}
        		
        		targetExpression.setValue(context.getELContext(), new WebPageBean(contentBuffer.toString(),con.getResponseMessage(),con.getResponseCode()));
        	}else{
        		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        		String line;
        		StringBuffer contentBuffer = new StringBuffer();
        		while((line = reader.readLine())!=null){
        			contentBuffer.append(line);
        		}
        		targetExpression.setValue(context.getELContext(), new WebPageBean(contentBuffer.toString(),con.getResponseMessage(),con.getResponseCode()));
        	}
        	con.disconnect();
        } catch (Exception e) {
    		targetExpression.setValue(context.getELContext(), new WebPageBean("Exception getting url "+url,e.getMessage(),-1));
        }
	}

}

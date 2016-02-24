package com.quakearts.workflowapp.jbpm.tag.output;

import java.util.Collection;

import javax.faces.component.UIOutput;

import org.jbpm.JbpmConfiguration;

public class FileDownloadComponent extends UIOutput {
	
	@SuppressWarnings("rawtypes")
	private Collection var; 
	private int width=600, height=400;
	private long tid;
	private String downloadUrl = JbpmConfiguration.Configs.getString("com.quakearts.download.url");;
	
	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	@SuppressWarnings("rawtypes")
	public Collection getVar() {
		return var;
	}

	@SuppressWarnings("rawtypes")
	public void setVar(Collection var) {
		this.var = var;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}
	
	@Override
	public String getFamily(){
		return COMPONENT_FAMILY;
	}
	
	public static final String COMPONENT_FAMILY = "com.quakearts.facelets.output",
	   COMPONENT_TYPE = "com.quakearts.facelets.output.download";

}

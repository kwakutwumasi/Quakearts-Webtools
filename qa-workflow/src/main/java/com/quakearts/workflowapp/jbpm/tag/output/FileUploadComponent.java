package com.quakearts.workflowapp.jbpm.tag.output;

import javax.faces.component.UIOutput;

import org.jbpm.JbpmConfiguration;

public class FileUploadComponent extends UIOutput {
	
	private String var; 
	private int width=600, height=400;
	private long tid;
	
	private String uploadUrl=JbpmConfiguration.Configs.getString("com.quakearts.upload.url");
	
	public String getVar() {
		return var;
	}

	public void setVar(String var) {
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

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public long getTid() {
		return tid;
	}
	
	public String getFamily(){
		return COMPONENT_FAMILY;
	}

	public static final String COMPONENT_FAMILY = "com.quakearts.facelets.output",
	   COMPONENT_TYPE = "com.quakearts.facelets.output.upload";

}

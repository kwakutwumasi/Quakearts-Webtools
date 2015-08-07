package com.quakearts.webapp.facelets.bootstrap.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="loader")
@ViewScoped
public class LoaderBean {
	private static final String MAINAJAXLOADER = "qaboot.mainLoaderHandler";
	private static final String MINIAJAXLOADER = "qaboot.miniLoaderHandler";
	
	public String getMainAjaxLoader() {
		return MAINAJAXLOADER;
	}
	
	public String getMiniAjaxLoader() {
		return MINIAJAXLOADER;
	}
}

package com.quakearts.webapp.facelets.bootstrap.beans;

public class LoaderBean {
	private static final String MAINAJAXLOADER = "qab.mainLoaderHandler";
	private static final String MINIAJAXLOADER = "qab.miniLoaderHandler";
	private static final String OVERLAYAJAXLOADER = "qab.overlayHandler";
	private static final String OVERLAYCLASS = "ajax-container";
	
	
	public String getMainAjaxLoader() {
		return MAINAJAXLOADER;
	}
	
	public String getMiniAjaxLoader() {
		return MINIAJAXLOADER;
	}
	
	public String getOverlayAjaxLoader() {
		return OVERLAYAJAXLOADER;
	}
	
	public String getOverlayClass() {
		return OVERLAYCLASS;
	}
}

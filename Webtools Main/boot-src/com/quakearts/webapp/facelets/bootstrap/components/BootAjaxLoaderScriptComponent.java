package com.quakearts.webapp.facelets.bootstrap.components;

import javax.faces.component.UIOutput;

import com.quakearts.webapp.facelets.bootstrap.components.BootAjaxLoaderComponent;

public class BootAjaxLoaderScriptComponent extends UIOutput {
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.ajaxscriptloader";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.ajaxscriptloader.renderer";

	private BootAjaxLoaderComponent loaderComponent;
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}
	
	@Override
	public void setRendererType(String rendererType) {
	}

	public BootAjaxLoaderComponent getLoaderComponent() {
		return loaderComponent;
	}

	public void setLoaderComponent(BootAjaxLoaderComponent loaderComponent) {
		this.loaderComponent = loaderComponent;
	}
}

package com.quakearts.webapp.facelets.bootstrap.components;

import java.io.IOException;
import java.util.function.Supplier;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class BootOutputComponent extends UIComponentBase {
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.output";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.output.renderer";		
	public static final String VALUE="com.quakearts.bootstrap.output.value";
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void encodeAll(FacesContext context) throws IOException {
		ResponseWriter responseWriter = context.getResponseWriter();
		Supplier<String> supplier = (Supplier<String>) getAttributes().get(VALUE);
		responseWriter.write(supplier.get());
	}
}

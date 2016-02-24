package com.quakearts.webapp.facelets.bootstrap.components;

import java.util.ArrayList;

import javax.faces.component.html.HtmlDataTable;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootTable extends HtmlDataTable {
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.datatable";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.datatable.renderer";
	
	public BootTable() {
		getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", new ArrayList<String>());
	}
	
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
	
	public String get(String attribute) {
		String attributeValue = ObjectExtractor
				.extractString(getValueExpression(attribute), getFacesContext()
						.getELContext());
		if (attributeValue == null)
			attributeValue = (String) getAttributes().get(attribute);

		return attributeValue;
	}
}

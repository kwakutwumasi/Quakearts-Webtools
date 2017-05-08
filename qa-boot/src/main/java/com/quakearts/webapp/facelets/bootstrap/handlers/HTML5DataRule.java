/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.bootstrap.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.MetaRule;
import javax.faces.view.facelets.Metadata;
import javax.faces.view.facelets.MetadataTarget;
import javax.faces.view.facelets.TagAttribute;

public class HTML5DataRule extends MetaRule {

	public static final HTML5DataRule Instance = new HTML5DataRule();
	public static final String HTML5DATAATTRIBUTES = "com.quakearts.bootstrap.DATAATTRIBUTES";
	
	private HTML5DataRule() {
	}
	
	final static class HTML5MetaData extends Metadata {
		String name;
		String value;
		TagAttribute attr;
		Class<?> type;

		public HTML5MetaData(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public HTML5MetaData(String name, TagAttribute attr, Class<?> type) {
			this.name = name;
			this.attr = attr;
			this.type = type;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void applyMetadata(FaceletContext ctx, Object instance) {
			List<String> html5passthru = (List<String>) ((UIComponent)instance).getAttributes().get(HTML5DATAATTRIBUTES);
			if(html5passthru==null){
				html5passthru = new ArrayList<>();
				((UIComponent)instance).getAttributes().put(HTML5DATAATTRIBUTES, html5passthru);
			}
			html5passthru.add(name);
			if(value!=null){
				((UIComponent)instance).getAttributes().put(name, value);
			} else {
				((UIComponent)instance).setValueExpression(name, attr.getValueExpression(ctx, type));
			}			
		}
	}
	
	@Override
	public Metadata applyRule(String name, TagAttribute attribute, MetadataTarget meta) {
		if(name.startsWith("data-")){
			if(attribute.isLiteral()){
				return new HTML5MetaData(name, attribute.getValue());
			} else {
				Class<?> type = meta.getPropertyType(name);
                if (type == null) {
                    type = Object.class;
                }
                return new HTML5MetaData(name, attribute, type);
			}
		}
		return null;
	}

}

/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.bootstrap.common;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

public class BootOnLoadComponent extends BootResourceBase {
	public static final String COMPONENT_FAMILY = "com.quakearts.bootstrap.footer";
	public static final String RENDERER_TYPE = "com.quakearts.bootstrap.footer.renderer";
	public static final String SCRIPTCONTENTLIST = "com.quakearts.bootstrap.SCRIPTCONTENTLIST";

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}
	
	@SuppressWarnings("unchecked")
	public static void addScriptContent(String scriptContent, FacesContext ctx){
		List<String> scriptContentList = (List<String>) ctx.getAttributes()
				.get(SCRIPTCONTENTLIST);

		if(scriptContentList==null){
			scriptContentList = new ArrayList<>();
			ctx.getAttributes().put(SCRIPTCONTENTLIST, scriptContentList);
		}
		
		scriptContentList.add(scriptContent);
	}
}

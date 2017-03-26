package com.quakearts.webapp.facelets.bootstrap.common;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

public class BootOnLoadComponent extends UIComponentBase {
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
			ctx.getViewRoot()
				.addComponentResource(ctx, new BootOnLoadComponent(), "body");
			scriptContentList = new ArrayList<>();
			ctx.getAttributes().put(SCRIPTCONTENTLIST, scriptContentList);
		}
		
		scriptContentList.add(scriptContent);
	}
}

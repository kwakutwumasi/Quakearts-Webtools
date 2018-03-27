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
package com.quakearts.webapp.facelets.bootstrap.handlers;

import java.io.IOException;
import java.util.ListIterator;

import javax.faces.application.ProjectStage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.MetaRuleset;
import com.quakearts.webapp.facelets.bootstrap.common.BootHeaderComponent;
import com.quakearts.webapp.facelets.bootstrap.common.BootOnLoadComponent;
import com.quakearts.webapp.facelets.bootstrap.common.BootResourceBase;

public class BootBaseHandler extends ComponentHandler {
	public static final String BOOT_LIBRARY_LOADED = "com.quakearts.facelets.bootstrap.LIBRARY_LOADED";
	public static final String BOOT_HEADER = "com.quakearts.facelets.bootstrap.HEADER";
	public static final String BOOT_ONLOAD = "com.quakearts.facelets.bootstrap.ONLOAD";
	private static final String[] PARTS = new String[]{"head","body","form"};
	private static final String JSF_LIB = "javax.faces";
	private static final String JSF_JS = "jsf.js"; 
	
	private static boolean jQueryEnabled;
	private static boolean bootstrapEnabled;
	private static boolean respondEnabled;
	private static boolean inJSDebugMode;
	private static boolean started;

	public static final String POSITION_TOP = "top";
	public static final String POSITION_BOTTOM = "bottom";

	public BootBaseHandler(ComponentConfig config) {
		super(config);
		if(!started){
			FacesContext ctx = FacesContext.getCurrentInstance();
			ExternalContext exctx = ctx.getExternalContext();
			jQueryEnabled = !Boolean.parseBoolean(exctx.getInitParameter("com.quakearts.bootstrap.nojquery")); //JQuery loading disabled;
			bootstrapEnabled = !Boolean.parseBoolean(exctx.getInitParameter("com.quakearts.bootstrap.nobootstrap")); //Bootstrap loading disabled;
			respondEnabled = !Boolean.parseBoolean(exctx.getInitParameter("com.quakearts.bootstrap.norespond"));//Disable responds loading
			inJSDebugMode = Boolean.parseBoolean(exctx.getInitParameter("com.quakearts.bootstrap.jsdebug"))
					|| (ctx.isProjectStage(ProjectStage.Development));
			started=true;
		}
	}
	
	@Override
	public void apply(FaceletContext ctx, UIComponent parent)
			throws IOException {
        addBootComponent(ctx.getFacesContext());
        getTagHandlerDelegate().apply(ctx, parent);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected MetaRuleset createMetaRuleset(Class type) {
		return super.createMetaRuleset(type).addRule(HTML5DataRule.Instance);
	}
	
	public static boolean isjQueryEnabled() {
		return jQueryEnabled;
	}
	
	public static boolean isBootstrapEnabled() {
		return bootstrapEnabled;
	}

	public static boolean isRespondEnabled() {
		return respondEnabled;
	}
	
	public static boolean isInJSDebugMode() {
		return inJSDebugMode;
	}
	
	public static void addBootComponent(FacesContext ctx){			
        if(ctx.getAttributes().get(BOOT_LIBRARY_LOADED)==null){
	    		if(ctx.getPartialViewContext().isPartialRequest()){
	    			if(!ctx.getPartialViewContext().getRenderIds().contains("javax.faces.ViewRoot")
	    					&& !ctx.getPartialViewContext().getRenderIds().contains("@all")) {
	    				ctx.getAttributes().put(BOOT_LIBRARY_LOADED, Boolean.TRUE);//Dummy
	    				return;
	    			}
	    		}

            if(!verifiedThatJSFResourceIsInstalledInBody(ctx)){
	            	UIOutput jsfJsComp = new UIOutput();
	            	jsfJsComp.getAttributes().put("name", JSF_JS);
	            	jsfJsComp.getAttributes().put("library", JSF_LIB);
	            	jsfJsComp.setRendererType("javax.faces.resource.Script");
	            	ctx.getViewRoot().addComponentResource(ctx,jsfJsComp,"body");
            }
            
            BootHeaderComponent headerComponent = new BootHeaderComponent();
			ctx.getViewRoot().addComponentResource(ctx, headerComponent);
			ctx.getAttributes().put(BOOT_HEADER, headerComponent);
			
			BootResourceBase onLoadComponent = new BootOnLoadComponent();
			ctx.getViewRoot().addComponentResource(ctx, onLoadComponent, "body");
			ctx.getAttributes().put(BOOT_ONLOAD, onLoadComponent);
			
			ctx.getAttributes().put(BOOT_LIBRARY_LOADED, Boolean.TRUE);
        }
	}

    @SuppressWarnings("rawtypes")
    private static boolean verifiedThatJSFResourceIsInstalledInBody(FacesContext ctx) {
        UIViewRoot viewRoot = ctx.getViewRoot();
        ListIterator iter;
        for(String part:PARTS){
	        iter = (viewRoot.getComponentResources(ctx, part)).listIterator();
	        while (iter.hasNext()) {
	            UIComponent resource = (UIComponent)iter.next();
	            String rname = (String)resource.getAttributes().get("name");
	            String rlibrary = (String)resource.getAttributes().get("library");
	            if (JSF_JS.equals(rname) && JSF_LIB.equals(rlibrary)) {
	            		if(part.equals("body")) {
	            			return true;
	            		} else {
	            			viewRoot.removeComponentResource(ctx, resource, part);
	            			viewRoot.addComponentResource(ctx, resource, "body");
	            			return true;
	            		}
	            }
	        }
        }
        return false;
    }

}

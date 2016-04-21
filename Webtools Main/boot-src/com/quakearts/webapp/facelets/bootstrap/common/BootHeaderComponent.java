package com.quakearts.webapp.facelets.bootstrap.common;

import java.util.ListIterator;

import javax.faces.application.ProjectStage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class BootHeaderComponent extends UIOutput {
	public static final String COMPONENT_FAMILY = "com.quakearts.bootstrap.header";
	public static final String RENDERER_TYPE = "com.quakearts.bootstrap.header.renderer";
	public static final String BOOT_LIBRARY_LOADED = "com.quakearts.facelets.bootstrap.LIBRARY_LOADED";
	public static final String POSITION_TOP = "top";
	public static final String POSITION_BOTTOM = "bottom";
	private static final String[] PARTS = new String[]{"head","body","form"};
	private static final String JSF_LIB = "javax.faces";
	private static final String JSF_JS = "jsf.js"; 
	
	private static boolean jQueryEnabled;
	private static boolean bootstrapEnabled;
	private static boolean respondEnabled;
	private static boolean inJSDebugMode;
	private static boolean started;
	
	public BootHeaderComponent() {
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
	
	private static final String NULLCOMPONENT = "";
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	protected static boolean isjQueryEnabled() {
		return jQueryEnabled;
	}
	
	protected static boolean isBootstrapEnabled() {
		return bootstrapEnabled;
	}

	protected static boolean isRespondEnabled() {
		return respondEnabled;
	}
	
	protected static boolean isInJSDebugMode() {
		return inJSDebugMode;
	}
	
	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}
	
	@Override
	public void setRendererType(String rendererType) {
	}
	
	public void addToTop(UIComponent resource){
		UIComponent component = getFacet(POSITION_TOP);
		if(component==null){
			getFacets().put(POSITION_TOP, resource);
		} else if(component instanceof BootHeaderContainer){
			((BootHeaderContainer)component).getChildren().add(resource);
		} else {
			BootHeaderContainer container = new BootHeaderContainer();
			container.getChildren().add(component);
			container.getChildren().add(resource);
			getFacets().put(POSITION_TOP, container);
		}
	}
	
	public void addToBottom(UIComponent resource){
		UIComponent component = getFacet(POSITION_BOTTOM);
		if(component==null){
			getFacets().put(POSITION_BOTTOM, resource);
		} else if(component instanceof BootHeaderContainer){
			((BootHeaderContainer)component).getChildren().add(resource);
		} else {
			BootHeaderContainer container = new BootHeaderContainer();
			container.getChildren().add(component);
			container.getChildren().add(resource);
			getFacets().put(POSITION_BOTTOM, container);
		}
	}
	
	public static void addBootComponentToHead(FacesContext ctx){			
        if(ctx.getAttributes().get(BOOT_LIBRARY_LOADED)==null){
    		if(ctx.getPartialViewContext().isPartialRequest()){
    			boolean hasall = false;
    			for(String id:ctx.getPartialViewContext().getRenderIds()){
    				if(!"javax.faces.ViewRoot".equals(id) &&
    						!"@all".equals(id));
    				{hasall=true; break;}
    			}
    			
    			if(!hasall){
    				ctx.getAttributes().put(BOOT_LIBRARY_LOADED, NULLCOMPONENT);//Dummy
    				return;
    			}
    		}

        	
            if(!hasJSFResourceBeenInstalled(ctx)){
            	UIOutput jsfJsComp = new UIOutput();
            	jsfJsComp.getAttributes().put("name", JSF_JS);
            	jsfJsComp.getAttributes().put("library", JSF_LIB);
            	jsfJsComp.setRendererType("javax.faces.resource.Script");
            	ctx.getViewRoot().addComponentResource(ctx,jsfJsComp);
            }
            BootHeaderComponent component = new BootHeaderComponent();
			ctx.getViewRoot().addComponentResource(ctx, component);
        	ctx.getAttributes().put(BOOT_LIBRARY_LOADED, component);
        }
	}

    @SuppressWarnings("rawtypes")
    private static boolean hasJSFResourceBeenInstalled(FacesContext ctx) {
        UIViewRoot viewRoot = ctx.getViewRoot();
        ListIterator iter;
        for(String part:PARTS){
	        iter = (viewRoot.getComponentResources(ctx, part)).listIterator();
	        while (iter.hasNext()) {
	            UIComponent resource = (UIComponent)iter.next();
	            String rname = (String)resource.getAttributes().get("name");
	            String rlibrary = (String)resource.getAttributes().get("library");
	            if (JSF_JS.equals(rname) && JSF_LIB.equals(rlibrary)) {
	                return true;
	            }
	        }
        }
        return false;
    }

}

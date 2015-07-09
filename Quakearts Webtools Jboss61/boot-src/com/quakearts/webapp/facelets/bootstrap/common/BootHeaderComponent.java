package com.quakearts.webapp.facelets.bootstrap.common;

import java.util.ListIterator;

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
	
	private boolean jQueryEnabled;
	private boolean bootstrapEnabled;
	private boolean respondEnabled = true;
	
	public BootHeaderComponent() {
		ExternalContext ctx = FacesContext.getCurrentInstance()
				.getExternalContext();
		jQueryEnabled = !Boolean.parseBoolean(ctx.getInitParameter("com.quakearts.bootstrap.nojquery")); //JQuery loading disabled;
		bootstrapEnabled = !Boolean.parseBoolean(ctx.getInitParameter("com.quakearts.bootstrap.nobootstrap")); //Bootstrap loading disabled;
		String respondString;//Respond loading disabled;
		if((respondString = ctx.getInitParameter("com.quakearts.bootstrap.norespond"))!=null){
			respondEnabled = !Boolean.parseBoolean(respondString);
		}
	}
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public boolean isjQueryEnabled() {
		return jQueryEnabled;
	}
	
	public boolean isBootstrapEnabled() {
		return bootstrapEnabled;
	}

	public boolean isRespondEnabled() {
		return respondEnabled;
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

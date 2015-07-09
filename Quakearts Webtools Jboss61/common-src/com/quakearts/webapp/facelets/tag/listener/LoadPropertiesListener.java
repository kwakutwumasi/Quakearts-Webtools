package com.quakearts.webapp.facelets.tag.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;

import com.quakearts.webapp.facelets.tag.BaseListener;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class LoadPropertiesListener extends BaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1700261218149499094L;
	private static final Logger log = Logger.getLogger(LoadPropertiesListener.class);
	private final ValueExpression fileExpression;
	private final ValueExpression varExpression;
	private ValueExpression streamExpression;
	
	public LoadPropertiesListener(ValueExpression fileExpression,
			ValueExpression varExpression, ValueExpression streamExpression) {
		this.fileExpression = fileExpression;
		this.varExpression = varExpression;
		this.streamExpression = streamExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		log.debug("Processing. Event source:"+event.getSource()+"; PhaseId: "+event.getPhaseId());
		ELContext elCtx = ctx.getELContext();
		InputStream is=null;
		try {
			String fileName = ObjectExtractor.extractString(fileExpression,elCtx);
			if(fileName==null){
				Object isObject = streamExpression!=null?streamExpression.getValue(elCtx):null;
				if(isObject instanceof InputStream){
					is = (InputStream) isObject;
				}else{
					log.error("Attribute is null");
					addError("Missing attributes", "Both attributes file and stream cannot be null. file = "+fileName+" and stream = "+isObject, ctx);
					return;
				}
			}else{
				Object servletObject = ctx.getExternalContext().getContext();
				if(servletObject instanceof ServletContext){
					ServletContext srvctx = (ServletContext) servletObject;
					String root = srvctx.getRealPath("/WEB-INF/classes");
					is = new FileInputStream(root+File.separator+fileName);			
				} else {
					log.error("Properties cannot be loaded. Non "+ServletContext.class.getName()+"s are not supported");
					addError("Context type not supported","Non "+ServletContext.class.getName()+"s are not supported", ctx);
					return;
				}
			}
			Properties props = new Properties();
			props.load(is);
			varExpression.setValue(elCtx, props);
			log.debug("Loaded :"+props);
		} catch (IOException e) {
			log.error("Properties cannot be loaded. "+e.getMessage(),e);
			addError("Error", "Properties cannot be loaded. "+e.getMessage(), ctx);
			return;
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
			}
		}
	}

}

package com.quakearts.webapp.facelets.tag.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.logging.Logger;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class SavePropertiesListener extends AbstractFileListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8664364294538590778L;
	private ValueExpression fileNameExpression,propertiesExpression;
	private static final Logger log = Logger.getLogger(SavePropertiesListener.class.getName());
	
	public SavePropertiesListener(ValueExpression fileNameExpression, ValueExpression propertiesExpression, ValueExpression rootExpression) {
		super(rootExpression);
		this.fileNameExpression = fileNameExpression;
		this.propertiesExpression = propertiesExpression;
	}

	@Override
	protected void performFileAction(ActionEvent event, FacesContext ctx, File root) {
		String fileName = ObjectExtractor.extractString(fileNameExpression,ctx.getELContext());
		Object propertiesObject = propertiesExpression.getValue(ctx.getELContext());
		if(propertiesObject==null){
			addError("Null properties submitted", "Properties submitted was null",ctx);
			setOutcome("error");
			return;
		}
		
		Properties properties = (Properties) propertiesObject;
		OutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(root,fileName));
			properties.store(fos, "");
			addMessage("Properties "+fileName+" saved", "Properies file "+fileName+" has been successfully saved.",ctx);
			setOutcome("success");
		} catch (Exception e) {
			log.severe("Error saving properties. "+e.getClass()+". "+ e.getMessage());
			setOutcome("error");
		} finally {
			try {
				fos.close();
			} catch (Exception e2) {
			}
		}
	}

}

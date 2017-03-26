package com.quakearts.webapp.facelets.tag.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.quakearts.webapp.facelets.util.ObjectExtractor;
import javax.faces.view.facelets.FaceletException;

public class SaveFileListener extends AbstractFileListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2934109738234434024L;
	private ValueExpression streamExpression,fileNameExpression, overwriteExpression;
	
	public SaveFileListener(ValueExpression streamExpression,
			ValueExpression fileNameExpression, ValueExpression overwriteExpression, ValueExpression rootExpression) {
		super(rootExpression);
		this.fileNameExpression = fileNameExpression;
		this.overwriteExpression = overwriteExpression;
		this.streamExpression = streamExpression;
	}

	@Override
	protected void performFileAction(ActionEvent event, FacesContext ctx, File root) {
		
		FileOutputStream fos=null;
		
		String filename = ObjectExtractor.extractString(fileNameExpression, ctx.getELContext());
		boolean overwrite = ObjectExtractor.extractBoolean(overwriteExpression, ctx.getELContext());
		
		if(streamExpression == null)
			throw new FaceletException("attribute stream cannot be null");
		
		Object streamObject = streamExpression.getValue(ctx.getELContext());
		if(streamObject == null)
			throw new FaceletException("attribute stream cannot be null");
		else if (! (streamObject instanceof InputStream))
			throw new FaceletException("attribute stream must be a valid InputStream");
		
		InputStream ios = (InputStream) streamObject;
		
		try {
			fos = new FileOutputStream(new File(root,filename),overwrite);
			int read;
			byte[] bites = new byte[2048];
			while((read=ios.read(bites))!=-1)
				fos.write(bites,0,read);
			
		} catch (Exception e) {
			addError("Error creating file", e.getClass()+". "+e.getMessage(),ctx);
		} finally {
			try {
				ios.close();
			} catch (Exception e2) {
			}
			try {
				fos.close();
			} catch (Exception e2) {
			}
		}
		setOutcome("success");
	}

}

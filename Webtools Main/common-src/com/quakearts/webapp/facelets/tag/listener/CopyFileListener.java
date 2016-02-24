package com.quakearts.webapp.facelets.tag.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class CopyFileListener extends AbstractFileListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -312975983390293643L;
	private ValueExpression fileExpression,newFileExpression;

	public CopyFileListener(ValueExpression fileExpression, ValueExpression newFileExpression, ValueExpression rootExpression) {
		super(rootExpression);
		this.fileExpression = fileExpression;
		this.newFileExpression = newFileExpression;
	}

	@Override
	protected void performFileAction(ActionEvent event, FacesContext ctx,File root) {
		String filename = ObjectExtractor.extractString(fileExpression, ctx.getELContext());
		if(filename == null){
			addError("Null filename", "The filename passed in was null",ctx);
			setOutcome("error");
			return;
		}
		String newFilename= ObjectExtractor.extractString(newFileExpression, ctx.getELContext());

		if(newFilename == null){
			addError("Null filename", "The newFilename passed in was null",ctx);
			setOutcome("error");
			return;
		}

		try {
			File file = new File(root,filename);
			File newFile = new File(root,newFilename);
			if(file.exists() && file.isFile() && !file.equals(newFile)) {
				FileInputStream in = new FileInputStream(file);
				FileOutputStream out = new FileOutputStream(newFile);
				try {
					byte[] fileBytes = new byte[2048];
					int read;
					while ((read = in.read(fileBytes))!=-1) {
						out.write(fileBytes, 0, read);
					}
					addMessage("File copied", "File "+filename+" has been copied to "+newFilename,ctx);
					setOutcome("success");
				} catch (Exception e) {
					addError("Error copying file", filename+" could not be copied. "+e.getClass().getName()+": "+e.getMessage(),ctx);
					setOutcome("error");
				} finally {
					try {
						in.close();
					} catch (Exception e2) {
					}
					try {
						out.close();
					} catch (Exception e2) {
					}
				}
			} else
				addError("Error copying file", filename+" could not be copied because it does not exist.",ctx);
				setOutcome("error");
		} catch (Exception e) {
			addError("Error creating file", e.getClass()+". "+e.getMessage(),ctx);
			setOutcome("error");
		}
	}

}

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
package com.quakearts.webapp.facelets.tag.listener;

import java.io.File;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class CreateFileListener extends AbstractFileListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -312975983390293643L;
	private ValueExpression fileExpression;

	public CreateFileListener(ValueExpression fileExpression, ValueExpression rootExpression) {
		super(rootExpression);
		this.fileExpression = fileExpression;
	}

	@Override
	protected void performFileAction(ActionEvent event, FacesContext ctx, File root) {
		String filename = ObjectExtractor.extractString(fileExpression, ctx.getELContext());
		if(filename == null){
			addError("Null filename", "The filename passed in was null",ctx);
			return;
		}
		try {
			File file = new File(root,filename);
			if(file.createNewFile())
				addMessage("File created", "File "+filename+" has been created",ctx);
			else
				addError("Error creating file", filename+" could not be created.",ctx);
		} catch (Exception e) {
			addError("Error creating file", e.getClass()+". "+e.getMessage(),ctx);
		}
		setOutcome("success");
	}

}

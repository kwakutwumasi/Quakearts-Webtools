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
package com.quakearts.webapp.facelets.bootstrap.utils;

import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

public class BootFileUpload {
	private String fileName;
	private byte[] data;
	private Part filePart;
	
	public String getFileName() {
		return fileName;
	}
	
	public byte[] getData() {
		if(data == null){
			data = new byte[(int)filePart.getSize()];
			try(InputStream is=filePart.getInputStream()) {
				is.read(data);
			} catch (IOException e) {
				LOGGER.warning("Exception of type " + e.getClass().getName()
						+ " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles loading input file "+fileName);
			}
		}
		return data;
	}
	
	public Part getFilePart() {
		return filePart;
	}
	
	public BootFileUpload(String fileName, Part filePart) {
		this.fileName = fileName;
		this.filePart = filePart;
	}
		
}

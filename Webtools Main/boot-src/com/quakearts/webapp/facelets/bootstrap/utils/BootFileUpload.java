package com.quakearts.webapp.facelets.bootstrap.utils;

import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

public class BootFileUpload {
	private String fileName;
	private byte[] data;
	
	public String getFileName() {
		return fileName;
	}
	
	public byte[] getData() {
		return data;
	}
	
	public BootFileUpload(String fileName, Part filePart) {
		this.fileName = fileName;
		data = new byte[(int)filePart.getSize()];
		try(InputStream is=filePart.getInputStream()) {
			is.read(data);
		} catch (IOException e) {
			LOGGER.warning("Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles loading input file "+fileName);
		}
	}
		
}

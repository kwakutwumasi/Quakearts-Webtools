package com.quakearts.webapp.facelets.bootstrap.utils;

import javax.servlet.http.Part;

public class BootFileUpload {
	private String fileName;
	private Part filePart;

	public String getFileName() {
		return fileName;
	}
	
	public Part getFilePart() {
		return filePart;
	}
		
	public BootFileUpload(String fileName, Part filePart) {
		this.fileName = fileName;
		this.filePart = filePart;
	}
		
}

package com.quakearts.webapp.facelets.tag.ext;

import java.io.InputStream;
import java.io.OutputStream;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

public class ImageUploadBean {
	private byte[] fileData;
		
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public byte[] getFileData() {
		return fileData;
	}

	private String mime = "image/gif";

	public void listener(FileUploadEvent event) throws Exception{
		UploadedFile item = event.getUploadedFile();
		if(item.getData()==null){
			InputStream in = item.getInputStream();
			fileData = new byte[in.available()];
			in.read(fileData);
		} else {
			fileData = item.getData();
		}
		mime = item.getContentType();
	}
	
	public void paint(OutputStream out, Object value) throws Exception{
		if(fileData == null || fileData.length>1){
			InputStream in = this.getClass().getResourceAsStream("noimage.gif");
			byte[] bites = new byte[in.available()];
			in.read(bites);
			out.write(bites);
		}else{
			out.write(fileData);
		}
	}

	public String getMime() {
		return mime;
	}
}

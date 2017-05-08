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
package com.quakearts.webapp.facelets.util;

import java.io.File;
import java.io.FilenameFilter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.quakearts.webapp.facelets.base.BaseBean;

public class ServerFolderExplorerBean extends BaseBean implements FilenameFilter, Validator {

	/**
	 * 
	 */
	private File rootFolder;
	private File currentFolder;	
	private String fileFilter;
	private String newFolderName;
	private File[] files;
	private static final long serialVersionUID = -8714818829380546946L;
	private static final String FILENAMEPATTERN = "((\\.)?[\\w]+)+";

	public File getRootFolder() {
		return rootFolder;
	}

	public void setRootFolder(File rootFolder) {
		if(rootFolder!=null){
			if(rootFolder.exists()&&rootFolder.isDirectory())
				this.rootFolder = rootFolder;
		}
	}

	public File getCurrentFolder() {
		return currentFolder;
	}

	public void setCurrentFolder(File currentFolder) {
		if(currentFolder!=null){
			if(currentFolder.exists()&&currentFolder.isDirectory())
				this.currentFolder = currentFolder;
		}
	}

	public String getFileFilter() {
		return fileFilter;
	}

	public void setFileFilter(String fileFilter) {
		this.fileFilter = fileFilter;
	}
	
	public File[] getFiles() {
		return files;
	}

	public String getNewFolderName() {
		return newFolderName;
	}

	public void setNewFolderName(String newFolderName) {
		this.newFolderName = newFolderName;
	}

	public void listFolder(ActionEvent event){
		if(currentFolder!=null){
			if(fileFilter!=null){
				files = currentFolder.listFiles(this);				
			} else {
				files = currentFolder.listFiles();
			}
		}
	}

	public void deleteFolder(ActionEvent event){
		StringBuilder builder = new StringBuilder();
		UtilityMethods.delete(currentFolder, builder);
		addMessage("Deleted folder "+currentFolder.getName(), builder.toString(), FacesContext.getCurrentInstance());
	}
	
	public void createFolder(ActionEvent event){
		if(newFolderName!=null){
			File newFile = new File(currentFolder,newFolderName);
			newFile.mkdir();
		}
	}

	@Override
	public boolean accept(File file, String name) {
		if(fileFilter==null)
			return true;
		
		if(name!=null)
			return name.matches(fileFilter);
			
		return false;
	}

	@Override
	public void validate(FacesContext ctx, UIComponent component, Object value)
			throws ValidatorException {
		if(value==null)
			return;
		
		if(!value.toString().matches(FILENAMEPATTERN))
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid folderName", "Illegal characters in string '"+newFolderName+"'"));
	}
}

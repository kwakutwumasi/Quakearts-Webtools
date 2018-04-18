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
package com.quakearts.tools.classloaders.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.quakearts.tools.classloaders.hibernate.JarFile;
import com.quakearts.tools.classloaders.hibernate.JarFileEntry;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.exception.DataStoreException;

public class JarFileStorer {
	
	private String domain;
	private String summary;
	
	public JarFileStorer() {
	}

	public JarFileStorer(String domain) {
		this.domain=domain;
	}
	
	public void storeJarFile(byte[] jarBytes, String jarName) throws DataStoreException, IOException {
		DataStore store;
		if(domain==null)
			store = DataStoreFactory.getInstance().getDataStore();
		else
			store = DataStoreFactory.getInstance().getDataStore(domain);
		
		storeJarFile(jarBytes, jarName, store);
	}
	
	public void storeJarFile(byte[] jarBytes,String jarName, DataStore store) throws DataStoreException, IOException {
		JarFile jarFile = new JarFile();
		jarFile.setJarData(jarBytes);
		jarFile.setJarName(jarName);
		store.save(jarFile);
		store.flushBuffers();
		
		ZipInputStream jarStream = new ZipInputStream(new ByteArrayInputStream(jarBytes));		
		ZipEntry zipEntry;
		
		StringBuilder builder = new StringBuilder("Stored jar file ").append(jarFile.getJarName()).append(". JARID:")
				.append(jarFile.getId()).append(".\nLoading files from jar:\n");

		int savecount=0;
		
		while((zipEntry = jarStream.getNextEntry())!=null){
			if(zipEntry.isDirectory())
				continue;
			
			if(zipEntry.getName().equalsIgnoreCase("META-INF/MANIFEST.MF"))
				continue;
				
			try{
				JarFileEntry duplicateJarFileEntry = (JarFileEntry) store.get(JarFileEntry.class,zipEntry.getName());
				if(duplicateJarFileEntry==null){
					store.save(new JarFileEntry(zipEntry.getName(), jarFile));
					builder.append("Loaded "+zipEntry.getName()).append("\n");
					++savecount;
				} else {
					builder.append(zipEntry.getName()).append(" already exists in database");
					JarFile duplicateFile = duplicateJarFileEntry.getJarFile();
					ZipInputStream duplicateJarStream = new ZipInputStream(new ByteArrayInputStream(duplicateFile.getJarData()));
					ZipEntry duplicateZipEntry = UtilityMethods.findZipEntry(duplicateJarFileEntry.getId(), duplicateJarStream);
					if(duplicateZipEntry == null){
						builder.append(", but its corresponding jar file entry was not found. Replacing with new file.");
						duplicateJarFileEntry.setJarFile(jarFile);
						store.update(duplicateJarFileEntry);
						++savecount;
					} else {
						if(duplicateZipEntry.getTime()<zipEntry.getTime()){
							builder.append(", but it is older than current file. Replacing with current file.");
							duplicateJarFileEntry.setJarFile(jarFile);
							store.update(duplicateJarFileEntry);
							++savecount;
						}else{
							builder.append(", and is newer than the current file. Current file will be skipped.");
						}
					}
					builder.append("\n");
				}
			} catch (IOException e1) {
				try {
					store.clearBuffers();
				} catch (Exception e2) {
				}
				throw new DataStoreException("IOException whiles reading from jar file. Message is "+e1.getMessage()
						+ ". Exception occured whiles loading jar entry "+zipEntry.getName());
			}
		}
		if(savecount==0){
			try {
				store.clearBuffers();
			} catch (Exception e) {
			}
			builder.append("No files were stored.\n");
		}
		summary = builder.toString();
	}

	public JarFile[] cleanOrphanJars() throws DataStoreException, IOException {
		DataStore store;
		if(domain==null)
			store = DataStoreFactory.getInstance().getDataStore();
		else
			store = DataStoreFactory.getInstance().getDataStore(domain);
		return cleanOrphanJars(store);
	}

	public JarFile[] cleanOrphanJars(DataStore store) {
		List<JarFile> files = store.list(JarFile.class, null);
		ArrayList<JarFile> deleteList = new ArrayList<JarFile>();
		for (JarFile file : files) {
			if (file.getJarFileEntries().size() == 0) {
				store.delete(file);
				deleteList.add(file);
			}
		}

		return (JarFile[]) deleteList.toArray(new JarFile[deleteList.size()]);
	}

	public String getSummary() {
		return summary;
	}

	public String getSummary(String newLine) {
		return getSummary().replace("\n", newLine);
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}

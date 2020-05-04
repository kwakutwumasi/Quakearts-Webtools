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
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.quakearts.tools.classloaders.hibernate.JarFile;
import com.quakearts.tools.classloaders.hibernate.JarFileEntry;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.exception.DataStoreException;

/**A utility class for storing jar files. Unzips the jar file, 
 * traverses its contents then adds the class files to persistent storage
 * @author kwakutwumasi-afriyie
 *
 */
public class JarFileStorer {
	
	private String domain;
	private String summary;
	private Set<FileStorageEventListener> eventListeners = Collections.synchronizedSet(new HashSet<>());
	
	/**Default Constructor
	 * 
	 */
	public JarFileStorer() {}

	/**Constructor passes the domain to the underlying {@link com.quakearts.webapp.orm.DataStore DataStore}
	 * @param domain the name of the domain to load
	 */
	public JarFileStorer(String domain) {
		this.domain=domain;
	}
	
	public JarFileStorer addListener(FileStorageEventListener listener) {
		eventListeners.add(listener);
		return this;
	}
	
	/**Store the jar file, represented by the byte array
	 * @param jarBytes the bytes of the jar file to load
	 * @param jarName the name used to save the jar file in the database
	 * @throws DataStoreException if the jar file could not be saved
	 * @throws IOException if there was a problem unzipping the jar contents
	 */
	public void storeJarFile(byte[] jarBytes, String jarName) throws IOException {
		DataStore store;
		if(domain==null)
			store = DataStoreFactory.getInstance().getDataStore();
		else
			store = DataStoreFactory.getInstance().getDataStore(domain);
		
		storeJarFile(jarBytes, jarName, store);
	}
	
	/**Store the jar file, represented by the byte array
	 * @param jarBytes the bytes of the jar file to load
	 * @param jarName the name used to save the jar file in the database
	 * @param store the {@linkplain DataStore} to use for storage
	 * @throws DataStoreException
	 * @throws IOException
	 */
	public void storeJarFile(byte[] jarBytes,String jarName, DataStore store) throws IOException {
		JarFile jarFile = createAndStoreJarFile(jarBytes, jarName, store);
		
		ZipInputStream jarStream = new ZipInputStream(new ByteArrayInputStream(jarBytes));				
		StringBuilder summaryBuilder = new StringBuilder("Stored jar file ").append(jarFile.getJarName()).append(". JARID:")
				.append(jarFile.getId()).append(".\nLoading files from jar:\n");

		int savecount=0;
		
		savecount = iterateOverEntriesAndStore(store, jarFile, jarStream, summaryBuilder, savecount);
		if(savecount==0){
			cancelJarStorage(store, summaryBuilder);
		}
		summary = summaryBuilder.toString();
	}

	private JarFile createAndStoreJarFile(byte[] jarBytes, String jarName, DataStore store) {
		JarFile jarFile = new JarFile();
		jarFile.setJarData(jarBytes);
		jarFile.setJarName(jarName);
		store.save(jarFile);
		store.flushBuffers();
		return jarFile;
	}

	private int iterateOverEntriesAndStore(DataStore store, JarFile jarFile, ZipInputStream jarStream,
			StringBuilder summaryBuilder, int savecount) throws IOException {
		ZipEntry zipEntry;
		while((zipEntry = jarStream.getNextEntry())!=null){
			if(zipEntry.isDirectory() || zipEntry.getName().equalsIgnoreCase("META-INF/MANIFEST.MF"))
				continue;
				
			try {
				JarFileEntry duplicateJarFileEntry = store.get(JarFileEntry.class,zipEntry.getName());
				if(duplicateJarFileEntry==null){
					savecount = storeEntry(store, jarFile, zipEntry, summaryBuilder, savecount);
				} else {
					savecount = testAndStoreDuplicateEntryIfNecessary(store, jarFile, zipEntry, summaryBuilder, savecount,
							duplicateJarFileEntry);
				}
			} catch (IOException e1) {
				cancelJarStorageAndThrowError(store, zipEntry, e1);
			}
		}
		return savecount;
	}

	private int storeEntry(DataStore store, JarFile jarFile, ZipEntry zipEntry, StringBuilder summaryBuilder, int savecount) {
		JarFileEntry jarFileEntry = new JarFileEntry(zipEntry.getName(), jarFile);
		store.save(jarFileEntry);
		fireEvent(new FileStorageEvent(EventType.NEWFILE, jarFileEntry));
		summaryBuilder.append("Loaded "+zipEntry.getName()).append("\n");
		++savecount;
		return savecount;
	}

	private int testAndStoreDuplicateEntryIfNecessary(DataStore store, JarFile jarFile, ZipEntry zipEntry,
			StringBuilder summaryBuilder, int savecount, JarFileEntry duplicateJarFileEntry) throws IOException {
		summaryBuilder.append(zipEntry.getName()).append(" already exists in database");
		JarFile duplicateFile = duplicateJarFileEntry.getJarFile();
		ZipInputStream duplicateJarStream = new ZipInputStream(new ByteArrayInputStream(duplicateFile.getJarData()));
		ZipEntry duplicateZipEntry = UtilityMethods.findZipEntry(duplicateJarFileEntry.getId(), duplicateJarStream);
		if(duplicateZipEntry == null){
			updateEmptyDuplicateEntry(store, jarFile, summaryBuilder, duplicateJarFileEntry);
			++savecount;			
		} else {
			savecount += testDuplicateEntryAndStoreIfNewer(store, jarFile, zipEntry, summaryBuilder,
					duplicateJarFileEntry, duplicateZipEntry);
		}
		summaryBuilder.append("\n");
		return savecount;
	}

	private void updateEmptyDuplicateEntry(DataStore store, JarFile jarFile, StringBuilder summaryBuilder,
			JarFileEntry duplicateJarFileEntry) {
		summaryBuilder.append(", but its corresponding jar file entry was not found. Replacing with new file.");
		duplicateJarFileEntry.setJarFile(jarFile);
		store.update(duplicateJarFileEntry);
		fireEvent(new FileStorageEvent(EventType.UPDATEDFILE, duplicateJarFileEntry));
	}

	private int testDuplicateEntryAndStoreIfNewer(DataStore store, JarFile jarFile, ZipEntry zipEntry,
			StringBuilder summaryBuilder, JarFileEntry duplicateJarFileEntry, ZipEntry duplicateZipEntry) {
		if(duplicateZipEntry.getTime()<zipEntry.getTime()){
			summaryBuilder.append(", but it is older than current file. Replacing with current file.");
			duplicateJarFileEntry.setJarFile(jarFile);
			store.update(duplicateJarFileEntry);
			fireEvent(new FileStorageEvent(EventType.UPDATEDFILE, duplicateJarFileEntry));
			return 1;
		} else {
			summaryBuilder.append(", and is newer than the current file. Current file will be skipped.");
			return 0;
		}
	}

	private void cancelJarStorageAndThrowError(DataStore store, ZipEntry zipEntry, IOException e1) {
		try {
			store.clearBuffers();
		} catch (Exception e2) {
			//Do nothing
		}
		throw new DataStoreException("IOException whiles reading from jar file. Message is "+e1.getMessage()
				+ ". Exception occured whiles loading jar entry "+zipEntry.getName());
	}

	private void cancelJarStorage(DataStore store, StringBuilder summaryBuilder) {
		try {
			store.clearBuffers();
		} catch (Exception e) {
			//Do nothing
		}
		summaryBuilder.append("No files were stored.\n");
	}
	
	/**Utility method to clean out jar files that have no child classes
	 * @return the list of jar files that were deleted
	 * @throws DataStoreException
	 * @throws IOException
	 */
	public JarFile[] cleanOrphanJars() {
		DataStore store;
		if(domain==null)
			store = DataStoreFactory.getInstance().getDataStore();
		else
			store = DataStoreFactory.getInstance().getDataStore(domain);
		return cleanOrphanJars(store);
	}

	/**Utility method to clean out jar files that have no child classes
	 * @param store the {@linkplain DataStore} to use for storage
	 * @return the list of jar files that were deleted
	 */
	public JarFile[] cleanOrphanJars(DataStore store) {
		List<JarFile> files = store.list(JarFile.class);
		ArrayList<JarFile> deleteList = new ArrayList<>();
		for (JarFile file : files) {
			if (file.getJarFileEntries().isEmpty()) {
				store.delete(file);
				deleteList.add(file);
			}
		}

		return deleteList.toArray(new JarFile[deleteList.size()]);
	}

	private void fireEvent(FileStorageEvent event) {
		synchronized (eventListeners) {
			Iterator<FileStorageEventListener> iterator = eventListeners.iterator();
			while (iterator.hasNext()) {
				iterator.next().handle(event);
			}
		}
	}

	/**Return the summary of the file storage action
	 * @return a java.lang.String containing the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**Return the summary of the file storage action, replacing the newline character with the passed in newLine
	 * @param newLine the newline character to use as a replacement
	 * @return a java.lang.String containing the summary
	 */
	public String getSummary(String newLine) {
		return getSummary().replace("\n", newLine);
	}

	/**Getter for domain
	 * @return the configured domain
	 */
	public String getDomain() {
		return domain;
	}

	/**Setter for domain
	 * @param domain the new/updated domain to use
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
}

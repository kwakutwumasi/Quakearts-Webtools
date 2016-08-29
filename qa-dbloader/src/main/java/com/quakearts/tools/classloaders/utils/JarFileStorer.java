package com.quakearts.tools.classloaders.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.quakearts.tools.classloaders.hibernate.JarFile;
import com.quakearts.tools.classloaders.hibernate.JarFileEntry;
import com.quakearts.webapp.hibernate.HibernateHelper;

public class JarFileStorer {
	
	private String domain;
	private String summary;
	
	public JarFileStorer() {
	}

	public JarFileStorer(String domain) {
		this.domain=domain;
	}
	
	public void storeJarFile(byte[] jarBytes, String jarName) throws HibernateException, IOException {
		Session session;
		if(domain==null)
			session = HibernateHelper.getCurrentSession();
		else
			session = HibernateHelper.getSession(domain);
		
		storeJarFile(jarBytes, jarName, session);
	}
	
	public void storeJarFile(byte[] jarBytes,String jarName, Session session) throws HibernateException, IOException {
		JarFile jarFile = new JarFile();
		jarFile.setJarData(jarBytes);
		jarFile.setJarName(jarName);
		session.save(jarFile);
		session.flush();
		
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
				JarFileEntry duplicateJarFileEntry = (JarFileEntry) session.get(JarFileEntry.class,zipEntry.getName());
				if(duplicateJarFileEntry==null){
					session.save(new JarFileEntry(zipEntry.getName(), jarFile));
					builder.append("Loaded "+zipEntry.getName()).append("\n");
					++savecount;
				}else{
					builder.append(zipEntry.getName()).append(" already exists in database");
					JarFile duplicateFile = duplicateJarFileEntry.getJarFile();
					ZipInputStream duplicateJarStream = new ZipInputStream(new ByteArrayInputStream(duplicateFile.getJarData()));
					ZipEntry duplicateZipEntry = UtilityMethods.findZipEntry(duplicateJarFileEntry.getId(), duplicateJarStream);
					if(duplicateZipEntry == null){
						builder.append(", but its corresponding jar file entry was not found. Replacing with new file.");
						duplicateJarFileEntry.setJarFile(jarFile);
						session.update(duplicateJarFileEntry);
						++savecount;
					} else {
						if(duplicateZipEntry.getTime()<zipEntry.getTime()){
							builder.append(", but it is older than current file. Replacing with current file.");
							duplicateJarFileEntry.setJarFile(jarFile);
							session.update(duplicateJarFileEntry);
							++savecount;
						}else{
							builder.append(", and is newer than the current file. Current file will be skipped.");
						}
					}
					builder.append("\n");
				}
			} catch (Exception e) {
				try{
					UtilityMethods.getTransaction().setRollbackOnly();
				}catch (Exception e2) {
				}
				throw new IOException("Exception of type " + e.getClass().getName()
						+ " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles loading jar entry "+zipEntry.getName());
			}
		}
		if(savecount==0){
			try {
				UtilityMethods.getTransaction().setRollbackOnly();
			} catch (Exception e) {
			}
			builder.append("No files were stored.\n");
		}
		summary = builder.toString();
	}

	public JarFile[] cleanOrphanJars() throws HibernateException, IOException {
		Session session;
		if (this.domain == null) {
			session = HibernateHelper.getCurrentSession();
		} else {
			session = HibernateHelper.getSession(this.domain);
		}
		return cleanOrphanJars(session);
	}

	@SuppressWarnings("unchecked")
	public JarFile[] cleanOrphanJars(Session session) {
		java.util.List<JarFile> files = session.createCriteria(JarFile.class)
				.list();
		ArrayList<JarFile> deleteList = new ArrayList<JarFile>();
		for (JarFile file : files) {
			if (file.getJarFileEntries().size() == 0) {
				session.delete(file);
				deleteList.add(file);
			}
		}

		return (JarFile[]) deleteList.toArray(new JarFile[deleteList.size()]);
	}

	public String getSummary() {
		return summary;
	}

	public String getSummary(String newLine) {
		return summary.replace("\n", newLine);
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}

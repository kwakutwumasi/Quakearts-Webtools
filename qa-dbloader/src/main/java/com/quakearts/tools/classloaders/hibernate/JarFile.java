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
package com.quakearts.tools.classloaders.hibernate;

// Generated 17-Mar-2014 00:13:24 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JarFile generated by hbm2java
 */
@Entity
@Table(name="jar_files")
public class JarFile implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 520678837264493311L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="jid")
	private long id;
	@Column(nullable=false, name="jar_data")
	private byte[] jarData;
	@Column(nullable=false, name="jar_name")
	private String jarName;
	@OneToMany(mappedBy="jarFile")
	private Set<JarFileEntry> jarFileEntries = new HashSet<JarFileEntry>(0);

	public JarFile() {
	}

	public JarFile(byte[] jarData) {
		this.jarData = jarData;
	}

	public JarFile(byte[] jarData, String jarName, Set<JarFileEntry> jarFileEntries) {
		this.jarData = jarData;
		this.jarName = jarName;
		this.jarFileEntries = jarFileEntries;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getJarData() {
		return this.jarData;
	}

	public void setJarData(byte[] jarData) {
		this.jarData = jarData;
	}

	public Set<JarFileEntry> getJarFileEntries() {
		return this.jarFileEntries;
	}

	public void setJarFileEntries(Set<JarFileEntry> jarFileEntries) {
		this.jarFileEntries = jarFileEntries;
	}

	public String getJarName() {
		return jarName;
	}

	public void setJarName(String jarName) {
		this.jarName = jarName;
	}
}

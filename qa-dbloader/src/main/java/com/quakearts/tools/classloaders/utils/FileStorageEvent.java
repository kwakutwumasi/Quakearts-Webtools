package com.quakearts.tools.classloaders.utils;

import java.io.Serializable;

import com.quakearts.tools.classloaders.hibernate.JarFileEntry;

public class FileStorageEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4856333992739985851L;

	public FileStorageEvent(EventType type, JarFileEntry jarFileEntry) {
		this.type = type;
		this.jarFileEntry = jarFileEntry;
	}

	private EventType type;
	private JarFileEntry jarFileEntry;

	public EventType getType() {
		return type;
	}

	public JarFileEntry getJarFileEntry() {
		return jarFileEntry;
	}

}

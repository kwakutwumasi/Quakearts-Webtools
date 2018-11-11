package com.quakearts.tools.classloaders.utils;

@FunctionalInterface
public interface FileStorageEventListener {
	void handle(FileStorageEvent event);
}

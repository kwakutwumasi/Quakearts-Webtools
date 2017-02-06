package com.quakearts.classpathscanner.resource;

import java.io.InputStream;

/**
 * Interface for Resource Iterator, usually files.
 * 
 * @author animesh.kumar
 */
public interface ResourceIterator {

    /**
     * Please close after use.
     * 
     * @return null if no more streams left to iterate on
     */
    InputStream next();

    /**
     * Close.
     */
    void close();
}

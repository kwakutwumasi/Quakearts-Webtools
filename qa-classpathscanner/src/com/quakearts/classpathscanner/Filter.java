package com.quakearts.classpathscanner;

/**
 * Interface to filter out filenames.
 * 
 * @author animesh.kumar
 */
public interface Filter {

    /**
     * If true, the file is accepted, else rejected.
     */
    boolean accepts(String filename);
}

package com.quakearts.classpathscanner.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.quakearts.classpathscanner.Filter;
import com.quakearts.classpathscanner.exception.ScannerRuntimeException;

/**
 * The Class ClassFileIterator.
 * 
 * @author animesh.kumar
 */
public class ClassFileIterator implements ResourceIterator {

    /** files. */
    private List<File> files;
    
    private File rootDir;

    /** The index. */
    private int index = 0;

    /**
     * Instantiates a new class file iterator.
     * 
     * @param file
     * @param filter
     */
    public ClassFileIterator(File file, Filter filter) {
        files = new ArrayList<File>();
        if(file.isDirectory())
        	rootDir = file;
        else
        	throw new ScannerRuntimeException("ClassFileIterator requires a directory to traverse");
        
        try {
        	init(files, file, filter);
        } catch (Exception e) {
            throw new ScannerRuntimeException(e);
        }
    }

    // helper method to initialize the iterator
    private void init(List<File> list, File dir, Filter filter) throws Exception {
        File[] files = dir.listFiles();
        for (File file:files) {
            if (file.isDirectory()) {
            	init(list, file, filter);
            } else {
            	String fileName = file.getAbsolutePath().substring(rootDir.getAbsolutePath().length());
            	
                if (filter == null || filter.accepts(fileName)) {
                    list.add(file);
                }
            }
        }
    }

    /* @see com.quakearts.annovention.resource.ResourceIterator#next() */
    @Override
    public final InputStream next() {
        if (index >= files.size()){
            return null;
        }
        File fp = (File) files.get(index++);
        try {
            return new FileInputStream(fp);
        } catch (FileNotFoundException e) {
            throw new ScannerRuntimeException(e);
        }
    }

    /* @see com.quakearts.annovention.resource.ResourceIterator#close() */
    @Override
    public void close() {
    	// DO Nothing
    }
}

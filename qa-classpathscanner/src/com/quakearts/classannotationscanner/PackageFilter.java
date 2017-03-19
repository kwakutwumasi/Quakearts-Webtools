package com.quakearts.classannotationscanner;

import java.io.File;

public class PackageFilter implements Filter {

	private String scanPackage;
	
    public PackageFilter(String scanPackage) {
		this.scanPackage = scanPackage;
	}

	/* (non-Javadoc)
     * @see com.quakearts.classpathscanner.Filter#accepts(java.lang.String)
     */
    @Override
    public final boolean accepts(String name) {
        if (name.endsWith(".class")) {
        	String separator = name.contains(File.separator)?File.separator:"/";
            if (name.startsWith(separator)) {
                name = name.substring(1);
            }
            return shouldScan(name.replace(separator, "."));
        }
        return false;
    }

    /**Ignore the package name passed in
     * @param packageName The package name to check
     * @return true of the package should be scanned
     */
    private boolean shouldScan(String packageName) {
        if (packageName.startsWith(scanPackage + ".")) {
            return true;
        }
        return false;
    }

}

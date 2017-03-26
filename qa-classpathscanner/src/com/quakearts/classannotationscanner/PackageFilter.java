package com.quakearts.classannotationscanner;

public class PackageFilter extends DefaultFilter {

	private String scanPackage;
	
    public PackageFilter(String scanPackage) {
		this.scanPackage = scanPackage;
	}


    /* (non-Javadoc)
     * @see com.quakearts.classannotationscanner.DefaultFilter#shouldScan(java.lang.String)
     */
    @Override
    protected boolean shouldScan(String packageName) {
        if (packageName.startsWith(scanPackage + ".")) {
            return true;
        }
        return false;
    }
}

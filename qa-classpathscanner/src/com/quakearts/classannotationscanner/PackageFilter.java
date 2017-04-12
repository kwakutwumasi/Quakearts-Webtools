package com.quakearts.classannotationscanner;

public class PackageFilter extends DefaultFilter {

	private String[] scanPackages;
	
    public PackageFilter(String... scanPackages) {
		this.scanPackages = scanPackages;
	}


    /* (non-Javadoc)
     * @see com.quakearts.classannotationscanner.DefaultFilter#shouldScan(java.lang.String)
     */
    @Override
    protected boolean shouldScan(String packageName) {
    	for(String scanPackage:scanPackages){
	        if (packageName.startsWith(scanPackage + ".")) {
	            return true;
	        }
    	}
        return false;
    }
}

package com.quakearts.classannotationscanner;

import java.io.File;

/**Default implementation of the {@link Filter} interface. Skips packages from well known vendors.
 * @author Kwaku Twumasi Afriyie (kwaku.twumasi@quakearts.com)
 *
 */
public class DefaultFilter implements Filter {
    /**Default set of packages to ignore
     * 
     */
    public static final String[] IGNORED_PACKAGES = {
        "java", "javax",
        "sun", "com.sun",
        "apple", "com.apple",
        "org.apache","org.w3c",
        "com.oracle","oracle",
        "javafx","jdk","org.eclipse",
        "com.mysql","org.jboss",
        "org.objectweb","bsh",
        "com.wutka","org.hibernate",
        "org.hornetq","org.hsqldb",
        "org.jaxen","org.jnp",
        "org.opennms","org.quartz",
        "org.slf4j","com.ibm",
        "org.jcp","EDU.oswego",
        "org.dom4j","javassist",
        "org.kohsuke","org.relaxng",
        "gnu.trove","org.infinispan",
        "com.microsoft","microsoft",
        "org.jvnet","com.ctc",
        "org.codehaus","com.google",
        "org.yaml","org.scannotation",
        "biz.source_code.base64Coder",
        "scalaj", "scala.tools.jline", "org.scala_tools.time",
        "javassist", "com.quakearts.classpathscanner"
    };

    /**List of packages to ignore
     * 
     */
    private String[] ignoredPackages;

	/**Default constructors. Uses the default list of packages to ignore
	 * 
	 */
	public DefaultFilter() {
		this.ignoredPackages = IGNORED_PACKAGES;
	}

	/**Constructor for providing a custom list of packages to ignore
	 * @param ignoredPackages
	 */
	public DefaultFilter(String[] ignoredPackages) {
		this.ignoredPackages = ignoredPackages;
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
            if (!ignoreScan(name.replace(separator, "."))) {
                return true;
            }
        }
        return false;
    }

    /**Ignore the package name passed in
     * @param packageName The package name to check
     * @return true of the package should be scanned
     */
    private boolean ignoreScan(String packageName) {
        for (String ignored : ignoredPackages) {
            if (packageName.startsWith(ignored + ".")) {
                return true;
            }
        }
        return false;
    }
}

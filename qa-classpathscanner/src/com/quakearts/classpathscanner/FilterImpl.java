package com.quakearts.classpathscanner;

import java.io.File;

/**
 * Basic implementation to skip well-known packages and allow only *.class files
 * 
 * @author animesh.kumar, ngocdaothanh@gmail.com
 */
public class FilterImpl implements Filter {
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

    private String[] ignoredPackages;

    public FilterImpl()                         { this.ignoredPackages = IGNORED_PACKAGES; }
    public FilterImpl(String[] ignoredPackages) { this.ignoredPackages = ignoredPackages;  }

    /* @see com.quakearts.annovention.Filter#accepts(java.lang.String) */
    @Override
    public final boolean accepts(String filename) {
        if (filename.endsWith(".class")) {
        	String separator = filename.contains(File.separator)?File.separator:"/";
            if (filename.startsWith(separator)) {
                filename = filename.substring(1);
            }
            if (!ignoreScan(filename.replace(separator, "."))) {
                return true;
            }
        }
        return false;
    }

    private boolean ignoreScan(String intf) {
        for (String ignored : ignoredPackages) {
            if (intf.startsWith(ignored + ".")) {
                return true;
            }
        }
        return false;
    }
}

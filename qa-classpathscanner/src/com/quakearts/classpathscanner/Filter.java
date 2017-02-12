package com.quakearts.classpathscanner;

/**An interface for implementing a filter to skip over class files while scanning
 * @author Kwaku Twumasi Afriyie (kwaku.twumasi@quakearts.com)
 *
 */
public interface Filter {

    /**Tests whether the scanner accepts the resource with the given name
     * @param name The name to test
     * @return true if the resource should be scanned
     */
    boolean accepts(String name);
}

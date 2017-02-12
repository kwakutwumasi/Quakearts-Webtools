package com.quakearts.classpathscanner.resource;

import java.io.InputStream;

/**Interface for iterating over a set of resources
 * @author Kwaku Twumasi Afriyie (kwaku.twumasi@quakearts.com)
 *
 */
public interface ResourceInputStreamIterator {

    /**Moves to the next resource
     * @return The {@link InputStream} of the resource. Null if there are no more resources
     */
    InputStream next();
    /**Cleans up and frees expensive resources
     * 
     */
    void close();
}

package com.quakearts.classannotationscanner;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.quakearts.classannotationscanner.exception.ScannerRuntimeException;
import com.quakearts.classannotationscanner.resource.ResourceInputStreamIterator;

public abstract class URLResourceScanner extends ScannerImplBase {

	/**Get an array of {@link URL}s to scan
	 * @return the array of {@link URL}s
	 */
	protected abstract URL[] findResources();

    /* (non-Javadoc)
	 * @see com.quakearts.classpathscanner.Scanner#scan()
	 */
    @Override
	public final void scan() {
    	long starttime = System.nanoTime();
        URL[] resources = findResources();
        for (URL resource : resources) {
            try {
                ResourceInputStreamIterator itr = getResourceIterator(resource, getFilter());
                if (itr != null) {
                    InputStream is = null;
                    while ((is = itr.next()) != null) {
                       processInputStream(is);
                    }
                }
            } catch (IOException e) {
                throw new ScannerRuntimeException(e);
            }
        }
    	log.info("Annotation scanning completed in "+((System.nanoTime()-starttime)/1000000)+" ms");
    }

    protected abstract ResourceInputStreamIterator getResourceIterator(URL url, Filter filter) throws IOException;
}

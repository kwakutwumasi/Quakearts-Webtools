package org.jboss.gravel.common.ui;

import org.jboss.gravel.common.annotation.TldAttribute;

/**
 *
 */
public interface SizeLimitedInput extends Input {

    @TldAttribute(
        description = "The maximum number of bytes of RAM that may be consumed by this value.  For " +
            "target types that do not support spooling to disk, the size of the request parameter value will not be " +
            "allowed to exceed this value.  If not specified, the default value given in the context initialization " +
            "parameter code>gravel.FileInput.MAX_MEMORY_SIZE</code>.  If that value is not given, the default is 512 bytes."
    )
    long getMaxMemorySize();

    @TldAttribute(
        description = "The maximum number of bytes allowed for upload.  If not specified, the default " +
            "value given in the context initialization parameter <code>gravel.FileInput.MAX_SIZE</code>.  If that " +
            "value is not given, the default is 256KiB."
    )
    long getMaxSize();

}

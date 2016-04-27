package org.jboss.gravel.common.util;

import java.util.Map;

/**
 *
 */
public interface NodeMap extends Map<String, NodeMap> {
    boolean hasNodeValue();

    String getNodeValue();

    void setNodeValue(String value);

    void lock();

    /**
     * Return the node value, or {@code null} if there is none.
     *
     * @return the node value
     */
    String toString();
}

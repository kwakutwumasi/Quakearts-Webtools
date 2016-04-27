package org.jboss.gravel.common.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public final class NodeHashMap implements NodeMap {
    private Map<String, NodeMap> nestedMap;
    private String nodeValue;
    private boolean locked = false;

    public NodeHashMap() {
        nestedMap = new HashMap<String, NodeMap>();
    }

    public NodeHashMap(int initialCapacity) {
        nestedMap = new HashMap<String, NodeMap>(initialCapacity);
    }

    public NodeHashMap(int initialCapacity, float loadFactor) {
        nestedMap = new HashMap<String, NodeMap>(initialCapacity, loadFactor);
    }

    public boolean hasNodeValue() {
        return nodeValue != null;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public void lock() {
        if (! locked) {
            for (NodeMap nodeMap : values()) {
                nodeMap.lock();
            }
            nestedMap = Collections.unmodifiableMap(nestedMap);
            locked = true;
        }
    }

    public int size() {
        return nestedMap.size();
    }

    public boolean isEmpty() {
        return nestedMap.isEmpty();
    }

    public boolean containsKey(final Object key) {
        return nestedMap.containsKey(key);
    }

    public boolean containsValue(final Object value) {
        return nestedMap.containsValue(value);
    }

    public NodeMap get(final Object key) {
        return nestedMap.get(key);
    }

    public NodeMap put(final String key, final NodeMap value) {
        return nestedMap.put(key, value);
    }

    public NodeMap remove(final Object key) {
        return nestedMap.remove(key);
    }

    public void putAll(final Map<? extends String, ? extends NodeMap> m) {
        nestedMap.putAll(m);
    }

    public void clear() {
        nestedMap.clear();
    }

    public Set<String> keySet() {
        return nestedMap.keySet();
    }

    public Collection<NodeMap> values() {
        return nestedMap.values();
    }

    public Set<Entry<String, NodeMap>> entrySet() {
        return nestedMap.entrySet();
    }

    public String toString() {
        return nodeValue;
    }
}

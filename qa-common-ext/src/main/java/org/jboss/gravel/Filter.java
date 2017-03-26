package org.jboss.gravel;

/**
 * A collection filter.  Used to filter a data set into a smaller dataset.
 */
public interface Filter {
    /**
     * Test to see if an item passes the filter.
     *
     * @param entry the item to test
     * @return {@code true} if the item passes the filter criteria
     */
    boolean testEntry(Object entry);
}

package org.jboss.gravel.common.util;

import java.util.Iterator;

/**
 *
 */
public final class DelimitedStringList implements Iterable<String> {
    private final String string;
    private final char delimiter;

    public DelimitedStringList(final String string) {
        this.string = string;
        delimiter = ',';
    }

    public DelimitedStringList(final String string, final char delimiter) {
        this.string = string;
        this.delimiter = delimiter;
    }

    public Iterator<String> iterator() {
        return new StringListIterator();
    }

    private final class StringListIterator implements Iterator<String> {
        private int pos = 0;
        private String next;

        public boolean hasNext() {
            queueNext();
            return next != null;
        }

        private void queueNext() {
            if (pos == -1 || next != null) {
                return;
            }
            final int idx = string.indexOf(delimiter, pos);
            if (idx == -1) {
                next = string.substring(pos).trim();
                if (next.length() == 0) {
                    next = null;
                }
                pos = -1;
            } else {
                next = string.substring(pos, idx).trim();
                pos = idx + 1;
            }
        }

        public String next() {
            queueNext();
            try {
                return next;
            } finally {
                next = null;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("remove() not allowed");
        }
    }
}

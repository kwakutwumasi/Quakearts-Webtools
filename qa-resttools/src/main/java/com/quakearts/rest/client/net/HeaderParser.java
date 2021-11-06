/*******************************************************************************
* Copyright (C) 2021 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.rest.client.net;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HeaderParser {

    String raw;
    String[][] table;
    int nkeys;
    int asize = 10; 
    
    public HeaderParser(String raw) {
        this.raw = raw;
        table = new String[asize][2];
        parse();
    }

    private HeaderParser () {}

    public HeaderParser subsequence (int start, int end) {
        if (start == 0 && end == nkeys) {
            return this;
        }
        if (start < 0 || start >= end || end > nkeys)
            throw new IllegalArgumentException ("invalid start or end");
        HeaderParser n = new HeaderParser ();
        n.table = new String [asize][2];
        n.asize = asize;
        System.arraycopy (table, start, n.table, 0, (end-start));
        n.nkeys= (end-start);
        return n;
    }

    private void parse() {
        if (raw != null) {
            raw = raw.trim();
            char[] ca = raw.toCharArray();
            int beg = 0;
            int end = 0;
            int i = 0;
            boolean inKey = true;
            boolean inQuote = false;
            int len = ca.length;
            while (end < len) {
                char c = ca[end];
                if ((c == '=') && !inQuote) {
                    table[i][0] = new String(ca, beg, end-beg).toLowerCase();
                    inKey = false;
                    end++;
                    beg = end;
                } else if (c == '\"') {
                    if (inQuote) {
                        table[i++][1]= new String(ca, beg, end-beg);
                        inQuote=false;
                        do {
                            end++;
                        } while (end < len && (ca[end] == ' ' || ca[end] == ','));
                        inKey=true;
                        beg=end;
                    } else {
                        inQuote=true;
                        end++;
                        beg=end;
                    }
                } else if (c == ' ' || c == ',') {
                    if (inQuote) {
                        end++;
                        continue;
                    } else if (inKey) {
                        table[i++][0] = (new String(ca, beg, end-beg)).toLowerCase();
                    } else {
                        table[i++][1] = (new String(ca, beg, end-beg));
                    }
                    while (end < len && (ca[end] == ' ' || ca[end] == ',')) {
                        end++;
                    }
                    inKey = true;
                    beg = end;
                } else {
                    end++;
                }
                if (i == asize) {
                    asize = asize * 2;
                    String[][] ntab = new String[asize][2];
                    System.arraycopy (table, 0, ntab, 0, table.length);
                    table = ntab;
                }
            }

            if (--end > beg) {
                if (!inKey) {
                    if (ca[end] == '\"') {
                        table[i++][1] = (new String(ca, beg, end-beg));
                    } else {
                        table[i++][1] = (new String(ca, beg, end-beg+1));
                    }
                } else {
                    table[i++][0] = (new String(ca, beg, end-beg+1)).toLowerCase();
                }
            } else if (end == beg) {
                if (!inKey) {
                    if (ca[end] == '\"') {
                        table[i++][1] = String.valueOf(ca[end-1]);
                    } else {
                        table[i++][1] = String.valueOf(ca[end]);
                    }
                } else {
                    table[i++][0] = String.valueOf(ca[end]).toLowerCase();
                }
            }
            nkeys=i;
        }

    }

    public String findKey(int i) {
        if (i < 0 || i > asize)
            return null;
        return table[i][0];
    }

    public String findValue(int i) {
        if (i < 0 || i > asize)
            return null;
        return table[i][1];
    }

    public String findValue(String key) {
        return findValue(key, null);
    }

    public String findValue(String k, String defaultValue) {
        if (k == null)
            return defaultValue;
        k = k.toLowerCase();
        for (int i = 0; i < asize; ++i) {
            if (table[i][0] == null) {
                return defaultValue;
            } else if (k.equals(table[i][0])) {
                return table[i][1];
            }
        }
        return defaultValue;
    }

    class ParserIterator implements Iterator<String> {
        int index;
        boolean returnsValue;

        ParserIterator (boolean returnValue) {
            returnsValue = returnValue;
        }
        
        public boolean hasNext () {
            return index<nkeys;
        }
        
        @Override
        public String next () {
        	if(index>=table.length)
        		throw new NoSuchElementException("Index exceeds tab length");
        	
            return table[index++][returnsValue?1:0];
        }
        
        @Override
        public void remove () {
            throw new UnsupportedOperationException ("remove not supported");
        }
    }

    public Iterator<String> keys () {
        return new ParserIterator (false);
    }

    public Iterator<String> values () {
        return new ParserIterator (true);
    }

    public String toString () {
        Iterator<String> k = keys();
        StringBuilder sbuf = new StringBuilder();
        sbuf.append ("{size="+asize+" nkeys="+nkeys+" ");
        for (int i=0; k.hasNext(); i++) {
            String key = k.next();
            String val = findValue (i);
            if (val != null && "".equals (val)) {
                val = null;
            }
            sbuf.append (" {"+key+(val==null?"":","+val)+"}");
            if (k.hasNext()) {
                sbuf.append (",");
            }
        }
        sbuf.append (" }");
        return new String (sbuf);
    }

    public int findInt(String k, int defaultValue) {
        try {
            return Integer.parseInt(findValue(k, String.valueOf(defaultValue)));
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

}

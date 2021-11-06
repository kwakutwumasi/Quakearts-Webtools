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

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class MessageHeader {
	private String[] keys;
	private String[] values;
	private int nkeys;

	public MessageHeader() {
		grow();
	}

	public synchronized void reset() {
		keys = null;
		values = null;
		nkeys = 0;
		grow();
	}

	public synchronized String findValue(String k) {
		if (k == null) {
			for (int i = nkeys; --i >= 0;)
				if (keys[i] == null)
					return values[i];
		} else
			for (int i = nkeys; --i >= 0;) {
				if (k.equalsIgnoreCase(keys[i]))
					return values[i];
			}
		return null;
	}

	public synchronized int getKey(String k) {
		for (int i = nkeys; --i >= 0;)
			if ((keys[i] == null && k == null) || (k != null && k.equalsIgnoreCase(keys[i])))
				return i;
		return -1;
	}

	public synchronized String getKey(int n) {
		if (n < 0 || n >= nkeys)
			return null;
		return keys[n];
	}

	public synchronized String getValue(int n) {
		if (n < 0 || n >= nkeys)
			return null;
		return values[n];
	}

	class HeaderIterator implements Iterator<String> {
		int index = 0;
		int next = -1;
		String key;
		boolean haveNext = false;
		Object lock;

		public HeaderIterator(String k, Object lock) {
			key = k;
			this.lock = lock;
		}

		@Override
		public boolean hasNext() {
			synchronized (lock) {
				if (haveNext) {
					return true;
				}
				while (index < nkeys) {
					if (key.equalsIgnoreCase(keys[index])) {
						haveNext = true;
						next = index++;
						return true;
					}
					index++;
				}
				return false;
			}
		}

		@Override
		public String next() {
			synchronized (lock) {
				if (haveNext) {
					haveNext = false;
					return values[next];
				}
				if (hasNext()) {
					return next();
				} else {
					throw new NoSuchElementException("No more elements");
				}
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove not allowed");
		}
	}

	public Iterator<String> multiValueIterator(String k) {
		return new HeaderIterator(k, this);
	}

	public synchronized Map<String, List<String>> getHeaders() {
		return getHeaders(null);
	}

	public synchronized Map<String, List<String>> getHeaders(String[] excludeList) {
		return filterAndAddHeaders(excludeList, null);
	}

	public synchronized Map<String, List<String>> filterAndAddHeaders(String[] excludeList,
			Map<String, List<String>> include) {
		Map<String, List<String>> m = new HashMap<>();
		filterExclude(excludeList, m);

		filterInclude(include, m);

		setUnmodifiable(m);

		return Collections.unmodifiableMap(m);
	}

	private void filterExclude(String[] excludeList, Map<String, List<String>> m) {
		for (int i = nkeys; --i >= 0;) {
			if (!checkExclude(excludeList, i)) {
				List<String> l = m.get(keys[i]);
				if (l == null) {
					l = new ArrayList<>();
					m.put(keys[i], l);
				}
				l.add(values[i]);
			}
		}
	}

	private boolean checkExclude(String[] excludeList, int i) {
		boolean skipIt = false;
		if (excludeList != null) {
			for (int j = 0; j < excludeList.length; j++) {
				if ((excludeList[j] != null) && (excludeList[j].equalsIgnoreCase(keys[i]))) {
					skipIt = true;
					break;
				}
			}
		}
		return skipIt;
	}

	private void filterInclude(Map<String, List<String>> include, Map<String, List<String>> m) {
		if (include != null) {
			for (Map.Entry<String, List<String>> entry : include.entrySet()) {
				List<String> l = m.get(entry.getKey());
				if (l == null) {
					l = new ArrayList<>();
					m.put(entry.getKey(), l);
				}
				l.addAll(entry.getValue());
			}
		}
	}

	private void setUnmodifiable(Map<String, List<String>> m) {
		for(Entry<String, List<String>> entry : m.entrySet()) {
			entry.setValue(Collections.unmodifiableList(entry.getValue()));
		}
	}

	private boolean isRequestline(String line) {
		String k = line.trim();
		int i = k.lastIndexOf(' ');
		if (i <= 0)
			return false;
		int len = k.length();
		if (len - i < 9)
			return false;

		char c1 = k.charAt(len - 3);
		char c2 = k.charAt(len - 2);
		char c3 = k.charAt(len - 1);
		if (c1 < '1' || c1 > '9')
			return false;
		if (c2 != '.')
			return false;
		if (c3 < '0' || c3 > '9')
			return false;

		return (k.substring(i + 1, len - 3).equalsIgnoreCase("HTTP/"));
	}

	public synchronized void print(PrintStream p) {
		for (int i = 0; i < nkeys; i++)
			if (keys[i] != null) {
				StringBuilder sb = new StringBuilder(keys[i]);
				if (values[i] != null) {
					sb.append(": " + values[i]);
				} else if (i != 0 || !isRequestline(keys[i])) {
					sb.append(":");
				}
				p.print(sb.append("\r\n"));
			}
		p.print("\r\n");
		p.flush();
	}

	public synchronized void add(String k, String v) {
		grow();
		keys[nkeys] = k;
		values[nkeys] = v;
		nkeys++;
	}

	public synchronized void prepend(String k, String v) {
		grow();
		for (int i = nkeys; i > 0; i--) {
			keys[i] = keys[i - 1];
			values[i] = values[i - 1];
		}
		keys[0] = k;
		values[0] = v;
		nkeys++;
	}

	public synchronized void set(int i, String k, String v) {
		grow();
		if (i < 0) {
			// Do nothing
		} else if (i >= nkeys) {
			add(k, v);
		} else {
			keys[i] = k;
			values[i] = v;
		}
	}

	private void grow() {
		if (keys == null || nkeys >= keys.length) {
			String[] nk = new String[nkeys + 4];
			String[] nv = new String[nkeys + 4];
			if (keys != null)
				System.arraycopy(keys, 0, nk, 0, nkeys);
			if (values != null)
				System.arraycopy(values, 0, nv, 0, nkeys);
			keys = nk;
			values = nv;
		}
	}

	public synchronized void remove(String k) {
		if (k == null) {
			removeBySearchingAllKeys();
		} else {
			removeUsingKey(k);
		}
	}

	private void removeBySearchingAllKeys() {
		for (int i = 0; i < nkeys; i++) {
			while (keys[i] == null && i < nkeys) {
				for (int j = i; j < nkeys - 1; j++) {
					keys[j] = keys[j + 1];
					values[j] = values[j + 1];
				}
				nkeys--;
			}
		}
	}

	private void removeUsingKey(String k) {
		for (int i = 0; i < nkeys; i++) {
			while (k.equalsIgnoreCase(keys[i]) && i < nkeys) {
				for (int j = i; j < nkeys - 1; j++) {
					keys[j] = keys[j + 1];
					values[j] = values[j + 1];
				}
				nkeys--;
			}
		}
	}

	public synchronized void set(String k, String v) {
		for (int i = nkeys; --i >= 0;)
			if (k.equalsIgnoreCase(keys[i])) {
				values[i] = v;
				return;
			}
		add(k, v);
	}

	public synchronized void setIfNotSet(String k, String v) {
		if (findValue(k) == null) {
			add(k, v);
		}
	}

	public static String canonicalID(String id) {
		if (id == null)
			return "";
		int st = 0;
		int len = id.length();
		boolean substr = false;
		int c;
		while (st < len && ((c = id.charAt(st)) == '<' || c <= ' ')) {
			st++;
			substr = true;
		}
		while (st < len && ((c = id.charAt(len - 1)) == '>' || c <= ' ')) {
			len--;
			substr = true;
		}
		return substr ? id.substring(st, len) : id;
	}

	public void parseHeader(InputStream is) throws java.io.IOException {
		synchronized (this) {
			nkeys = 0;
		}
		mergeHeader(is);
	}

	public void mergeHeader(InputStream is) throws java.io.IOException {
		if (is == null)
			return;
		char[] s = new char[10];
		int firstc = is.read();
		while (firstc != '\n' && firstc != '\r' && firstc >= 0) {
			int len = 0;
			int keyend = -1;
			int c;
			boolean inKey = firstc > ' ';
			s[len++] = (char) firstc;
			parseloop: {
				while ((c = is.read()) >= 0) {
					switch (c) {
					case ':':
						if (inKey && len > 0)
							keyend = len;
						inKey = false;
						break;
					case '\t':
						c = ' ';
						/* fall through */
					case ' ':
						inKey = false;
						break;
					case '\r':
					case '\n':
						firstc = is.read();
						if (c == '\r' && firstc == '\n') {
							firstc = is.read();
							if (firstc == '\r')
								firstc = is.read();
						}
						if (firstc == '\n' || firstc == '\r' || firstc > ' ')
							break parseloop;
						c = ' ';
						break;
					default:
					}
					if (len >= s.length) {
						char[] ns = new char[s.length * 2];
						System.arraycopy(s, 0, ns, 0, len);
						s = ns;
					}
					s[len++] = (char) c;
				}
				firstc = -1;
			}
			while (len > 0 && s[len - 1] <= ' ')
				len--;
			String k;
			if (keyend <= 0) {
				k = null;
				keyend = 0;
			} else {
				k = String.copyValueOf(s, 0, keyend);
				if (keyend < len && s[keyend] == ':')
					keyend++;
				while (keyend < len && s[keyend] <= ' ')
					keyend++;
			}
			String v;
			if (keyend >= len)
				v = "";
			else
				v = String.copyValueOf(s, keyend, len - keyend);
			add(k, v);
		}
	}

	public synchronized String toString() {
		StringBuilder resultBuilder = new StringBuilder(super.toString()).append(nkeys).append(" pairs: ");
		for (int i = 0; i < keys.length && i < nkeys; i++) {
			resultBuilder.append("{").append(keys[i]).append(": ").append(values[i]).append("}");
		}
		
		return resultBuilder.toString();
	}
}

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
package com.quakearts.rest.client.net.http;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.HashMap;

public class AuthCacheImpl implements AuthCache {
	Map<String, LinkedList<AuthCacheValue>> hashtable;

	public AuthCacheImpl() {
		hashtable = new HashMap<>();
	}

	public synchronized void put(String pkey, AuthCacheValue value) {
		LinkedList<AuthCacheValue> list = hashtable.computeIfAbsent(pkey, key -> new LinkedList<>());
		String skey = value.getPath();

		ListIterator<AuthCacheValue> iter = list.listIterator();
		while (iter.hasNext()) {
			AuthenticationInfo inf = (AuthenticationInfo) iter.next();
			if (inf.path == null || inf.path.startsWith(skey)) {
				iter.remove();
			}
		}
		iter.add(value);
	}

	public synchronized AuthCacheValue get(String pkey, String skey) {

		LinkedList<AuthCacheValue> list = hashtable.get(pkey);
		if (list == null || list.isEmpty()) {
			return null;
		}

		if (skey == null) {

			return list.get(0);
		}
		ListIterator<AuthCacheValue> iter = list.listIterator();
		while (iter.hasNext()) {
			AuthenticationInfo inf = (AuthenticationInfo) iter.next();
			if (skey.startsWith(inf.path)) {
				return inf;
			}
		}
		return null;
	}

	public synchronized void remove(String pkey, AuthCacheValue entry) {
		LinkedList<AuthCacheValue> list = hashtable.get(pkey);
		if (list == null) {
			return;
		}
		if (entry == null) {
			list.clear();
			return;
		}
		ListIterator<AuthCacheValue> iter = list.listIterator();
		while (iter.hasNext()) {
			AuthenticationInfo inf = (AuthenticationInfo) iter.next();
			if (entry.equals(inf)) {
				iter.remove();
			}
		}
	}
}

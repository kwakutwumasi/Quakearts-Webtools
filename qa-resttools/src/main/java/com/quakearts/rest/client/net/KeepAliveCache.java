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

import java.io.IOException;
import java.io.NotSerializableException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import java.net.URL;

public class KeepAliveCache extends HashMap<KeepAliveKey, ClientVector> implements Runnable {
	private static final long serialVersionUID = -2937172892064557949L;

	static final int MAX_CONNECTIONS = 5;
	static int result = -1;

	static int getMaxConnections() {
		if (result == -1) {

			result = Integer.getInteger("com.quakearts.net.http.maxConnections", MAX_CONNECTIONS).intValue();

			if (result <= 0)
				result = MAX_CONNECTIONS;
		}
		return result;
	}

	static final int LIFETIME = 5000;

	private Thread keepAliveTimer = null;

	public synchronized void put(final URL url, Object obj, HttpClient http) {
		boolean startThread = (keepAliveTimer == null);
		if (!startThread && !keepAliveTimer.isAlive()) {
			startThread = true;
		}

		if (startThread) {
			clear();

			final KeepAliveCache cache = this;
			keepAliveTimer = new Thread(cache, "Keep-Alive-Timer");
			keepAliveTimer.setDaemon(true);
			keepAliveTimer.setPriority(Thread.MAX_PRIORITY - 2);
			keepAliveTimer.setContextClassLoader(null);
			keepAliveTimer.start();
		}

		KeepAliveKey key = new KeepAliveKey(url, obj);
		ClientVector v = super.get(key);

		if (v == null) {
			int keepAliveTimeout = http.getKeepAliveTimeout();
			v = new ClientVector(keepAliveTimeout > 0 ? keepAliveTimeout * 1000 : LIFETIME);
			v.put(http);
			super.put(key, v);
		} else {
			v.put(http);
		}
	}

	public synchronized void remove(HttpClient h, Object obj) {
		KeepAliveKey key = new KeepAliveKey(h.url, obj);
		ClientVector v = super.get(key);
		if (v != null) {
			v.remove(new KeepAliveEntry(h, 0));
			if (v.isEmpty()) {
				removeVector(key);
			}
		}
	}

	synchronized void removeVector(KeepAliveKey k) {
		super.remove(k);
	}

	public synchronized HttpClient get(URL url, Object obj) {

		KeepAliveKey key = new KeepAliveKey(url, obj);
		ClientVector v = super.get(key);
		if (v == null) {
			return null;
		}
		return v.get();
	}

	@Override
	public void run() {
		do {
			try {
				Thread.sleep(LIFETIME);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
			synchronized (this) {
				long currentTime = System.currentTimeMillis();
				ArrayList<KeepAliveKey> keysToRemove = new ArrayList<>();
				prune(currentTime, keysToRemove);
				removeDead(keysToRemove);
			}
		} while (size() > 0);
	}

	private void prune(long currentTime, ArrayList<KeepAliveKey> keysToRemove) {
		for (Entry<KeepAliveKey, ClientVector> entry : entrySet()) {
			ClientVector v = entry.getValue();
			synchronized (v) {
				int count = 0;

				for (KeepAliveEntry e : v) {
					if ((currentTime - e.idleStartTime) > v.nap) {
						HttpClient h = e.hc;
						h.closeServer();
						count++;
					} else {
						break;
					}
				}

				for (int index = 0; index < count; index++)
					v.pop();

				if (v.isEmpty()) {
					keysToRemove.add(entry.getKey());
				}
			}
		}
	}

	private void removeDead(ArrayList<KeepAliveKey> keysToRemove) {
		for (KeepAliveKey key : keysToRemove) {
			removeVector(key);
		}
	}

	private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
		throw new NotSerializableException();
	}

	private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
		throw new NotSerializableException();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int superHashCode = super.hashCode();
		superHashCode = prime * superHashCode + Objects.hash(keepAliveTimer);
		return superHashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof KeepAliveCache))
			return false;
		KeepAliveCache other = (KeepAliveCache) obj;
		return Objects.equals(keepAliveTimer, other.keepAliveTimer);
	}
}

class ClientVector extends java.util.ArrayDeque<KeepAliveEntry> {
	private static final long serialVersionUID = -8680532108106489459L;

	int nap;

	ClientVector(int nap) {
		this.nap = nap;
	}

	synchronized HttpClient get() {
		if (isEmpty()) {
			return null;
		} else {
			HttpClient hc = null;
			long currentTime = System.currentTimeMillis();
			do {
				KeepAliveEntry e = pop();
				if ((currentTime - e.idleStartTime) > nap) {
					e.hc.closeServer();
				} else {
					hc = e.hc;
				}
			} while ((hc == null) && (!isEmpty()));
			return hc;
		}
	}

	synchronized void put(HttpClient h) {
		if (size() >= KeepAliveCache.getMaxConnections()) {
			h.closeServer();
		} else {
			push(new KeepAliveEntry(h, System.currentTimeMillis()));
		}
	}

	private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
		throw new NotSerializableException();
	}

	private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
		throw new NotSerializableException();
	}
}

class KeepAliveKey {
	private String protocol = null;
	private String host = null;
	private int port = 0;
	private Object obj = null;

	public KeepAliveKey(URL url, Object obj) {
		this.protocol = url.getProtocol();
		this.host = url.getHost();
		this.port = url.getPort();
		this.obj = obj;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof KeepAliveKey))
			return false;

		KeepAliveKey kae = (KeepAliveKey) obj;
		return host.equals(kae.host) && (port == kae.port) && protocol.equals(kae.protocol) && this.obj == kae.obj;
	}

	@Override
	public int hashCode() {
		String str = protocol + host + port;
		return this.obj == null ? str.hashCode() : str.hashCode() + this.obj.hashCode();
	}
}

class KeepAliveEntry {
	HttpClient hc;
	long idleStartTime;

	KeepAliveEntry(HttpClient hc, long idleStartTime) {
		this.hc = hc;
		this.idleStartTime = idleStartTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof KeepAliveEntry))
			return false;
		KeepAliveEntry other = (KeepAliveEntry) obj;
		return Objects.equals(hc, other.hc);
	}
}

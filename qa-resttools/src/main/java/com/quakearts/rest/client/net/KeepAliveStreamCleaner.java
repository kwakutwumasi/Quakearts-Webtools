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
import java.util.LinkedList;

class KeepAliveStreamCleaner extends LinkedList<KeepAliveCleanerEntry> implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3560722958475185669L;

	protected static final int MAX_DATA_REMAINING = NetProperties.getInteger("com.quakearts.net.http.KeepAlive.remainingData", 512)
			.intValue() * 1024;
	protected static final int MAX_CAPACITY = NetProperties.getInteger("com.quakearts.net.http.KeepAlive.queuedConnections", 10)
			.intValue();
	protected static final int TIMEOUT = 5000;
	private static final int MAX_RETRIES = 5;

	@Override
	public boolean offer(KeepAliveCleanerEntry e) {
		if (size() >= MAX_CAPACITY)
			return false;

		return super.offer(e);
	}

	@Override
	public void run() {
		KeepAliveCleanerEntry kace = null;
		do {
			try {
				synchronized (this) {
					long before = System.currentTimeMillis();
					long timeout = TIMEOUT;
					while ((kace = poll()) == null) {
						this.wait(timeout);

						long after = System.currentTimeMillis();
						long elapsed = after - before;
						if (elapsed > timeout) {
							/* one last try */
							kace = poll();
							break;
						}
						before = after;
						timeout -= elapsed;
					}
				}

				if (kace == null)
					break;

				KeepAliveStream kas = kace.getKeepAliveStream();

				if (kas != null) {
					synchronized (kas) {
						HttpClient hc = kace.getHttpClient();
						prune(kas, hc);
					}
				}
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
				return;
			}
		} while (!Thread.interrupted());
	}

	private void prune(KeepAliveStream kas, HttpClient hc) {
		try {
			if (hc != null && !hc.isInKeepAliveCache()) {
				int oldTimeout = hc.getReadTimeout();
				hc.setReadTimeout(TIMEOUT);
				long remainingToRead = kas.remainingToRead();
				checkRemainingToRead(kas, hc, oldTimeout, remainingToRead);
			}
		} catch (IOException ioe) {
			hc.closeServer();
		} finally {
			kas.setClosed();
		}
	}

	private void checkRemainingToRead(KeepAliveStream kas, HttpClient hc, int oldTimeout, long remainingToRead)
			throws IOException {
		if (remainingToRead > 0) {
			long n = 0;
			int retries = 0;
			while (n < remainingToRead && retries < MAX_RETRIES) {
				remainingToRead = remainingToRead - n;
				n = kas.skip(remainingToRead);
				if (n == 0)
					retries++;
			}
			remainingToRead = remainingToRead - n;
		}
		if (remainingToRead == 0) {
			hc.setReadTimeout(oldTimeout);
			hc.finished();
		} else
			hc.closeServer();
	}
}

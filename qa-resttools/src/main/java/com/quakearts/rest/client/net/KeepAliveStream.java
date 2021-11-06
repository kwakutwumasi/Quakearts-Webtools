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

import java.io.*;

public class KeepAliveStream extends MeteredStream implements Hurryable {

	HttpClient httpClient;

	boolean hurried;

	protected boolean queuedForCleanup = false;

	private static final KeepAliveStreamCleaner queue = new KeepAliveStreamCleaner();
	private static Thread cleanerThread; 

	public KeepAliveStream(InputStream inputStream, ProgressSource progressSource, long expected, HttpClient httpClient) {
		super(inputStream, progressSource, expected);
		this.httpClient = httpClient;
	}

	@Override
	public void close() throws IOException {
		if (closed) {
			return;
		}

		if (queuedForCleanup) {
			return;
		}

		cleanUp();
	}

	private void cleanUp() throws IOException {
		try {
			if (expected > count) {
				long nskip = expected - count;
				if (nskip <= available()) {
					while ((nskip = (expected - count)) > 0L && skip(Math.min(nskip, available())) > 0L);
				} else if (expected <= KeepAliveStreamCleaner.MAX_DATA_REMAINING && !hurried) {
					queueForCleanup(new KeepAliveCleanerEntry(this, httpClient));
				} else {
					httpClient.closeServer();
				}
			}
			if (!closed && !hurried && !queuedForCleanup) {
				httpClient.finished();
			}
		} finally {
			if (progressSource != null)
				progressSource.finishTracking();

			if (!queuedForCleanup) {
				in = null;
				httpClient = null;
				closed = true;
			}
		}
	}
	
	@Override
	public boolean markSupported() {
		return false;
	}

	@Override
	public synchronized void mark(int limit) {
		//Not supported
	}

	@Override
	public synchronized void reset() throws IOException {
		throw new IOException("mark/reset not supported");
	}

	@Override
	public synchronized boolean hurry() {
		try {
			if (closed || count >= expected) {
				return false;
			} else if (in.available() < (expected - count)) {
				return false;
			} else {
				int size = (int) (expected - count);
				byte[] buf = new byte[size];
				DataInputStream dis = new DataInputStream(in);
				dis.readFully(buf);
				in = new ByteArrayInputStream(buf);
				hurried = true;
				return true;
			}
		} catch (IOException e) {
			return false;
		}
	}

	private static void queueForCleanup(KeepAliveCleanerEntry kace) {
		synchronized (queue) {
			if (!kace.getQueuedForCleanup()) {
				if (!queue.offer(kace)) {
					kace.getHttpClient().closeServer();
					return;
				}

				kace.setQueuedForCleanup();
				queue.notifyAll();
			}

			if (cleanerThread == null || !cleanerThread.isAlive()) {
				cleanerThread = new Thread(queue, "Keep-Alive-SocketCleaner");
				cleanerThread.setDaemon(true);
				cleanerThread.setPriority(Thread.MAX_PRIORITY - 2);
				cleanerThread.setContextClassLoader(null);
				cleanerThread.start();
			}
		}
	}

	protected long remainingToRead() {
		return expected - count;
	}

	protected void setClosed() {
		in = null;
		httpClient = null;
		closed = true;
	}
}

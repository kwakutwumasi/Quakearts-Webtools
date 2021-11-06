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

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MeteredStream extends FilterInputStream {

	protected boolean closed = false;
	protected long expected;
	protected long count = 0;
	protected long markedCount = 0;
	protected int markLimit = -1;
	protected ProgressSource progressSource;

	public MeteredStream(InputStream inputStream, ProgressSource progressSource, long expected) {
		super(inputStream);

		this.progressSource = progressSource;
		this.expected = expected;

		if (progressSource != null) {
			progressSource.updateProgress(0, expected);
		}
	}

	private final void justRead(long n) throws IOException {
		if (n == -1) {
			if (!isMarked()) {
				close();
			}
			return;
		}

		count += n;

		if (count - markedCount > markLimit) {
			markLimit = -1;
		}

		if (progressSource != null)
			progressSource.updateProgress(count, expected);

		if (isMarked()) {
			return;
		}

		if (expected > 0 && count >= expected) {
			close();
		}
	}

	private boolean isMarked() {

		if (markLimit < 0) {
			return false;
		}

		return (count - markedCount > markLimit);
	}

	@Override
	public synchronized int read() throws java.io.IOException {
		if (closed) {
			return -1;
		}
		int c = in.read();
		if (c != -1) {
			justRead(1);
		} else {
			justRead(c);
		}
		return c;
	}

	@Override
	public synchronized int read(byte[] b, int off, int len) throws IOException {
		if (closed) {
			return -1;
		}
		int n = in.read(b, off, len);
		justRead(n);
		return n;
	}

	@Override
	public synchronized long skip(long n) throws IOException {

		if (closed) {
			return 0;
		}

		if (in instanceof ChunkedInputStream) {
			n = in.skip(n);
		} else {
			long min = (n > expected - count) ? expected - count : n;
			n = in.skip(min);
		}
		justRead(n);
		return n;
	}

	@Override
	public void close() throws IOException {
		if (closed) {
			return;
		}
		if (progressSource != null)
			progressSource.finishTracking();

		closed = true;
		in.close();
	}

	@Override
	public synchronized int available() throws IOException {
		return closed ? 0 : in.available();
	}

	@Override
	public synchronized void mark(int readLimit) {
		if (closed) {
			return;
		}
		super.mark(readLimit);

		markedCount = count;
		markLimit = readLimit;
	}

	@Override
	public synchronized void reset() throws IOException {
		if (closed) {
			return;
		}

		if (!isMarked()) {
			throw new IOException("Resetting to an invalid mark");
		}

		count = markedCount;
		super.reset();
	}

	@Override
	public boolean markSupported() {
		if (closed) {
			return false;
		}
		return super.markSupported();
	}
}

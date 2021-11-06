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
import java.nio.charset.StandardCharsets;

public class ChunkedInputStream extends InputStream implements Hurryable {

	private InputStream in;
	private HttpClient hc;
	private MessageHeader responses;
	private int chunkSize;
	private int chunkRead;
	private byte[] chunkData = new byte[4096];
	private int chunkPos;
	private int chunkCount;
	private byte[] rawData = new byte[32];
	private int rawPos;
	private int rawCount;
	private boolean error;
	private boolean closed;

	private static final int MAX_CHUNK_HEADER_SIZE = 2050;
	enum State {
		STATE_AWAITING_CHUNK_HEADER,
		STATE_READING_CHUNK,
		STATE_AWAITING_CHUNK_EOL,
		STATE_AWAITING_TRAILERS,
		STATE_DONE;
	}
	
	private State state;

	private void ensureOpen() throws IOException {
		if (closed) {
			throw new IOException("stream is closed");
		}
	}

	private void ensureRawAvailable(int size) {
		if (rawCount + size > rawData.length) {
			int used = rawCount - rawPos;
			if (used + size > rawData.length) {
				byte[] temp = new byte[used + size];
				if (used > 0) {
					System.arraycopy(rawData, rawPos, temp, 0, used);
				}
				rawData = temp;
			} else {
				if (used > 0) {
					System.arraycopy(rawData, rawPos, rawData, 0, used);
				}
			}
			rawCount = used;
			rawPos = 0;
		}
	}

	private void closeUnderlying() {
		if (in == null) {
			return;
		}

		if (!error && state == State.STATE_DONE) {
			hc.finished();
		} else {
			if (!hurry()) {
				hc.closeServer();
			}
		}

		in = null;
	}

	private int fastRead(byte[] b, int off, int len) throws IOException {
		int remaining = chunkSize - chunkRead;
		int cnt = (remaining < len) ? remaining : len;
		if (cnt > 0) {
			int nread;
			try {
				nread = in.read(b, off, cnt);
			} catch (IOException e) {
				error = true;
				throw e;
			}
			if (nread > 0) {
				chunkRead += nread;
				if (chunkRead >= chunkSize) {
					state = State.STATE_AWAITING_CHUNK_EOL;
				}
				return nread;
			}
			error = true;
			throw new IOException("Premature EOF");
		} else {
			return 0;
		}
	}

	private void processRaw() throws IOException {
		int pos;
		int i;

		while (state != State.STATE_DONE) {
			switch (state) {
			case STATE_AWAITING_CHUNK_HEADER:
				pos = rawPos;
				while (pos < rawCount) {
					if (rawData[pos] == '\n') {
						break;
					}
					pos++;
					if ((pos - rawPos) >= MAX_CHUNK_HEADER_SIZE) {
						error = true;
						throw new IOException("Chunk header too long");
					}
				}
				
				if (pos >= rawCount) {
					return;
				}

				String header = new String(rawData, rawPos, pos - rawPos + 1, StandardCharsets.US_ASCII);
				for (i = 0; i < header.length(); i++) {
					if (Character.digit(header.charAt(i), 16) == -1)
						break;
				}
				try {
					chunkSize = Integer.parseInt(header.substring(0, i), 16);
				} catch (NumberFormatException e) {
					error = true;
					throw new IOException("Bogus chunk size");
				}

				rawPos = pos + 1;
				chunkRead = 0;

				if (chunkSize > 0) {
					state = State.STATE_READING_CHUNK;
				} else {
					state = State.STATE_AWAITING_TRAILERS;
				}
				break;
			case STATE_READING_CHUNK:
				if (rawPos >= rawCount) {
					return;
				}

				int copyLen = Math.min(chunkSize - chunkRead, rawCount - rawPos);

				if (chunkData.length < chunkCount + copyLen) {
					int cnt = chunkCount - chunkPos;
					if (chunkData.length < cnt + copyLen) {
						byte[] temp = new byte[cnt + copyLen];
						System.arraycopy(chunkData, chunkPos, temp, 0, cnt);
						chunkData = temp;
					} else {
						System.arraycopy(chunkData, chunkPos, chunkData, 0, cnt);
					}
					chunkPos = 0;
					chunkCount = cnt;
				}

				System.arraycopy(rawData, rawPos, chunkData, chunkCount, copyLen);
				rawPos += copyLen;
				chunkCount += copyLen;
				chunkRead += copyLen;

				if (chunkSize - chunkRead <= 0) {
					state = State.STATE_AWAITING_CHUNK_EOL;
				} else {
					return;
				}
				break;
			case STATE_AWAITING_CHUNK_EOL:

				if (rawPos + 1 >= rawCount) {
					return;
				}

				if (rawData[rawPos] != '\r') {
					error = true;
					throw new IOException("missing CR");
				}
				if (rawData[rawPos + 1] != '\n') {
					error = true;
					throw new IOException("missing LF");
				}
				rawPos += 2;

				state = State.STATE_AWAITING_CHUNK_HEADER;
				break;
			case STATE_AWAITING_TRAILERS:
				pos = rawPos;
				while (pos < rawCount) {
					if (rawData[pos] == '\n') {
						break;
					}
					pos++;
				}
				
				if (pos >= rawCount) {
					return;
				}

				if (pos == rawPos) {
					error = true;
					throw new IOException("LF should be proceeded by CR");
				}
				
				if (rawData[pos - 1] != '\r') {
					error = true;
					throw new IOException("LF should be proceeded by CR");
				}

				if (pos == (rawPos + 1)) {
					state = State.STATE_DONE;
					closeUnderlying();
					return;
				}

				String trailer = new String(rawData, rawPos, pos - rawPos, StandardCharsets.US_ASCII);
				i = trailer.indexOf(':');
				if (i == -1) {
					throw new IOException("Malformed tailer - format should be key:value");
				}
				String key = (trailer.substring(0, i)).trim();
				String value = (trailer.substring(i + 1, trailer.length())).trim();

				responses.add(key, value);

				rawPos = pos + 1;
				break;
			case STATE_DONE:
				break;
			}
		}
	}

	private int readAheadNonBlocking() throws IOException {

		int avail = in.available();
		if (avail > 0) {

			ensureRawAvailable(avail);

			int nread;
			try {
				nread = in.read(rawData, rawCount, avail);
			} catch (IOException e) {
				error = true;
				throw e;
			}
			if (nread < 0) {
				error = true;
				return -1;
			}
			rawCount += nread;

			processRaw();
		}

		return chunkCount - chunkPos;
	}

	private int readAheadBlocking() throws IOException {

		do {
			if (state == State.STATE_DONE) {
				return -1;
			}

			ensureRawAvailable(32);
			int nread;
			try {
				nread = in.read(rawData, rawCount, rawData.length - rawCount);
			} catch (IOException e) {
				error = true;
				throw e;
			}

			if (nread < 0) {
				error = true;
				throw new IOException("Premature EOF");
			}

			rawCount += nread;
			processRaw();

		} while (chunkCount <= 0);

		return chunkCount - chunkPos;
	}

	private int readAhead(boolean allowBlocking) throws IOException {

		if (state == State.STATE_DONE) {
			return -1;
		}

		if (chunkPos >= chunkCount) {
			chunkCount = 0;
			chunkPos = 0;
		}

		if (allowBlocking) {
			return readAheadBlocking();
		} else {
			return readAheadNonBlocking();
		}
	}

	public ChunkedInputStream(InputStream in, HttpClient hc, MessageHeader responses) {
		this.in = in;
		this.responses = responses;
		this.hc = hc;

		state = State.STATE_AWAITING_CHUNK_HEADER;
	}

	public synchronized int read() throws IOException {
		ensureOpen();
		if (chunkPos >= chunkCount && readAhead(true) <= 0) {
			return -1;
		}
		return chunkData[chunkPos++] & 0xff;
	}

	@Override
	public synchronized int read(byte[] b, int off, int len) throws IOException {
		ensureOpen();
		if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) > b.length) || ((off + len) < 0)) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return 0;
		}

		int avail = chunkCount - chunkPos;
		if (avail <= 0) {

			if (state == State.STATE_READING_CHUNK) {
				return fastRead(b, off, len);
			}

			avail = readAhead(true);
			if (avail < 0) {
				return -1;
			}
		}
		int cnt = (avail < len) ? avail : len;
		System.arraycopy(chunkData, chunkPos, b, off, cnt);
		chunkPos += cnt;

		return cnt;
	}

	@Override
	public synchronized int available() throws IOException {
		ensureOpen();

		int avail = chunkCount - chunkPos;
		if (avail > 0) {
			return avail;
		}

		avail = readAhead(false);

		if (avail < 0) {
			return 0;
		} else {
			return avail;
		}
	}

	@Override
	public synchronized void close() throws IOException {
		if (closed) {
			return;
		}
		closeUnderlying();
		closed = true;
	}

	public synchronized boolean hurry() {
		if (in == null || error) {
			return false;
		}

		try {
			readAhead(false);
		} catch (Exception e) {
			return false;
		}

		if (error) {
			return false;
		}

		return (state == State.STATE_DONE);
	}
}

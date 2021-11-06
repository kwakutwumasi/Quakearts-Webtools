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

import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

public abstract class NetworkClient {
	private static final String ISO8859_1 = "ISO8859_1";
	public static final int DEFAULT_READ_TIMEOUT = -1;
	public static final int DEFAULT_CONNECT_TIMEOUT = -1;

	protected Proxy proxy = Proxy.NO_PROXY;
	protected Socket serverSocket = null;
	protected PrintStream serverOutput;
	protected InputStream serverInput;

	protected static int defaultSoTimeout;
	protected static int defaultConnectTimeout;

	protected int readTimeout = DEFAULT_READ_TIMEOUT;
	protected int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
	protected static String encoding;

	static {
		final int[] vals = { 0, 0 };
		final String[] encs = { null };
		vals[0] = Integer.getInteger("com.quakearts.net.defaultReadTimeout", 0).intValue();
		vals[1] = Integer.getInteger("com.quakearts.net.defaultConnectTimeout", 0).intValue();
		encs[0] = System.getProperty("file.encoding", ISO8859_1);
		if (vals[0] != 0) {
			defaultSoTimeout = vals[0];
		}
		if (vals[1] != 0) {
			defaultConnectTimeout = vals[1];
		}

		encoding = encs[0];
		try {
			if (!isASCIISuperset(encoding)) {
				encoding = ISO8859_1;
			}
		} catch (Exception e) {
			encoding = ISO8859_1;
		}
	}

	/**
	 * Test the named character encoding to verify that it converts ASCII characters
	 * correctly. We have to use an ASCII based encoding, or else the NetworkClients
	 * will not work correctly in EBCDIC based systems. However, we cannot just use
	 * ASCII or ISO8859_1 universally, because in Asian locales, non-ASCII
	 * characters may be embedded in otherwise ASCII based protocols (eg. HTTP). The
	 * specifications (RFC2616, 2398) are a little ambiguous in this matter. For
	 * instance, RFC2398 [part 2.1] says that the HTTP request URI should be escaped
	 * using a defined mechanism, but there is no way to specify in the escaped
	 * string what the original character set is. It is not correct to assume that
	 * UTF-8 is always used (as in URLs in HTML 4.0). For this reason, until the
	 * specifications are updated to deal with this issue more comprehensively, and
	 * more importantly, HTTP servers are known to support these mechanisms, we will
	 * maintain the current behavior where it is possible to send non-ASCII
	 * characters in their original unescaped form.
	 */
	private static boolean isASCIISuperset(String encoding) throws Exception {
		String chkS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz-_.!~*'();/?:@&=+$,";

		// Expected byte sequence for string above
		byte[] chkB = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78,
				79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108,
				109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 45, 95, 46, 33, 126, 42, 39, 40,
				41, 59, 47, 63, 58, 64, 38, 61, 43, 36, 44 };

		byte[] b = chkS.getBytes(encoding);
		return Arrays.equals(b, chkB);
	}

	/** Open a connection to the server. */
	public void openServer(String server, int port) throws IOException {
		if (serverSocket != null)
			closeServer();
		serverSocket = doConnect(server, port);
		try {
			serverOutput = new PrintStream(new BufferedOutputStream(serverSocket.getOutputStream()), true, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new InternalError(encoding + "encoding not found", e);
		}
		serverInput = new BufferedInputStream(serverSocket.getInputStream());
	}

	@SuppressWarnings("resource")
	protected Socket doConnect(String server, int port) throws IOException {
		Socket s;
		if (proxy != null) {
			if (proxy.type() == Proxy.Type.SOCKS) {
				s = new Socket(proxy);
			} else if (proxy.type() == Proxy.Type.DIRECT) {
				s = createSocket();
			} else {
				s = new Socket(Proxy.NO_PROXY);
			}
		} else
			s = createSocket();

		if (connectTimeout >= 0) {
			s.connect(new InetSocketAddress(server, port), connectTimeout);
		} else {
			if (defaultConnectTimeout > 0) {
				s.connect(new InetSocketAddress(server, port), defaultConnectTimeout);
			} else {
				s.connect(new InetSocketAddress(server, port));
			}
		}
		if (readTimeout >= 0)
			s.setSoTimeout(readTimeout);
		else if (defaultSoTimeout > 0) {
			s.setSoTimeout(defaultSoTimeout);
		}
		return s;
	}

	protected abstract Socket createSocket() throws IOException;

	protected InetAddress getLocalAddress() throws IOException {
		if (serverSocket == null)
			throw new IOException("not connected");
		return serverSocket.getLocalAddress();
	}

	public void closeServer() throws IOException {
		if (!serverIsOpen()) {
			return;
		}
		serverSocket.close();
		serverSocket = null;
		serverInput = null;
		serverOutput = null;
	}

	public boolean serverIsOpen() {
		return serverSocket != null;
	}

	protected NetworkClient(String host, int port) throws IOException {
		openServer(host, port);
	}

	protected NetworkClient() {}

	public void setConnectTimeout(int timeout) {
		connectTimeout = timeout;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setReadTimeout(int timeout) {
		if (timeout == DEFAULT_READ_TIMEOUT)
			timeout = defaultSoTimeout;

		if (serverSocket != null && timeout >= 0) {
			try {
				serverSocket.setSoTimeout(timeout);
			} catch (IOException e) {
				// We tried...
			}
		}
		readTimeout = timeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}
}

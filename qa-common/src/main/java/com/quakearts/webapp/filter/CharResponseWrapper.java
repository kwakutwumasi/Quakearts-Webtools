/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.filter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CharResponseWrapper extends HttpServletResponseWrapper {
	protected CharArrayWriter charWriter;
	protected PrintWriter writer;
	private boolean getOutputStreamCalled;
	private boolean getWriterCalled;

	public CharResponseWrapper(HttpServletResponse response) {
		super(response);

		this.charWriter = new CharArrayWriter();
	}

	public ServletOutputStream getOutputStream() throws IOException {
		if (this.getWriterCalled) {
			throw new IllegalStateException("getWriter already called");
		}
		this.getOutputStreamCalled = true;
		return super.getOutputStream();
	}

	public PrintWriter getWriter() throws IOException {
		if (this.writer != null) {
			return this.writer;
		}
		if (this.getOutputStreamCalled) {
			throw new IllegalStateException("getOutputStream already called");
		}
		this.getWriterCalled = true;
		this.writer = new PrintWriter(this.charWriter);
		return this.writer;
	}

	public String toString() {
		String s = null;
		if (this.writer != null) {
			s = this.charWriter.toString();
		}
		return s;
	}

	public boolean isGetOutputStreamCalled() {
		return this.getOutputStreamCalled;
	}

	public boolean isGetWriterCalled() {
		return this.getWriterCalled;
	}
}

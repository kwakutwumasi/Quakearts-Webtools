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

import java.util.EventObject;
import java.net.URL;

public class ProgressEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3265476787702736837L;
	private URL url;
	private String contentType;
	private String method;
	private long progress;
	private long expected;
	private ProgressSource.State state;

	public ProgressEvent(ProgressSource source, URL url, String method, String contentType, ProgressSource.State state,
			long progress, long expected) {
		super(source);
		this.url = url;
		this.method = method;
		this.contentType = contentType;
		this.progress = progress;
		this.expected = expected;
		this.state = state;
	}

	public URL getURL() {
		return url;
	}

	public String getMethod() {
		return method;
	}

	public String getContentType() {
		return contentType;
	}

	public long getProgress() {
		return progress;
	}

	public long getExpected() {
		return expected;
	}

	public ProgressSource.State getState() {
		return state;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[url=" + url + ", method=" + method + ", state=" + state + ", content-type="
				+ contentType + ", progress=" + progress + ", expected=" + expected + "]";
	}
}

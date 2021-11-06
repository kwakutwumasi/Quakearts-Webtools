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

import java.net.URL;

public class ProgressSource {
	public enum State {
		NEW, CONNECTED, UPDATE, DELETE
	}
	
	private URL url;
	private String method;
	private String contentType;
	private long progress = 0;
	private long lastProgress = 0;
	private long expected = -1;
	private State state;
	private boolean connected = false;
	private int threshold = 8192;
	private ProgressMonitor progressMonitor;

	public ProgressSource(URL url, String method) {
		this(url, method, -1);
	}

	public ProgressSource(URL url, String method, long expected) {
		this.url = url;
		this.method = method;
		this.contentType = "content/unknown";
		this.progress = 0;
		this.lastProgress = 0;
		this.expected = expected;
		this.state = State.NEW;
		this.progressMonitor = ProgressMonitor.getDefault();
		this.threshold = progressMonitor.getProgressUpdateThreshold();
	}
	
	public ProgressSource(ProgressSource toCopy) {
		this.url = toCopy.url;
		this.method = toCopy.method;
		this.contentType = toCopy.contentType;
		this.progress = toCopy.progress;
		this.lastProgress = toCopy.lastProgress;
		this.expected = toCopy.expected;
		this.state = toCopy.state;
		this.progressMonitor = toCopy.progressMonitor;
		this.threshold = toCopy.threshold;
		this.connected = toCopy.connected;
	}

	public boolean connected() {
		if (!connected) {
			connected = true;
			state = State.CONNECTED;
			return false;
		}
		return true;
	}

	public void close() {
		state = State.DELETE;
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

	public void setContentType(String ct) {
		contentType = ct;
	}

	public long getProgress() {
		return progress;
	}

	public long getExpected() {
		return expected;
	}

	public State getState() {
		return state;
	}

	public void beginTracking() {
		if(progressMonitor != null)
			progressMonitor.registerSource(this);
	}

	public void finishTracking() {
		progressMonitor.unregisterSource(this);
	}

	public void updateProgress(long latestProgress, long expectedProgress) {
		lastProgress = progress;
		progress = latestProgress;
		expected = expectedProgress;

		if (!connected())
			state = State.CONNECTED;
		else
			state = State.UPDATE;

		if (lastProgress / threshold != progress / threshold) {
			progressMonitor.updateProgress(this);
		}

		if (expected != -1 && progress >= expected && progress != 0)
			close();
	}

	public String toString() {
		return getClass().getName() + "[url=" + url + ", method=" + method + ", state=" + state + ", content-type="
				+ contentType + ", progress=" + progress + ", expected=" + expected + "]";
	}
}

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

class KeepAliveCleanerEntry {
	KeepAliveStream kas;
	HttpClient hc;

	public KeepAliveCleanerEntry(KeepAliveStream kas, HttpClient hc) {
		this.kas = kas;
		this.hc = hc;
	}

	protected KeepAliveStream getKeepAliveStream() {
		return kas;
	}

	protected HttpClient getHttpClient() {
		return hc;
	}

	protected void setQueuedForCleanup() {
		kas.queuedForCleanup = true;
	}

	protected boolean getQueuedForCleanup() {
		return kas.queuedForCleanup;
	}

}

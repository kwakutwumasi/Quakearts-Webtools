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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.net.URL;

public class ProgressMonitor {
	public static synchronized ProgressMonitor getDefault() {
		return progressMonitor;
	}

	public static synchronized void setDefault(ProgressMonitor m) {
		if (m != null)
			progressMonitor = m;
	}

	public static synchronized void setMeteringPolicy(ProgressMeteringPolicy policy) {
		if (policy != null)
			meteringPolicy = policy;
	}

	public List<ProgressSource> getProgressSources() {
		ArrayList<ProgressSource> snapshot = new ArrayList<>();

		synchronized (progressSourceList) {
			for (Iterator<ProgressSource> iter = progressSourceList.iterator(); iter.hasNext();) {
				ProgressSource pi = iter.next();

				snapshot.add(new ProgressSource(pi));
			}
		}

		return snapshot;
	}

	public synchronized int getProgressUpdateThreshold() {
		return meteringPolicy.getProgressUpdateThreshold();
	}

	public boolean shouldMeterInput(URL url, String method) {
		return meteringPolicy.shouldMeterInput(url, method);
	}

	public void registerSource(ProgressSource progressSource) {

		synchronized (progressSourceList) {
			if (progressSourceList.contains(progressSource))
				return;

			progressSourceList.add(progressSource);
		}

		if (!progressListenerList.isEmpty()) {
			ArrayList<ProgressListener> listeners = new ArrayList<>();

			synchronized (progressListenerList) {
				for (Iterator<ProgressListener> iter = progressListenerList.iterator(); iter.hasNext();) {
					listeners.add(iter.next());
				}
			}

			for (Iterator<ProgressListener> iter = listeners.iterator(); iter.hasNext();) {
				ProgressListener pl = iter.next();
				if(pl == null)
					continue;
				
				ProgressEvent pe = new ProgressEvent(progressSource, progressSource.getURL(), progressSource.getMethod(), progressSource.getContentType(),
						progressSource.getState(), progressSource.getProgress(), progressSource.getExpected());
				pl.progressStart(pe);
			}
		}
	}

	public void unregisterSource(ProgressSource progressSource) {

		synchronized (progressSourceList) {
			if (!progressSourceList.contains(progressSource))
				return;

			progressSource.close();
			progressSourceList.remove(progressSource);
		}

		if (!progressListenerList.isEmpty()) {
			ArrayList<ProgressListener> listeners = new ArrayList<>();

			synchronized (progressListenerList) {
				for (Iterator<ProgressListener> iter = progressListenerList.iterator(); iter.hasNext();) {
					listeners.add(iter.next());
				}
			}

			for (Iterator<ProgressListener> iter = listeners.iterator(); iter.hasNext();) {
				ProgressListener pl = iter.next();
				ProgressEvent pe = new ProgressEvent(progressSource, progressSource.getURL(), progressSource.getMethod(), progressSource.getContentType(),
						progressSource.getState(), progressSource.getProgress(), progressSource.getExpected());
				pl.progressFinish(pe);
			}
		}
	}

	public void updateProgress(ProgressSource progressSource) {

		synchronized (progressSourceList) {
			if (!progressSourceList.contains(progressSource))
				return;
		}

		if (!progressListenerList.isEmpty()) {
			ArrayList<ProgressListener> listeners = new ArrayList<>();

			synchronized (progressListenerList) {
				for (Iterator<ProgressListener> iter = progressListenerList.iterator(); iter.hasNext();) {
					listeners.add(iter.next());
				}
			}

			for (Iterator<ProgressListener> iter = listeners.iterator(); iter.hasNext();) {
				ProgressListener pl = iter.next();
				ProgressEvent pe = new ProgressEvent(progressSource, progressSource.getURL(), progressSource.getMethod(), progressSource.getContentType(),
						progressSource.getState(), progressSource.getProgress(), progressSource.getExpected());
				pl.progressUpdate(pe);
			}
		}
	}

	public void addProgressListener(ProgressListener l) {
		synchronized (progressListenerList) {
			progressListenerList.add(l);
		}
	}

	public void removeProgressListener(ProgressListener l) {
		synchronized (progressListenerList) {
			progressListenerList.remove(l);
		}
	}

	private static ProgressMeteringPolicy meteringPolicy = new DefaultProgressMeteringPolicy();
	private static ProgressMonitor progressMonitor = new ProgressMonitor();
	private ArrayList<ProgressSource> progressSourceList = new ArrayList<>();
	private ArrayList<ProgressListener> progressListenerList = new ArrayList<>();
}

class DefaultProgressMeteringPolicy implements ProgressMeteringPolicy {

	public boolean shouldMeterInput(URL url, String method) {
		return false;
	}

	public int getProgressUpdateThreshold() {
		return 8192;
	}
}
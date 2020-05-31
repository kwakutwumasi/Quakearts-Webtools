package com.quakearts.syshub.core.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

/**A singleton ManagedBean that provides support for registering for
 * and firing classes that want to observe {@link ParameterEvent}'s
 * @author kwaku
 *
 */
@Singleton
public class ParameterEventBroadcaster {
	private List<ParameterEventListener> eventListeners = Collections.synchronizedList(new ArrayList<>());
	
	public void registerListener(ParameterEventListener listener){
		eventListeners.add(listener);
	}
	
	public void unregisterListener(ParameterEventListener listener){
		eventListeners.remove(listener);
	}
	
	public void broadcast(ParameterEvent parameterEvent) {
		synchronized (eventListeners) {
			eventListeners.parallelStream()
				.forEach(listener->listener.handleParameterChanged(parameterEvent));
		}
	}
	
}

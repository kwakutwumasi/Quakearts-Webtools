package com.quakearts.syshub.webapp.helpers.viewupdate;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.websocket.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.quakearts.syshub.agent.event.ProcessingEvent;

@Singleton
public class ViewUpdaterServiceImpl implements ViewUpdaterService {

	private final Map<Session, Boolean> registeredSessions = new ConcurrentHashMap<>();
	private static final Logger log = LoggerFactory.getLogger(ViewUpdaterService.class);
	private Gson gson = new Gson();
	
	@Override
	public void registerForViewUpdates(Session session) {
		registeredSessions.put(session, Boolean.TRUE);
	}

	@Override
	public void unRegisterForViewUpdates(Session session) {
		registeredSessions.remove(session);
	}
	
	public void triggerViewUpdateForUpdateViewEvent(@Observes UpdateViewEvent event) {
		sendEvent(event);
	}

	public void triggerViewUpdateFor(@Observes ProcessingEvent event) {
		sendEvent(new ProcessingUpdateViewEvent(event));
	}

	private synchronized void sendEvent(Object event) {
		registeredSessions.keySet().parallelStream().forEach((session)->{
			if(session.isOpen()) {
				try {
					session.getBasicRemote().sendText(gson.toJson(event));
				} catch (IOException e) {
					log.error("Unable to send message to session endpoint. Unregistering to prevent further errors", e);
					unRegisterForViewUpdates(session);
				}
			} else {
				unRegisterForViewUpdates(session);
			}
		});
	}
}

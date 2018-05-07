package com.quakearts.syshub.webapp.helpers.viewupdate;

import javax.websocket.Session;

public interface ViewUpdaterService {
	void registerForViewUpdates(Session session);
	void unRegisterForViewUpdates(Session session);
}

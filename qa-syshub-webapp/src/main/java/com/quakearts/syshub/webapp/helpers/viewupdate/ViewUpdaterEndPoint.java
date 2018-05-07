package com.quakearts.syshub.webapp.helpers.viewupdate;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.quakearts.appbase.Main;

@ServerEndpoint("/update")
public class ViewUpdaterEndPoint extends Endpoint implements MessageHandler.Whole<String> {
	@Inject
	private ViewUpdaterService service;
	
	@Override
	public void onMessage(String message) {
	}
	
	@Override
	public void onOpen(Session session, EndpointConfig config) {
		service.registerForViewUpdates(session);
		session.addMessageHandler(this);
	}
	
	@Override
	public void onClose(Session session, CloseReason closeReason) {
		service.unRegisterForViewUpdates(session);
	}
	
	@Override
	public void onError(Session session, Throwable t) {
		Main.log.error("Protocol exception on WebSocket "+session.getId(), t);
		service.unRegisterForViewUpdates(session);
	}
}

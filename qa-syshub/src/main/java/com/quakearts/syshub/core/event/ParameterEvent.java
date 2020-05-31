package com.quakearts.syshub.core.event;

import java.util.EventObject;

import com.quakearts.syshub.model.AgentConfigurationParameter;

/**Event Object that wraps the {@linkplain AgentConfigurationParameter}
 * that has changed
 * @author kwaku
 *
 */
public class ParameterEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5036346063577949800L;

	public enum EventType {
		DELETED,
		UPDATED;
	}
	
	private final EventType eventType;
	
	public ParameterEvent(AgentConfigurationParameter source, EventType eventType) {
		super(source);
		this.eventType = eventType;
	}

	/**Get the {@link AgentConfigurationParameter}
	 *
	 */
	@Override
	public AgentConfigurationParameter getSource() {
		return (AgentConfigurationParameter) super.getSource();
	}
	
	/**Get the {@link EventType}
	 * @return the event type (one of UPDATED,DELETED)
	 */
	public EventType getEventType() {
		return eventType;
	}
}

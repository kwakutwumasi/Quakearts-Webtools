package com.quakearts.syshub.core.event;

import com.quakearts.syshub.model.AgentConfigurationParameter;

public interface ParameterEventListener {

    /**This method should be implemented by modules that are interested in 
     * changes to {@linkplain AgentConfigurationParameter}. They will also 
     * need to register as a listener using the 
     * {@link com.quakearts.syshub.core.event.ParameterEventBroadcaster ParameterEventBroadcaster}
     * service, which can be injected into the bean at startup.
     * @param changeEvent
     */
    void handleParameterChanged(ParameterEvent changeEvent);
}

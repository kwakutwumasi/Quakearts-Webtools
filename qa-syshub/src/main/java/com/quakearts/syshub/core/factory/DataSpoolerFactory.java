package com.quakearts.syshub.core.factory;

import com.quakearts.syshub.core.DataSpooler;
import com.quakearts.syshub.core.utils.SysHubUtils;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentModule;

import java.util.Map;

import javax.enterprise.inject.spi.CDI;
import javax.naming.NamingException;

public class DataSpoolerFactory {

	private static final String SPOOLER = "spooler:/";

	private DataSpoolerFactory() {
	}

	private static DataSpoolerFactory factory = new DataSpoolerFactory();
	
	public static DataSpoolerFactory getFactory() {
		return factory;
	}
	
    @SuppressWarnings("rawtypes")
	public DataSpooler getInstance(String instancename) throws ConfigurationException {
        Class dataSpoolerClass;
        try {
			dataSpoolerClass = Class.forName(instancename);
		} catch (ClassNotFoundException e) {
			throw new ConfigurationException(e.getMessage(), e);
		}
        
        if(!DataSpooler.class.isAssignableFrom(dataSpoolerClass))
        	throw new ConfigurationException(instancename+" does not implement "+DataSpooler.class.getName());
            
        @SuppressWarnings("unchecked")
		DataSpooler dataSpooler = (DataSpooler) CDI.current().select(dataSpoolerClass).get();
        return dataSpooler;
    }

    public DataSpooler getInstance(String instancename, 
    		Map<String, AgentConfigurationParameter> parameters,
    		AgentModule module) throws ConfigurationException {
    	DataSpooler dataSpooler;
    	if(module.getModuleName() != null && !module.getModuleName().trim().isEmpty()){
        	try {
				dataSpooler = (DataSpooler) SysHubUtils.getInitialContext().lookup(SPOOLER+module.getModuleName());
				if(dataSpooler!=null)
					return dataSpooler;
			} catch (NamingException e) {
			} catch (ClassCastException e) {
	            throw new ConfigurationException(module.getModuleName()+" bound to context does not implement "+DataSpooler.class.getName());
			}
        }    	
    	
        dataSpooler = getInstance(instancename);
        dataSpooler.setupWithConfigurationParameters(parameters);
        dataSpooler.setAgentConfiguration(module.getAgentConfiguration().cloneById());

        if(module.getModuleName() != null && !module.getModuleName().trim().isEmpty()){
        	try {
				SysHubUtils.getInitialContext().bind(SPOOLER+module.getModuleName(), dataSpooler);
			} catch (NamingException e) {
	            throw new ConfigurationException("Unable to bind "+module.getModuleName()+" to context.");
			}
        }
        
        return dataSpooler;
    }
}

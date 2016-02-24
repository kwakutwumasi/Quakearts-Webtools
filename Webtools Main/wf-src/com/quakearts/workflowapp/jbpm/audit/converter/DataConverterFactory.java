package com.quakearts.workflowapp.jbpm.audit.converter;

import java.util.Properties;

public class DataConverterFactory {
    public static DataConverter getInstance(String instancename) 
    throws ClassNotFoundException,InstantiationException,IllegalAccessException 
    {
        Class<?> dataspoolerclass = Class.forName(instancename);
        Object obj = dataspoolerclass.newInstance();
        if(obj instanceof DataConverter){
            DataConverter dtcv = (DataConverter) obj;
            return dtcv;
        }
        return null;
    }
    public static DataConverter getInstance(String instancename, Properties props) throws ClassNotFoundException,InstantiationException,IllegalAccessException 
    {
        DataConverter conv = getInstance(instancename);
        conv.initialize(props);
        return conv;
    }
}

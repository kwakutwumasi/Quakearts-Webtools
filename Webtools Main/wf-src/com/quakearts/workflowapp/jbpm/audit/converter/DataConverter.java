package com.quakearts.workflowapp.jbpm.audit.converter;

import java.util.Properties;

public interface DataConverter {
    public String convert(Object obj);
    public void initialize(Properties props);
}

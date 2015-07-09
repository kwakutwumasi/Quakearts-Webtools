package com.quakearts.workflowapp.jbpm.audit.converters;

import com.quakearts.workflowapp.jbpm.audit.converter.DataConverter;

import java.util.Properties;

public class DefaultConverter implements DataConverter {
    public DefaultConverter() {
    }

    public String convert(Object obj) {
        return obj.toString();
    }

    public void initialize(Properties props) {
    }
}

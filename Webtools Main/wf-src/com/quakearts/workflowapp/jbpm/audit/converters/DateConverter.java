package com.quakearts.workflowapp.jbpm.audit.converters;

import com.quakearts.workflowapp.jbpm.audit.converter.DataConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class DateConverter implements DataConverter {
    private static DateFormat dtfrmt = new SimpleDateFormat("EE dd MMM, yyyy HH:mm:ss. S");
    private String format=null;
    public DateConverter() {
    }

    public String convert(Object obj) {
        if(obj instanceof Date){
            if(format!=null)
                return new SimpleDateFormat(format).format((Date)obj);
            else
                return dtfrmt.format((Date)obj);
        }
        return "";
    }

    public void initialize(Properties props) {
        format = props.getProperty("date.format");
    }
}

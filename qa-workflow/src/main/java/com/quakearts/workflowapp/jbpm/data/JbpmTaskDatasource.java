package com.quakearts.workflowapp.jbpm.data;

import java.util.Collection;
import java.util.Map;

public interface JbpmTaskDatasource {
    public boolean search();
    public void setQueryName(String queryName);
    public Object getDetail(String detail);
    public void setDetail(String detail, Object detailobj);
    public Map<String, Object> getDetails();
    public Collection<Object> getValues();
}

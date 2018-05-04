/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.syshub.core.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.quakearts.syshub.core.Result;

/**Core object for holding the data results a data spooler
 * @author Kwaku Twumasi-Afriyie
 *
 */
public class ResultImpl implements Result<Object> {

	private static final long serialVersionUID = 9198455986339975423L;
	private Properties properties;
    private List<Map<String, Object>> dataresults=new ArrayList<>(),
    		metaresults = new ArrayList<>();
    
    public ResultImpl(Properties properties){
        this.properties = properties;
    }
    
    public ResultImpl(){
    		properties = new Properties();
    }

    /* (non-Javadoc)
	 * @see com.quakearts.notification.core.IResult#addProperties(java.lang.String, java.lang.String)
	 */
    @Override
	public Result<Object> addProperty(String name, String property){
        properties.setProperty(name, property) ;
        return this;
    }
    
    /* (non-Javadoc)
	 * @see com.quakearts.notification.core.IResult#getProperties()
	 */
    @Override
	public Properties getProperties(){
		return properties;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IResult#addQueryResults(java.util.Map)
	 */
	@Override
	public Result<Object> addRow(Map<String, Object> rs){
        if(rs.size()>0){
            dataresults.add(rs);
        }
        return this;
    }
    
    /* (non-Javadoc)
	 * @see com.quakearts.notification.core.IResult#addMetaResults(java.util.Map)
	 */
    @Override
	public Result<Object> addMetaDataResult(Map<String, Object> rs){
        if(rs.size()>0){
            metaresults.add(rs);
        }
        return this;
    }
    
    /* (non-Javadoc)
	 * @see com.quakearts.notification.core.IResult#getDataResults()
	 */
    @Override
	public List<Map<String, Object>> getDataResults() {
        return Collections.unmodifiableList(dataresults);
    }
    
    /* (non-Javadoc)
	 * @see com.quakearts.notification.core.IResult#hasDataResults()
	 */
    @Override
	public boolean hasResults(){
        return dataresults.size()>0;
    }

    /* (non-Javadoc)
	 * @see com.quakearts.notification.core.IResult#hasMetaDataResults()
	 */
    @Override
	public boolean hasMetaDataResults(){
        return metaresults.size()>0;
    }
    
    /* (non-Javadoc)
	 * @see com.quakearts.notification.core.IResult#getDataResultSize()
	 */
    @Override
	public int getDataResultSize(){
        return dataresults.size();
    }

    /* (non-Javadoc)
	 * @see com.quakearts.notification.core.IResult#addAllDataResults(java.util.List)
	 */
    @Override
	public Result<Object> addAllRows(List<Map<String, Object>> results) {
        dataresults.addAll(results);
        return this;
    }

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IResult#getMetaDataResults()
	 */
	@Override
	public List<Map<String, Object>> getMetaDataResults() {
		return Collections.unmodifiableList(metaresults);
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IResult#addAllMetaDataResults(java.util.List)
	 */
	@Override
	public Result<Object> addAllMetaData(List<Map<String, Object>> metaresults) {
		this.metaresults.addAll(metaresults);
        return this;		
	}

	@Override
	public Iterator<Map<String, Object>> iterator() {
		return dataresults.iterator();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataresults == null) ? 0 : dataresults.hashCode());
		result = prime * result + ((metaresults == null) ? 0 : metaresults.hashCode());
		result = prime * result + ((properties == null) ? 0 : properties.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultImpl other = (ResultImpl) obj;
		if (dataresults == null) {
			if (other.dataresults != null)
				return false;
		} else if (!dataresults.equals(other.dataresults))
			return false;
		if (metaresults == null) {
			if (other.metaresults != null)
				return false;
		} else if (!metaresults.equals(other.metaresults))
			return false;
		if (properties == null) {
			if (other.properties != null)
				return false;
		} else if (!properties.equals(other.properties))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResultImpl [properties=" + properties + ", dataresults=" + dataresults + ", metaresults=" + metaresults
				+ "]";
	}
}

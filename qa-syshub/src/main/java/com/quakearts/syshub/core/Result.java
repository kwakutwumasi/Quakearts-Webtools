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
package com.quakearts.syshub.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**This class encapsulates the entirety of data required to create a single message. 
 * It is passed to a formatter to transforms
 * into the {@link Message} to be processed
 * 
 * @author kwaku.twumasi@quakearts.com
 *
 */
public interface Result extends Serializable, Iterable<Map<String, Object>> {

	/**Add a result specific property
	 * @param name
	 * @param property
	 * @return
	 */
	Result addProperty(String name, String property);

	/**Get result properties
	 * @return
	 */
	Properties getProperties();

	/**Add a result to the 
	 * @param rs
	 * @return
	 */
	Result addRow(Map<String, Object> rs);

	/**Add Results that describe the main results
	 * @param rs
	 * @return
	 */
	Result addMetaDataResult(Map<String, Object> rs);

	/**Return a List of Map objects of column/data tuples
	 * @return 
	 */
	List<Map<String, Object>> getDataResults();

	/**Determines if there are data results
	 * @return True if there are Results
	 */
	boolean hasResults();

	/**
	 * @return
	 */
	boolean hasMetaDataResults();

	/**
	 * @return
	 */
	int getDataResultSize();

	/**
	 * @param results
	 * @return
	 */
	Result addAll(List<Map<String, Object>> results);

	/**Get meta data for this Result object
	 * @return the metaresults
	 */
	List<Map<String, Object>> getMetaDataResults();

	/**Add all the metadata
	 * @param metaresults the metaresults to set
	 */
	Result addAllMetaData(List<Map<String, Object>> metaresults);

}

/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.util;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * A special implementation of {@link java.util.LinkedHashMap} to provide
 * LRU functionality.
 */
public class LRUMap<K,V> extends LinkedHashMap<K,V> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3129489648273163446L;
	private int maxCapacity;

    // ------------------------------------------------------------ Constructors

    public LRUMap(int maxCapacity) {
        super(maxCapacity, 1.0f, true);
        this.maxCapacity = maxCapacity;        
    }

    // ---------------------------------------------- Methods from LinkedHashMap

    @SuppressWarnings("rawtypes")
	protected boolean removeEldestEntry(Map.Entry eldest) {
        return (size() > maxCapacity);   
    }
    
    // TEST: com.sun.faces.TestLRUMap_local
}

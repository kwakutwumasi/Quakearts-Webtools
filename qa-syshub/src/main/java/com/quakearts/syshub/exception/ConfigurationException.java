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
package com.quakearts.syshub.exception;

public class ConfigurationException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7293767477390702563L;
	public ConfigurationException(Exception e){
        super(e);
    }
    public ConfigurationException(String msg, Exception e){
        super(msg, e);
    }
    public ConfigurationException(String msg){
        super(msg);
    }
}

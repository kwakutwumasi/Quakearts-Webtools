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

import javax.enterprise.inject.Vetoed;

@Vetoed
public class ProcessingException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2618970868113563139L;
	public ProcessingException(Exception e) {
        super(e);
    }
    public ProcessingException(String msg) {
        super(msg);
    }
    public ProcessingException(String msg, Exception e) {
        super(msg,e);
    }
}

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
package com.quakearts.security.cryptography.exception;

public class IllegalCryptoActionException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4173764902983590551L;

	public IllegalCryptoActionException() {
        super();
    }

   public IllegalCryptoActionException(String message) {
        super(message);
    }
    
    public IllegalCryptoActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCryptoActionException(Throwable cause) {
        super(cause);
    }
}

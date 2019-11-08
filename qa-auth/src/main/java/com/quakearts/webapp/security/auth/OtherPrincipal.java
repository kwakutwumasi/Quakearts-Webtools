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
package com.quakearts.webapp.security.auth;

public class OtherPrincipal extends DirectoryPrincipal {
    private String attribute = "other";
    
    public OtherPrincipal(String name) {
    	super(name);
    }

    public OtherPrincipal(String name,String attribute) {
    	super(name);
        this.attribute=attribute;
    }

    @Override
    public String getAttribute() {
        return attribute;
    }    
}

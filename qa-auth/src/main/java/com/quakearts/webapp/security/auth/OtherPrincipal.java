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
package com.quakearts.webapp.security.auth;

import java.security.Principal;

public class OtherPrincipal implements Principal{
    private String name;
    private String attribute = "other";
    
    public OtherPrincipal(String name) {
        this.name = name;
    }

    public OtherPrincipal(String name,String attribute) {
        this.name = name;
        this.attribute=attribute;
    }

    public String getAttribute() {
        return attribute;
    }
    
    @Override
    public String getName() {
        return name;
    }
}

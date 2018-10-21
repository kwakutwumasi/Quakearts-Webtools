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

import java.security.Principal;

public abstract class DirectoryPrincipal implements Principal{
    String value;
    public DirectoryPrincipal(String value) {
        this.value = value==null?"":value;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAttribute() == null) ? 0 : getAttribute().hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		
		DirectoryPrincipal other = (DirectoryPrincipal) obj;
		if (getAttribute() == null) {
			if (other.getAttribute() != null)
				return false;
		} else if (!getAttribute().equals(other.getAttribute())) {
			return false;
		}
		
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	public String getName() {
        return value;
    }
    
    public abstract String getAttribute();
}

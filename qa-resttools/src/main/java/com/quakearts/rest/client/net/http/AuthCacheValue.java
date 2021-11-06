/*******************************************************************************
* Copyright (C) 2021 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.rest.client.net.http;

import java.io.Serializable;
import java.net.PasswordAuthentication;

public abstract class AuthCacheValue implements Serializable {

    static final long serialVersionUID = 735249334068211611L;

    public enum Type {
        PROXY,
        SERVER
    }

    protected static AuthCache cache = new AuthCacheImpl();

    public static void setAuthCache (AuthCache map) {
        cache = map;
    }

    AuthCacheValue() {}

    abstract Type getAuthType ();

    abstract AuthScheme getAuthScheme();

    abstract String getHost ();

    abstract int getPort();

    abstract String getRealm();

    abstract String getPath();

    abstract String getProtocolScheme();

    abstract PasswordAuthentication credentials();
}

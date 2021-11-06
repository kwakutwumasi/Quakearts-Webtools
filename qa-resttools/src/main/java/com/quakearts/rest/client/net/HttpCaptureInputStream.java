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
package com.quakearts.rest.client.net;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpCaptureInputStream extends FilterInputStream {
    private HttpCapture capture = null;

    public HttpCaptureInputStream(InputStream in, HttpCapture cap) {
        super(in);
        capture = cap;
    }

    @Override
    public int read() throws IOException {
        int i = super.read();
        capture.received(i);
        return i;
    }

    @Override
    public void close() throws IOException {
        try {
            capture.flush();
        } catch (IOException iOException) {
        	//Do nothing
        }
        super.close();
    }

    @Override
    public int read(byte[] b) throws IOException {
        int ret = super.read(b);
        for (int i = 0; i < ret; i++) {
            capture.received(b[i]);
        }
        return ret;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int ret = super.read(b, off, len);
        for (int i = 0; i < ret; i++) {
            capture.received(b[off+i]);
        }
        return ret;
    }
}

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
import java.io.*;

public class HttpCaptureOutputStream extends FilterOutputStream {
    private HttpCapture capture = null;

    public HttpCaptureOutputStream(OutputStream out, HttpCapture cap) {
        super(out);
        capture = cap;
    }

    @Override
    public void write(int b) throws IOException {
        capture.sent(b);
        out.write(b);
    }

    @Override
    public void write(byte[] ba) throws IOException {
        for (byte b : ba) {
            capture.sent(b);
        }
        out.write(ba);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (int i = off; i < len; i++) {
            capture.sent(b[i]);
        }
        out.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        try {
            capture.flush();
        } catch (IOException iOException) {
        	//Do nothing
        }
        super.flush();
    }
}

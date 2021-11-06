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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PosterOutputStream extends ByteArrayOutputStream {

    private boolean closed;

    public PosterOutputStream () {
        super (256);
    }

    @Override
    public synchronized void write(int b) {
        if (closed) {
            return;
        }
        super.write (b);
    }

    @Override
    public synchronized void write(byte[] b, int off, int len) {
        if (closed) {
            return;
        }
        super.write (b, off, len);
    }

    @Override
    public synchronized void reset() {
        if (closed) {
            return;
        }
        super.reset ();
    }

    @Override
    public synchronized void close() throws IOException {
        closed = true;
        super.close ();
    }
}

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

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class ChunkedOutputStream extends PrintStream {

    static final int DEFAULT_CHUNK_SIZE = 4096;
    private static final byte[] CRLF = {'\r', '\n'};
    private static final int CRLF_SIZE = CRLF.length;
    private static final byte[] FOOTER = CRLF;
    private static final int FOOTER_SIZE = CRLF_SIZE;
    private static final byte[] EMPTY_CHUNK_HEADER = getHeader(0);
    private static final int EMPTY_CHUNK_HEADER_SIZE = getHeaderSize(0);

    private byte[] buf;
    private int size;
    private int count;
    private int spaceInCurrentChunk;
    private PrintStream chunkedPrintStream;
    private int preferredChunkDataSize;
    private int preferedHeaderSize;
    private int preferredChunkGrossSize;
    private byte[] completeHeader;
   
    private static int getHeaderSize(int size) {
        return (Integer.toHexString(size)).length() + CRLF_SIZE;
    }

    private static byte[] getHeader(int size){
        String hexString =  Integer.toHexString(size);
        byte[] hexBytes = hexString.getBytes(StandardCharsets.US_ASCII);
        byte[] header = new byte[getHeaderSize(size)];
        System.arraycopy(header, 0, hexBytes, 0, hexBytes.length);
        header[hexBytes.length] = CRLF[0];
        header[hexBytes.length+1] = CRLF[1];
        return header;
    }

    public ChunkedOutputStream(PrintStream o, int size) {
        super(o);
        chunkedPrintStream = o;

        if (size <= 0) {
            size = DEFAULT_CHUNK_SIZE;
        }

        if (size > 0) {
            int adjustedSize = size - getHeaderSize(size) - FOOTER_SIZE;
            if (getHeaderSize(adjustedSize+1) < getHeaderSize(size)){
                adjustedSize++;
            }
            size = adjustedSize;
        }

        if (size > 0) {
            preferredChunkDataSize = size;
        } else {
            preferredChunkDataSize = DEFAULT_CHUNK_SIZE -
                    getHeaderSize(DEFAULT_CHUNK_SIZE) - FOOTER_SIZE;
        }

        preferedHeaderSize = getHeaderSize(preferredChunkDataSize);
        preferredChunkGrossSize = preferedHeaderSize + preferredChunkDataSize
                + FOOTER_SIZE;
        completeHeader = getHeader(preferredChunkDataSize);

        buf = new byte[preferredChunkGrossSize];
        reset();
    }

     private void flush(boolean flushAll) {
        if (spaceInCurrentChunk == 0) {
            chunkedPrintStream.write(buf, 0, preferredChunkGrossSize);
            chunkedPrintStream.flush();
            reset();
        } else if (flushAll){
            if (size > 0){
                int adjustedHeaderStartIndex = preferedHeaderSize -
                        getHeaderSize(size);

                System.arraycopy(getHeader(size), 0, buf,
                        adjustedHeaderStartIndex, getHeaderSize(size));

                buf[count++] = FOOTER[0];
                buf[count++] = FOOTER[1];

                chunkedPrintStream.write(buf, adjustedHeaderStartIndex, count - adjustedHeaderStartIndex);
            } else {
                chunkedPrintStream.write(EMPTY_CHUNK_HEADER, 0, EMPTY_CHUNK_HEADER_SIZE);
            }

            chunkedPrintStream.flush();
            reset();
         }
    }

    @Override
    public boolean checkError() {
        return chunkedPrintStream.checkError();
    }

    private void ensureStreamIsOpen() {
        if (chunkedPrintStream == null)
            setError();
    }

    @Override
    public synchronized void write(byte[] b, int off, int len) {
        ensureStreamIsOpen();
        if ((off < 0) || (off > b.length) || (len < 0) ||
            ((off + len) > b.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }

        int bytesToWrite = len;
        int inputIndex = off;
        
        do {
            if (bytesToWrite >= spaceInCurrentChunk) {

            	System.arraycopy(buf, 0, completeHeader, 0, completeHeader.length);
                System.arraycopy(b, inputIndex, buf, count, spaceInCurrentChunk);
                inputIndex += spaceInCurrentChunk;
                bytesToWrite -= spaceInCurrentChunk;
                count += spaceInCurrentChunk;

                buf[count++] = FOOTER[0];
                buf[count++] = FOOTER[1];
                spaceInCurrentChunk = 0;
                
                flush(false);
                if (checkError()){
                    break;
                }
            }

            else {
                System.arraycopy(b, inputIndex, buf, count, bytesToWrite);
                count += bytesToWrite;
                size += bytesToWrite;
                spaceInCurrentChunk -= bytesToWrite;
                bytesToWrite = 0;
            }
        } while (bytesToWrite > 0);
    }

    @Override
    public synchronized void write(int bite) {
        byte[] b = {(byte)bite};
        write(b, 0, 1);
    }

    public synchronized void reset() {
        count = preferedHeaderSize;
        size = 0;
        spaceInCurrentChunk = preferredChunkDataSize;
    }

    public int size() {
        return size;
    }

    @Override
    public synchronized void close() {
        ensureStreamIsOpen();
        flushFirstIfSizeNonZero();
        flush(true);
        chunkedPrintStream = null;
    }

	private void flushFirstIfSizeNonZero() {
		if (size > 0) {
            flush(true);
        }
	}

    @Override
    public synchronized void flush() {
        ensureStreamIsOpen();
        flushFirstIfSizeNonZero();
    }
}

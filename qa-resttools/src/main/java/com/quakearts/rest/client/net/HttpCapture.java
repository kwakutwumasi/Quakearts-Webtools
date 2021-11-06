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
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.*;

import com.quakearts.rest.client.net.http.HttpURLConnectionImpl;

public class HttpCapture {
    private File file = null;
    private boolean incoming = true;
    private BufferedWriter out = null;
    private static boolean initialized = false;
    private static volatile ArrayList<Pattern> patterns = null;
    private static volatile ArrayList<String> capFiles = null;
    private static Random rand = new Random();

    private static synchronized void init() {
        initialized = true;
        String rulesFile = NetProperties.get("com.quakearts.net.http.captureRules");
        if (rulesFile != null && !rulesFile.isEmpty()) {
            BufferedReader in;
            try {
                in = new BufferedReader(new FileReader(rulesFile));
            } catch (FileNotFoundException ex) {
                return;
            }
            try {
                String line = in.readLine();
                while (line != null) {
                    line = line.trim();
                    if (!line.startsWith("#")) {
                        
                        String[] s = line.split(",");
                        if (s.length == 2) {
                            if (patterns == null) {
                                patterns = new ArrayList<>();
                                capFiles = new ArrayList<>();
                            }
                            patterns.add(Pattern.compile(s[0].trim()));
                            capFiles.add(s[1].trim());
                        }
                    }
                    line = in.readLine();
                }
            } catch (IOException ioe) {
            	//Do nothing
            } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                	//Do nothing
                }
            }
        }
    }

    private static synchronized boolean isInitialized() {
        return initialized;
    }

    private HttpCapture(File f, URL url) {
        file = f;
        try {
            out = new BufferedWriter(new FileWriter(file, true));
            out.write("URL: " + url + "\n");
        } catch (IOException ex) {
            HttpURLConnectionImpl.getHttpLogger().error("", ex);
        }
    }

    public synchronized void sent(int c) throws IOException {
        if (incoming) {
            out.write("\n------>\n");
            incoming = false;
            out.flush();
        }
        out.write(c);
    }

    public synchronized void received(int c) throws IOException {
        if (!incoming) {
            out.write("\n<------\n");
            incoming = true;
            out.flush();
        }
        out.write(c);
    }

    public synchronized void flush() throws IOException {
        out.flush();
    }

    public static HttpCapture getCapture(URL url) {
        if (!isInitialized()) {
            init();
        }
        if (patterns == null || patterns.isEmpty()) {
            return null;
        }
        String s = url.toString();
        for (int i = 0; i < patterns.size(); i++) {
            Pattern p = patterns.get(i);
            if (p.matcher(s).find()) {
                String f = capFiles.get(i);
                File fi;
                if (f.indexOf("%d") >= 0) {
                    do {
                        String f2 = f.replace("%d", Integer.toString(rand.nextInt()));
                        fi = new File(f2);
                    } while (fi.exists());
                } else {
                    fi = new File(f);
                }
                return new HttpCapture(fi, url);
            }
        }
        return null;
    }
}

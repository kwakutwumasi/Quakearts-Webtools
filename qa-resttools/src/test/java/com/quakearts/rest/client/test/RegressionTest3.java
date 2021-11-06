package com.quakearts.rest.client.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest3 {

    public static boolean debug = false;

    @Test
    public void test1501() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1501");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler2 = new com.quakearts.rest.client.net.http.HttpHandler();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl3 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler2);
        // The following exception was thrown during execution in test generation
        try {
            long long6 = httpURLConnectionImpl3.getHeaderFieldLong("\000", (long) (byte) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test1502() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1502");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        long long11 = httpsURLConnectionImpl3.getHeaderFieldDate("hi!", (long) 206);
        httpsURLConnectionImpl3.setUseCaches(true);
        java.lang.String str14 = httpsURLConnectionImpl3.getContentType();
        httpsURLConnectionImpl3.setChunkedStreamingMode((int) '4');
        java.lang.String str17 = httpsURLConnectionImpl3.toString();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + 206L + "'", long11 == 206L);
        org.junit.Assert.assertNull(str14);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str17, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
    }

    @Test
    public void test1503() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1503");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setDoInput(false);
        javax.net.ssl.HostnameVerifier hostnameVerifier10 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier11 = httpsURLConnectionImpl3.getHostnameVerifier();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNotNull(hostnameVerifier10);
        org.junit.Assert.assertNotNull(hostnameVerifier11);
    }

    @Test
    public void test1504() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1504");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler5 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl6 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL3, proxy4, httpsHandler5);
        javax.net.ssl.HostnameVerifier hostnameVerifier7 = httpsURLConnectionImpl6.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier8 = httpsURLConnectionImpl6.getHostnameVerifier();
        httpsURLConnectionImpl6.setFixedLengthStreamingMode(401);
        java.net.CacheRequest cacheRequest11 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpsURLConnectionImpl6);
        long long14 = httpsURLConnectionImpl6.getHeaderFieldLong("hi!", (long) 100);
        long long15 = httpsURLConnectionImpl6.getDate();
        java.net.URL uRL16 = null;
        java.net.Proxy proxy17 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler18 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl19 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL16, proxy17, httpsHandler18);
        javax.net.ssl.HostnameVerifier hostnameVerifier20 = httpsURLConnectionImpl19.getHostnameVerifier();
        boolean boolean21 = httpsURLConnectionImpl19.getDoInput();
        boolean boolean22 = httpsURLConnectionImpl19.getInstanceFollowRedirects();
        httpsURLConnectionImpl19.setChunkedStreamingMode(10);
        java.lang.String str26 = httpsURLConnectionImpl19.getHeaderFieldKey(402);
        boolean boolean27 = httpsURLConnectionImpl19.getUseCaches();
        httpsURLConnectionImpl19.setConnectTimeout(1);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap30 = httpsURLConnectionImpl19.getHeaderFields();
        java.lang.Class<?> wildcardClass31 = httpsURLConnectionImpl19.getClass();
        com.quakearts.rest.client.net.MessageHeader messageHeader32 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str34 = messageHeader32.getValue(503);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap35 = messageHeader32.getHeaders();
        messageHeader32.set("{size=10 nkeys=1  {hi!} }", "content/unknown");
        java.lang.Class<?> wildcardClass39 = messageHeader32.getClass();
        java.lang.Class[] classArray40 = new java.lang.Class[] { wildcardClass31, wildcardClass39 };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj41 = httpsURLConnectionImpl6.getContent(classArray40);
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + 100L + "'", long14 == 100L);
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + 0L + "'", long15 == 0L);
        org.junit.Assert.assertNotNull(hostnameVerifier20);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + true + "'", boolean27 == true);
        org.junit.Assert.assertNotNull(strMap30);
        org.junit.Assert.assertNotNull(wildcardClass31);
        org.junit.Assert.assertNull(str34);
        org.junit.Assert.assertNotNull(strMap35);
        org.junit.Assert.assertNotNull(wildcardClass39);
        org.junit.Assert.assertNotNull(classArray40);
    }

    @Test
    public void test1505() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1505");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor27 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        long long31 = progressSource30.getExpected();
        progressMonitor27.registerSource(progressSource30);
        java.lang.String str33 = progressSource30.toString();
        com.quakearts.rest.client.net.HttpClient httpClient35 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream36 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream10, progressSource30, (long) 203, httpClient35);
        com.quakearts.rest.client.net.HttpClient httpClient37 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader38 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str40 = messageHeader38.getValue(503);
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream41 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) meteredStream10, httpClient37, messageHeader38);
        com.quakearts.rest.client.net.HttpClient httpClient42 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader43 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str45 = messageHeader43.getValue(503);
        messageHeader43.prepend("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {hi!} }");
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream49 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) meteredStream10, httpClient42, messageHeader43);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream50 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream50.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture52 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream53 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream50, httpCapture52);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream54 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream54.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture56 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream57 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream54, httpCapture56);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream58 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray59 = posterOutputStream58.toByteArray();
        httpCaptureOutputStream57.write(byteArray59);
        httpCaptureOutputStream53.write(byteArray59);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream62 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray63 = posterOutputStream62.toByteArray();
        httpCaptureOutputStream53.write(byteArray63);
        // The following exception was thrown during execution in test generation
        try {
            int int67 = chunkedInputStream49.read(byteArray63, (int) (byte) 0, 203);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
        org.junit.Assert.assertNull(str45);
        org.junit.Assert.assertNotNull(byteArray59);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray59), "[]");
        org.junit.Assert.assertNotNull(byteArray63);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray63), "[]");
    }

    @Test
    public void test1506() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1506");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource.State state9 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent12 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL6, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state9, (long) 300, (-1L));
        long long13 = progressEvent12.getExpected();
        long long14 = progressEvent12.getProgress();
        java.lang.Object obj15 = progressEvent12.getSource();
        java.net.URL uRL16 = progressEvent12.getURL();
        java.lang.String str17 = progressEvent12.getContentType();
        long long18 = progressEvent12.getExpected();
        java.lang.Object obj19 = progressEvent12.getSource();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-1L) + "'", long13 == (-1L));
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + 300L + "'", long14 == 300L);
        org.junit.Assert.assertNotNull(obj15);
        org.junit.Assert.assertEquals(obj15.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj15), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj15), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(uRL16);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str17, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertTrue("'" + long18 + "' != '" + (-1L) + "'", long18 == (-1L));
        org.junit.Assert.assertNotNull(obj19);
        org.junit.Assert.assertEquals(obj19.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj19), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj19), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test1507() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1507");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int1 = progressMonitor0.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressMonitor.setDefault(progressMonitor0);
        java.net.URL uRL3 = null;
        boolean boolean5 = progressMonitor0.shouldMeterInput(uRL3, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.net.URL uRL6 = null;
        boolean boolean8 = progressMonitor0.shouldMeterInput(uRL6, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.net.URL uRL9 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource11 = new com.quakearts.rest.client.net.ProgressSource(uRL9, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        long long12 = progressSource11.getProgress();
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(progressSource11);
        java.net.URL uRL14 = progressSource11.getURL();
        java.net.URL uRL15 = progressSource11.getURL();
        progressMonitor0.unregisterSource(progressSource11);
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource19 = new com.quakearts.rest.client.net.ProgressSource(uRL17, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=UPDATE, content-type=content/unknown, progress=0, expected=32]");
        progressMonitor0.unregisterSource(progressSource19);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8192 + "'", int1 == 8192);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
        org.junit.Assert.assertNull(uRL14);
        org.junit.Assert.assertNull(uRL15);
    }

    @Test
    public void test1508() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1508");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        java.io.InputStream inputStream10 = httpURLConnectionImpl5.getErrorStream();
        java.io.InputStream inputStream11 = httpURLConnectionImpl5.getErrorStream();
        boolean boolean12 = httpURLConnectionImpl5.streaming();
        java.io.InputStream inputStream13 = httpURLConnectionImpl5.getErrorStream();
        java.net.CookieHandler cookieHandler14 = httpURLConnectionImpl5.getCookieHandler();
        java.net.URL uRL15 = null;
        java.net.Proxy proxy16 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler19 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl20 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL15, proxy16, httpHandler19);
        int int21 = httpURLConnectionImpl20.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState22 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl20.setTunnelState(tunnelState22);
        boolean boolean24 = httpURLConnectionImpl20.streaming();
        java.io.InputStream inputStream25 = httpURLConnectionImpl20.getErrorStream();
        java.io.InputStream inputStream26 = httpURLConnectionImpl20.getErrorStream();
        java.io.InputStream inputStream27 = httpURLConnectionImpl20.getErrorStream();
        boolean boolean28 = httpURLConnectionImpl20.usingProxy();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState29 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.NONE;
        httpURLConnectionImpl20.setTunnelState(tunnelState29);
        httpURLConnectionImpl5.setTunnelState(tunnelState29);
        java.lang.Object obj32 = httpURLConnectionImpl5.authObj();
        java.io.InputStream inputStream33 = httpURLConnectionImpl5.getErrorStream();
        httpURLConnectionImpl5.setAuthenticationProperty("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], state=UPDATE, content-type=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], progress=501, expected=304]");
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertNull(inputStream11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNull(inputStream13);
        org.junit.Assert.assertNull(cookieHandler14);
        org.junit.Assert.assertTrue("'" + int21 + "' != '" + 0 + "'", int21 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState22 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState22.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNull(inputStream25);
        org.junit.Assert.assertNull(inputStream26);
        org.junit.Assert.assertNull(inputStream27);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertTrue("'" + tunnelState29 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.NONE + "'", tunnelState29.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.NONE));
        org.junit.Assert.assertNull(obj32);
        org.junit.Assert.assertNull(inputStream33);
    }

    @Test
    public void test1509() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1509");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor27 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        long long31 = progressSource30.getExpected();
        progressMonitor27.registerSource(progressSource30);
        java.lang.String str33 = progressSource30.toString();
        com.quakearts.rest.client.net.HttpClient httpClient35 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream36 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream10, progressSource30, (long) 203, httpClient35);
        com.quakearts.rest.client.net.HttpClient httpClient37 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader38 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str40 = messageHeader38.getValue(503);
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream41 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) meteredStream10, httpClient37, messageHeader38);
        com.quakearts.rest.client.net.HttpCapture httpCapture42 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream43 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) meteredStream10, httpCapture42);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureInputStream43.reset();
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: Resetting to an invalid mark");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
    }

    @Test
    public void test1510() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1510");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findValue(305);
        java.lang.String str5 = headerParser1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.util.Iterator<java.lang.String> strItor6 = headerParser1.values();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertNotNull(strItor6);
    }

    @Test
    public void test1511() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1511");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.URI uRI1 = null;
        java.net.URL uRL2 = null;
        java.net.Proxy proxy3 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler4 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl5 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL2, proxy3, httpsHandler4);
        javax.net.ssl.HostnameVerifier hostnameVerifier6 = httpsURLConnectionImpl5.getHostnameVerifier();
        boolean boolean7 = httpsURLConnectionImpl5.getDoInput();
        httpsURLConnectionImpl5.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl5.addRequestProperty("", "hi!");
        int int15 = httpsURLConnectionImpl5.getHeaderFieldInt("hi!", 100);
        java.net.CacheRequest cacheRequest16 = responseCacheImpl0.put(uRI1, (java.net.URLConnection) httpsURLConnectionImpl5);
        httpsURLConnectionImpl5.setUseCaches(false);
        long long21 = httpsURLConnectionImpl5.getHeaderFieldLong("", (long) 304);
        boolean boolean22 = httpsURLConnectionImpl5.getDefaultUseCaches();
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertTrue("'" + long21 + "' != '" + 304L + "'", long21 == 304L);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
    }

    @Test
    public void test1512() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1512");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setIfModifiedSince(0L);
        boolean boolean11 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setReadTimeout((int) (byte) 2);
        httpsURLConnectionImpl3.setConnectTimeout(0);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
    }

    @Test
    public void test1513() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1513");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor27 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        long long31 = progressSource30.getExpected();
        progressMonitor27.registerSource(progressSource30);
        java.lang.String str33 = progressSource30.toString();
        com.quakearts.rest.client.net.HttpClient httpClient35 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream36 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream10, progressSource30, (long) 203, httpClient35);
        boolean boolean37 = keepAliveStream36.markSupported();
        keepAliveStream36.mark(505);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor40 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL41 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource43 = new com.quakearts.rest.client.net.ProgressSource(uRL41, "");
        long long44 = progressSource43.getExpected();
        progressMonitor40.registerSource(progressSource43);
        java.lang.String str46 = progressSource43.getContentType();
        com.quakearts.rest.client.net.MeteredStream meteredStream48 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) keepAliveStream36, progressSource43, (long) (byte) 10);
        boolean boolean49 = keepAliveStream36.markSupported();
        boolean boolean50 = keepAliveStream36.markSupported();
        keepAliveStream36.mark((int) 'p');
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertTrue("'" + long44 + "' != '" + (-1L) + "'", long44 == (-1L));
        org.junit.Assert.assertEquals("'" + str46 + "' != '" + "content/unknown" + "'", str46, "content/unknown");
        org.junit.Assert.assertTrue("'" + boolean49 + "' != '" + false + "'", boolean49 == false);
        org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + false + "'", boolean50 == false);
    }

    @Test
    public void test1514() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1514");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor27 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        long long31 = progressSource30.getExpected();
        progressMonitor27.registerSource(progressSource30);
        java.lang.String str33 = progressSource30.toString();
        com.quakearts.rest.client.net.HttpClient httpClient35 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream36 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream10, progressSource30, (long) 203, httpClient35);
        boolean boolean37 = keepAliveStream36.markSupported();
        boolean boolean38 = keepAliveStream36.markSupported();
        java.lang.String str39 = java.net.URLConnection.guessContentTypeFromStream((java.io.InputStream) keepAliveStream36);
        com.quakearts.rest.client.net.HttpCapture httpCapture40 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream41 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) keepAliveStream36, httpCapture40);
        boolean boolean42 = keepAliveStream36.markSupported();
        // The following exception was thrown during execution in test generation
        try {
            int int43 = keepAliveStream36.read();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + false + "'", boolean38 == false);
        org.junit.Assert.assertNull(str39);
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + false + "'", boolean42 == false);
    }

    @Test
    public void test1515() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1515");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = progressSource2.getURL();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNull(uRL6);
    }

    @Test
    public void test1516() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1516");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL1 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL1, "");
        boolean boolean4 = progressSource3.connected();
        boolean boolean5 = progressSource3.connected();
        progressMonitor0.registerSource(progressSource3);
        com.quakearts.rest.client.net.ProgressSource progressSource7 = null;
        progressMonitor0.unregisterSource(progressSource7);
        java.net.URL uRL9 = null;
        boolean boolean11 = progressMonitor0.shouldMeterInput(uRL9, "");
        int int12 = progressMonitor0.getProgressUpdateThreshold();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 8192 + "'", int12 == 8192);
    }

    @Test
    public void test1517() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1517");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        java.lang.String str10 = httpsURLConnectionImpl3.getHeaderFieldKey(402);
        javax.net.ssl.SSLSocketFactory sSLSocketFactory11 = httpsURLConnectionImpl3.getSSLSocketFactory();
        boolean boolean12 = httpsURLConnectionImpl3.getDefaultUseCaches();
        int int13 = httpsURLConnectionImpl3.getReadTimeout();
        long long16 = httpsURLConnectionImpl3.getHeaderFieldDate("hi!", (long) 408);
        long long17 = httpsURLConnectionImpl3.getExpiration();
        httpsURLConnectionImpl3.setConnectTimeout(0);
        java.lang.String str21 = httpsURLConnectionImpl3.getHeaderFieldKey(409);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
        org.junit.Assert.assertTrue("'" + long16 + "' != '" + 408L + "'", long16 == 408L);
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 0L + "'", long17 == 0L);
        org.junit.Assert.assertNull(str21);
    }

    @Test
    public void test1518() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1518");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        httpsURLConnectionImpl3.setConnectTimeout((int) (short) 0);
        java.io.InputStream inputStream11 = httpsURLConnectionImpl3.getErrorStream();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(inputStream11);
    }

    @Test
    public void test1519() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1519");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = null;
        authCacheImpl0.remove("content/unknown", authCacheValue6);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue9 = null;
        authCacheImpl0.remove("\u2018", authCacheValue9);
        org.junit.Assert.assertNull(authCacheValue3);
    }

    @Test
    public void test1520() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1520");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        httpsURLConnectionImpl3.setConnectTimeout((int) (short) 0);
        int int11 = httpsURLConnectionImpl3.getReadTimeout();
        httpsURLConnectionImpl3.setInstanceFollowRedirects(true);
        java.lang.String str14 = httpsURLConnectionImpl3.getContentType();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNull(str14);
    }

    @Test
    public void test1521() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1521");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        java.lang.String str10 = httpsURLConnectionImpl3.getHeaderFieldKey(402);
        javax.net.ssl.SSLSocketFactory sSLSocketFactory11 = httpsURLConnectionImpl3.getSSLSocketFactory();
        boolean boolean12 = httpsURLConnectionImpl3.getDefaultUseCaches();
        int int13 = httpsURLConnectionImpl3.getReadTimeout();
        long long16 = httpsURLConnectionImpl3.getHeaderFieldDate("hi!", (long) 408);
        long long17 = httpsURLConnectionImpl3.getExpiration();
        httpsURLConnectionImpl3.setConnectTimeout(0);
        boolean boolean20 = httpsURLConnectionImpl3.getUseCaches();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
        org.junit.Assert.assertTrue("'" + long16 + "' != '" + 408L + "'", long16 == 408L);
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 0L + "'", long17 == 0L);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
    }

    @Test
    public void test1522() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1522");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource.State state9 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent12 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL6, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state9, (long) 300, (-1L));
        long long13 = progressEvent12.getExpected();
        long long14 = progressEvent12.getProgress();
        java.lang.String str15 = progressEvent12.getContentType();
        com.quakearts.rest.client.net.ProgressSource.State state16 = progressEvent12.getState();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-1L) + "'", long13 == (-1L));
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + 300L + "'", long14 == 300L);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str15, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertNull(state16);
    }

    @Test
    public void test1523() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1523");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("GET");
        java.lang.String str4 = headerParser1.findValue("hi!", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        int int7 = headerParser1.findInt("content/unknown", 502);
        int int10 = headerParser1.findInt("{size=10 nkeys=1  {hi!} }", 32);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str4, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 502 + "'", int7 == 502);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 32 + "'", int10 == 32);
    }

    @Test
    public void test1524() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1524");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "GET", (long) 405);
    }

    @Test
    public void test1525() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1525");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        boolean boolean8 = httpsURLConnectionImpl3.getAllowUserInteraction();
        long long9 = httpsURLConnectionImpl3.getIfModifiedSince();
        httpsURLConnectionImpl3.setInstanceFollowRedirects(true);
        boolean boolean12 = httpsURLConnectionImpl3.getDoOutput();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + long9 + "' != '" + 0L + "'", long9 == 0L);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
    }

    @Test
    public void test1526() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1526");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setRequestMethod("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]");
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: Invalid HTTP method: com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test1527() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1527");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler5 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl6 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL3, proxy4, httpsHandler5);
        javax.net.ssl.HostnameVerifier hostnameVerifier7 = httpsURLConnectionImpl6.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier8 = httpsURLConnectionImpl6.getHostnameVerifier();
        httpsURLConnectionImpl6.setFixedLengthStreamingMode(401);
        java.net.CacheRequest cacheRequest11 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpsURLConnectionImpl6);
        httpsURLConnectionImpl6.setReadTimeout(415);
        java.lang.String str14 = httpsURLConnectionImpl6.getContentEncoding();
        long long15 = httpsURLConnectionImpl6.getDate();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str16 = httpsURLConnectionImpl6.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertNull(str14);
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + 0L + "'", long15 == 0L);
    }

    @Test
    public void test1528() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1528");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        messageHeader1.set("content/unknown", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.io.InputStream inputStream5 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture6 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream7 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream5, httpCapture6);
        messageHeader1.parseHeader(inputStream5);
        java.lang.String str10 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        java.lang.String[] strArray21 = new java.lang.String[] { "GET", "{size=10 nkeys=1  {hi!} }", "GET", "AuthenticationHeader: prefer null", "{size=10 nkeys=1  {get} }", "", "", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "content/unknown", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" };
        java.util.LinkedHashSet<java.lang.String> strSet22 = new java.util.LinkedHashSet<java.lang.String>();
        boolean boolean23 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strSet22, strArray21);
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader24 = new com.quakearts.rest.client.net.http.AuthenticationHeader("", messageHeader1, (java.util.Set<java.lang.String>) strSet22);
        java.lang.String str26 = messageHeader1.findValue("{size=10 nkeys=1  {get} }");
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(strArray21);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + true + "'", boolean23 == true);
        org.junit.Assert.assertNull(str26);
    }

    @Test
    public void test1529() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1529");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.net.URL uRL3 = null;
        com.quakearts.rest.client.net.ProgressSource.State state6 = com.quakearts.rest.client.net.ProgressSource.State.UPDATE;
        com.quakearts.rest.client.net.ProgressEvent progressEvent9 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL3, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", state6, (long) 501, (long) 304);
        java.net.URL uRL10 = null;
        com.quakearts.rest.client.net.ProgressSource.State state13 = com.quakearts.rest.client.net.ProgressSource.State.NEW;
        com.quakearts.rest.client.net.ProgressEvent progressEvent16 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL10, "hi!", "GET", state13, 1L, (long) 408);
        long long17 = progressSource2.getProgress();
        progressSource2.updateProgress((long) 414, (long) 201);
        java.lang.String str21 = progressSource2.getContentType();
        org.junit.Assert.assertTrue("'" + state6 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.UPDATE + "'", state6.equals(com.quakearts.rest.client.net.ProgressSource.State.UPDATE));
        org.junit.Assert.assertTrue("'" + state13 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.NEW + "'", state13.equals(com.quakearts.rest.client.net.ProgressSource.State.NEW));
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 0L + "'", long17 == 0L);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "content/unknown" + "'", str21, "content/unknown");
    }

    @Test
    public void test1530() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1530");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        httpsURLConnectionImpl3.setRequestProperty("hi!", "");
        httpsURLConnectionImpl3.setReadTimeout((int) (short) 100);
        httpsURLConnectionImpl3.setUseCaches(true);
        java.net.URL uRL15 = httpsURLConnectionImpl3.getURL();
        boolean boolean16 = httpsURLConnectionImpl3.getUseCaches();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNull(uRL15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
    }

    @Test
    public void test1531() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1531");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.URI uRI1 = null;
        java.net.URL uRL2 = null;
        java.net.Proxy proxy3 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler4 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl5 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL2, proxy3, httpsHandler4);
        javax.net.ssl.HostnameVerifier hostnameVerifier6 = httpsURLConnectionImpl5.getHostnameVerifier();
        boolean boolean7 = httpsURLConnectionImpl5.getDoInput();
        httpsURLConnectionImpl5.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl5.addRequestProperty("", "hi!");
        int int15 = httpsURLConnectionImpl5.getHeaderFieldInt("hi!", 100);
        java.net.CacheRequest cacheRequest16 = responseCacheImpl0.put(uRI1, (java.net.URLConnection) httpsURLConnectionImpl5);
        java.lang.String str17 = httpsURLConnectionImpl5.getContentType();
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertNull(str17);
    }

    @Test
    public void test1532() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1532");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.URI uRI1 = null;
        java.net.URL uRL2 = null;
        java.net.Proxy proxy3 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler4 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl5 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL2, proxy3, httpsHandler4);
        javax.net.ssl.HostnameVerifier hostnameVerifier6 = httpsURLConnectionImpl5.getHostnameVerifier();
        boolean boolean7 = httpsURLConnectionImpl5.getDoInput();
        httpsURLConnectionImpl5.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl5.addRequestProperty("", "hi!");
        int int15 = httpsURLConnectionImpl5.getHeaderFieldInt("hi!", 100);
        java.net.CacheRequest cacheRequest16 = responseCacheImpl0.put(uRI1, (java.net.URLConnection) httpsURLConnectionImpl5);
        httpsURLConnectionImpl5.setInstanceFollowRedirects(false);
        int int19 = httpsURLConnectionImpl5.getConnectTimeout();
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + 0 + "'", int19 == 0);
    }

    @Test
    public void test1533() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1533");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor27 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        long long31 = progressSource30.getExpected();
        progressMonitor27.registerSource(progressSource30);
        java.lang.String str33 = progressSource30.toString();
        com.quakearts.rest.client.net.HttpClient httpClient35 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream36 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream10, progressSource30, (long) 203, httpClient35);
        com.quakearts.rest.client.net.HttpClient httpClient37 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader38 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str40 = messageHeader38.getValue(503);
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream41 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) meteredStream10, httpClient37, messageHeader38);
        com.quakearts.rest.client.net.HttpClient httpClient42 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader43 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str45 = messageHeader43.getValue(503);
        messageHeader43.prepend("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {hi!} }");
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream49 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) meteredStream10, httpClient42, messageHeader43);
        com.quakearts.rest.client.net.HttpCapture httpCapture50 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream51 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) meteredStream10, httpCapture50);
        com.quakearts.rest.client.net.HttpCapture httpCapture52 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream53 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) httpCaptureInputStream51, httpCapture52);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureInputStream51.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
        org.junit.Assert.assertNull(str45);
    }

    @Test
    public void test1534() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1534");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setConnectTimeout((int) ' ');
        java.lang.String str8 = httpsURLConnectionImpl3.toString();
        httpsURLConnectionImpl3.setChunkedStreamingMode((int) (byte) -1);
        java.net.URL uRL11 = httpsURLConnectionImpl3.getURL();
        java.lang.String str13 = httpsURLConnectionImpl3.getHeaderField((int) (byte) 2);
        java.lang.String str14 = httpsURLConnectionImpl3.getContentType();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertNull(uRL11);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertNull(str14);
    }

    @Test
    public void test1535() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1535");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        java.net.URL uRL10 = null;
        java.net.Proxy proxy11 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler14 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl15 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL10, proxy11, httpHandler14);
        int int16 = httpURLConnectionImpl15.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState17 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl15.setTunnelState(tunnelState17);
        httpURLConnectionImpl5.setTunnelState(tunnelState17);
        httpURLConnectionImpl5.setConnectTimeout(303);
        java.net.URL uRL22 = null;
        java.net.Proxy proxy23 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler26 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl27 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL22, proxy23, httpHandler26);
        int int28 = httpURLConnectionImpl27.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState29 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl27.setTunnelState(tunnelState29);
        boolean boolean31 = httpURLConnectionImpl27.streaming();
        boolean boolean32 = httpURLConnectionImpl27.streaming();
        httpURLConnectionImpl27.setReadTimeout(414);
        java.io.InputStream inputStream35 = httpURLConnectionImpl27.getErrorStream();
        java.net.URL uRL36 = null;
        java.net.Proxy proxy37 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler40 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl41 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL36, proxy37, httpHandler40);
        int int42 = httpURLConnectionImpl41.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState43 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl41.setTunnelState(tunnelState43);
        boolean boolean45 = httpURLConnectionImpl41.streaming();
        java.io.InputStream inputStream46 = httpURLConnectionImpl41.getErrorStream();
        java.io.InputStream inputStream47 = httpURLConnectionImpl41.getErrorStream();
        java.io.InputStream inputStream48 = httpURLConnectionImpl41.getErrorStream();
        boolean boolean49 = httpURLConnectionImpl41.usingProxy();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState50 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.NONE;
        httpURLConnectionImpl41.setTunnelState(tunnelState50);
        httpURLConnectionImpl27.setTunnelState(tunnelState50);
        httpURLConnectionImpl5.setTunnelState(tunnelState50);
        int int54 = httpURLConnectionImpl5.getReadTimeout();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 0 + "'", int16 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState17 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState17.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + 0 + "'", int28 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState29 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState29.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertNull(inputStream35);
        org.junit.Assert.assertTrue("'" + int42 + "' != '" + 0 + "'", int42 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState43 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState43.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean45 + "' != '" + false + "'", boolean45 == false);
        org.junit.Assert.assertNull(inputStream46);
        org.junit.Assert.assertNull(inputStream47);
        org.junit.Assert.assertNull(inputStream48);
        org.junit.Assert.assertTrue("'" + boolean49 + "' != '" + false + "'", boolean49 == false);
        org.junit.Assert.assertTrue("'" + tunnelState50 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.NONE + "'", tunnelState50.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.NONE));
        org.junit.Assert.assertTrue("'" + int54 + "' != '" + 0 + "'", int54 == 0);
    }

    @Test
    public void test1536() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1536");
        java.lang.String str1 = java.net.URLConnection.getDefaultRequestProperty("AuthenticationHeader: prefer null");
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test1537() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1537");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler5 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl6 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL3, proxy4, httpsHandler5);
        javax.net.ssl.HostnameVerifier hostnameVerifier7 = httpsURLConnectionImpl6.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier8 = httpsURLConnectionImpl6.getHostnameVerifier();
        httpsURLConnectionImpl6.setFixedLengthStreamingMode(401);
        java.net.CacheRequest cacheRequest11 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpsURLConnectionImpl6);
        long long12 = httpsURLConnectionImpl6.getLastModified();
        httpsURLConnectionImpl6.setAllowUserInteraction(false);
        long long15 = httpsURLConnectionImpl6.getContentLengthLong();
        httpsURLConnectionImpl6.setConnectTimeout(405);
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission18 = httpsURLConnectionImpl6.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + (-1L) + "'", long15 == (-1L));
    }

    @Test
    public void test1538() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1538");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL1 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL1, "");
        boolean boolean4 = progressSource3.connected();
        boolean boolean5 = progressSource3.connected();
        progressMonitor0.registerSource(progressSource3);
        com.quakearts.rest.client.net.ProgressSource progressSource7 = null;
        progressMonitor0.unregisterSource(progressSource7);
        com.quakearts.rest.client.net.ProgressMonitor.setDefault(progressMonitor0);
        java.util.List<com.quakearts.rest.client.net.ProgressSource> progressSourceList10 = progressMonitor0.getProgressSources();
        java.net.URL uRL11 = null;
        boolean boolean13 = progressMonitor0.shouldMeterInput(uRL11, "{size=10 nkeys=1  {hi!} }");
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNotNull(progressSourceList10);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }

    @Test
    public void test1539() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1539");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor27 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        long long31 = progressSource30.getExpected();
        progressMonitor27.registerSource(progressSource30);
        java.lang.String str33 = progressSource30.toString();
        com.quakearts.rest.client.net.HttpClient httpClient35 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream36 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream10, progressSource30, (long) 203, httpClient35);
        com.quakearts.rest.client.net.HttpClient httpClient37 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader38 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str40 = messageHeader38.getValue(503);
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream41 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) meteredStream10, httpClient37, messageHeader38);
        boolean boolean42 = chunkedInputStream41.hurry();
        boolean boolean43 = chunkedInputStream41.markSupported();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream44 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream44.flush();
        posterOutputStream44.write(204);
        com.quakearts.rest.client.net.HttpCapture httpCapture48 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream49 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream44, httpCapture48);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream50 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream50.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture52 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream53 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream50, httpCapture52);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream54 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream54.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture56 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream57 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream54, httpCapture56);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream58 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray59 = posterOutputStream58.toByteArray();
        httpCaptureOutputStream57.write(byteArray59);
        httpCaptureOutputStream53.write(byteArray59);
        httpCaptureOutputStream49.write(byteArray59);
        // The following exception was thrown during execution in test generation
        try {
            int int65 = chunkedInputStream41.read(byteArray59, (int) (short) 0, 205);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + false + "'", boolean42 == false);
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + false + "'", boolean43 == false);
        org.junit.Assert.assertNotNull(byteArray59);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray59), "[]");
    }

    @Test
    public void test1540() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1540");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader1.set(406, "content/unknown", "hi!");
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader8 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        com.quakearts.rest.client.net.MessageHeader messageHeader9 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str11 = messageHeader9.getValue(503);
        messageHeader9.add("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "");
        java.lang.String[] strArray19 = new java.lang.String[] { "", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" };
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap20 = messageHeader9.getHeaders(strArray19);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap21 = messageHeader1.getHeaders(strArray19);
        java.lang.String str23 = messageHeader1.findValue("\u2018");
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str11);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertNotNull(strMap20);
        org.junit.Assert.assertNotNull(strMap21);
        org.junit.Assert.assertNull(str23);
    }

    @Test
    public void test1541() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1541");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        // The following exception was thrown during execution in test generation
        try {
            long long28 = meteredStream26.skip((long) (short) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test1542() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1542");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.lang.String str5 = messageHeader1.getValue((int) (byte) -1);
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader6 = new com.quakearts.rest.client.net.http.AuthenticationHeader("{size=10 nkeys=1  {hi!} }", messageHeader1);
        messageHeader1.prepend("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "com.quakearts.rest.client.net.http.HttpURLConnectionImpl:null");
        messageHeader1.add("\u2018", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str5);
    }

    @Test
    public void test1543() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1543");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.URI uRI1 = null;
        java.net.URL uRL2 = null;
        java.net.Proxy proxy3 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler4 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl5 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL2, proxy3, httpsHandler4);
        javax.net.ssl.HostnameVerifier hostnameVerifier6 = httpsURLConnectionImpl5.getHostnameVerifier();
        boolean boolean7 = httpsURLConnectionImpl5.getDoInput();
        httpsURLConnectionImpl5.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl5.addRequestProperty("", "hi!");
        int int15 = httpsURLConnectionImpl5.getHeaderFieldInt("hi!", 100);
        java.net.CacheRequest cacheRequest16 = responseCacheImpl0.put(uRI1, (java.net.URLConnection) httpsURLConnectionImpl5);
        java.lang.String str18 = httpsURLConnectionImpl5.getHeaderField(1);
        javax.net.ssl.HostnameVerifier hostnameVerifier19 = httpsURLConnectionImpl5.getHostnameVerifier();
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertNull(str18);
        org.junit.Assert.assertNotNull(hostnameVerifier19);
    }

    @Test
    public void test1544() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1544");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor27 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        long long31 = progressSource30.getExpected();
        progressMonitor27.registerSource(progressSource30);
        java.lang.String str33 = progressSource30.toString();
        com.quakearts.rest.client.net.ProgressSource progressSource34 = new com.quakearts.rest.client.net.ProgressSource(progressSource30);
        com.quakearts.rest.client.net.HttpClient httpClient36 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream37 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream26, progressSource34, (long) 303, httpClient36);
        // The following exception was thrown during execution in test generation
        try {
            meteredStream26.reset();
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: Resetting to an invalid mark");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test1545() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1545");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(100);
        httpsURLConnectionImpl3.setIfModifiedSince(0L);
        java.lang.String str12 = httpsURLConnectionImpl3.getContentEncoding();
        boolean boolean13 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.setDoOutput(false);
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setRequestMethod("\310");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connect in progress");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }

    @Test
    public void test1546() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1546");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        java.io.InputStream inputStream10 = httpURLConnectionImpl5.getErrorStream();
        java.io.InputStream inputStream11 = httpURLConnectionImpl5.getErrorStream();
        boolean boolean12 = httpURLConnectionImpl5.streaming();
        java.io.InputStream inputStream13 = httpURLConnectionImpl5.getErrorStream();
        java.net.CookieHandler cookieHandler14 = httpURLConnectionImpl5.getCookieHandler();
        java.io.InputStream inputStream15 = httpURLConnectionImpl5.getErrorStream();
        boolean boolean16 = httpURLConnectionImpl5.usingProxy();
        httpURLConnectionImpl5.setConnectTimeout(206);
        httpURLConnectionImpl5.disconnect();
        httpURLConnectionImpl5.setInstanceFollowRedirects(true);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertNull(inputStream11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNull(inputStream13);
        org.junit.Assert.assertNull(cookieHandler14);
        org.junit.Assert.assertNull(inputStream15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
    }

    @Test
    public void test1547() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1547");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setConnectTimeout((int) ' ');
        java.lang.String str8 = httpsURLConnectionImpl3.toString();
        httpsURLConnectionImpl3.setChunkedStreamingMode((int) (byte) -1);
        java.net.URL uRL11 = httpsURLConnectionImpl3.getURL();
        java.lang.String str13 = httpsURLConnectionImpl3.getHeaderField((int) (byte) 2);
        long long14 = httpsURLConnectionImpl3.getExpiration();
        long long15 = httpsURLConnectionImpl3.getLastModified();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertNull(uRL11);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + 0L + "'", long14 == 0L);
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + 0L + "'", long15 == 0L);
    }

    @Test
    public void test1548() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1548");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findKey(305);
        java.lang.String str5 = headerParser1.findValue("{size=10 nkeys=1  {hi!} }");
        java.lang.String str6 = headerParser1.toString();
        java.lang.String str7 = headerParser1.toString();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "{size=10 nkeys=1  {hi!} }" + "'", str6, "{size=10 nkeys=1  {hi!} }");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "{size=10 nkeys=1  {hi!} }" + "'", str7, "{size=10 nkeys=1  {hi!} }");
    }

    @Test
    public void test1549() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1549");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        com.quakearts.rest.client.net.HttpClient httpClient27 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader28 = new com.quakearts.rest.client.net.MessageHeader();
        java.io.InputStream inputStream29 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture30 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream31 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream29, httpCapture30);
        com.quakearts.rest.client.net.HttpCapture httpCapture32 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream33 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream29, httpCapture32);
        messageHeader28.parseHeader(inputStream29);
        java.io.InputStream inputStream35 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture36 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream37 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream35, httpCapture36);
        com.quakearts.rest.client.net.HttpCapture httpCapture38 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream39 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream35, httpCapture38);
        java.net.URL uRL40 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource42 = new com.quakearts.rest.client.net.ProgressSource(uRL40, "");
        long long43 = progressSource42.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream45 = new com.quakearts.rest.client.net.MeteredStream(inputStream35, progressSource42, (long) (byte) 1);
        messageHeader28.parseHeader(inputStream35);
        java.lang.String str48 = messageHeader28.getValue(409);
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream49 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) meteredStream10, httpClient27, messageHeader28);
        java.lang.String str50 = java.net.URLConnection.guessContentTypeFromStream((java.io.InputStream) chunkedInputStream49);
        com.quakearts.rest.client.net.HttpClient httpClient51 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader52 = new com.quakearts.rest.client.net.MessageHeader();
        messageHeader52.set("content/unknown", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.io.InputStream inputStream56 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture57 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream58 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream56, httpCapture57);
        messageHeader52.parseHeader(inputStream56);
        java.lang.String str61 = messageHeader52.findValue("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        java.lang.String str63 = messageHeader52.getKey(204);
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream64 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) chunkedInputStream49, httpClient51, messageHeader52);
        boolean boolean65 = chunkedInputStream49.hurry();
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long43 + "' != '" + (-1L) + "'", long43 == (-1L));
        org.junit.Assert.assertNull(str48);
        org.junit.Assert.assertNull(str50);
        org.junit.Assert.assertNull(str61);
        org.junit.Assert.assertNull(str63);
        org.junit.Assert.assertTrue("'" + boolean65 + "' != '" + false + "'", boolean65 == false);
    }

    @Test
    public void test1550() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1550");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int1 = progressMonitor0.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressMonitor.setDefault(progressMonitor0);
        java.net.URL uRL3 = null;
        boolean boolean5 = progressMonitor0.shouldMeterInput(uRL3, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor6 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL7 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource9 = new com.quakearts.rest.client.net.ProgressSource(uRL7, "");
        long long10 = progressSource9.getExpected();
        progressMonitor6.registerSource(progressSource9);
        java.lang.String str12 = progressSource9.getContentType();
        progressSource9.setContentType("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        progressSource9.finishTracking();
        progressMonitor0.updateProgress(progressSource9);
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource19 = new com.quakearts.rest.client.net.ProgressSource(uRL17, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        long long20 = progressSource19.getProgress();
        java.lang.String str21 = progressSource19.getContentType();
        progressMonitor0.unregisterSource(progressSource19);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor23 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL24 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource26 = new com.quakearts.rest.client.net.ProgressSource(uRL24, "");
        long long27 = progressSource26.getExpected();
        progressMonitor23.registerSource(progressSource26);
        progressSource26.finishTracking();
        long long30 = progressSource26.getExpected();
        boolean boolean31 = progressSource26.connected();
        progressMonitor0.updateProgress(progressSource26);
        java.lang.Class<?> wildcardClass33 = progressSource26.getClass();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8192 + "'", int1 == 8192);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + long10 + "' != '" + (-1L) + "'", long10 == (-1L));
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "content/unknown" + "'", str12, "content/unknown");
        org.junit.Assert.assertTrue("'" + long20 + "' != '" + 0L + "'", long20 == 0L);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "content/unknown" + "'", str21, "content/unknown");
        org.junit.Assert.assertTrue("'" + long27 + "' != '" + (-1L) + "'", long27 == (-1L));
        org.junit.Assert.assertTrue("'" + long30 + "' != '" + (-1L) + "'", long30 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertNotNull(wildcardClass33);
    }

    @Test
    public void test1551() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1551");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("GET");
        java.lang.String str4 = headerParser1.findValue("hi!", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.lang.String str6 = headerParser1.findValue("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.lang.String str7 = headerParser1.toString();
        java.util.Iterator<java.lang.String> strItor8 = headerParser1.values();
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str4, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "{size=10 nkeys=1  {get} }" + "'", str7, "{size=10 nkeys=1  {get} }");
        org.junit.Assert.assertNotNull(strItor8);
    }

    @Test
    public void test1552() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1552");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        boolean boolean10 = httpURLConnectionImpl5.streaming();
        httpURLConnectionImpl5.setReadTimeout(414);
        httpURLConnectionImpl5.setDefaultUseCaches(false);
        com.quakearts.rest.client.net.MessageHeader messageHeader16 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str18 = messageHeader16.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader16.set(406, "content/unknown", "hi!");
        messageHeader16.prepend("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "");
        java.lang.String[] strArray36 = new java.lang.String[] { "{size=10 nkeys=1  {hi!} }", "GET", "{size=10 nkeys=1  {get} }", "{size=10 nkeys=1  {hi!} }", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "AuthenticationHeader: prefer null", "AuthenticationHeader: prefer null", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "hi!", "{size=10 nkeys=1  {hi!} }" };
        java.util.LinkedHashSet<java.lang.String> strSet37 = new java.util.LinkedHashSet<java.lang.String>();
        boolean boolean38 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strSet37, strArray36);
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader39 = new com.quakearts.rest.client.net.http.AuthenticationHeader("", messageHeader16, (java.util.Set<java.lang.String>) strSet37);
        httpURLConnectionImpl5.authObj((java.lang.Object) "");
        httpURLConnectionImpl5.setRequestProperty("", "{size=10 nkeys=1  {hi!} }");
        httpURLConnectionImpl5.setConnectTimeout(407);
        httpURLConnectionImpl5.setReadTimeout((int) (short) 1);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str49 = httpURLConnectionImpl5.getHeaderField("com.quakearts.rest.client.net.ProgressEvent[url=null, method=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], state=UPDATE, content-type=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], progress=501, expected=304]");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNull(str18);
        org.junit.Assert.assertNotNull(strArray36);
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + true + "'", boolean38 == true);
    }

    @Test
    public void test1553() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1553");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.io.InputStream inputStream1 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream3 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture2);
        com.quakearts.rest.client.net.HttpCapture httpCapture4 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream5 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture4);
        messageHeader0.parseHeader(inputStream1);
        messageHeader0.add("GET", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.util.Iterator<java.lang.String> strItor11 = messageHeader0.multiValueIterator("");
        messageHeader0.set("content/unknown", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.util.Iterator<java.lang.String> strItor16 = messageHeader0.multiValueIterator("hi!");
        messageHeader0.add("p4", "");
        messageHeader0.remove("");
        org.junit.Assert.assertNotNull(strItor11);
        org.junit.Assert.assertNotNull(strItor16);
    }

    @Test
    public void test1554() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1554");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        boolean boolean4 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.setInstanceFollowRedirects(false);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type com.quakearts.rest.client.exception.HttpClientRuntimeException; message: java.lang.NullPointerException");
        } catch (com.quakearts.rest.client.exception.HttpClientRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test1555() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1555");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        java.lang.String str10 = httpsURLConnectionImpl3.getHeaderFieldKey(402);
        javax.net.ssl.SSLSocketFactory sSLSocketFactory11 = httpsURLConnectionImpl3.getSSLSocketFactory();
        long long14 = httpsURLConnectionImpl3.getHeaderFieldDate("content/unknown", (long) (short) 100);
        httpsURLConnectionImpl3.setDoOutput(true);
        httpsURLConnectionImpl3.setDoInput(true);
        // The following exception was thrown during execution in test generation
        try {
            int int21 = httpsURLConnectionImpl3.getHeaderFieldInt("", (int) '4');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + 100L + "'", long14 == 100L);
    }

    @Test
    public void test1556() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1556");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        httpsURLConnectionImpl3.setRequestProperty("hi!", "");
        httpsURLConnectionImpl3.setReadTimeout((int) (short) 100);
        httpsURLConnectionImpl3.setUseCaches(true);
        httpsURLConnectionImpl3.setChunkedStreamingMode(500);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test1557() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1557");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor27 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        long long31 = progressSource30.getExpected();
        progressMonitor27.registerSource(progressSource30);
        java.lang.String str33 = progressSource30.toString();
        com.quakearts.rest.client.net.HttpClient httpClient35 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream36 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream10, progressSource30, (long) 203, httpClient35);
        com.quakearts.rest.client.net.HttpClient httpClient37 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader38 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str40 = messageHeader38.getValue(503);
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream41 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) meteredStream10, httpClient37, messageHeader38);
        com.quakearts.rest.client.net.HttpClient httpClient42 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader43 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str45 = messageHeader43.getValue(503);
        messageHeader43.prepend("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {hi!} }");
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream49 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) meteredStream10, httpClient42, messageHeader43);
        // The following exception was thrown during execution in test generation
        try {
            meteredStream10.mark(10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
        org.junit.Assert.assertNull(str45);
    }

    @Test
    public void test1558() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1558");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        int int9 = httpsURLConnectionImpl3.getReadTimeout();
        boolean boolean10 = httpsURLConnectionImpl3.getAllowUserInteraction();
        boolean boolean11 = httpsURLConnectionImpl3.getDefaultUseCaches();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test1559() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1559");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.net.URL uRL3 = null;
        com.quakearts.rest.client.net.ProgressSource.State state6 = com.quakearts.rest.client.net.ProgressSource.State.UPDATE;
        com.quakearts.rest.client.net.ProgressEvent progressEvent9 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL3, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", state6, (long) 501, (long) 304);
        java.net.URL uRL10 = null;
        com.quakearts.rest.client.net.ProgressSource.State state13 = com.quakearts.rest.client.net.ProgressSource.State.NEW;
        com.quakearts.rest.client.net.ProgressEvent progressEvent16 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL10, "hi!", "GET", state13, 1L, (long) 408);
        long long17 = progressSource2.getProgress();
        java.net.URL uRL18 = null;
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor21 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL22 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(uRL22, "");
        long long25 = progressSource24.getExpected();
        progressMonitor21.registerSource(progressSource24);
        java.lang.String str27 = progressSource24.toString();
        java.net.URL uRL28 = null;
        java.net.URL uRL31 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource33 = new com.quakearts.rest.client.net.ProgressSource(uRL31, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.net.URL uRL34 = null;
        com.quakearts.rest.client.net.ProgressSource.State state37 = com.quakearts.rest.client.net.ProgressSource.State.UPDATE;
        com.quakearts.rest.client.net.ProgressEvent progressEvent40 = new com.quakearts.rest.client.net.ProgressEvent(progressSource33, uRL34, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", state37, (long) 501, (long) 304);
        java.net.URL uRL41 = null;
        com.quakearts.rest.client.net.ProgressSource.State state44 = com.quakearts.rest.client.net.ProgressSource.State.NEW;
        com.quakearts.rest.client.net.ProgressEvent progressEvent47 = new com.quakearts.rest.client.net.ProgressEvent(progressSource33, uRL41, "hi!", "GET", state44, 1L, (long) 408);
        com.quakearts.rest.client.net.ProgressEvent progressEvent50 = new com.quakearts.rest.client.net.ProgressEvent(progressSource24, uRL28, "content/unknown", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", state44, (long) 504, (long) (byte) 100);
        java.net.URL uRL51 = null;
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor54 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int55 = progressMonitor54.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor56 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL57 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource59 = new com.quakearts.rest.client.net.ProgressSource(uRL57, "");
        long long60 = progressSource59.getExpected();
        progressMonitor56.registerSource(progressSource59);
        progressMonitor54.unregisterSource(progressSource59);
        com.quakearts.rest.client.net.ProgressListener progressListener63 = null;
        progressMonitor54.removeProgressListener(progressListener63);
        java.io.InputStream inputStream65 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture66 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream67 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream65, httpCapture66);
        com.quakearts.rest.client.net.HttpCapture httpCapture68 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream69 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream65, httpCapture68);
        java.net.URL uRL70 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource72 = new com.quakearts.rest.client.net.ProgressSource(uRL70, "");
        long long73 = progressSource72.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream75 = new com.quakearts.rest.client.net.MeteredStream(inputStream65, progressSource72, (long) (byte) 1);
        progressMonitor54.updateProgress(progressSource72);
        com.quakearts.rest.client.net.ProgressSource.State state77 = progressSource72.getState();
        com.quakearts.rest.client.net.ProgressEvent progressEvent80 = new com.quakearts.rest.client.net.ProgressEvent(progressSource24, uRL51, "{size=10 nkeys=1  {hi!} }", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state77, (long) 402, (long) 301);
        com.quakearts.rest.client.net.ProgressEvent progressEvent83 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL18, "content/unknown", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], state=UPDATE, content-type=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], progress=501, expected=304]", state77, (long) 413, (long) 'a');
        progressSource2.updateProgress((long) 302, 0L);
        org.junit.Assert.assertTrue("'" + state6 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.UPDATE + "'", state6.equals(com.quakearts.rest.client.net.ProgressSource.State.UPDATE));
        org.junit.Assert.assertTrue("'" + state13 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.NEW + "'", state13.equals(com.quakearts.rest.client.net.ProgressSource.State.NEW));
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 0L + "'", long17 == 0L);
        org.junit.Assert.assertTrue("'" + long25 + "' != '" + (-1L) + "'", long25 == (-1L));
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str27, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + state37 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.UPDATE + "'", state37.equals(com.quakearts.rest.client.net.ProgressSource.State.UPDATE));
        org.junit.Assert.assertTrue("'" + state44 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.NEW + "'", state44.equals(com.quakearts.rest.client.net.ProgressSource.State.NEW));
        org.junit.Assert.assertTrue("'" + int55 + "' != '" + 8192 + "'", int55 == 8192);
        org.junit.Assert.assertTrue("'" + long60 + "' != '" + (-1L) + "'", long60 == (-1L));
        org.junit.Assert.assertTrue("'" + long73 + "' != '" + (-1L) + "'", long73 == (-1L));
        org.junit.Assert.assertTrue("'" + state77 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.CONNECTED + "'", state77.equals(com.quakearts.rest.client.net.ProgressSource.State.CONNECTED));
    }

    @Test
    public void test1560() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1560");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.io.InputStream inputStream1 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream3 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture2);
        com.quakearts.rest.client.net.HttpCapture httpCapture4 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream5 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture4);
        messageHeader0.parseHeader(inputStream1);
        java.io.InputStream inputStream7 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture8 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream9 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream7, httpCapture8);
        com.quakearts.rest.client.net.HttpCapture httpCapture10 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream11 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream7, httpCapture10);
        java.net.URL uRL12 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource14 = new com.quakearts.rest.client.net.ProgressSource(uRL12, "");
        long long15 = progressSource14.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream17 = new com.quakearts.rest.client.net.MeteredStream(inputStream7, progressSource14, (long) (byte) 1);
        messageHeader0.parseHeader(inputStream7);
        java.lang.String str20 = messageHeader0.getValue(409);
        messageHeader0.add("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        messageHeader0.setIfNotSet("p4", "\u2018");
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + (-1L) + "'", long15 == (-1L));
        org.junit.Assert.assertNull(str20);
    }

    @Test
    public void test1561() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1561");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findKey(305);
        int int6 = headerParser1.findInt("hi!", 32);
        java.lang.String str8 = headerParser1.findValue(406);
        java.util.Iterator<java.lang.String> strItor9 = headerParser1.values();
        java.lang.String str11 = headerParser1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.util.Iterator<java.lang.String> strItor12 = headerParser1.keys();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 32 + "'", int6 == 32);
        org.junit.Assert.assertNull(str8);
        org.junit.Assert.assertNotNull(strItor9);
        org.junit.Assert.assertNull(str11);
        org.junit.Assert.assertNotNull(strItor12);
    }

    @Test
    public void test1562() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1562");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.URI uRI1 = null;
        java.net.URL uRL2 = null;
        java.net.Proxy proxy3 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler4 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl5 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL2, proxy3, httpsHandler4);
        javax.net.ssl.HostnameVerifier hostnameVerifier6 = httpsURLConnectionImpl5.getHostnameVerifier();
        boolean boolean7 = httpsURLConnectionImpl5.getDoInput();
        httpsURLConnectionImpl5.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl5.addRequestProperty("", "hi!");
        int int15 = httpsURLConnectionImpl5.getHeaderFieldInt("hi!", 100);
        java.net.CacheRequest cacheRequest16 = responseCacheImpl0.put(uRI1, (java.net.URLConnection) httpsURLConnectionImpl5);
        httpsURLConnectionImpl5.setFixedLengthStreamingMode((long) 203);
        java.lang.String str19 = httpsURLConnectionImpl5.getContentType();
        httpsURLConnectionImpl5.setUseCaches(false);
        int int24 = httpsURLConnectionImpl5.getHeaderFieldInt("p4", 504);
        long long25 = httpsURLConnectionImpl5.getDate();
        long long26 = httpsURLConnectionImpl5.getExpiration();
        // The following exception was thrown during execution in test generation
        try {
            java.security.Principal principal27 = httpsURLConnectionImpl5.getLocalPrincipal();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertNull(str19);
        org.junit.Assert.assertTrue("'" + int24 + "' != '" + 504 + "'", int24 == 504);
        org.junit.Assert.assertTrue("'" + long25 + "' != '" + 0L + "'", long25 == 0L);
        org.junit.Assert.assertTrue("'" + long26 + "' != '" + 0L + "'", long26 == 0L);
    }

    @Test
    public void test1563() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1563");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("p4");
        java.util.Iterator<java.lang.String> strItor2 = headerParser1.values();
        org.junit.Assert.assertNotNull(strItor2);
    }

    @Test
    public void test1564() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1564");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        int int12 = httpsURLConnectionImpl3.getHeaderFieldInt("GET", 403);
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 403 + "'", int12 == 403);
    }

    @Test
    public void test1565() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1565");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.net.URL uRL3 = null;
        com.quakearts.rest.client.net.ProgressSource.State state6 = com.quakearts.rest.client.net.ProgressSource.State.UPDATE;
        com.quakearts.rest.client.net.ProgressEvent progressEvent9 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL3, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", state6, (long) 501, (long) 304);
        long long10 = progressEvent9.getExpected();
        java.lang.String str11 = progressEvent9.toString();
        long long12 = progressEvent9.getExpected();
        org.junit.Assert.assertTrue("'" + state6 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.UPDATE + "'", state6.equals(com.quakearts.rest.client.net.ProgressSource.State.UPDATE));
        org.junit.Assert.assertTrue("'" + long10 + "' != '" + 304L + "'", long10 == 304L);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "com.quakearts.rest.client.net.ProgressEvent[url=null, method=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], state=UPDATE, content-type=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], progress=501, expected=304]" + "'", str11, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], state=UPDATE, content-type=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], progress=501, expected=304]");
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 304L + "'", long12 == 304L);
    }

    @Test
    public void test1566() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1566");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader0.set(406, "content/unknown", "hi!");
        messageHeader0.prepend("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "");
        java.lang.String str11 = messageHeader0.getValue((int) (byte) 1);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "hi!" + "'", str11, "hi!");
    }

    @Test
    public void test1567() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1567");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(100);
        httpsURLConnectionImpl3.setDefaultUseCaches(true);
        java.lang.String str12 = httpsURLConnectionImpl3.getContentType();
        long long13 = httpsURLConnectionImpl3.getDate();
        httpsURLConnectionImpl3.setAllowUserInteraction(true);
        boolean boolean16 = httpsURLConnectionImpl3.getDoInput();
        java.lang.String str17 = httpsURLConnectionImpl3.getContentEncoding();
        java.lang.String str18 = httpsURLConnectionImpl3.getContentType();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(501);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + 0L + "'", long13 == 0L);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertNull(str17);
        org.junit.Assert.assertNull(str18);
    }

    @Test
    public void test1568() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1568");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        java.lang.String str10 = httpsURLConnectionImpl3.getHeaderFieldKey(402);
        javax.net.ssl.SSLSocketFactory sSLSocketFactory11 = httpsURLConnectionImpl3.getSSLSocketFactory();
        long long14 = httpsURLConnectionImpl3.getHeaderFieldDate("content/unknown", (long) (short) 100);
        long long17 = httpsURLConnectionImpl3.getHeaderFieldDate("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", (long) 501);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj18 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + 100L + "'", long14 == 100L);
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 501L + "'", long17 == 501L);
    }

    @Test
    public void test1569() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1569");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(100);
        httpsURLConnectionImpl3.setDefaultUseCaches(true);
        long long12 = httpsURLConnectionImpl3.getExpiration();
        httpsURLConnectionImpl3.setDoInput(true);
        httpsURLConnectionImpl3.setDoInput(false);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
    }

    @Test
    public void test1570() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1570");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler5 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl6 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL3, proxy4, httpsHandler5);
        javax.net.ssl.HostnameVerifier hostnameVerifier7 = httpsURLConnectionImpl6.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier8 = httpsURLConnectionImpl6.getHostnameVerifier();
        httpsURLConnectionImpl6.setFixedLengthStreamingMode(401);
        java.net.CacheRequest cacheRequest11 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpsURLConnectionImpl6);
        long long12 = httpsURLConnectionImpl6.getLastModified();
        httpsURLConnectionImpl6.setAllowUserInteraction(false);
        long long15 = httpsURLConnectionImpl6.getContentLengthLong();
        java.lang.String str16 = httpsURLConnectionImpl6.getContentEncoding();
        boolean boolean17 = httpsURLConnectionImpl6.getDefaultUseCaches();
        long long18 = httpsURLConnectionImpl6.getExpiration();
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + (-1L) + "'", long15 == (-1L));
        org.junit.Assert.assertNull(str16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + true + "'", boolean17 == true);
        org.junit.Assert.assertTrue("'" + long18 + "' != '" + 0L + "'", long18 == 0L);
    }

    @Test
    public void test1571() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1571");
        boolean boolean1 = com.quakearts.rest.client.net.URLConnectionImpl.isProxiedHost("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=UPDATE, content-type=content/unknown, progress=0, expected=32]");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test1572() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1572");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader0.set(406, "content/unknown", "hi!");
        messageHeader0.set(402, "GET", "");
        messageHeader0.set((int) (short) 1, "GET", "{size=10 nkeys=1  {hi!} }");
        messageHeader0.set((int) (byte) 1, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "{}");
        java.io.PrintStream printStream19 = null;
        // The following exception was thrown during execution in test generation
        try {
            messageHeader0.print(printStream19);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str2);
    }

    @Test
    public void test1573() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1573");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        java.lang.String str10 = httpsURLConnectionImpl3.getHeaderFieldKey(402);
        long long11 = httpsURLConnectionImpl3.getLastModified();
        boolean boolean12 = httpsURLConnectionImpl3.getDoOutput();
        httpsURLConnectionImpl3.setUseCaches(false);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + 0L + "'", long11 == 0L);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test1574() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1574");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        java.lang.String str10 = httpsURLConnectionImpl3.getHeaderFieldKey(402);
        javax.net.ssl.SSLSocketFactory sSLSocketFactory11 = httpsURLConnectionImpl3.getSSLSocketFactory();
        java.lang.String str12 = httpsURLConnectionImpl3.getContentType();
        httpsURLConnectionImpl3.disconnect();
        java.io.InputStream inputStream14 = httpsURLConnectionImpl3.getErrorStream();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertNull(inputStream14);
    }

    @Test
    public void test1575() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1575");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = null;
        authCacheImpl0.remove("content/unknown", authCacheValue6);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue9 = null;
        authCacheImpl0.remove("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]", authCacheValue9);
        org.junit.Assert.assertNull(authCacheValue3);
    }

    @Test
    public void test1576() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1576");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL1 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL1, "");
        boolean boolean4 = progressSource3.connected();
        boolean boolean5 = progressSource3.connected();
        progressMonitor0.registerSource(progressSource3);
        progressSource3.finishTracking();
        java.lang.String str8 = progressSource3.getContentType();
        java.lang.String str9 = progressSource3.getContentType();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "content/unknown" + "'", str8, "content/unknown");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "content/unknown" + "'", str9, "content/unknown");
    }

    @Test
    public void test1577() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1577");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setUseCaches(false);
        java.lang.String str8 = httpsURLConnectionImpl3.getRequestMethod();
        httpsURLConnectionImpl3.addRequestProperty("p4", "{}");
        // The following exception was thrown during execution in test generation
        try {
            long long14 = httpsURLConnectionImpl3.getHeaderFieldDate("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=UPDATE, content-type=content/unknown, progress=0, expected=32]", (long) (short) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "GET" + "'", str8, "GET");
    }

    @Test
    public void test1578() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1578");
        com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker1 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) 1);
        java.security.cert.X509Certificate x509Certificate3 = null;
        // The following exception was thrown during execution in test generation
        try {
            hostnameChecker1.match("AuthenticationHeader: prefer null", x509Certificate3);
            org.junit.Assert.fail("Expected exception of type java.security.cert.CertificateException; message: Illegal given domain name: AuthenticationHeader: prefer null");
        } catch (java.security.cert.CertificateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameChecker1);
    }

    @Test
    public void test1579() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1579");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor27 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        long long31 = progressSource30.getExpected();
        progressMonitor27.registerSource(progressSource30);
        java.lang.String str33 = progressSource30.toString();
        com.quakearts.rest.client.net.HttpClient httpClient35 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream36 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream10, progressSource30, (long) 203, httpClient35);
        boolean boolean37 = keepAliveStream36.markSupported();
        keepAliveStream36.mark(401);
        boolean boolean40 = keepAliveStream36.markSupported();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream41 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream41.reset();
        posterOutputStream41.flush();
        posterOutputStream41.write(8192);
        byte[] byteArray46 = posterOutputStream41.toByteArray();
        // The following exception was thrown during execution in test generation
        try {
            int int47 = keepAliveStream36.read(byteArray46);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertTrue("'" + boolean40 + "' != '" + false + "'", boolean40 == false);
        org.junit.Assert.assertNotNull(byteArray46);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray46), "[0]");
    }

    @Test
    public void test1580() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1580");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        boolean boolean10 = httpURLConnectionImpl5.streaming();
        int int11 = httpURLConnectionImpl5.getConnectTimeout();
        java.io.InputStream inputStream12 = httpURLConnectionImpl5.getErrorStream();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState13 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState13);
        httpURLConnectionImpl5.setConnectTimeout((int) 'a');
        int int17 = httpURLConnectionImpl5.getConnectTimeout();
        // The following exception was thrown during execution in test generation
        try {
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap18 = httpURLConnectionImpl5.getHeaderFields();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNull(inputStream12);
        org.junit.Assert.assertTrue("'" + tunnelState13 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState13.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 97 + "'", int17 == 97);
    }

    @Test
    public void test1581() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1581");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 8192);
        java.net.URL uRL11 = null;
        java.net.Proxy proxy12 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler13 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl14 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL11, proxy12, httpsHandler13);
        javax.net.ssl.HostnameVerifier hostnameVerifier15 = httpsURLConnectionImpl14.getHostnameVerifier();
        boolean boolean16 = httpsURLConnectionImpl14.getDoInput();
        boolean boolean17 = httpsURLConnectionImpl14.getInstanceFollowRedirects();
        httpsURLConnectionImpl14.setChunkedStreamingMode(10);
        java.lang.String str21 = httpsURLConnectionImpl14.getHeaderFieldKey(402);
        boolean boolean22 = httpsURLConnectionImpl14.getUseCaches();
        httpsURLConnectionImpl14.setConnectTimeout(1);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap25 = httpsURLConnectionImpl14.getHeaderFields();
        java.lang.Class<?> wildcardClass26 = httpsURLConnectionImpl14.getClass();
        com.quakearts.rest.client.net.MessageHeader messageHeader27 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str29 = messageHeader27.getValue(503);
        messageHeader27.prepend("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {hi!} }");
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap33 = messageHeader27.getHeaders();
        java.lang.Class<?> wildcardClass34 = strMap33.getClass();
        java.net.URL uRL35 = null;
        java.net.Proxy proxy36 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler37 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl38 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL35, proxy36, httpsHandler37);
        javax.net.ssl.HostnameVerifier hostnameVerifier39 = httpsURLConnectionImpl38.getHostnameVerifier();
        boolean boolean40 = httpsURLConnectionImpl38.getDoInput();
        boolean boolean41 = httpsURLConnectionImpl38.getInstanceFollowRedirects();
        httpsURLConnectionImpl38.setChunkedStreamingMode(10);
        java.lang.String str45 = httpsURLConnectionImpl38.getHeaderFieldKey(402);
        boolean boolean46 = httpsURLConnectionImpl38.getUseCaches();
        httpsURLConnectionImpl38.setConnectTimeout(1);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap49 = httpsURLConnectionImpl38.getHeaderFields();
        java.lang.Class<?> wildcardClass50 = httpsURLConnectionImpl38.getClass();
        com.quakearts.rest.client.net.MessageHeader messageHeader51 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str53 = messageHeader51.getValue(503);
        messageHeader51.prepend("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {hi!} }");
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap57 = messageHeader51.getHeaders();
        java.lang.Class<?> wildcardClass58 = strMap57.getClass();
        java.lang.Class[] classArray59 = new java.lang.Class[] { wildcardClass26, wildcardClass34, wildcardClass50, wildcardClass58 };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj60 = httpsURLConnectionImpl3.getContent(classArray59);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertNotNull(hostnameVerifier15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + true + "'", boolean17 == true);
        org.junit.Assert.assertNull(str21);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertNotNull(strMap25);
        org.junit.Assert.assertNotNull(wildcardClass26);
        org.junit.Assert.assertNull(str29);
        org.junit.Assert.assertNotNull(strMap33);
        org.junit.Assert.assertNotNull(wildcardClass34);
        org.junit.Assert.assertNotNull(hostnameVerifier39);
        org.junit.Assert.assertTrue("'" + boolean40 + "' != '" + true + "'", boolean40 == true);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + true + "'", boolean41 == true);
        org.junit.Assert.assertNull(str45);
        org.junit.Assert.assertTrue("'" + boolean46 + "' != '" + true + "'", boolean46 == true);
        org.junit.Assert.assertNotNull(strMap49);
        org.junit.Assert.assertNotNull(wildcardClass50);
        org.junit.Assert.assertNull(str53);
        org.junit.Assert.assertNotNull(strMap57);
        org.junit.Assert.assertNotNull(wildcardClass58);
        org.junit.Assert.assertNotNull(classArray59);
    }

    @Test
    public void test1582() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1582");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor27 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        long long31 = progressSource30.getExpected();
        progressMonitor27.registerSource(progressSource30);
        java.lang.String str33 = progressSource30.toString();
        com.quakearts.rest.client.net.HttpClient httpClient35 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream36 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream10, progressSource30, (long) 203, httpClient35);
        com.quakearts.rest.client.net.HttpClient httpClient37 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader38 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str40 = messageHeader38.getValue(503);
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream41 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) meteredStream10, httpClient37, messageHeader38);
        com.quakearts.rest.client.net.HttpCapture httpCapture42 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream43 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) meteredStream10, httpCapture42);
        java.net.URL uRL44 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource46 = new com.quakearts.rest.client.net.ProgressSource(uRL44, "");
        progressSource46.close();
        progressSource46.close();
        com.quakearts.rest.client.net.MeteredStream meteredStream50 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource46, (long) 501);
        // The following exception was thrown during execution in test generation
        try {
            meteredStream10.mark(200);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
    }

    @Test
    public void test1583() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1583");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        boolean boolean3 = progressSource2.connected();
        java.net.URL uRL4 = null;
        java.net.URL uRL7 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource9 = new com.quakearts.rest.client.net.ProgressSource(uRL7, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=UPDATE, content-type=content/unknown, progress=0, expected=32]");
        java.net.URL uRL10 = null;
        com.quakearts.rest.client.net.ProgressSource.State state13 = com.quakearts.rest.client.net.ProgressSource.State.CONNECTED;
        com.quakearts.rest.client.net.ProgressEvent progressEvent16 = new com.quakearts.rest.client.net.ProgressEvent(progressSource9, uRL10, "hi!", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]", state13, (long) (byte) 0, (long) 202);
        com.quakearts.rest.client.net.ProgressEvent progressEvent19 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL4, "", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state13, 1L, (long) 301);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + state13 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.CONNECTED + "'", state13.equals(com.quakearts.rest.client.net.ProgressSource.State.CONNECTED));
    }

    @Test
    public void test1584() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1584");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setIfModifiedSince(0L);
        httpsURLConnectionImpl3.setConnectTimeout(501);
        httpsURLConnectionImpl3.setChunkedStreamingMode(501);
        httpsURLConnectionImpl3.setDoInput(true);
        long long17 = httpsURLConnectionImpl3.getIfModifiedSince();
        java.lang.String str18 = httpsURLConnectionImpl3.toString();
        long long19 = httpsURLConnectionImpl3.getContentLengthLong();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 0L + "'", long17 == 0L);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str18, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertTrue("'" + long19 + "' != '" + (-1L) + "'", long19 == (-1L));
    }

    @Test
    public void test1585() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1585");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        httpsURLConnectionImpl3.setReadTimeout(10);
        httpsURLConnectionImpl3.setDoOutput(true);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj13 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
    }

    @Test
    public void test1586() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1586");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        java.net.URL uRL9 = httpsURLConnectionImpl3.getURL();
        boolean boolean10 = httpsURLConnectionImpl3.getDoInput();
        long long13 = httpsURLConnectionImpl3.getHeaderFieldDate("{size=10 nkeys=1  {hi!} }", (long) 52);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + 52L + "'", long13 == 52L);
    }

    @Test
    public void test1587() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1587");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        java.lang.String str10 = httpsURLConnectionImpl3.getHeaderFieldKey(402);
        javax.net.ssl.SSLSocketFactory sSLSocketFactory11 = httpsURLConnectionImpl3.getSSLSocketFactory();
        httpsURLConnectionImpl3.setIfModifiedSince((long) (byte) 100);
        httpsURLConnectionImpl3.setChunkedStreamingMode(206);
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
    }

    @Test
    public void test1588() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1588");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor27 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        long long31 = progressSource30.getExpected();
        progressMonitor27.registerSource(progressSource30);
        java.lang.String str33 = progressSource30.toString();
        com.quakearts.rest.client.net.HttpClient httpClient35 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream36 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream10, progressSource30, (long) 203, httpClient35);
        boolean boolean37 = keepAliveStream36.markSupported();
        boolean boolean38 = keepAliveStream36.markSupported();
        keepAliveStream36.mark(404);
        com.quakearts.rest.client.net.ProgressSource progressSource41 = null;
        com.quakearts.rest.client.net.MeteredStream meteredStream43 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) keepAliveStream36, progressSource41, (long) 32);
        com.quakearts.rest.client.net.HttpCapture httpCapture44 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream45 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) keepAliveStream36, httpCapture44);
        boolean boolean46 = keepAliveStream36.markSupported();
        // The following exception was thrown during execution in test generation
        try {
            keepAliveStream36.reset();
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: mark/reset not supported");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + false + "'", boolean38 == false);
        org.junit.Assert.assertTrue("'" + boolean46 + "' != '" + false + "'", boolean46 == false);
    }

    @Test
    public void test1589() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1589");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setIfModifiedSince(0L);
        httpsURLConnectionImpl3.setConnectTimeout(501);
        httpsURLConnectionImpl3.setChunkedStreamingMode(501);
        httpsURLConnectionImpl3.setUseCaches(true);
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test1590() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1590");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        // The following exception was thrown during execution in test generation
        try {
            meteredStream10.reset();
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: Resetting to an invalid mark");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test1591() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1591");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(100);
        httpsURLConnectionImpl3.setDefaultUseCaches(true);
        long long12 = httpsURLConnectionImpl3.getExpiration();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode((long) 205);
        int int15 = httpsURLConnectionImpl3.getConnectTimeout();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
    }

    @Test
    public void test1592() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1592");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.close();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = posterOutputStream0.toString("GET");
            org.junit.Assert.fail("Expected exception of type java.io.UnsupportedEncodingException; message: GET");
        } catch (java.io.UnsupportedEncodingException e) {
            // Expected exception.
        }
    }

    @Test
    public void test1593() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1593");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        boolean boolean9 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.disconnect();
        // The following exception was thrown during execution in test generation
        try {
            long long11 = httpsURLConnectionImpl3.getExpiration();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test1594() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1594");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL1 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL1, "");
        long long4 = progressSource3.getExpected();
        progressMonitor0.registerSource(progressSource3);
        com.quakearts.rest.client.net.ProgressListener progressListener6 = null;
        progressMonitor0.addProgressListener(progressListener6);
        java.net.URL uRL8 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource10 = new com.quakearts.rest.client.net.ProgressSource(uRL8, "");
        long long11 = progressSource10.getProgress();
        progressSource10.setContentType("AuthenticationHeader: prefer null");
        java.net.URL uRL14 = progressSource10.getURL();
        progressMonitor0.unregisterSource(progressSource10);
        com.quakearts.rest.client.net.ProgressListener progressListener16 = null;
        progressMonitor0.removeProgressListener(progressListener16);
        org.junit.Assert.assertTrue("'" + long4 + "' != '" + (-1L) + "'", long4 == (-1L));
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + 0L + "'", long11 == 0L);
        org.junit.Assert.assertNull(uRL14);
    }

    @Test
    public void test1595() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1595");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        java.net.URL uRL9 = httpsURLConnectionImpl3.getURL();
        boolean boolean10 = httpsURLConnectionImpl3.getDoInput();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap11 = httpsURLConnectionImpl3.getRequestProperties();
        java.lang.String str13 = httpsURLConnectionImpl3.getHeaderField("{size=10 nkeys=1  {get} }");
        java.lang.String str15 = httpsURLConnectionImpl3.getHeaderField(414);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(strMap11);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertNull(str15);
    }

    @Test
    public void test1596() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1596");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        progressSource2.beginTracking();
        progressSource2.beginTracking();
        boolean boolean6 = progressSource2.connected();
        progressSource2.beginTracking();
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test1597() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1597");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        boolean boolean9 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setDoInput(false);
        long long14 = httpsURLConnectionImpl3.getHeaderFieldDate("com.quakearts.rest.client.net.http.HttpURLConnectionImpl:null", (long) 501);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + 501L + "'", long14 == 501L);
    }

    @Test
    public void test1598() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1598");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int1 = progressMonitor0.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor2 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL3 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource5 = new com.quakearts.rest.client.net.ProgressSource(uRL3, "");
        long long6 = progressSource5.getExpected();
        progressMonitor2.registerSource(progressSource5);
        progressMonitor0.unregisterSource(progressSource5);
        com.quakearts.rest.client.net.ProgressListener progressListener9 = null;
        progressMonitor0.removeProgressListener(progressListener9);
        java.io.InputStream inputStream11 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture12 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream13 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream11, httpCapture12);
        com.quakearts.rest.client.net.HttpCapture httpCapture14 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream15 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream11, httpCapture14);
        java.net.URL uRL16 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource18 = new com.quakearts.rest.client.net.ProgressSource(uRL16, "");
        long long19 = progressSource18.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream21 = new com.quakearts.rest.client.net.MeteredStream(inputStream11, progressSource18, (long) (byte) 1);
        progressMonitor0.updateProgress(progressSource18);
        java.net.URL uRL23 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource25 = new com.quakearts.rest.client.net.ProgressSource(uRL23, "");
        progressMonitor0.updateProgress(progressSource25);
        com.quakearts.rest.client.net.ProgressSource progressSource27 = new com.quakearts.rest.client.net.ProgressSource(progressSource25);
        java.net.URL uRL28 = progressSource25.getURL();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8192 + "'", int1 == 8192);
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + (-1L) + "'", long6 == (-1L));
        org.junit.Assert.assertTrue("'" + long19 + "' != '" + (-1L) + "'", long19 == (-1L));
        org.junit.Assert.assertNull(uRL28);
    }

    @Test
    public void test1599() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1599");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        httpsURLConnectionImpl3.setDoInput(true);
        javax.net.ssl.HostnameVerifier hostnameVerifier11 = httpsURLConnectionImpl3.getHostnameVerifier();
        int int14 = httpsURLConnectionImpl3.getHeaderFieldInt("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=UPDATE, content-type=content/unknown, progress=0, expected=32]", 413);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNotNull(hostnameVerifier11);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 413 + "'", int14 == 413);
    }

    @Test
    public void test1600() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1600");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap1 = messageHeader0.getHeaders();
        messageHeader0.set(406, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "content/unknown");
        messageHeader0.set(32, "AuthenticationHeader: prefer null", "GET");
        org.junit.Assert.assertNotNull(strMap1);
    }

    @Test
    public void test1601() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1601");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler5 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl6 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL3, proxy4, httpsHandler5);
        javax.net.ssl.HostnameVerifier hostnameVerifier7 = httpsURLConnectionImpl6.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier8 = httpsURLConnectionImpl6.getHostnameVerifier();
        httpsURLConnectionImpl6.setFixedLengthStreamingMode(401);
        java.net.CacheRequest cacheRequest11 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpsURLConnectionImpl6);
        httpsURLConnectionImpl6.setReadTimeout(415);
        httpsURLConnectionImpl6.setAllowUserInteraction(true);
        httpsURLConnectionImpl6.setFixedLengthStreamingMode((long) 300);
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
    }

    @Test
    public void test1602() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1602");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler5 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl6 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL3, proxy4, httpsHandler5);
        javax.net.ssl.HostnameVerifier hostnameVerifier7 = httpsURLConnectionImpl6.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier8 = httpsURLConnectionImpl6.getHostnameVerifier();
        httpsURLConnectionImpl6.setFixedLengthStreamingMode(401);
        java.net.CacheRequest cacheRequest11 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpsURLConnectionImpl6);
        long long12 = httpsURLConnectionImpl6.getLastModified();
        httpsURLConnectionImpl6.setAllowUserInteraction(false);
        long long15 = httpsURLConnectionImpl6.getContentLengthLong();
        httpsURLConnectionImpl6.setConnectTimeout(405);
        httpsURLConnectionImpl6.setFixedLengthStreamingMode((int) (short) 1);
        long long20 = httpsURLConnectionImpl6.getDate();
        httpsURLConnectionImpl6.disconnect();
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + (-1L) + "'", long15 == (-1L));
        org.junit.Assert.assertTrue("'" + long20 + "' != '" + 0L + "'", long20 == 0L);
    }

    @Test
    public void test1603() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1603");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int1 = progressMonitor0.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor2 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL3 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource5 = new com.quakearts.rest.client.net.ProgressSource(uRL3, "");
        long long6 = progressSource5.getExpected();
        progressMonitor2.registerSource(progressSource5);
        progressMonitor0.unregisterSource(progressSource5);
        java.net.URL uRL9 = null;
        boolean boolean11 = progressMonitor0.shouldMeterInput(uRL9, "{}");
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8192 + "'", int1 == 8192);
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + (-1L) + "'", long6 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test1604() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1604");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        messageHeader1.set("content/unknown", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.io.InputStream inputStream5 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture6 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream7 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream5, httpCapture6);
        messageHeader1.parseHeader(inputStream5);
        java.lang.String str10 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        java.lang.String[] strArray21 = new java.lang.String[] { "GET", "{size=10 nkeys=1  {hi!} }", "GET", "AuthenticationHeader: prefer null", "{size=10 nkeys=1  {get} }", "", "", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "content/unknown", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" };
        java.util.LinkedHashSet<java.lang.String> strSet22 = new java.util.LinkedHashSet<java.lang.String>();
        boolean boolean23 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strSet22, strArray21);
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader24 = new com.quakearts.rest.client.net.http.AuthenticationHeader("", messageHeader1, (java.util.Set<java.lang.String>) strSet22);
        java.lang.String str25 = authenticationHeader24.scheme();
        com.quakearts.rest.client.net.HeaderParser headerParser26 = authenticationHeader24.headerParser();
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(strArray21);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + true + "'", boolean23 == true);
        org.junit.Assert.assertNull(str25);
        org.junit.Assert.assertNull(headerParser26);
    }

    @Test
    public void test1605() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1605");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor27 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        long long31 = progressSource30.getExpected();
        progressMonitor27.registerSource(progressSource30);
        java.lang.String str33 = progressSource30.toString();
        com.quakearts.rest.client.net.HttpClient httpClient35 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream36 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream10, progressSource30, (long) 203, httpClient35);
        boolean boolean37 = keepAliveStream36.markSupported();
        boolean boolean38 = keepAliveStream36.markSupported();
        keepAliveStream36.mark((int) (short) 100);
        boolean boolean41 = keepAliveStream36.markSupported();
        keepAliveStream36.mark(412);
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + false + "'", boolean38 == false);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + false + "'", boolean41 == false);
    }

    @Test
    public void test1606() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1606");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 8192);
        java.net.URL uRL11 = httpsURLConnectionImpl3.getURL();
        httpsURLConnectionImpl3.setChunkedStreamingMode(409);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertNull(uRL11);
    }

    @Test
    public void test1607() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1607");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream3 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture2);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream4 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray5 = posterOutputStream4.toByteArray();
        httpCaptureOutputStream3.write(byteArray5);
        com.quakearts.rest.client.net.HttpCapture httpCapture7 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream8 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) httpCaptureOutputStream3, httpCapture7);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream8.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray5), "[]");
    }

    @Test
    public void test1608() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1608");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        long long3 = progressSource2.getProgress();
        java.lang.String str4 = progressSource2.getMethod();
        progressSource2.beginTracking();
        org.junit.Assert.assertTrue("'" + long3 + "' != '" + 0L + "'", long3 == 0L);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str4, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
    }

    @Test
    public void test1609() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1609");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue2 = null;
        authCacheImpl0.remove("{}", authCacheValue2);
    }

    @Test
    public void test1610() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1610");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        java.lang.String str7 = httpsURLConnectionImpl3.getRequestProperty("hi!");
        httpsURLConnectionImpl3.setDefaultUseCaches(true);
        // The following exception was thrown during execution in test generation
        try {
            int int12 = httpsURLConnectionImpl3.getHeaderFieldInt("GET", 402);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNull(str7);
    }

    @Test
    public void test1611() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1611");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = new com.quakearts.rest.client.net.https.HttpsHandler();
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str4 = httpsURLConnectionImpl3.getContentType();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test1612() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1612");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.URI uRI1 = null;
        java.net.URL uRL2 = null;
        java.net.Proxy proxy3 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler4 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl5 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL2, proxy3, httpsHandler4);
        javax.net.ssl.HostnameVerifier hostnameVerifier6 = httpsURLConnectionImpl5.getHostnameVerifier();
        boolean boolean7 = httpsURLConnectionImpl5.getDoInput();
        httpsURLConnectionImpl5.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl5.addRequestProperty("", "hi!");
        int int15 = httpsURLConnectionImpl5.getHeaderFieldInt("hi!", 100);
        java.net.CacheRequest cacheRequest16 = responseCacheImpl0.put(uRI1, (java.net.URLConnection) httpsURLConnectionImpl5);
        httpsURLConnectionImpl5.setUseCaches(false);
        httpsURLConnectionImpl5.setReadTimeout((int) (short) 100);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str21 = httpsURLConnectionImpl5.getCipherSuite();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
    }

    @Test
    public void test1613() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1613");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler2 = new com.quakearts.rest.client.net.http.HttpHandler();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl3 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler2);
        int int4 = httpURLConnectionImpl3.getReadTimeout();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test1614() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1614");
        java.net.URL uRL0 = null;
        java.net.URL uRL5 = null;
        java.net.Proxy proxy6 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler9 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl10 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL5, proxy6, httpHandler9);
        int int11 = httpURLConnectionImpl10.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState12 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl10.setTunnelState(tunnelState12);
        int int14 = httpURLConnectionImpl10.getConnectTimeout();
        boolean boolean15 = httpURLConnectionImpl10.streaming();
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient16 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, "", (int) (short) 0, true, 204, httpURLConnectionImpl10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState12 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState12.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test1615() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1615");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap8 = httpsURLConnectionImpl3.getRequestProperties();
        long long11 = httpsURLConnectionImpl3.getHeaderFieldDate("content/unknown", (long) 401);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNotNull(strMap8);
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + 401L + "'", long11 == 401L);
    }

    @Test
    public void test1616() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1616");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        java.lang.String str10 = httpsURLConnectionImpl3.getHeaderFieldKey(402);
        // The following exception was thrown during execution in test generation
        try {
            java.security.Principal principal11 = httpsURLConnectionImpl3.getPeerPrincipal();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
    }

    @Test
    public void test1617() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1617");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        java.lang.String str10 = httpsURLConnectionImpl3.getHeaderFieldKey(402);
        javax.net.ssl.SSLSocketFactory sSLSocketFactory11 = httpsURLConnectionImpl3.getSSLSocketFactory();
        java.lang.String str12 = httpsURLConnectionImpl3.getContentType();
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setReadTimeout((int) (short) 100);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test1618() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1618");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream5 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream5.reset();
        posterOutputStream5.flush();
        posterOutputStream5.write(8192);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream10 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream10.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream12 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream12.flush();
        posterOutputStream10.writeTo((java.io.OutputStream) posterOutputStream12);
        com.quakearts.rest.client.net.HttpCapture httpCapture15 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream16 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream10, httpCapture15);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream17 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray18 = posterOutputStream17.toByteArray();
        byte[] byteArray19 = posterOutputStream17.toByteArray();
        httpCaptureOutputStream16.write(byteArray19);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream21 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray22 = posterOutputStream21.toByteArray();
        httpCaptureOutputStream16.write(byteArray22);
        posterOutputStream5.write(byteArray22);
        // The following exception was thrown during execution in test generation
        try {
            int int25 = httpCaptureInputStream4.read(byteArray22);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray18), "[]");
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray19), "[]");
        org.junit.Assert.assertNotNull(byteArray22);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray22), "[]");
    }

    @Test
    public void test1619() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1619");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", 204);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
    }

    @Test
    public void test1620() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1620");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        java.io.InputStream inputStream10 = httpURLConnectionImpl5.getErrorStream();
        java.io.InputStream inputStream11 = httpURLConnectionImpl5.getErrorStream();
        boolean boolean12 = httpURLConnectionImpl5.streaming();
        httpURLConnectionImpl5.disconnect();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str15 = httpURLConnectionImpl5.getHeaderField("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertNull(inputStream11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test1621() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1621");
        com.quakearts.rest.client.net.MessageHeader messageHeader2 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str4 = messageHeader2.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader2.set(406, "content/unknown", "hi!");
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader9 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader2);
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader10 = new com.quakearts.rest.client.net.http.AuthenticationHeader("p4", messageHeader2);
        java.lang.String str11 = authenticationHeader10.toString();
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "AuthenticationHeader: prefer null" + "'", str11, "AuthenticationHeader: prefer null");
    }

    @Test
    public void test1622() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1622");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource.State state9 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent12 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL6, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state9, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(progressSource2);
        java.net.URL uRL14 = progressSource13.getURL();
        progressSource13.close();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNull(uRL14);
    }

    @Test
    public void test1623() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1623");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        java.lang.String str10 = httpsURLConnectionImpl3.getHeaderFieldKey(402);
        javax.net.ssl.SSLSocketFactory sSLSocketFactory11 = httpsURLConnectionImpl3.getSSLSocketFactory();
        long long14 = httpsURLConnectionImpl3.getHeaderFieldDate("content/unknown", (long) (short) 100);
        long long17 = httpsURLConnectionImpl3.getHeaderFieldDate("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", (long) 501);
        httpsURLConnectionImpl3.setReadTimeout(405);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + 100L + "'", long14 == 100L);
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 501L + "'", long17 == 501L);
    }

    @Test
    public void test1624() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1624");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        java.net.URL uRL4 = null;
        java.net.Proxy proxy5 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler8 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl9 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL4, proxy5, httpHandler8);
        int int10 = httpURLConnectionImpl9.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState11 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl9.setTunnelState(tunnelState11);
        boolean boolean13 = httpURLConnectionImpl9.streaming();
        java.io.InputStream inputStream14 = httpURLConnectionImpl9.getErrorStream();
        java.io.InputStream inputStream15 = httpURLConnectionImpl9.getErrorStream();
        java.io.InputStream inputStream16 = httpURLConnectionImpl9.getErrorStream();
        boolean boolean17 = httpURLConnectionImpl9.usingProxy();
        boolean boolean18 = httpURLConnectionImpl9.streaming();
        java.net.URL uRL19 = null;
        java.net.Proxy proxy20 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler23 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl24 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL19, proxy20, httpHandler23);
        int int25 = httpURLConnectionImpl24.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState26 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl24.setTunnelState(tunnelState26);
        httpURLConnectionImpl9.setTunnelState(tunnelState26);
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient29 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, proxy1, 302, true, httpURLConnectionImpl9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState11 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState11.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNull(inputStream14);
        org.junit.Assert.assertNull(inputStream15);
        org.junit.Assert.assertNull(inputStream16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertTrue("'" + int25 + "' != '" + 0 + "'", int25 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState26 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState26.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
    }

    @Test
    public void test1625() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1625");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        boolean boolean10 = httpURLConnectionImpl5.streaming();
        httpURLConnectionImpl5.setReadTimeout(414);
        httpURLConnectionImpl5.setDefaultUseCaches(false);
        com.quakearts.rest.client.net.MessageHeader messageHeader16 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str18 = messageHeader16.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader16.set(406, "content/unknown", "hi!");
        messageHeader16.prepend("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "");
        java.lang.String[] strArray36 = new java.lang.String[] { "{size=10 nkeys=1  {hi!} }", "GET", "{size=10 nkeys=1  {get} }", "{size=10 nkeys=1  {hi!} }", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "AuthenticationHeader: prefer null", "AuthenticationHeader: prefer null", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "hi!", "{size=10 nkeys=1  {hi!} }" };
        java.util.LinkedHashSet<java.lang.String> strSet37 = new java.util.LinkedHashSet<java.lang.String>();
        boolean boolean38 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strSet37, strArray36);
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader39 = new com.quakearts.rest.client.net.http.AuthenticationHeader("", messageHeader16, (java.util.Set<java.lang.String>) strSet37);
        httpURLConnectionImpl5.authObj((java.lang.Object) "");
        httpURLConnectionImpl5.setRequestProperty("", "{size=10 nkeys=1  {hi!} }");
        httpURLConnectionImpl5.setConnectTimeout(407);
        httpURLConnectionImpl5.setRequestProperty("GET", "\310");
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNull(str18);
        org.junit.Assert.assertNotNull(strArray36);
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + true + "'", boolean38 == true);
    }

    @Test
    public void test1626() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1626");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], state=UPDATE, content-type=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], progress=501, expected=304]", 304L);
    }

    @Test
    public void test1627() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1627");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        httpsURLConnectionImpl3.setReadTimeout(10);
        long long13 = httpsURLConnectionImpl3.getHeaderFieldLong("{}", (long) (byte) 1);
        int int14 = httpsURLConnectionImpl3.getReadTimeout();
        java.lang.String str15 = httpsURLConnectionImpl3.toString();
        boolean boolean16 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + 1L + "'", long13 == 1L);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 10 + "'", int14 == 10);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str15, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
    }

    @Test
    public void test1628() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1628");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setChunkedStreamingMode(408);
        httpsURLConnectionImpl3.setChunkedStreamingMode((int) (byte) 10);
        com.quakearts.rest.client.net.HeaderParser headerParser11 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str13 = headerParser11.findKey(305);
        int int16 = headerParser11.findInt("hi!", 32);
        java.lang.String str18 = headerParser11.findValue(406);
        java.lang.String str21 = headerParser11.findValue("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {get} }");
        java.util.Iterator<java.lang.String> strItor22 = headerParser11.values();
        java.lang.String str24 = headerParser11.findValue(0);
        java.util.Iterator<java.lang.String> strItor25 = headerParser11.keys();
        java.lang.String str27 = headerParser11.findValue("AuthenticationHeader: prefer null");
        java.lang.Class<?> wildcardClass28 = headerParser11.getClass();
        java.net.URL uRL29 = null;
        java.net.Proxy proxy30 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler31 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl32 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL29, proxy30, httpsHandler31);
        javax.net.ssl.HostnameVerifier hostnameVerifier33 = httpsURLConnectionImpl32.getHostnameVerifier();
        boolean boolean34 = httpsURLConnectionImpl32.getDoInput();
        httpsURLConnectionImpl32.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl32.setFixedLengthStreamingMode(100);
        httpsURLConnectionImpl32.setIfModifiedSince(0L);
        java.lang.Class<?> wildcardClass41 = httpsURLConnectionImpl32.getClass();
        com.quakearts.rest.client.net.MessageHeader messageHeader42 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str44 = messageHeader42.getValue(503);
        messageHeader42.prepend("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {hi!} }");
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap48 = messageHeader42.getHeaders();
        java.lang.Class<?> wildcardClass49 = strMap48.getClass();
        com.quakearts.rest.client.net.HeaderParser headerParser51 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str53 = headerParser51.findKey(305);
        int int56 = headerParser51.findInt("hi!", 32);
        java.lang.String str58 = headerParser51.findValue(406);
        java.lang.String str61 = headerParser51.findValue("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {get} }");
        java.util.Iterator<java.lang.String> strItor62 = headerParser51.values();
        java.lang.String str64 = headerParser51.findValue(0);
        java.util.Iterator<java.lang.String> strItor65 = headerParser51.keys();
        java.lang.String str67 = headerParser51.findValue("AuthenticationHeader: prefer null");
        java.lang.Class<?> wildcardClass68 = headerParser51.getClass();
        java.net.URL uRL69 = null;
        java.net.Proxy proxy70 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler71 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl72 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL69, proxy70, httpsHandler71);
        javax.net.ssl.HostnameVerifier hostnameVerifier73 = httpsURLConnectionImpl72.getHostnameVerifier();
        boolean boolean74 = httpsURLConnectionImpl72.getDoInput();
        boolean boolean75 = httpsURLConnectionImpl72.getInstanceFollowRedirects();
        httpsURLConnectionImpl72.setChunkedStreamingMode(10);
        java.lang.String str79 = httpsURLConnectionImpl72.getHeaderFieldKey(402);
        boolean boolean80 = httpsURLConnectionImpl72.getUseCaches();
        httpsURLConnectionImpl72.setConnectTimeout(1);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap83 = httpsURLConnectionImpl72.getHeaderFields();
        java.lang.Class<?> wildcardClass84 = httpsURLConnectionImpl72.getClass();
        java.net.URL uRL85 = null;
        java.net.Proxy proxy86 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler87 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl88 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL85, proxy86, httpsHandler87);
        javax.net.ssl.HostnameVerifier hostnameVerifier89 = httpsURLConnectionImpl88.getHostnameVerifier();
        boolean boolean90 = httpsURLConnectionImpl88.getDoInput();
        httpsURLConnectionImpl88.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl88.setFixedLengthStreamingMode(100);
        httpsURLConnectionImpl88.setIfModifiedSince(0L);
        java.lang.Class<?> wildcardClass97 = httpsURLConnectionImpl88.getClass();
        java.lang.Class[] classArray98 = new java.lang.Class[] { wildcardClass28, wildcardClass41, wildcardClass49, wildcardClass68, wildcardClass84, wildcardClass97 };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj99 = httpsURLConnectionImpl3.getContent(classArray98);
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 32 + "'", int16 == 32);
        org.junit.Assert.assertNull(str18);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "{size=10 nkeys=1  {get} }" + "'", str21, "{size=10 nkeys=1  {get} }");
        org.junit.Assert.assertNotNull(strItor22);
        org.junit.Assert.assertNull(str24);
        org.junit.Assert.assertNotNull(strItor25);
        org.junit.Assert.assertNull(str27);
        org.junit.Assert.assertNotNull(wildcardClass28);
        org.junit.Assert.assertNotNull(hostnameVerifier33);
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + true + "'", boolean34 == true);
        org.junit.Assert.assertNotNull(wildcardClass41);
        org.junit.Assert.assertNull(str44);
        org.junit.Assert.assertNotNull(strMap48);
        org.junit.Assert.assertNotNull(wildcardClass49);
        org.junit.Assert.assertNull(str53);
        org.junit.Assert.assertTrue("'" + int56 + "' != '" + 32 + "'", int56 == 32);
        org.junit.Assert.assertNull(str58);
        org.junit.Assert.assertEquals("'" + str61 + "' != '" + "{size=10 nkeys=1  {get} }" + "'", str61, "{size=10 nkeys=1  {get} }");
        org.junit.Assert.assertNotNull(strItor62);
        org.junit.Assert.assertNull(str64);
        org.junit.Assert.assertNotNull(strItor65);
        org.junit.Assert.assertNull(str67);
        org.junit.Assert.assertNotNull(wildcardClass68);
        org.junit.Assert.assertNotNull(hostnameVerifier73);
        org.junit.Assert.assertTrue("'" + boolean74 + "' != '" + true + "'", boolean74 == true);
        org.junit.Assert.assertTrue("'" + boolean75 + "' != '" + true + "'", boolean75 == true);
        org.junit.Assert.assertNull(str79);
        org.junit.Assert.assertTrue("'" + boolean80 + "' != '" + false + "'", boolean80 == false);
        org.junit.Assert.assertNotNull(strMap83);
        org.junit.Assert.assertNotNull(wildcardClass84);
        org.junit.Assert.assertNotNull(hostnameVerifier89);
        org.junit.Assert.assertTrue("'" + boolean90 + "' != '" + true + "'", boolean90 == true);
        org.junit.Assert.assertNotNull(wildcardClass97);
        org.junit.Assert.assertNotNull(classArray98);
    }

    @Test
    public void test1629() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1629");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        java.net.URL uRL11 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(uRL11, "");
        progressSource13.close();
        boolean boolean15 = progressSource13.connected();
        progressSource13.finishTracking();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource13, uRL17, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state20, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(progressSource13);
        com.quakearts.rest.client.net.MeteredStream meteredStream26 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource13, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor27 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        long long31 = progressSource30.getExpected();
        progressMonitor27.registerSource(progressSource30);
        java.lang.String str33 = progressSource30.toString();
        com.quakearts.rest.client.net.HttpClient httpClient35 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream36 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream10, progressSource30, (long) 203, httpClient35);
        boolean boolean37 = keepAliveStream36.markSupported();
        boolean boolean38 = keepAliveStream36.markSupported();
        java.lang.String str39 = java.net.URLConnection.guessContentTypeFromStream((java.io.InputStream) keepAliveStream36);
        com.quakearts.rest.client.net.HttpCapture httpCapture40 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream41 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) keepAliveStream36, httpCapture40);
        boolean boolean42 = keepAliveStream36.markSupported();
        boolean boolean43 = keepAliveStream36.markSupported();
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + false + "'", boolean38 == false);
        org.junit.Assert.assertNull(str39);
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + false + "'", boolean42 == false);
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + false + "'", boolean43 == false);
    }

    @Test
    public void test1630() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1630");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(100);
        httpsURLConnectionImpl3.setIfModifiedSince(0L);
        java.lang.String str12 = httpsURLConnectionImpl3.getContentEncoding();
        boolean boolean13 = httpsURLConnectionImpl3.getDefaultUseCaches();
        boolean boolean14 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setUseCaches(true);
        java.io.InputStream inputStream17 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture18 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream19 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream17, httpCapture18);
        com.quakearts.rest.client.net.HttpCapture httpCapture20 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream21 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream17, httpCapture20);
        java.net.URL uRL22 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource24 = new com.quakearts.rest.client.net.ProgressSource(uRL22, "");
        long long25 = progressSource24.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream27 = new com.quakearts.rest.client.net.MeteredStream(inputStream17, progressSource24, (long) (byte) 1);
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        progressSource30.close();
        boolean boolean32 = progressSource30.connected();
        progressSource30.finishTracking();
        java.net.URL uRL34 = null;
        com.quakearts.rest.client.net.ProgressSource.State state37 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent40 = new com.quakearts.rest.client.net.ProgressEvent(progressSource30, uRL34, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state37, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource41 = new com.quakearts.rest.client.net.ProgressSource(progressSource30);
        com.quakearts.rest.client.net.MeteredStream meteredStream43 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream27, progressSource30, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor44 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL45 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource47 = new com.quakearts.rest.client.net.ProgressSource(uRL45, "");
        long long48 = progressSource47.getExpected();
        progressMonitor44.registerSource(progressSource47);
        java.lang.String str50 = progressSource47.toString();
        com.quakearts.rest.client.net.HttpClient httpClient52 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream53 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream27, progressSource47, (long) 203, httpClient52);
        com.quakearts.rest.client.net.HttpClient httpClient54 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader55 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str57 = messageHeader55.getValue(503);
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream58 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) meteredStream27, httpClient54, messageHeader55);
        boolean boolean59 = httpsURLConnectionImpl3.equals((java.lang.Object) chunkedInputStream58);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream60 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream60.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture62 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream63 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream60, httpCapture62);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream64 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream64.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture66 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream67 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream64, httpCapture66);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream68 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray69 = posterOutputStream68.toByteArray();
        httpCaptureOutputStream67.write(byteArray69);
        httpCaptureOutputStream63.write(byteArray69);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream72 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray73 = posterOutputStream72.toByteArray();
        httpCaptureOutputStream63.write(byteArray73);
        // The following exception was thrown during execution in test generation
        try {
            int int77 = chunkedInputStream58.read(byteArray73, 8192, 202);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
        org.junit.Assert.assertTrue("'" + long25 + "' != '" + (-1L) + "'", long25 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertTrue("'" + long48 + "' != '" + (-1L) + "'", long48 == (-1L));
        org.junit.Assert.assertEquals("'" + str50 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str50, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str57);
        org.junit.Assert.assertTrue("'" + boolean59 + "' != '" + false + "'", boolean59 == false);
        org.junit.Assert.assertNotNull(byteArray69);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray69), "[]");
        org.junit.Assert.assertNotNull(byteArray73);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray73), "[]");
    }

    @Test
    public void test1631() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1631");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setUseCaches(false);
        java.lang.String str8 = httpsURLConnectionImpl3.getRequestMethod();
        java.net.URL uRL9 = httpsURLConnectionImpl3.getURL();
        httpsURLConnectionImpl3.setDefaultUseCaches(true);
        httpsURLConnectionImpl3.setIfModifiedSince(10L);
        boolean boolean14 = httpsURLConnectionImpl3.getDefaultUseCaches();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "GET" + "'", str8, "GET");
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
    }

    @Test
    public void test1632() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1632");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader1.set(406, "content/unknown", "hi!");
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader8 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        com.quakearts.rest.client.net.MessageHeader messageHeader9 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str11 = messageHeader9.getValue(503);
        messageHeader9.add("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "");
        java.lang.String[] strArray19 = new java.lang.String[] { "", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" };
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap20 = messageHeader9.getHeaders(strArray19);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap21 = messageHeader1.getHeaders(strArray19);
        messageHeader1.setIfNotSet("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]");
        java.lang.String str26 = messageHeader1.findValue("{size=10 nkeys=1  {hi!} }");
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str11);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertNotNull(strMap20);
        org.junit.Assert.assertNotNull(strMap21);
        org.junit.Assert.assertNull(str26);
    }

    @Test
    public void test1633() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1633");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        boolean boolean4 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.setInstanceFollowRedirects(false);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj7 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test1634() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1634");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(100);
        httpsURLConnectionImpl3.setIfModifiedSince(0L);
        java.net.URL uRL12 = httpsURLConnectionImpl3.getURL();
        httpsURLConnectionImpl3.setReadTimeout(413);
        // The following exception was thrown during execution in test generation
        try {
            int int15 = httpsURLConnectionImpl3.getResponseCode();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(uRL12);
    }

    @Test
    public void test1635() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1635");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.io.InputStream inputStream1 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream3 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture2);
        com.quakearts.rest.client.net.HttpCapture httpCapture4 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream5 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture4);
        messageHeader0.parseHeader(inputStream1);
        java.io.InputStream inputStream7 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture8 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream9 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream7, httpCapture8);
        com.quakearts.rest.client.net.HttpCapture httpCapture10 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream11 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream7, httpCapture10);
        java.net.URL uRL12 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource14 = new com.quakearts.rest.client.net.ProgressSource(uRL12, "");
        long long15 = progressSource14.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream17 = new com.quakearts.rest.client.net.MeteredStream(inputStream7, progressSource14, (long) (byte) 1);
        messageHeader0.parseHeader(inputStream7);
        java.lang.String str20 = messageHeader0.getValue(409);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap21 = messageHeader0.getHeaders();
        messageHeader0.reset();
        messageHeader0.add("content/unknown", "{size=10 nkeys=1  {get} }");
        java.lang.String str27 = messageHeader0.getKey(300);
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + (-1L) + "'", long15 == (-1L));
        org.junit.Assert.assertNull(str20);
        org.junit.Assert.assertNotNull(strMap21);
        org.junit.Assert.assertNull(str27);
    }

    @Test
    public void test1636() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1636");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setIfModifiedSince(0L);
        httpsURLConnectionImpl3.setConnectTimeout(501);
        javax.net.ssl.SSLSocketFactory sSLSocketFactory13 = javax.net.ssl.HttpsURLConnection.getDefaultSSLSocketFactory();
        httpsURLConnectionImpl3.setSSLSocketFactory(sSLSocketFactory13);
        boolean boolean15 = httpsURLConnectionImpl3.getDoInput();
        // The following exception was thrown during execution in test generation
        try {
            int int18 = httpsURLConnectionImpl3.getHeaderFieldInt("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", 410);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertNotNull(sSLSocketFactory13);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + true + "'", boolean15 == true);
    }

    @Test
    public void test1637() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1637");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.URI uRI1 = null;
        java.net.URL uRL2 = null;
        java.net.Proxy proxy3 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler4 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl5 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL2, proxy3, httpsHandler4);
        javax.net.ssl.HostnameVerifier hostnameVerifier6 = httpsURLConnectionImpl5.getHostnameVerifier();
        boolean boolean7 = httpsURLConnectionImpl5.getDoInput();
        httpsURLConnectionImpl5.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl5.addRequestProperty("", "hi!");
        int int15 = httpsURLConnectionImpl5.getHeaderFieldInt("hi!", 100);
        java.net.CacheRequest cacheRequest16 = responseCacheImpl0.put(uRI1, (java.net.URLConnection) httpsURLConnectionImpl5);
        httpsURLConnectionImpl5.setFixedLengthStreamingMode((long) 203);
        java.lang.String str19 = httpsURLConnectionImpl5.getContentType();
        long long22 = httpsURLConnectionImpl5.getHeaderFieldDate("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", (long) (byte) -1);
        boolean boolean23 = httpsURLConnectionImpl5.getInstanceFollowRedirects();
        httpsURLConnectionImpl5.setFixedLengthStreamingMode(100L);
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertNull(str19);
        org.junit.Assert.assertTrue("'" + long22 + "' != '" + (-1L) + "'", long22 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + true + "'", boolean23 == true);
    }

    @Test
    public void test1638() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1638");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        httpsURLConnectionImpl3.setRequestProperty("hi!", "");
        httpsURLConnectionImpl3.setReadTimeout((int) (short) 100);
        httpsURLConnectionImpl3.setUseCaches(true);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test1639() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1639");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.URI uRI1 = null;
        java.net.URL uRL2 = null;
        java.net.Proxy proxy3 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler4 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl5 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL2, proxy3, httpsHandler4);
        javax.net.ssl.HostnameVerifier hostnameVerifier6 = httpsURLConnectionImpl5.getHostnameVerifier();
        boolean boolean7 = httpsURLConnectionImpl5.getDoInput();
        httpsURLConnectionImpl5.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl5.addRequestProperty("", "hi!");
        int int15 = httpsURLConnectionImpl5.getHeaderFieldInt("hi!", 100);
        java.net.CacheRequest cacheRequest16 = responseCacheImpl0.put(uRI1, (java.net.URLConnection) httpsURLConnectionImpl5);
        httpsURLConnectionImpl5.setFixedLengthStreamingMode((long) 203);
        java.lang.String str19 = httpsURLConnectionImpl5.getContentType();
        long long22 = httpsURLConnectionImpl5.getHeaderFieldDate("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", (long) (byte) -1);
        java.lang.String str23 = httpsURLConnectionImpl5.getContentType();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap24 = httpsURLConnectionImpl5.getHeaderFields();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str25 = httpsURLConnectionImpl5.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertNull(str19);
        org.junit.Assert.assertTrue("'" + long22 + "' != '" + (-1L) + "'", long22 == (-1L));
        org.junit.Assert.assertNull(str23);
        org.junit.Assert.assertNotNull(strMap24);
    }

    @Test
    public void test1640() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1640");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("GET");
        java.lang.String str4 = headerParser1.findValue("GET", "{size=10 nkeys=1  {get} }");
        java.lang.String str6 = headerParser1.findValue(301);
        java.lang.String str8 = headerParser1.findValue("{}");
        java.lang.String str11 = headerParser1.findValue("AuthenticationHeader: prefer null", "{size=10 nkeys=1  {hi!} }");
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertNull(str8);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "{size=10 nkeys=1  {hi!} }" + "'", str11, "{size=10 nkeys=1  {hi!} }");
    }

    @Test
    public void test1641() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1641");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        boolean boolean10 = httpURLConnectionImpl5.streaming();
        int int11 = httpURLConnectionImpl5.getConnectTimeout();
        java.io.InputStream inputStream12 = httpURLConnectionImpl5.getErrorStream();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState13 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState13);
        httpURLConnectionImpl5.setConnectTimeout((int) 'a');
        java.net.URL uRL17 = null;
        java.net.Proxy proxy18 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler21 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl22 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL17, proxy18, httpHandler21);
        int int23 = httpURLConnectionImpl22.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState24 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl22.setTunnelState(tunnelState24);
        boolean boolean26 = httpURLConnectionImpl22.streaming();
        java.net.URL uRL27 = null;
        java.net.Proxy proxy28 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler31 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl32 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL27, proxy28, httpHandler31);
        int int33 = httpURLConnectionImpl32.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState34 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl32.setTunnelState(tunnelState34);
        httpURLConnectionImpl22.setTunnelState(tunnelState34);
        httpURLConnectionImpl5.setTunnelState(tunnelState34);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNull(inputStream12);
        org.junit.Assert.assertTrue("'" + tunnelState13 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState13.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + int23 + "' != '" + 0 + "'", int23 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState24 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState24.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertTrue("'" + int33 + "' != '" + 0 + "'", int33 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState34 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState34.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
    }

    @Test
    public void test1642() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1642");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        messageHeader0.set("content/unknown", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.io.InputStream inputStream4 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture5 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream6 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream4, httpCapture5);
        messageHeader0.parseHeader(inputStream4);
        java.lang.String str9 = messageHeader0.findValue("hi!");
        com.quakearts.rest.client.net.MessageHeader messageHeader10 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str12 = messageHeader10.getValue(503);
        messageHeader10.add("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "");
        java.util.Iterator<java.lang.String> strItor17 = messageHeader10.multiValueIterator("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        java.lang.String[] strArray20 = new java.lang.String[] { "hi!", "AuthenticationHeader: prefer null" };
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap21 = messageHeader10.getHeaders(strArray20);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap22 = messageHeader0.getHeaders(strArray20);
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertNotNull(strItor17);
        org.junit.Assert.assertNotNull(strArray20);
        org.junit.Assert.assertNotNull(strMap21);
        org.junit.Assert.assertNotNull(strMap22);
    }

    @Test
    public void test1643() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1643");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setConnectTimeout((int) ' ');
        java.lang.String str8 = httpsURLConnectionImpl3.toString();
        httpsURLConnectionImpl3.setChunkedStreamingMode((int) (byte) -1);
        java.lang.String str11 = httpsURLConnectionImpl3.getRequestMethod();
        java.lang.String str13 = httpsURLConnectionImpl3.getRequestProperty("");
        java.lang.String str14 = httpsURLConnectionImpl3.getContentType();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "GET" + "'", str11, "GET");
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertNull(str14);
    }

    @Test
    public void test1644() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest3.test1644");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        boolean boolean9 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        boolean boolean10 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        long long13 = httpsURLConnectionImpl3.getDate();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + 0L + "'", long13 == 0L);
    }
}


package com.quakearts.rest.client.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest1 {

    public static boolean debug = false;

    @Test
    public void test0501() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0501");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        boolean boolean8 = httpsURLConnectionImpl3.getAllowUserInteraction();
        long long9 = httpsURLConnectionImpl3.getIfModifiedSince();
        // The following exception was thrown during execution in test generation
        try {
            long long12 = httpsURLConnectionImpl3.getHeaderFieldLong("{}", (long) (byte) 0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + long9 + "' != '" + 0L + "'", long9 == 0L);
    }

    @Test
    public void test0502() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0502");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        progressSource2.close();
        progressSource2.finishTracking();
    }

    @Test
    public void test0503() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0503");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        boolean boolean4 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        long long5 = httpsURLConnectionImpl3.getIfModifiedSince();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
        org.junit.Assert.assertTrue("'" + long5 + "' != '" + 0L + "'", long5 == 0L);
    }

    @Test
    public void test0504() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0504");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        boolean boolean8 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.setInstanceFollowRedirects(false);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0505() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0505");
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
            long long28 = meteredStream10.skip((long) 414);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0506() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0506");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        httpsURLConnectionImpl3.setRequestProperty("hi!", "");
        httpsURLConnectionImpl3.setReadTimeout((int) (short) 100);
        javax.net.ssl.SSLSocketFactory sSLSocketFactory13 = httpsURLConnectionImpl3.getSSLSocketFactory();
        int int14 = httpsURLConnectionImpl3.getReadTimeout();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNotNull(sSLSocketFactory13);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 100 + "'", int14 == 100);
    }

    @Test
    public void test0507() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0507");
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
            long long45 = meteredStream10.skip((long) 301);
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
    public void test0508() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0508");
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
        boolean boolean14 = httpURLConnectionImpl9.streaming();
        int int15 = httpURLConnectionImpl9.getReadTimeout();
        boolean boolean16 = httpURLConnectionImpl9.usingProxy();
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient17 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, proxy1, 500, false, httpURLConnectionImpl9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState11 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState11.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
    }

    @Test
    public void test0509() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0509");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        boolean boolean7 = httpsURLConnectionImpl3.getDoInput();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test0510() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0510");
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
        // The following exception was thrown during execution in test generation
        try {
            meteredStream10.reset();
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
    public void test0511() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0511");
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
        java.net.URL uRL27 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource29 = new com.quakearts.rest.client.net.ProgressSource(uRL27, "");
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(progressSource29);
        com.quakearts.rest.client.net.MeteredStream meteredStream32 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource29, (long) 505);
        // The following exception was thrown during execution in test generation
        try {
            long long34 = meteredStream10.skip((long) 409);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0512() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0512");
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
        httpsURLConnectionImpl3.setReadTimeout(205);
        org.slf4j.Logger logger15 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass16 = logger15.getClass();
        java.net.URL uRL17 = null;
        java.net.Proxy proxy18 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler19 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl20 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL17, proxy18, httpsHandler19);
        javax.net.ssl.HostnameVerifier hostnameVerifier21 = httpsURLConnectionImpl20.getHostnameVerifier();
        httpsURLConnectionImpl20.setAllowUserInteraction(false);
        boolean boolean24 = httpsURLConnectionImpl20.getDefaultUseCaches();
        httpsURLConnectionImpl20.disconnect();
        java.net.URL uRL26 = httpsURLConnectionImpl20.getURL();
        java.lang.Class<?> wildcardClass27 = httpsURLConnectionImpl20.getClass();
        org.slf4j.Logger logger28 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass29 = logger28.getClass();
        java.lang.Class[] classArray30 = new java.lang.Class[] { wildcardClass16, wildcardClass27, wildcardClass29 };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj31 = httpsURLConnectionImpl3.getContent(classArray30);
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertNotNull(logger15);
        org.junit.Assert.assertNotNull(wildcardClass16);
        org.junit.Assert.assertNotNull(hostnameVerifier21);
// flaky:         org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNull(uRL26);
        org.junit.Assert.assertNotNull(wildcardClass27);
        org.junit.Assert.assertNotNull(logger28);
        org.junit.Assert.assertNotNull(wildcardClass29);
        org.junit.Assert.assertNotNull(classArray30);
    }

    @Test
    public void test0513() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0513");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setFixedLengthStreamingMode(206);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Chunked encoding streaming mode set");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
// flaky:         org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test0514() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0514");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(100);
        httpsURLConnectionImpl3.setIfModifiedSince(0L);
        java.lang.Class<?> wildcardClass12 = httpsURLConnectionImpl3.getClass();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNotNull(wildcardClass12);
    }

    @Test
    public void test0515() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0515");
        boolean boolean1 = com.quakearts.rest.client.net.URLConnectionImpl.isProxiedHost("{size=10 nkeys=1  {hi!} }");
// flaky:         org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
    }

    @Test
    public void test0516() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0516");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream2 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream2.flush();
        posterOutputStream0.writeTo((java.io.OutputStream) posterOutputStream2);
        com.quakearts.rest.client.net.HttpCapture httpCapture5 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream6 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture5);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream7 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream7.reset();
        posterOutputStream7.flush();
        byte[] byteArray10 = posterOutputStream7.toByteArray();
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream6.write(byteArray10, (int) (byte) 1, (int) (byte) 100);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 1");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray10), "[]");
    }

    @Test
    public void test0517() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0517");
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
        java.net.URL uRL27 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource29 = new com.quakearts.rest.client.net.ProgressSource(uRL27, "");
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(progressSource29);
        com.quakearts.rest.client.net.MeteredStream meteredStream32 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource29, (long) 505);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean33 = meteredStream10.markSupported();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0518() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0518");
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
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream16 = httpsURLConnectionImpl3.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + 0L + "'", long13 == 0L);
    }

    @Test
    public void test0519() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0519");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.getValue(503);
        messageHeader1.prepend("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {hi!} }");
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap7 = messageHeader1.getHeaders();
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader8 = new com.quakearts.rest.client.net.http.AuthenticationHeader("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", messageHeader1);
        java.lang.String str9 = authenticationHeader8.raw();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNotNull(strMap7);
        org.junit.Assert.assertNull(str9);
    }

    @Test
    public void test0520() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0520");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray1 = posterOutputStream0.toByteArray();
        posterOutputStream0.write((int) 'p');
        byte[] byteArray5 = new byte[] { (byte) -1 };
        // The following exception was thrown during execution in test generation
        try {
            posterOutputStream0.write(byteArray5, 0, 415);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray1), "[]");
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray5), "[-1]");
    }

    @Test
    public void test0521() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0521");
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
        httpURLConnectionImpl5.setRequestProperty("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {get} }");
        // The following exception was thrown during execution in test generation
        try {
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap15 = httpURLConnectionImpl5.getHeaderFields();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertNull(inputStream11);
    }

    @Test
    public void test0522() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0522");
        boolean boolean1 = com.quakearts.rest.client.net.IPAddressUtil.isIPv4LiteralAddress("hi!");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test0523() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0523");
        java.io.PrintStream printStream0 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.ChunkedOutputStream chunkedOutputStream2 = new com.quakearts.rest.client.net.ChunkedOutputStream(printStream0, 0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Null output stream");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0524() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0524");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.connect();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(strMap11);
        org.junit.Assert.assertNull(str13);
    }

    @Test
    public void test0525() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0525");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler5 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl6 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL3, proxy4, httpsHandler5);
        javax.net.ssl.HostnameVerifier hostnameVerifier7 = httpsURLConnectionImpl6.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier8 = httpsURLConnectionImpl6.getHostnameVerifier();
        httpsURLConnectionImpl6.setConnectTimeout((int) ' ');
        java.net.CacheRequest cacheRequest11 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpsURLConnectionImpl6);
        httpsURLConnectionImpl6.setIfModifiedSince(100L);
        // The following exception was thrown during execution in test generation
        try {
            int int16 = httpsURLConnectionImpl6.getHeaderFieldInt("hi!", 411);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
    }

    @Test
    public void test0526() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0526");
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
        // The following exception was thrown during execution in test generation
        try {
            keepAliveStream36.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
    }

    @Test
    public void test0527() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0527");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("hi!", (int) (short) 1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        httpURLConnectionImpl5.setReadTimeout(411);
    }

    @Test
    public void test0528() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0528");
        java.lang.String str1 = com.quakearts.rest.client.net.MessageHeader.canonicalID("GET");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "GET" + "'", str1, "GET");
    }

    @Test
    public void test0529() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0529");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader0.set(406, "content/unknown", "hi!");
        messageHeader0.prepend("", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str2);
    }

    @Test
    public void test0530() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0530");
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
        java.lang.String str15 = httpsURLConnectionImpl6.getContentEncoding();
        int int16 = httpsURLConnectionImpl6.getReadTimeout();
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl6.setRequestProperty("GET", "{}");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Already connected");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
        org.junit.Assert.assertNull(str15);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 0 + "'", int16 == 0);
    }

    @Test
    public void test0531() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0531");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpClient httpClient3 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader4 = new com.quakearts.rest.client.net.MessageHeader();
        java.io.InputStream inputStream5 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture6 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream7 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream5, httpCapture6);
        com.quakearts.rest.client.net.HttpCapture httpCapture8 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream9 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream5, httpCapture8);
        messageHeader4.parseHeader(inputStream5);
        messageHeader4.add("GET", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.util.Iterator<java.lang.String> strItor15 = messageHeader4.multiValueIterator("");
        messageHeader4.add("GET", "GET");
        java.lang.String[] strArray23 = new java.lang.String[] { "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "", "content/unknown", "{size=10 nkeys=1  {hi!} }" };
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap24 = messageHeader4.getHeaders(strArray23);
        messageHeader4.remove("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader4.add("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "hi!");
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream30 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) httpCaptureInputStream2, httpClient3, messageHeader4);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream31 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream31.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture33 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream34 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream31, httpCapture33);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream35 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream35.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture37 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream38 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream35, httpCapture37);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream39 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray40 = posterOutputStream39.toByteArray();
        httpCaptureOutputStream38.write(byteArray40);
        httpCaptureOutputStream34.write(byteArray40);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream43 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray44 = posterOutputStream43.toByteArray();
        httpCaptureOutputStream34.write(byteArray44);
        // The following exception was thrown during execution in test generation
        try {
            int int48 = httpCaptureInputStream2.read(byteArray44, 410, (int) (byte) 100);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strItor15);
        org.junit.Assert.assertNotNull(strArray23);
        org.junit.Assert.assertNotNull(strMap24);
        org.junit.Assert.assertNotNull(byteArray40);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray40), "[]");
        org.junit.Assert.assertNotNull(byteArray44);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray44), "[]");
    }

    @Test
    public void test0532() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0532");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        httpsURLConnectionImpl3.setDoOutput(false);
        // The following exception was thrown during execution in test generation
        try {
            long long8 = httpsURLConnectionImpl3.getExpiration();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0533() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0533");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        httpsURLConnectionImpl3.setIfModifiedSince(0L);
        // The following exception was thrown during execution in test generation
        try {
            long long12 = httpsURLConnectionImpl3.getHeaderFieldDate("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", (long) 407);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0534() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0534");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL1 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL1, "");
        long long4 = progressSource3.getExpected();
        progressMonitor0.registerSource(progressSource3);
        java.lang.String str6 = progressSource3.toString();
        java.lang.String str7 = progressSource3.getContentType();
        java.lang.String str8 = progressSource3.getMethod();
        org.junit.Assert.assertTrue("'" + long4 + "' != '" + (-1L) + "'", long4 == (-1L));
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str6, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "content/unknown" + "'", str7, "content/unknown");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
    }

    @Test
    public void test0535() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0535");
        com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker1 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) 2);
        java.security.cert.X509Certificate x509Certificate3 = null;
        // The following exception was thrown during execution in test generation
        try {
            hostnameChecker1.match("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", x509Certificate3);
            org.junit.Assert.fail("Expected exception of type java.security.cert.CertificateException; message: Illegal given domain name: com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        } catch (java.security.cert.CertificateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameChecker1);
    }

    @Test
    public void test0536() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0536");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("", "");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue5 = null;
        // The following exception was thrown during execution in test generation
        try {
            authCacheImpl0.put("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", authCacheValue5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(authCacheValue3);
    }

    @Test
    public void test0537() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0537");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int1 = progressMonitor0.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressMonitor.setDefault(progressMonitor0);
        java.net.URL uRL3 = null;
        boolean boolean5 = progressMonitor0.shouldMeterInput(uRL3, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.net.URL uRL6 = null;
        boolean boolean8 = progressMonitor0.shouldMeterInput(uRL6, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.ProgressListener progressListener9 = null;
        progressMonitor0.addProgressListener(progressListener9);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8192 + "'", int1 == 8192);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0538() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0538");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpClient httpClient3 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader4 = new com.quakearts.rest.client.net.MessageHeader();
        java.io.InputStream inputStream5 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture6 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream7 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream5, httpCapture6);
        com.quakearts.rest.client.net.HttpCapture httpCapture8 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream9 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream5, httpCapture8);
        messageHeader4.parseHeader(inputStream5);
        messageHeader4.add("GET", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.util.Iterator<java.lang.String> strItor15 = messageHeader4.multiValueIterator("");
        messageHeader4.add("GET", "GET");
        java.lang.String[] strArray23 = new java.lang.String[] { "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "", "content/unknown", "{size=10 nkeys=1  {hi!} }" };
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap24 = messageHeader4.getHeaders(strArray23);
        messageHeader4.remove("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader4.add("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "hi!");
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream30 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) httpCaptureInputStream2, httpClient3, messageHeader4);
        // The following exception was thrown during execution in test generation
        try {
            int int31 = chunkedInputStream30.read();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strItor15);
        org.junit.Assert.assertNotNull(strArray23);
        org.junit.Assert.assertNotNull(strMap24);
    }

    @Test
    public void test0539() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0539");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream2 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream2.flush();
        posterOutputStream0.writeTo((java.io.OutputStream) posterOutputStream2);
        com.quakearts.rest.client.net.HttpCapture httpCapture5 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream6 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture5);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream7 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray8 = posterOutputStream7.toByteArray();
        byte[] byteArray9 = posterOutputStream7.toByteArray();
        httpCaptureOutputStream6.write(byteArray9);
        byte[] byteArray15 = new byte[] { (byte) 10, (byte) -1, (byte) -1, (byte) 100 };
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream6.write(byteArray15, 406, 0);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray8), "[]");
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray9), "[]");
        org.junit.Assert.assertNotNull(byteArray15);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray15), "[10, -1, -1, 100]");
    }

    @Test
    public void test0540() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0540");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setUseCaches(false);
        boolean boolean8 = httpsURLConnectionImpl3.getDoOutput();
        boolean boolean9 = httpsURLConnectionImpl3.getDefaultUseCaches();
        // The following exception was thrown during execution in test generation
        try {
            long long10 = httpsURLConnectionImpl3.getExpiration();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test0541() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0541");
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
        long long17 = httpsURLConnectionImpl6.getHeaderFieldDate("{}", 408L);
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertNull(str14);
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 408L + "'", long17 == 408L);
    }

    @Test
    public void test0542() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0542");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.getValue(503);
        messageHeader0.prepend("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {hi!} }");
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap6 = messageHeader0.getHeaders();
        java.lang.Class<?> wildcardClass7 = strMap6.getClass();
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strMap6);
        org.junit.Assert.assertNotNull(wildcardClass7);
    }

    @Test
    public void test0543() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0543");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource.State state9 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent12 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL6, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state9, (long) 300, (-1L));
        long long13 = progressEvent12.getExpected();
        long long14 = progressEvent12.getExpected();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-1L) + "'", long13 == (-1L));
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + (-1L) + "'", long14 == (-1L));
    }

    @Test
    public void test0544() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0544");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.addRequestProperty("", "hi!");
        int int13 = httpsURLConnectionImpl3.getHeaderFieldInt("hi!", 100);
        boolean boolean14 = httpsURLConnectionImpl3.getAllowUserInteraction();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str15 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 100 + "'", int13 == 100);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test0545() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0545");
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
        // The following exception was thrown during execution in test generation
        try {
            int int39 = keepAliveStream36.available();
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
    }

    @Test
    public void test0546() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0546");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.getValue(503);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap4 = messageHeader1.getHeaders();
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader5 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        com.quakearts.rest.client.net.HeaderParser headerParser6 = authenticationHeader5.headerParser();
        java.lang.String str7 = authenticationHeader5.scheme();
        java.lang.String str8 = authenticationHeader5.toString();
        java.lang.String str9 = authenticationHeader5.raw();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNotNull(strMap4);
        org.junit.Assert.assertNull(headerParser6);
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "AuthenticationHeader: prefer null" + "'", str8, "AuthenticationHeader: prefer null");
        org.junit.Assert.assertNull(str9);
    }

    @Test
    public void test0547() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0547");
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
        java.io.InputStream inputStream12 = httpURLConnectionImpl5.getErrorStream();
        httpURLConnectionImpl5.setReadTimeout(206);
        // The following exception was thrown during execution in test generation
        try {
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap15 = httpURLConnectionImpl5.getHeaderFields();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertNull(inputStream11);
        org.junit.Assert.assertNull(inputStream12);
    }

    @Test
    public void test0548() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0548");
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
    }

    @Test
    public void test0549() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0549");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.SSLSocketFactory sSLSocketFactory6 = httpsURLConnectionImpl3.getSSLSocketFactory();
        // The following exception was thrown during execution in test generation
        try {
            long long7 = httpsURLConnectionImpl3.getExpiration();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNotNull(sSLSocketFactory6);
    }

    @Test
    public void test0550() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0550");
        com.quakearts.rest.client.net.KeepAliveCache keepAliveCache0 = new com.quakearts.rest.client.net.KeepAliveCache();
        java.lang.Object obj1 = keepAliveCache0.clone();
        java.net.URL uRL2 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource4 = new com.quakearts.rest.client.net.ProgressSource(uRL2, "");
        boolean boolean5 = progressSource4.connected();
        boolean boolean6 = keepAliveCache0.containsValue((java.lang.Object) boolean5);
        com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker8 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) 2);
        boolean boolean9 = keepAliveCache0.containsValue((java.lang.Object) hostnameChecker8);
        java.security.cert.X509Certificate x509Certificate11 = null;
        // The following exception was thrown during execution in test generation
        try {
            hostnameChecker8.match("content/unknown", x509Certificate11);
            org.junit.Assert.fail("Expected exception of type java.security.cert.CertificateException; message: Illegal given domain name: content/unknown");
        } catch (java.security.cert.CertificateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(obj1);
        org.junit.Assert.assertEquals(obj1.toString(), "{}");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj1), "{}");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj1), "{}");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(hostnameChecker8);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0551() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0551");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader2 = new com.quakearts.rest.client.net.http.AuthenticationHeader("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", messageHeader1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0552() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0552");
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
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl5.setRequestMethod("");
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: Invalid HTTP method: ");
        } catch (java.net.ProtocolException e) {
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
    public void test0553() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0553");
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
        java.nio.charset.CharsetEncoder charsetEncoder17 = com.quakearts.rest.client.net.ThreadLocalCoders.encoderFor((java.lang.Object) httpsURLConnectionImpl3);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
        org.junit.Assert.assertNull(charsetEncoder17);
    }

    @Test
    public void test0554() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0554");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureInputStream4.reset();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0555() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0555");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        // The following exception was thrown during execution in test generation
        try {
            int int9 = httpsURLConnectionImpl3.getHeaderFieldInt("AuthenticationHeader: prefer null", 401);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
    }

    @Test
    public void test0556() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0556");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        javax.net.ssl.SSLSocketFactory sSLSocketFactory10 = javax.net.ssl.HttpsURLConnection.getDefaultSSLSocketFactory();
        httpsURLConnectionImpl3.setSSLSocketFactory(sSLSocketFactory10);
        org.slf4j.Logger logger12 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass13 = logger12.getClass();
        java.net.URL uRL14 = null;
        java.net.Proxy proxy15 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler16 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl17 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL14, proxy15, httpsHandler16);
        javax.net.ssl.HostnameVerifier hostnameVerifier18 = httpsURLConnectionImpl17.getHostnameVerifier();
        boolean boolean19 = httpsURLConnectionImpl17.getDoInput();
        httpsURLConnectionImpl17.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl17.setFixedLengthStreamingMode(100);
        httpsURLConnectionImpl17.setIfModifiedSince(0L);
        java.lang.Class<?> wildcardClass26 = httpsURLConnectionImpl17.getClass();
        org.slf4j.Logger logger27 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass28 = logger27.getClass();
        java.net.URL uRL29 = null;
        java.net.Proxy proxy30 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler31 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl32 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL29, proxy30, httpsHandler31);
        javax.net.ssl.HostnameVerifier hostnameVerifier33 = httpsURLConnectionImpl32.getHostnameVerifier();
        httpsURLConnectionImpl32.setAllowUserInteraction(false);
        boolean boolean36 = httpsURLConnectionImpl32.getDefaultUseCaches();
        httpsURLConnectionImpl32.disconnect();
        java.net.URL uRL38 = httpsURLConnectionImpl32.getURL();
        java.lang.Class<?> wildcardClass39 = httpsURLConnectionImpl32.getClass();
        java.net.URL uRL40 = null;
        java.net.Proxy proxy41 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler42 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl43 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL40, proxy41, httpsHandler42);
        javax.net.ssl.HostnameVerifier hostnameVerifier44 = httpsURLConnectionImpl43.getHostnameVerifier();
        httpsURLConnectionImpl43.setAllowUserInteraction(false);
        boolean boolean47 = httpsURLConnectionImpl43.getDefaultUseCaches();
        httpsURLConnectionImpl43.disconnect();
        java.net.URL uRL49 = httpsURLConnectionImpl43.getURL();
        java.lang.Class<?> wildcardClass50 = httpsURLConnectionImpl43.getClass();
        java.lang.Class[] classArray51 = new java.lang.Class[] { wildcardClass13, wildcardClass26, wildcardClass28, wildcardClass39, wildcardClass50 };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj52 = httpsURLConnectionImpl3.getContent(classArray51);
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNotNull(sSLSocketFactory10);
        org.junit.Assert.assertNotNull(logger12);
        org.junit.Assert.assertNotNull(wildcardClass13);
        org.junit.Assert.assertNotNull(hostnameVerifier18);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + true + "'", boolean19 == true);
        org.junit.Assert.assertNotNull(wildcardClass26);
        org.junit.Assert.assertNotNull(logger27);
        org.junit.Assert.assertNotNull(wildcardClass28);
        org.junit.Assert.assertNotNull(hostnameVerifier33);
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + false + "'", boolean36 == false);
        org.junit.Assert.assertNull(uRL38);
        org.junit.Assert.assertNotNull(wildcardClass39);
        org.junit.Assert.assertNotNull(hostnameVerifier44);
        org.junit.Assert.assertTrue("'" + boolean47 + "' != '" + false + "'", boolean47 == false);
        org.junit.Assert.assertNull(uRL49);
        org.junit.Assert.assertNotNull(wildcardClass50);
        org.junit.Assert.assertNotNull(classArray51);
    }

    @Test
    public void test0557() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0557");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        long long4 = progressSource2.getExpected();
        boolean boolean5 = progressSource2.connected();
        org.junit.Assert.assertTrue("'" + long4 + "' != '" + (-1L) + "'", long4 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test0558() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0558");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findValue(305);
        java.lang.String str5 = headerParser1.findValue((int) (byte) 2);
        java.lang.String str7 = headerParser1.findValue(301);
        int int10 = headerParser1.findInt("content/unknown", 403);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 403 + "'", int10 == 403);
    }

    @Test
    public void test0559() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0559");
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
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean40 = keepAliveStream36.hurry();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
    }

    @Test
    public void test0560() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0560");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setUseCaches(false);
        boolean boolean8 = httpsURLConnectionImpl3.getDoOutput();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj9 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0561() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0561");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setConnectTimeout((int) ' ');
        java.lang.String str8 = httpsURLConnectionImpl3.toString();
        // The following exception was thrown during execution in test generation
        try {
            java.security.Principal principal9 = httpsURLConnectionImpl3.getPeerPrincipal();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
    }

    @Test
    public void test0562() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0562");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.SSLSocketFactory sSLSocketFactory6 = httpsURLConnectionImpl3.getSSLSocketFactory();
        // The following exception was thrown during execution in test generation
        try {
            long long9 = httpsURLConnectionImpl3.getHeaderFieldDate("content/unknown", (long) (byte) 0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNotNull(sSLSocketFactory6);
    }

    @Test
    public void test0563() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0563");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "hi!");
    }

    @Test
    public void test0564() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0564");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap1 = messageHeader0.getHeaders();
        messageHeader0.set(406, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "content/unknown");
        int int7 = messageHeader0.getKey("GET");
        org.junit.Assert.assertNotNull(strMap1);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + (-1) + "'", int7 == (-1));
    }

    @Test
    public void test0565() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0565");
        java.lang.String str1 = com.quakearts.rest.client.net.NetProperties.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test0566() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0566");
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
        long long17 = httpsURLConnectionImpl3.getContentLengthLong();
        java.lang.Object obj18 = null;
        boolean boolean19 = httpsURLConnectionImpl3.equals(obj18);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
        org.junit.Assert.assertTrue("'" + long16 + "' != '" + 408L + "'", long16 == 408L);
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + (-1L) + "'", long17 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
    }

    @Test
    public void test0567() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0567");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str11 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
    }

    @Test
    public void test0568() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0568");
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
        httpsURLConnectionImpl6.setFixedLengthStreamingMode((long) '4');
        java.lang.String str15 = httpsURLConnectionImpl6.getRequestMethod();
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "GET" + "'", str15, "GET");
    }

    @Test
    public void test0569() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0569");
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
        // The following exception was thrown during execution in test generation
        try {
            java.security.cert.Certificate[] certificateArray19 = httpsURLConnectionImpl5.getLocalCertificates();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertNull(str18);
    }

    @Test
    public void test0570() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0570");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        boolean boolean10 = httpURLConnectionImpl5.streaming();
        java.lang.Class<?> wildcardClass11 = httpURLConnectionImpl5.getClass();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(wildcardClass11);
    }

    @Test
    public void test0571() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0571");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.disconnect();
        int int8 = httpsURLConnectionImpl3.getConnectTimeout();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 403);
        httpsURLConnectionImpl3.setDoOutput(true);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test0572() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0572");
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
        // The following exception was thrown during execution in test generation
        try {
            keepAliveStream36.close();
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
    }

    @Test
    public void test0573() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0573");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue5 = null;
        // The following exception was thrown during execution in test generation
        try {
            authCacheImpl0.put("GET", authCacheValue5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(authCacheValue3);
    }

    @Test
    public void test0574() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0574");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = null;
        authCacheImpl0.remove("", authCacheValue3);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = null;
        // The following exception was thrown during execution in test generation
        try {
            authCacheImpl0.put("", authCacheValue6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0575() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0575");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        progressSource2.finishTracking();
    }

    @Test
    public void test0576() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0576");
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
        com.quakearts.rest.client.net.HttpCapture httpCapture39 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream40 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) keepAliveStream36, httpCapture39);
        // The following exception was thrown during execution in test generation
        try {
            long long42 = keepAliveStream36.skip((long) 'a');
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
    }

    @Test
    public void test0577() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0577");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.getValue(503);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap3 = messageHeader0.getHeaders();
        messageHeader0.set("{size=10 nkeys=1  {hi!} }", "content/unknown");
        java.lang.String str8 = messageHeader0.findValue("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strMap3);
        org.junit.Assert.assertNull(str8);
    }

    @Test
    public void test0578() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0578");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader1.set(406, "content/unknown", "hi!");
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader8 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        java.lang.String str10 = messageHeader1.getValue(0);
        java.lang.String str12 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.io.InputStream inputStream13 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture14 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream15 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream13, httpCapture14);
        // The following exception was thrown during execution in test generation
        try {
            messageHeader1.parseHeader((java.io.InputStream) httpCaptureInputStream15);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "hi!" + "'", str10, "hi!");
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0579() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0579");
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
        long long17 = httpsURLConnectionImpl5.getExpiration();
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream18 = httpsURLConnectionImpl5.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 0L + "'", long17 == 0L);
    }

    @Test
    public void test0580() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0580");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(100);
        httpsURLConnectionImpl3.setIfModifiedSince(0L);
        // The following exception was thrown during execution in test generation
        try {
            java.security.cert.Certificate[] certificateArray12 = httpsURLConnectionImpl3.getLocalCertificates();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0581() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0581");
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
        // The following exception was thrown during execution in test generation
        try {
            int int38 = keepAliveStream36.available();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
    }

    @Test
    public void test0582() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0582");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str18 = httpURLConnectionImpl5.getHeaderField("{}");
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
    }

    @Test
    public void test0583() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0583");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str17 = httpURLConnectionImpl5.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type com.quakearts.rest.client.exception.HttpClientRuntimeException; message: java.lang.NullPointerException");
        } catch (com.quakearts.rest.client.exception.HttpClientRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNull(inputStream12);
        org.junit.Assert.assertTrue("'" + tunnelState13 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState13.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
    }

    @Test
    public void test0584() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0584");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream3 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture2);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream4 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream4.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture6 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream7 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream4, httpCapture6);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream8 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray9 = posterOutputStream8.toByteArray();
        httpCaptureOutputStream7.write(byteArray9);
        httpCaptureOutputStream3.write(byteArray9);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream12 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray13 = posterOutputStream12.toByteArray();
        httpCaptureOutputStream3.write(byteArray13);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream3.flush();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray9), "[]");
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray13), "[]");
    }

    @Test
    public void test0585() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0585");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue5 = null;
        // The following exception was thrown during execution in test generation
        try {
            authCacheImpl0.put("", authCacheValue5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(authCacheValue3);
    }

    @Test
    public void test0586() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0586");
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
        posterOutputStream50.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream52 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream52.flush();
        posterOutputStream50.writeTo((java.io.OutputStream) posterOutputStream52);
        com.quakearts.rest.client.net.HttpCapture httpCapture55 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream56 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream50, httpCapture55);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream57 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray58 = posterOutputStream57.toByteArray();
        byte[] byteArray59 = posterOutputStream57.toByteArray();
        httpCaptureOutputStream56.write(byteArray59);
        // The following exception was thrown during execution in test generation
        try {
            int int63 = chunkedInputStream49.read(byteArray59, 502, 200);
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
        org.junit.Assert.assertNotNull(byteArray58);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray58), "[]");
        org.junit.Assert.assertNotNull(byteArray59);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray59), "[]");
    }

    @Test
    public void test0587() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0587");
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
        messageHeader38.set(0, "AuthenticationHeader: prefer null", "{size=10 nkeys=1  {get} }");
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
    }

    @Test
    public void test0588() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0588");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "content/unknown");
    }

    @Test
    public void test0589() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0589");
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
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl5.connect();
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
    public void test0590() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0590");
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
        org.junit.Assert.assertNull(str39);
    }

    @Test
    public void test0591() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0591");
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
        keepAliveStream36.mark(504);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean39 = keepAliveStream36.hurry();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test0592() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0592");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        java.lang.String str10 = httpsURLConnectionImpl3.getHeaderFieldKey(402);
        boolean boolean11 = httpsURLConnectionImpl3.getUseCaches();
        httpsURLConnectionImpl3.setConnectTimeout(1);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap14 = httpsURLConnectionImpl3.getHeaderFields();
        java.lang.Class<?> wildcardClass15 = httpsURLConnectionImpl3.getClass();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(strMap14);
        org.junit.Assert.assertNotNull(wildcardClass15);
    }

    @Test
    public void test0593() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0593");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        int int10 = httpURLConnectionImpl5.getReadTimeout();
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream11 = httpURLConnectionImpl5.getInputStream();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
    }

    @Test
    public void test0594() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0594");
        java.security.Principal principal1 = null;
        boolean boolean2 = com.quakearts.rest.client.net.https.HostnameChecker.match("AuthenticationHeader: prefer null", principal1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test0595() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0595");
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
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream14 = httpsURLConnectionImpl3.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
    }

    @Test
    public void test0596() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0596");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str16 = httpURLConnectionImpl5.getHeaderField(10);
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
        org.junit.Assert.assertNull(inputStream13);
        org.junit.Assert.assertNull(cookieHandler14);
    }

    @Test
    public void test0597() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0597");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        java.io.InputStream inputStream10 = httpURLConnectionImpl5.getErrorStream();
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream11 = httpURLConnectionImpl5.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
    }

    @Test
    public void test0598() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0598");
        com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker1 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) 2);
        java.security.cert.X509Certificate x509Certificate3 = null;
        // The following exception was thrown during execution in test generation
        try {
            hostnameChecker1.match("", x509Certificate3);
            org.junit.Assert.fail("Expected exception of type java.security.cert.CertificateException; message: Illegal given domain name: ");
        } catch (java.security.cert.CertificateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameChecker1);
    }

    @Test
    public void test0599() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0599");
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
        // The following exception was thrown during execution in test generation
        try {
            long long44 = chunkedInputStream41.skip((long) 'p');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + false + "'", boolean42 == false);
    }

    @Test
    public void test0600() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0600");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(100);
        httpsURLConnectionImpl3.setDefaultUseCaches(true);
        boolean boolean12 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean13 = httpsURLConnectionImpl3.getAllowUserInteraction();
        boolean boolean14 = httpsURLConnectionImpl3.getAllowUserInteraction();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test0601() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0601");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler7 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl8 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL3, proxy4, httpHandler7);
        int int9 = httpURLConnectionImpl8.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState10 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl8.setTunnelState(tunnelState10);
        boolean boolean12 = httpURLConnectionImpl8.streaming();
        java.io.InputStream inputStream13 = httpURLConnectionImpl8.getErrorStream();
        java.io.InputStream inputStream14 = httpURLConnectionImpl8.getErrorStream();
        java.io.InputStream inputStream15 = httpURLConnectionImpl8.getErrorStream();
        httpURLConnectionImpl8.setReadTimeout(206);
        java.net.CacheRequest cacheRequest18 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpURLConnectionImpl8);
        java.net.URI uRI19 = null;
        java.net.URLConnection uRLConnection20 = null;
        java.net.CacheRequest cacheRequest21 = responseCacheImpl0.put(uRI19, uRLConnection20);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState10 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState10.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNull(inputStream13);
        org.junit.Assert.assertNull(inputStream14);
        org.junit.Assert.assertNull(inputStream15);
        org.junit.Assert.assertNull(cacheRequest18);
        org.junit.Assert.assertNull(cacheRequest21);
    }

    @Test
    public void test0602() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0602");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        int int11 = httpsURLConnectionImpl3.getConnectTimeout();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap12 = httpsURLConnectionImpl3.getRequestProperties();
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNotNull(strMap12);
    }

    @Test
    public void test0603() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0603");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.addRequestProperty("{size=10 nkeys=1  {get} }", "{size=10 nkeys=1  {get} }");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Already connected");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
    }

    @Test
    public void test0604() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0604");
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
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean50 = meteredStream10.markSupported();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long43 + "' != '" + (-1L) + "'", long43 == (-1L));
        org.junit.Assert.assertNull(str48);
    }

    @Test
    public void test0605() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0605");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.getValue(503);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap3 = messageHeader0.getHeaders();
        messageHeader0.set("{size=10 nkeys=1  {hi!} }", "content/unknown");
        java.lang.String str8 = messageHeader0.getValue(0);
        messageHeader0.reset();
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strMap3);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "content/unknown" + "'", str8, "content/unknown");
    }

    @Test
    public void test0606() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0606");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        javax.net.ssl.HostnameVerifier hostnameVerifier6 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        org.junit.Assert.assertNotNull(hostnameVerifier6);
    }

    @Test
    public void test0607() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0607");
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
    }

    @Test
    public void test0608() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0608");
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
        java.io.InputStream inputStream13 = httpsURLConnectionImpl3.getErrorStream();
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream14 = httpsURLConnectionImpl3.getInputStream();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(inputStream13);
    }

    @Test
    public void test0609() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0609");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        httpsURLConnectionImpl3.setReadTimeout(10);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap11 = httpsURLConnectionImpl3.getRequestProperties();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 100);
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream14 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.openConnectionCheckRedirects((java.net.URLConnection) httpsURLConnectionImpl3);
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNotNull(strMap11);
    }

    @Test
    public void test0610() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0610");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findValue(305);
        java.lang.String str5 = headerParser1.findValue((int) (byte) 2);
        java.lang.String str7 = headerParser1.findValue("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertNull(str7);
    }

    @Test
    public void test0611() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0611");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        httpsURLConnectionImpl3.setRequestProperty("hi!", "");
        httpsURLConnectionImpl3.setReadTimeout((int) (short) 100);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode((long) 410);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode((int) '#');
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0612() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0612");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue5 = null;
        authCacheImpl0.remove("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", authCacheValue5);
        org.junit.Assert.assertNull(authCacheValue3);
    }

    @Test
    public void test0613() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0613");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader1.set(406, "content/unknown", "hi!");
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader8 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        java.lang.String str10 = messageHeader1.getValue(0);
        messageHeader1.prepend("", "GET");
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "hi!" + "'", str10, "hi!");
    }

    @Test
    public void test0614() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0614");
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
        int int12 = httpsURLConnectionImpl6.getContentLength();
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + (-1) + "'", int12 == (-1));
    }

    @Test
    public void test0615() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0615");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        long long10 = httpsURLConnectionImpl3.getDate();
        long long11 = httpsURLConnectionImpl3.getContentLengthLong();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + long10 + "' != '" + 0L + "'", long10 == 0L);
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + (-1L) + "'", long11 == (-1L));
    }

    @Test
    public void test0616() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0616");
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
        httpsURLConnectionImpl3.disconnect();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap13 = httpsURLConnectionImpl3.getHeaderFields();
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission14 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "GET" + "'", str11, "GET");
        org.junit.Assert.assertNotNull(strMap13);
    }

    @Test
    public void test0617() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0617");
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
        keepAliveStream36.mark(502);
        // The following exception was thrown during execution in test generation
        try {
            keepAliveStream36.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test0618() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0618");
        java.net.URL uRL0 = null;
        java.net.URL uRL5 = null;
        java.net.Proxy proxy6 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler9 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl10 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL5, proxy6, httpHandler9);
        boolean boolean11 = httpURLConnectionImpl10.usingProxy();
        httpURLConnectionImpl10.setDoInput(false);
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient14 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, "content/unknown", (int) '#', false, (int) (byte) 0, httpURLConnectionImpl10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0619() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0619");
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
        boolean boolean14 = httpsURLConnectionImpl3.getUseCaches();
        int int17 = httpsURLConnectionImpl3.getHeaderFieldInt("", 406);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(strMap11);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 406 + "'", int17 == 406);
    }

    @Test
    public void test0620() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0620");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        int int9 = httpsURLConnectionImpl3.getConnectTimeout();
        boolean boolean10 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.setUseCaches(true);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test0621() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0621");
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
        java.io.InputStream inputStream12 = httpURLConnectionImpl5.getErrorStream();
        boolean boolean13 = httpURLConnectionImpl5.usingProxy();
        boolean boolean14 = httpURLConnectionImpl5.streaming();
        // The following exception was thrown during execution in test generation
        try {
            long long17 = httpURLConnectionImpl5.getHeaderFieldDate("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]", 0L);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertNull(inputStream11);
        org.junit.Assert.assertNull(inputStream12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test0622() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0622");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream3 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture2);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream4 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray5 = posterOutputStream4.toByteArray();
        httpCaptureOutputStream3.write(byteArray5);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream7 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream7.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream9 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream9.flush();
        posterOutputStream7.writeTo((java.io.OutputStream) posterOutputStream9);
        com.quakearts.rest.client.net.HttpCapture httpCapture12 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream13 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream7, httpCapture12);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream14 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray15 = posterOutputStream14.toByteArray();
        byte[] byteArray16 = posterOutputStream14.toByteArray();
        httpCaptureOutputStream13.write(byteArray16);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream3.write(byteArray16, 414, (int) (short) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray5), "[]");
        org.junit.Assert.assertNotNull(byteArray15);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray15), "[]");
        org.junit.Assert.assertNotNull(byteArray16);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray16), "[]");
    }

    @Test
    public void test0623() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0623");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.disconnect();
        int int8 = httpsURLConnectionImpl3.getConnectTimeout();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 403);
        httpsURLConnectionImpl3.setDoInput(true);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test0624() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0624");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.connect();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0625() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0625");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = authCacheImpl0.get("hi!", "");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue8 = null;
        // The following exception was thrown during execution in test generation
        try {
            authCacheImpl0.put("", authCacheValue8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(authCacheValue3);
        org.junit.Assert.assertNull(authCacheValue6);
    }

    @Test
    public void test0626() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0626");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        javax.net.ssl.SSLSocketFactory sSLSocketFactory9 = httpsURLConnectionImpl3.getSSLSocketFactory();
        httpsURLConnectionImpl3.setUseCaches(false);
        int int12 = httpsURLConnectionImpl3.getConnectTimeout();
        java.lang.String str14 = httpsURLConnectionImpl3.getRequestProperty("{size=10 nkeys=1  {hi!} }");
        // The following exception was thrown during execution in test generation
        try {
            long long17 = httpsURLConnectionImpl3.getHeaderFieldDate("hi!", (long) 405);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(sSLSocketFactory9);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
        org.junit.Assert.assertNull(str14);
    }

    @Test
    public void test0627() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0627");
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
        boolean boolean14 = httpsURLConnectionImpl3.getUseCaches();
        httpsURLConnectionImpl3.setUseCaches(false);
        // The following exception was thrown during execution in test generation
        try {
            int int19 = httpsURLConnectionImpl3.getHeaderFieldInt("{size=10 nkeys=1  {hi!} }", (int) 's');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test0628() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0628");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        boolean boolean10 = httpURLConnectionImpl5.streaming();
        httpURLConnectionImpl5.addRequestProperty("", "hi!");
        httpURLConnectionImpl5.setAuthenticationProperty("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "{}");
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl5.setRequestMethod("content/unknown");
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: Invalid HTTP method: content/unknown");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test0629() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0629");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL1 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL1, "");
        long long4 = progressSource3.getExpected();
        progressMonitor0.registerSource(progressSource3);
        progressSource3.close();
        progressSource3.finishTracking();
        progressSource3.updateProgress(0L, (long) 412);
        org.junit.Assert.assertTrue("'" + long4 + "' != '" + (-1L) + "'", long4 == (-1L));
    }

    @Test
    public void test0630() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0630");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource.State state9 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent12 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL6, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state9, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(progressSource2);
        progressSource2.close();
        long long15 = progressSource2.getExpected();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + (-1L) + "'", long15 == (-1L));
    }

    @Test
    public void test0631() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0631");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        java.net.URL uRL6 = null;
        java.net.Proxy proxy7 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler8 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl9 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL6, proxy7, httpsHandler8);
        javax.net.ssl.HostnameVerifier hostnameVerifier10 = httpsURLConnectionImpl9.getHostnameVerifier();
        boolean boolean11 = httpsURLConnectionImpl9.getDoInput();
        httpsURLConnectionImpl9.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream14 = httpsURLConnectionImpl9.getErrorStream();
        java.net.URL uRL15 = httpsURLConnectionImpl9.getURL();
        boolean boolean16 = httpsURLConnectionImpl9.getDoInput();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap17 = httpsURLConnectionImpl9.getRequestProperties();
        httpURLConnectionImpl5.authObj((java.lang.Object) httpsURLConnectionImpl9);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str19 = httpsURLConnectionImpl9.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier10);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertNull(inputStream14);
        org.junit.Assert.assertNull(uRL15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertNotNull(strMap17);
    }

    @Test
    public void test0632() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0632");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        progressSource2.beginTracking();
        java.lang.String str5 = progressSource2.getMethod();
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
    }

    @Test
    public void test0633() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0633");
        java.lang.String str1 = com.quakearts.rest.client.net.NetProperties.get("content/unknown");
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test0634() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0634");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        httpsURLConnectionImpl3.setIfModifiedSince(0L);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj10 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0635() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0635");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findKey(305);
        int int6 = headerParser1.findInt("hi!", 32);
        java.lang.String str8 = headerParser1.findValue(406);
        java.lang.String str11 = headerParser1.findValue("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {get} }");
        java.util.Iterator<java.lang.String> strItor12 = headerParser1.values();
        java.util.Iterator<java.lang.String> strItor13 = headerParser1.values();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 32 + "'", int6 == 32);
        org.junit.Assert.assertNull(str8);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "{size=10 nkeys=1  {get} }" + "'", str11, "{size=10 nkeys=1  {get} }");
        org.junit.Assert.assertNotNull(strItor12);
        org.junit.Assert.assertNotNull(strItor13);
    }

    @Test
    public void test0636() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0636");
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
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl5.doTunneling();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
    }

    @Test
    public void test0637() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0637");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL1 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL1, "");
        boolean boolean4 = progressSource3.connected();
        boolean boolean5 = progressSource3.connected();
        progressMonitor0.registerSource(progressSource3);
        com.quakearts.rest.client.net.ProgressSource progressSource7 = null;
        progressMonitor0.unregisterSource(progressSource7);
        java.net.URL uRL9 = null;
        boolean boolean11 = progressMonitor0.shouldMeterInput(uRL9, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0638() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0638");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        boolean boolean6 = httpURLConnectionImpl5.usingProxy();
        httpURLConnectionImpl5.setDoInput(false);
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl5.connect();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0639() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0639");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setInstanceFollowRedirects(false);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0640() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0640");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl3 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient4 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, proxy1, 415, httpURLConnectionImpl3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0641() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0641");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findKey(305);
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HeaderParser headerParser6 = headerParser1.subsequence(300, (int) 'p');
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: invalid start or end");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str3);
    }

    @Test
    public void test0642() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0642");
        com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker1 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) 1);
        java.security.cert.X509Certificate x509Certificate3 = null;
        // The following exception was thrown during execution in test generation
        try {
            hostnameChecker1.match("{size=10 nkeys=1  {hi!} }", x509Certificate3);
            org.junit.Assert.fail("Expected exception of type java.security.cert.CertificateException; message: Illegal given domain name: {size=10 nkeys=1  {hi!} }");
        } catch (java.security.cert.CertificateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameChecker1);
    }

    @Test
    public void test0643() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0643");
        com.quakearts.rest.client.net.URLConnectionImpl.setProxiedHost("");
    }

    @Test
    public void test0644() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0644");
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
        // The following exception was thrown during execution in test generation
        try {
            int int42 = meteredStream10.read();
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
    public void test0645() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0645");
        java.net.URL uRL0 = null;
        java.net.URL uRL5 = null;
        java.net.Proxy proxy6 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler9 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl10 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL5, proxy6, httpHandler9);
        int int11 = httpURLConnectionImpl10.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState12 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl10.setTunnelState(tunnelState12);
        boolean boolean14 = httpURLConnectionImpl10.streaming();
        int int15 = httpURLConnectionImpl10.getReadTimeout();
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient16 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, "{size=10 nkeys=1  {hi!} }", 403, false, 405, httpURLConnectionImpl10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState12 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState12.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
    }

    @Test
    public void test0646() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0646");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        int int9 = httpsURLConnectionImpl3.getConnectTimeout();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str10 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
    }

    @Test
    public void test0647() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0647");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 8192);
        boolean boolean11 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setReadTimeout(0);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = httpsURLConnectionImpl3.getContentEncoding();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
// flaky:         org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
    }

    @Test
    public void test0648() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0648");
        java.lang.String str1 = java.net.URLConnection.getDefaultRequestProperty("{size=10 nkeys=1  {hi!} }");
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test0649() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0649");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        int int2 = posterOutputStream0.size();
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
    }

    @Test
    public void test0650() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0650");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setConnectTimeout((int) ' ');
        java.lang.String str8 = httpsURLConnectionImpl3.toString();
        httpsURLConnectionImpl3.setAllowUserInteraction(true);
        java.lang.String str11 = httpsURLConnectionImpl3.toString();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str12 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type com.quakearts.rest.client.exception.HttpClientRuntimeException; message: java.lang.NullPointerException");
        } catch (com.quakearts.rest.client.exception.HttpClientRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str11, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
    }

    @Test
    public void test0651() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0651");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = null;
        authCacheImpl0.remove("", authCacheValue3);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = null;
        // The following exception was thrown during execution in test generation
        try {
            authCacheImpl0.put("{size=10 nkeys=1  {get} }", authCacheValue6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0652() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0652");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL1 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL1, "");
        long long4 = progressSource3.getExpected();
        progressMonitor0.registerSource(progressSource3);
        java.lang.String str6 = progressSource3.toString();
        java.net.URL uRL7 = null;
        java.net.URL uRL10 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource12 = new com.quakearts.rest.client.net.ProgressSource(uRL10, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.net.URL uRL13 = null;
        com.quakearts.rest.client.net.ProgressSource.State state16 = com.quakearts.rest.client.net.ProgressSource.State.UPDATE;
        com.quakearts.rest.client.net.ProgressEvent progressEvent19 = new com.quakearts.rest.client.net.ProgressEvent(progressSource12, uRL13, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", state16, (long) 501, (long) 304);
        java.net.URL uRL20 = null;
        com.quakearts.rest.client.net.ProgressSource.State state23 = com.quakearts.rest.client.net.ProgressSource.State.NEW;
        com.quakearts.rest.client.net.ProgressEvent progressEvent26 = new com.quakearts.rest.client.net.ProgressEvent(progressSource12, uRL20, "hi!", "GET", state23, 1L, (long) 408);
        com.quakearts.rest.client.net.ProgressEvent progressEvent29 = new com.quakearts.rest.client.net.ProgressEvent(progressSource3, uRL7, "content/unknown", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", state23, (long) 504, (long) (byte) 100);
        java.lang.Object obj30 = progressEvent29.getSource();
        org.junit.Assert.assertTrue("'" + long4 + "' != '" + (-1L) + "'", long4 == (-1L));
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str6, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + state16 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.UPDATE + "'", state16.equals(com.quakearts.rest.client.net.ProgressSource.State.UPDATE));
        org.junit.Assert.assertTrue("'" + state23 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.NEW + "'", state23.equals(com.quakearts.rest.client.net.ProgressSource.State.NEW));
        org.junit.Assert.assertNotNull(obj30);
        org.junit.Assert.assertEquals(obj30.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj30), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj30), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test0653() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0653");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setFixedLengthStreamingMode((long) 414);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Chunked encoding streaming mode set");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
// flaky:         org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test0654() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0654");
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
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission17 = httpsURLConnectionImpl5.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
    }

    @Test
    public void test0655() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0655");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setConnectTimeout((int) ' ');
        java.lang.String str8 = httpsURLConnectionImpl3.toString();
        httpsURLConnectionImpl3.setChunkedStreamingMode((int) (byte) -1);
        int int13 = httpsURLConnectionImpl3.getHeaderFieldInt("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", (int) (short) 10);
        long long16 = httpsURLConnectionImpl3.getHeaderFieldLong("", (long) 201);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 10 + "'", int13 == 10);
        org.junit.Assert.assertTrue("'" + long16 + "' != '" + 201L + "'", long16 == 201L);
    }

    @Test
    public void test0656() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0656");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        javax.net.ssl.SSLSocketFactory sSLSocketFactory9 = httpsURLConnectionImpl3.getSSLSocketFactory();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str10 = httpsURLConnectionImpl3.getContentEncoding();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
// flaky:         org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertNotNull(sSLSocketFactory9);
    }

    @Test
    public void test0657() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0657");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream3 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture2);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream4 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream4.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture6 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream7 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream4, httpCapture6);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream8 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream8.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture10 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream11 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream8, httpCapture10);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream12 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray13 = posterOutputStream12.toByteArray();
        httpCaptureOutputStream11.write(byteArray13);
        httpCaptureOutputStream7.write(byteArray13);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream3.write(byteArray13, 206, 203);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray13), "[]");
    }

    @Test
    public void test0658() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0658");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader0.set(406, "content/unknown", "hi!");
        messageHeader0.prepend("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "");
        java.lang.String str11 = messageHeader0.getKey(202);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str11);
    }

    @Test
    public void test0659() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0659");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission9 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
// flaky:         org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test0660() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0660");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpClient httpClient1 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader2 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str4 = messageHeader2.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.lang.String str6 = messageHeader2.getValue((int) (byte) -1);
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream7 = new com.quakearts.rest.client.net.ChunkedInputStream(inputStream0, httpClient1, messageHeader2);
        boolean boolean8 = chunkedInputStream7.hurry();
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0661() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0661");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler7 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl8 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL3, proxy4, httpHandler7);
        int int9 = httpURLConnectionImpl8.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState10 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl8.setTunnelState(tunnelState10);
        boolean boolean12 = httpURLConnectionImpl8.streaming();
        java.io.InputStream inputStream13 = httpURLConnectionImpl8.getErrorStream();
        java.io.InputStream inputStream14 = httpURLConnectionImpl8.getErrorStream();
        java.io.InputStream inputStream15 = httpURLConnectionImpl8.getErrorStream();
        httpURLConnectionImpl8.setReadTimeout(206);
        java.net.CacheRequest cacheRequest18 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpURLConnectionImpl8);
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl8.doTunneling();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState10 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState10.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNull(inputStream13);
        org.junit.Assert.assertNull(inputStream14);
        org.junit.Assert.assertNull(inputStream15);
        org.junit.Assert.assertNull(cacheRequest18);
    }

    @Test
    public void test0662() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0662");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader0.set(406, "content/unknown", "hi!");
        messageHeader0.setIfNotSet("", "{size=10 nkeys=1  {hi!} }");
        int int11 = messageHeader0.getKey("{size=10 nkeys=1  {get} }");
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + (-1) + "'", int11 == (-1));
    }

    @Test
    public void test0663() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0663");
        com.quakearts.rest.client.net.MessageHeader messageHeader2 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str4 = messageHeader2.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader2.set(406, "content/unknown", "hi!");
        messageHeader2.prepend("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "");
        java.lang.String[] strArray22 = new java.lang.String[] { "{size=10 nkeys=1  {hi!} }", "GET", "{size=10 nkeys=1  {get} }", "{size=10 nkeys=1  {hi!} }", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "AuthenticationHeader: prefer null", "AuthenticationHeader: prefer null", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "hi!", "{size=10 nkeys=1  {hi!} }" };
        java.util.LinkedHashSet<java.lang.String> strSet23 = new java.util.LinkedHashSet<java.lang.String>();
        boolean boolean24 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strSet23, strArray22);
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader25 = new com.quakearts.rest.client.net.http.AuthenticationHeader("", messageHeader2, (java.util.Set<java.lang.String>) strSet23);
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader26 = new com.quakearts.rest.client.net.http.AuthenticationHeader("", messageHeader2);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(strArray22);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + true + "'", boolean24 == true);
    }

    @Test
    public void test0664() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0664");
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
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl5.connect();
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
        org.junit.Assert.assertNull(inputStream13);
    }

    @Test
    public void test0665() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0665");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource.State state9 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent12 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL6, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state9, (long) 300, (-1L));
        long long13 = progressEvent12.getExpected();
        java.net.URL uRL14 = progressEvent12.getURL();
        java.lang.Object obj15 = progressEvent12.getSource();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-1L) + "'", long13 == (-1L));
        org.junit.Assert.assertNull(uRL14);
        org.junit.Assert.assertNotNull(obj15);
        org.junit.Assert.assertEquals(obj15.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj15), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj15), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test0666() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0666");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl5.setAuthenticationProperty("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal character(s) in message header field: com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0667() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0667");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        // The following exception was thrown during execution in test generation
        try {
            long long6 = httpsURLConnectionImpl3.getExpiration();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0668() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0668");
        com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker1 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) 1);
        java.security.cert.X509Certificate x509Certificate3 = null;
        // The following exception was thrown during execution in test generation
        try {
            hostnameChecker1.match("hi!", x509Certificate3);
            org.junit.Assert.fail("Expected exception of type java.security.cert.CertificateException; message: Illegal given domain name: hi!");
        } catch (java.security.cert.CertificateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameChecker1);
    }

    @Test
    public void test0669() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0669");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        java.net.URL uRL6 = null;
        java.net.Proxy proxy7 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler8 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl9 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL6, proxy7, httpsHandler8);
        javax.net.ssl.HostnameVerifier hostnameVerifier10 = httpsURLConnectionImpl9.getHostnameVerifier();
        boolean boolean11 = httpsURLConnectionImpl9.getDoInput();
        httpsURLConnectionImpl9.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream14 = httpsURLConnectionImpl9.getErrorStream();
        java.net.URL uRL15 = httpsURLConnectionImpl9.getURL();
        boolean boolean16 = httpsURLConnectionImpl9.getDoInput();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap17 = httpsURLConnectionImpl9.getRequestProperties();
        httpURLConnectionImpl5.authObj((java.lang.Object) httpsURLConnectionImpl9);
        long long19 = httpsURLConnectionImpl9.getLastModified();
        long long20 = httpsURLConnectionImpl9.getIfModifiedSince();
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl9.setChunkedStreamingMode((int) (byte) 2);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Fixed length streaming mode set");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier10);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertNull(inputStream14);
        org.junit.Assert.assertNull(uRL15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertNotNull(strMap17);
        org.junit.Assert.assertTrue("'" + long19 + "' != '" + 0L + "'", long19 == 0L);
        org.junit.Assert.assertTrue("'" + long20 + "' != '" + 0L + "'", long20 == 0L);
    }

    @Test
    public void test0670() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0670");
        java.security.Principal principal1 = null;
        boolean boolean2 = com.quakearts.rest.client.net.https.HostnameChecker.match("{}", principal1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test0671() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0671");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        boolean boolean3 = progressSource2.connected();
        progressSource2.beginTracking();
        java.lang.Class<?> wildcardClass5 = progressSource2.getClass();
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(wildcardClass5);
    }

    @Test
    public void test0672() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0672");
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
        int int14 = httpsURLConnectionImpl3.getConnectTimeout();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap15 = httpsURLConnectionImpl3.getRequestProperties();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
        org.junit.Assert.assertNotNull(strMap15);
    }

    @Test
    public void test0673() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0673");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        boolean boolean8 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.setReadTimeout(303);
        httpsURLConnectionImpl3.setDoOutput(false);
        java.lang.String str13 = httpsURLConnectionImpl3.getContentType();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNull(str13);
    }

    @Test
    public void test0674() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0674");
        java.lang.String str1 = com.quakearts.rest.client.net.NetProperties.get("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test0675() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0675");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue9 = authCacheImpl0.get("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "content/unknown");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue11 = null;
        // The following exception was thrown during execution in test generation
        try {
            authCacheImpl0.put("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]", authCacheValue11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(authCacheValue3);
        org.junit.Assert.assertNull(authCacheValue6);
        org.junit.Assert.assertNull(authCacheValue9);
    }

    @Test
    public void test0676() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0676");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        long long6 = httpsURLConnectionImpl3.getIfModifiedSince();
        java.lang.String str7 = httpsURLConnectionImpl3.toString();
        boolean boolean8 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + 0L + "'", long6 == 0L);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str7, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
    }

    @Test
    public void test0677() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0677");
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
        long long12 = httpsURLConnectionImpl3.getIfModifiedSince();
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream13 = httpsURLConnectionImpl3.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(strMap11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
    }

    @Test
    public void test0678() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0678");
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
        // The following exception was thrown during execution in test generation
        try {
            long long17 = httpURLConnectionImpl5.getHeaderFieldDate("", (long) 406);
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
    }

    @Test
    public void test0679() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0679");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = null;
        authCacheImpl0.remove("content/unknown", authCacheValue6);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue9 = null;
        authCacheImpl0.remove("{size=10 nkeys=1  {get} }", authCacheValue9);
        org.junit.Assert.assertNull(authCacheValue3);
    }

    @Test
    public void test0680() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0680");
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
        java.nio.charset.CharsetEncoder charsetEncoder13 = com.quakearts.rest.client.net.ThreadLocalCoders.encoderFor((java.lang.Object) httpURLConnectionImpl5);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNull(inputStream12);
        org.junit.Assert.assertNull(charsetEncoder13);
    }

    @Test
    public void test0681() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0681");
        com.quakearts.rest.client.net.URLConnectionImpl.setProxiedHost("AuthenticationHeader: prefer null");
    }

    @Test
    public void test0682() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0682");
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
            int int44 = meteredStream10.available();
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
    public void test0683() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0683");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setUseCaches(false);
        boolean boolean8 = httpsURLConnectionImpl3.getDoOutput();
        boolean boolean9 = httpsURLConnectionImpl3.getDefaultUseCaches();
        java.io.InputStream inputStream10 = httpsURLConnectionImpl3.getErrorStream();
        // The following exception was thrown during execution in test generation
        try {
            java.security.cert.Certificate[] certificateArray11 = httpsURLConnectionImpl3.getLocalCertificates();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
// flaky:         org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertNull(inputStream10);
    }

    @Test
    public void test0684() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0684");
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
            meteredStream26.mark(97);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test0685() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0685");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray1 = posterOutputStream0.toByteArray();
        byte[] byteArray2 = posterOutputStream0.toByteArray();
        posterOutputStream0.close();
        posterOutputStream0.write(405);
        posterOutputStream0.write((int) '#');
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream8 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream8.reset();
        posterOutputStream8.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream11 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray12 = posterOutputStream11.toByteArray();
        byte[] byteArray13 = posterOutputStream11.toByteArray();
        posterOutputStream8.write(byteArray13);
        posterOutputStream0.write(byteArray13, 204, (int) '4');
        int int18 = posterOutputStream0.size();
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray1), "[]");
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray2), "[]");
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray12), "[]");
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray13), "[]");
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 0 + "'", int18 == 0);
    }

    @Test
    public void test0686() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0686");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = httpsURLConnectionImpl3.getCipherSuite();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertNull(uRL11);
        org.junit.Assert.assertNull(str13);
    }

    @Test
    public void test0687() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0687");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream2 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream2.flush();
        posterOutputStream0.writeTo((java.io.OutputStream) posterOutputStream2);
        com.quakearts.rest.client.net.HttpCapture httpCapture5 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream6 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture5);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream6.write(206);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0688() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0688");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL1 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL1, "");
        boolean boolean4 = progressSource3.connected();
        boolean boolean5 = progressSource3.connected();
        progressMonitor0.registerSource(progressSource3);
        com.quakearts.rest.client.net.ProgressSource progressSource7 = null;
        progressMonitor0.unregisterSource(progressSource7);
        java.net.URL uRL9 = null;
        boolean boolean11 = progressMonitor0.shouldMeterInput(uRL9, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0689() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0689");
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
        // The following exception was thrown during execution in test generation
        try {
            long long50 = meteredStream48.skip((long) (short) 100);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertTrue("'" + long44 + "' != '" + (-1L) + "'", long44 == (-1L));
        org.junit.Assert.assertEquals("'" + str46 + "' != '" + "content/unknown" + "'", str46, "content/unknown");
    }

    @Test
    public void test0690() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0690");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        httpsURLConnectionImpl3.setRequestProperty("hi!", "");
        httpsURLConnectionImpl3.setReadTimeout((int) (short) 100);
        javax.net.ssl.SSLSocketFactory sSLSocketFactory13 = httpsURLConnectionImpl3.getSSLSocketFactory();
        java.lang.Class[] classArray14 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj15 = httpsURLConnectionImpl3.getContent(classArray14);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNotNull(sSLSocketFactory13);
    }

    @Test
    public void test0691() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0691");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        long long6 = httpsURLConnectionImpl3.getIfModifiedSince();
        java.io.InputStream inputStream7 = httpsURLConnectionImpl3.getErrorStream();
        boolean boolean8 = httpsURLConnectionImpl3.getUseCaches();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + 0L + "'", long6 == 0L);
        org.junit.Assert.assertNull(inputStream7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0692() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0692");
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
        keepAliveStream36.mark(300);
        keepAliveStream36.mark(200);
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
    }

    @Test
    public void test0693() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0693");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.addRequestProperty("", "hi!");
        int int13 = httpsURLConnectionImpl3.getHeaderFieldInt("hi!", 100);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 100 + "'", int13 == 100);
    }

    @Test
    public void test0694() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0694");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        boolean boolean7 = httpsURLConnectionImpl3.getUseCaches();
        long long8 = httpsURLConnectionImpl3.getIfModifiedSince();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + 0L + "'", long8 == 0L);
    }

    @Test
    public void test0695() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0695");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        java.net.URL uRL6 = null;
        java.net.Proxy proxy7 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler8 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl9 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL6, proxy7, httpsHandler8);
        javax.net.ssl.HostnameVerifier hostnameVerifier10 = httpsURLConnectionImpl9.getHostnameVerifier();
        boolean boolean11 = httpsURLConnectionImpl9.getDoInput();
        httpsURLConnectionImpl9.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream14 = httpsURLConnectionImpl9.getErrorStream();
        java.net.URL uRL15 = httpsURLConnectionImpl9.getURL();
        boolean boolean16 = httpsURLConnectionImpl9.getDoInput();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap17 = httpsURLConnectionImpl9.getRequestProperties();
        httpURLConnectionImpl5.authObj((java.lang.Object) httpsURLConnectionImpl9);
        java.net.URL uRL19 = httpsURLConnectionImpl9.getURL();
        // The following exception was thrown during execution in test generation
        try {
            int int20 = httpsURLConnectionImpl9.getResponseCode();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier10);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertNull(inputStream14);
        org.junit.Assert.assertNull(uRL15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertNotNull(strMap17);
        org.junit.Assert.assertNull(uRL19);
    }

    @Test
    public void test0696() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0696");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler7 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl8 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL3, proxy4, httpHandler7);
        int int9 = httpURLConnectionImpl8.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState10 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl8.setTunnelState(tunnelState10);
        boolean boolean12 = httpURLConnectionImpl8.streaming();
        java.io.InputStream inputStream13 = httpURLConnectionImpl8.getErrorStream();
        java.io.InputStream inputStream14 = httpURLConnectionImpl8.getErrorStream();
        java.io.InputStream inputStream15 = httpURLConnectionImpl8.getErrorStream();
        httpURLConnectionImpl8.setReadTimeout(206);
        java.net.CacheRequest cacheRequest18 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpURLConnectionImpl8);
        httpURLConnectionImpl8.setAuthenticationProperty("", "AuthenticationHeader: prefer null");
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState10 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState10.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNull(inputStream13);
        org.junit.Assert.assertNull(inputStream14);
        org.junit.Assert.assertNull(inputStream15);
        org.junit.Assert.assertNull(cacheRequest18);
    }

    @Test
    public void test0697() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0697");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setChunkedStreamingMode(408);
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setFixedLengthStreamingMode(204);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Chunked encoding streaming mode set");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0698() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0698");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setUseCaches(false);
        boolean boolean8 = httpsURLConnectionImpl3.getDoOutput();
        boolean boolean9 = httpsURLConnectionImpl3.getDefaultUseCaches();
        java.io.InputStream inputStream10 = httpsURLConnectionImpl3.getErrorStream();
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream11 = httpsURLConnectionImpl3.getInputStream();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
    }

    @Test
    public void test0699() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0699");
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
            int int50 = chunkedInputStream49.available();
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
    public void test0700() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0700");
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
        com.quakearts.rest.client.net.HttpCapture httpCapture39 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream40 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) keepAliveStream36, httpCapture39);
        // The following exception was thrown during execution in test generation
        try {
            int int41 = keepAliveStream36.available();
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
    }

    @Test
    public void test0701() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0701");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        long long3 = progressSource2.getExpected();
        long long4 = progressSource2.getExpected();
        long long5 = progressSource2.getProgress();
        org.junit.Assert.assertTrue("'" + long3 + "' != '" + (-1L) + "'", long3 == (-1L));
        org.junit.Assert.assertTrue("'" + long4 + "' != '" + (-1L) + "'", long4 == (-1L));
        org.junit.Assert.assertTrue("'" + long5 + "' != '" + 0L + "'", long5 == 0L);
    }

    @Test
    public void test0702() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0702");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("GET");
        java.lang.String str4 = headerParser1.findValue("hi!", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.lang.String str6 = headerParser1.findValue("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.lang.String str7 = headerParser1.toString();
        java.lang.String str9 = headerParser1.findKey(412);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str4, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "{size=10 nkeys=1  {get} }" + "'", str7, "{size=10 nkeys=1  {get} }");
        org.junit.Assert.assertNull(str9);
    }

    @Test
    public void test0703() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0703");
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker1 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) 0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Unknown check type: 0");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0704() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0704");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = null;
        authCacheImpl0.remove("hi!", authCacheValue6);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue9 = null;
        // The following exception was thrown during execution in test generation
        try {
            authCacheImpl0.put("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]", authCacheValue9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(authCacheValue3);
    }

    @Test
    public void test0705() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0705");
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
        keepAliveStream36.mark(300);
        boolean boolean42 = keepAliveStream36.markSupported();
        // The following exception was thrown during execution in test generation
        try {
            long long44 = keepAliveStream36.skip((long) 'a');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + false + "'", boolean42 == false);
    }

    @Test
    public void test0706() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0706");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler7 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl8 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL3, proxy4, httpHandler7);
        int int9 = httpURLConnectionImpl8.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState10 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl8.setTunnelState(tunnelState10);
        boolean boolean12 = httpURLConnectionImpl8.streaming();
        int int13 = httpURLConnectionImpl8.getReadTimeout();
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient14 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, proxy1, (int) 'a', httpURLConnectionImpl8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState10 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState10.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
    }

    @Test
    public void test0707() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0707");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream2 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream2.flush();
        posterOutputStream0.writeTo((java.io.OutputStream) posterOutputStream2);
        com.quakearts.rest.client.net.HttpCapture httpCapture5 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream6 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture5);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream6.write(205);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0708() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0708");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        java.net.URL uRL9 = httpsURLConnectionImpl3.getURL();
        boolean boolean10 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean11 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setRequestMethod("");
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: Invalid HTTP method: ");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
// flaky:         org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
    }

    @Test
    public void test0709() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0709");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        long long10 = httpsURLConnectionImpl3.getDate();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 402);
        httpsURLConnectionImpl3.setInstanceFollowRedirects(false);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + long10 + "' != '" + 0L + "'", long10 == 0L);
    }

    @Test
    public void test0710() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0710");
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
        com.quakearts.rest.client.net.HttpCapture httpCapture39 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream40 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) keepAliveStream36, httpCapture39);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream41 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream41.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture43 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream44 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream41, httpCapture43);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream45 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream45.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture47 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream48 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream45, httpCapture47);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream49 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray50 = posterOutputStream49.toByteArray();
        httpCaptureOutputStream48.write(byteArray50);
        httpCaptureOutputStream44.write(byteArray50);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream53 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray54 = posterOutputStream53.toByteArray();
        httpCaptureOutputStream44.write(byteArray54);
        // The following exception was thrown during execution in test generation
        try {
            int int58 = httpCaptureInputStream40.read(byteArray54, 415, 500);
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
        org.junit.Assert.assertNotNull(byteArray50);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray50), "[]");
        org.junit.Assert.assertNotNull(byteArray54);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray54), "[]");
    }

    @Test
    public void test0711() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0711");
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
        java.net.URL uRL27 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource29 = new com.quakearts.rest.client.net.ProgressSource(uRL27, "");
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(progressSource29);
        com.quakearts.rest.client.net.MeteredStream meteredStream32 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource29, (long) 505);
        // The following exception was thrown during execution in test generation
        try {
            long long34 = meteredStream32.skip((long) (short) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0712() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0712");
        com.quakearts.rest.client.net.http.HttpHandler httpHandler2 = new com.quakearts.rest.client.net.http.HttpHandler("content/unknown", 503);
    }

    @Test
    public void test0713() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0713");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource.State state9 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent12 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL6, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state9, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource13 = new com.quakearts.rest.client.net.ProgressSource(progressSource2);
        progressSource2.close();
        java.net.URL uRL15 = progressSource2.getURL();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNull(uRL15);
    }

    @Test
    public void test0714() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0714");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        java.net.URL uRL6 = null;
        java.net.Proxy proxy7 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler10 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl11 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL6, proxy7, httpHandler10);
        int int12 = httpURLConnectionImpl11.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState13 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl11.setTunnelState(tunnelState13);
        boolean boolean15 = httpURLConnectionImpl11.streaming();
        boolean boolean16 = httpURLConnectionImpl11.streaming();
        java.lang.Class<?> wildcardClass17 = httpURLConnectionImpl11.getClass();
        java.lang.Class[] classArray18 = new java.lang.Class[] { wildcardClass17 };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj19 = httpsURLConnectionImpl3.getContent(classArray18);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState13 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState13.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNotNull(wildcardClass17);
        org.junit.Assert.assertNotNull(classArray18);
    }

    @Test
    public void test0715() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0715");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl3 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl3);
        java.net.URI uRI5 = null;
        java.net.URL uRL6 = null;
        java.net.Proxy proxy7 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler10 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl11 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL6, proxy7, httpHandler10);
        int int12 = httpURLConnectionImpl11.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState13 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl11.setTunnelState(tunnelState13);
        boolean boolean15 = httpURLConnectionImpl11.streaming();
        java.io.InputStream inputStream16 = httpURLConnectionImpl11.getErrorStream();
        java.io.InputStream inputStream17 = httpURLConnectionImpl11.getErrorStream();
        java.io.InputStream inputStream18 = httpURLConnectionImpl11.getErrorStream();
        httpURLConnectionImpl11.setReadTimeout(206);
        java.net.CacheRequest cacheRequest21 = responseCacheImpl3.put(uRI5, (java.net.URLConnection) httpURLConnectionImpl11);
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient22 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, proxy1, 303, httpURLConnectionImpl11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState13 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState13.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNull(inputStream16);
        org.junit.Assert.assertNull(inputStream17);
        org.junit.Assert.assertNull(inputStream18);
        org.junit.Assert.assertNull(cacheRequest21);
    }

    @Test
    public void test0716() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0716");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue9 = authCacheImpl0.get("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "content/unknown");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue11 = null;
        authCacheImpl0.remove("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", authCacheValue11);
        org.junit.Assert.assertNull(authCacheValue3);
        org.junit.Assert.assertNull(authCacheValue6);
        org.junit.Assert.assertNull(authCacheValue9);
    }

    @Test
    public void test0717() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0717");
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
        java.net.URL uRL17 = httpsURLConnectionImpl5.getURL();
        long long20 = httpsURLConnectionImpl5.getHeaderFieldDate("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", (long) 409);
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl5.setFixedLengthStreamingMode((long) (-1));
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: invalid content length");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertNull(uRL17);
        org.junit.Assert.assertTrue("'" + long20 + "' != '" + 409L + "'", long20 == 409L);
    }

    @Test
    public void test0718() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0718");
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
        httpURLConnectionImpl5.setRequestProperty("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {get} }");
        // The following exception was thrown during execution in test generation
        try {
            int int15 = httpURLConnectionImpl5.getResponseCode();
            org.junit.Assert.fail("Expected exception of type com.quakearts.rest.client.exception.HttpClientRuntimeException; message: java.lang.NullPointerException");
        } catch (com.quakearts.rest.client.exception.HttpClientRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertNull(inputStream11);
    }

    @Test
    public void test0719() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0719");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray1 = posterOutputStream0.toByteArray();
        byte[] byteArray2 = posterOutputStream0.toByteArray();
        posterOutputStream0.close();
        posterOutputStream0.write(405);
        posterOutputStream0.write((int) '#');
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream8 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream8.reset();
        posterOutputStream8.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream11 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray12 = posterOutputStream11.toByteArray();
        byte[] byteArray13 = posterOutputStream11.toByteArray();
        posterOutputStream8.write(byteArray13);
        posterOutputStream0.write(byteArray13, 204, (int) '4');
        java.lang.String str18 = posterOutputStream0.toString();
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray1), "[]");
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray2), "[]");
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray12), "[]");
        org.junit.Assert.assertNotNull(byteArray13);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray13), "[]");
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "" + "'", str18, "");
    }

    @Test
    public void test0720() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0720");
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
        java.net.URL uRL27 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource29 = new com.quakearts.rest.client.net.ProgressSource(uRL27, "");
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(progressSource29);
        com.quakearts.rest.client.net.MeteredStream meteredStream32 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource29, (long) 505);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream33 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream33.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream35 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream35.reset();
        posterOutputStream35.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream38 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray39 = posterOutputStream38.toByteArray();
        byte[] byteArray40 = posterOutputStream38.toByteArray();
        posterOutputStream35.write(byteArray40);
        posterOutputStream33.write(byteArray40);
        // The following exception was thrown during execution in test generation
        try {
            int int43 = meteredStream32.read(byteArray40);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(byteArray39);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray39), "[]");
        org.junit.Assert.assertNotNull(byteArray40);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray40), "[]");
    }

    @Test
    public void test0721() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0721");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        boolean boolean10 = httpURLConnectionImpl5.streaming();
        int int11 = httpURLConnectionImpl5.getReadTimeout();
        // The following exception was thrown during execution in test generation
        try {
            long long14 = httpURLConnectionImpl5.getHeaderFieldDate("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", 32L);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
    }

    @Test
    public void test0722() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0722");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        java.net.URL uRL2 = null;
        java.net.Proxy proxy3 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler6 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl7 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL2, proxy3, httpHandler6);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl8 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler6);
        // The following exception was thrown during execution in test generation
        try {
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap9 = httpURLConnectionImpl8.getHeaderFields();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0723() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0723");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray1 = posterOutputStream0.toByteArray();
        posterOutputStream0.write((int) 'p');
        posterOutputStream0.write((int) '4');
        com.quakearts.rest.client.net.HttpCapture httpCapture6 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream7 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture6);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream7.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray1), "[]");
    }

    @Test
    public void test0724() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0724");
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
        com.quakearts.rest.client.net.HttpCapture httpCapture39 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream40 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) keepAliveStream36, httpCapture39);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor41 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL42 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource44 = new com.quakearts.rest.client.net.ProgressSource(uRL42, "");
        long long45 = progressSource44.getExpected();
        progressMonitor41.registerSource(progressSource44);
        progressSource44.close();
        java.net.URL uRL48 = progressSource44.getURL();
        com.quakearts.rest.client.net.HttpClient httpClient50 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream51 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) httpCaptureInputStream40, progressSource44, (long) 'a', httpClient50);
        // The following exception was thrown during execution in test generation
        try {
            keepAliveStream51.close();
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
        org.junit.Assert.assertTrue("'" + long45 + "' != '" + (-1L) + "'", long45 == (-1L));
        org.junit.Assert.assertNull(uRL48);
    }

    @Test
    public void test0725() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0725");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        java.io.InputStream inputStream10 = httpURLConnectionImpl5.getErrorStream();
        httpURLConnectionImpl5.disconnect();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str13 = httpURLConnectionImpl5.getHeaderField("{size=10 nkeys=1  {get} }");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
    }

    @Test
    public void test0726() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0726");
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
        java.lang.String str17 = httpsURLConnectionImpl5.getRequestMethod();
        long long20 = httpsURLConnectionImpl5.getHeaderFieldLong("hi!", (long) ' ');
        // The following exception was thrown during execution in test generation
        try {
            java.security.Principal principal21 = httpsURLConnectionImpl5.getLocalPrincipal();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "GET" + "'", str17, "GET");
        org.junit.Assert.assertTrue("'" + long20 + "' != '" + 32L + "'", long20 == 32L);
    }

    @Test
    public void test0727() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0727");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream2 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream2.flush();
        posterOutputStream0.writeTo((java.io.OutputStream) posterOutputStream2);
        com.quakearts.rest.client.net.HttpCapture httpCapture5 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream6 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture5);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream6.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0728() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0728");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        java.lang.String str7 = httpsURLConnectionImpl3.getRequestProperty("hi!");
        boolean boolean8 = httpsURLConnectionImpl3.usingProxy();
        int int9 = httpsURLConnectionImpl3.getConnectTimeout();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj10 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
    }

    @Test
    public void test0729() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0729");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream2 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream2.flush();
        posterOutputStream0.writeTo((java.io.OutputStream) posterOutputStream2);
        java.lang.String str5 = posterOutputStream2.toString();
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
    }

    @Test
    public void test0730() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0730");
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
        long long14 = httpsURLConnectionImpl3.getIfModifiedSince();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + 1L + "'", long13 == 1L);
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + 0L + "'", long14 == 0L);
    }

    @Test
    public void test0731() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0731");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        boolean boolean8 = httpsURLConnectionImpl3.getDoInput();
        java.lang.String str10 = httpsURLConnectionImpl3.getHeaderFieldKey(501);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertNull(str10);
    }

    @Test
    public void test0732() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0732");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue4 = authCacheImpl0.get("hi!", "GET");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = null;
        // The following exception was thrown during execution in test generation
        try {
            authCacheImpl0.put("AuthenticationHeader: prefer null", authCacheValue6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(authCacheValue4);
    }

    @Test
    public void test0733() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0733");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        java.lang.String str8 = httpsURLConnectionImpl3.getContentType();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNull(str8);
    }

    @Test
    public void test0734() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0734");
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
        httpsURLConnectionImpl3.setDoOutput(true);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
    }

    @Test
    public void test0735() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0735");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray1 = posterOutputStream0.toByteArray();
        byte[] byteArray2 = posterOutputStream0.toByteArray();
        posterOutputStream0.close();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str5 = posterOutputStream0.toString("");
            org.junit.Assert.fail("Expected exception of type java.io.UnsupportedEncodingException; message: ");
        } catch (java.io.UnsupportedEncodingException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray1), "[]");
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray2), "[]");
    }

    @Test
    public void test0736() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0736");
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
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean41 = keepAliveStream36.hurry();
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
    }

    @Test
    public void test0737() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0737");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL1 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL1, "");
        long long4 = progressSource3.getExpected();
        progressMonitor0.registerSource(progressSource3);
        com.quakearts.rest.client.net.ProgressListener progressListener6 = null;
        progressMonitor0.addProgressListener(progressListener6);
        java.net.URL uRL8 = null;
        boolean boolean10 = progressMonitor0.shouldMeterInput(uRL8, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + long4 + "' != '" + (-1L) + "'", long4 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test0738() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0738");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        boolean boolean3 = progressSource2.connected();
        progressSource2.finishTracking();
        java.lang.String str5 = progressSource2.getContentType();
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "content/unknown" + "'", str5, "content/unknown");
    }

    @Test
    public void test0739() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0739");
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
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream12 = httpURLConnectionImpl5.getInputStream();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertNull(inputStream11);
    }

    @Test
    public void test0740() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0740");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setUseCaches(false);
        java.lang.String str8 = httpsURLConnectionImpl3.getRequestMethod();
        httpsURLConnectionImpl3.setDoInput(false);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "GET" + "'", str8, "GET");
    }

    @Test
    public void test0741() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0741");
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
        int int17 = httpURLConnectionImpl5.getConnectTimeout();
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
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 0 + "'", int17 == 0);
    }

    @Test
    public void test0742() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0742");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        boolean boolean8 = httpsURLConnectionImpl3.getDoInput();
        // The following exception was thrown during execution in test generation
        try {
            int int9 = httpsURLConnectionImpl3.getResponseCode();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
    }

    @Test
    public void test0743() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0743");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findValue(412);
        org.junit.Assert.assertNull(str3);
    }

    @Test
    public void test0744() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0744");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("", "");
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        org.junit.Assert.assertNull(authCacheValue3);
    }

    @Test
    public void test0745() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0745");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.disconnect();
        int int8 = httpsURLConnectionImpl3.getConnectTimeout();
        httpsURLConnectionImpl3.setDefaultUseCaches(true);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str11 = httpsURLConnectionImpl3.getCipherSuite();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test0746() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0746");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        // The following exception was thrown during execution in test generation
        try {
            meteredStream10.mark((int) (byte) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
    }

    @Test
    public void test0747() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0747");
        java.lang.String str1 = com.quakearts.rest.client.net.MessageHeader.canonicalID("content/unknown");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "content/unknown" + "'", str1, "content/unknown");
    }

    @Test
    public void test0748() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0748");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setConnectTimeout((int) ' ');
        java.lang.String str8 = httpsURLConnectionImpl3.toString();
        httpsURLConnectionImpl3.setChunkedStreamingMode((int) (byte) -1);
        int int13 = httpsURLConnectionImpl3.getHeaderFieldInt("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", (int) (short) 10);
        httpsURLConnectionImpl3.disconnect();
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream15 = httpsURLConnectionImpl3.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 10 + "'", int13 == 10);
    }

    @Test
    public void test0749() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0749");
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
            meteredStream10.mark((-1));
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
    public void test0750() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0750");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpClient httpClient3 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader4 = new com.quakearts.rest.client.net.MessageHeader();
        java.io.InputStream inputStream5 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture6 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream7 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream5, httpCapture6);
        com.quakearts.rest.client.net.HttpCapture httpCapture8 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream9 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream5, httpCapture8);
        messageHeader4.parseHeader(inputStream5);
        messageHeader4.add("GET", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.util.Iterator<java.lang.String> strItor15 = messageHeader4.multiValueIterator("");
        messageHeader4.add("GET", "GET");
        java.lang.String[] strArray23 = new java.lang.String[] { "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "", "content/unknown", "{size=10 nkeys=1  {hi!} }" };
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap24 = messageHeader4.getHeaders(strArray23);
        messageHeader4.remove("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader4.add("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "hi!");
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream30 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) httpCaptureInputStream2, httpClient3, messageHeader4);
        // The following exception was thrown during execution in test generation
        try {
            long long32 = httpCaptureInputStream2.skip((long) 301);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strItor15);
        org.junit.Assert.assertNotNull(strArray23);
        org.junit.Assert.assertNotNull(strMap24);
    }

    @Test
    public void test0751() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0751");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int1 = progressMonitor0.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressMonitor.setDefault(progressMonitor0);
        java.net.URL uRL3 = null;
        boolean boolean5 = progressMonitor0.shouldMeterInput(uRL3, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.net.URL uRL6 = null;
        boolean boolean8 = progressMonitor0.shouldMeterInput(uRL6, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.net.URL uRL9 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource11 = new com.quakearts.rest.client.net.ProgressSource(uRL9, "");
        long long12 = progressSource11.getExpected();
        progressMonitor0.registerSource(progressSource11);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8192 + "'", int1 == 8192);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + (-1L) + "'", long12 == (-1L));
    }

    @Test
    public void test0752() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0752");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        int int11 = httpsURLConnectionImpl3.getConnectTimeout();
        java.lang.String str12 = httpsURLConnectionImpl3.getContentType();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0753() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0753");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL1 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL1, "");
        long long4 = progressSource3.getExpected();
        progressMonitor0.registerSource(progressSource3);
        progressSource3.beginTracking();
        org.junit.Assert.assertTrue("'" + long4 + "' != '" + (-1L) + "'", long4 == (-1L));
    }

    @Test
    public void test0754() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0754");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.getValue(503);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap3 = messageHeader0.getHeaders();
        messageHeader0.reset();
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strMap3);
    }

    @Test
    public void test0755() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0755");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.getValue(503);
        messageHeader0.prepend("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {hi!} }");
        messageHeader0.set("", "AuthenticationHeader: prefer null");
        int int10 = messageHeader0.getKey("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader0.add("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=UPDATE, content-type=content/unknown, progress=0, expected=32]", "");
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + (-1) + "'", int10 == (-1));
    }

    @Test
    public void test0756() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0756");
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
        java.io.InputStream inputStream13 = httpsURLConnectionImpl3.getErrorStream();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj14 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertNull(inputStream13);
    }

    @Test
    public void test0757() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0757");
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
        // The following exception was thrown during execution in test generation
        try {
            int int17 = httpsURLConnectionImpl3.getResponseCode();
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
    public void test0758() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0758");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpClient httpClient1 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader2 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str4 = messageHeader2.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.lang.String str6 = messageHeader2.getValue((int) (byte) -1);
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream7 = new com.quakearts.rest.client.net.ChunkedInputStream(inputStream0, httpClient1, messageHeader2);
        chunkedInputStream7.close();
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNull(str6);
    }

    @Test
    public void test0759() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0759");
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
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl5.setConnectTimeout((int) (short) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: timeouts can't be negative");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertNull(inputStream11);
    }

    @Test
    public void test0760() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0760");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int1 = progressMonitor0.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor2 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL3 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource5 = new com.quakearts.rest.client.net.ProgressSource(uRL3, "");
        long long6 = progressSource5.getExpected();
        progressMonitor2.registerSource(progressSource5);
        progressMonitor0.updateProgress(progressSource5);
        progressSource5.beginTracking();
        long long10 = progressSource5.getProgress();
        java.net.URL uRL11 = null;
        java.net.URL uRL14 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource16 = new com.quakearts.rest.client.net.ProgressSource(uRL14, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource.State state20 = com.quakearts.rest.client.net.ProgressSource.State.UPDATE;
        com.quakearts.rest.client.net.ProgressEvent progressEvent23 = new com.quakearts.rest.client.net.ProgressEvent(progressSource16, uRL17, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", state20, (long) 501, (long) 304);
        java.net.URL uRL24 = null;
        com.quakearts.rest.client.net.ProgressSource.State state27 = com.quakearts.rest.client.net.ProgressSource.State.NEW;
        com.quakearts.rest.client.net.ProgressEvent progressEvent30 = new com.quakearts.rest.client.net.ProgressEvent(progressSource16, uRL24, "hi!", "GET", state27, 1L, (long) 408);
        com.quakearts.rest.client.net.ProgressEvent progressEvent33 = new com.quakearts.rest.client.net.ProgressEvent(progressSource5, uRL11, "{size=10 nkeys=1  {get} }", "", state27, (long) 's', (long) 100);
        java.lang.String str34 = progressSource5.toString();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8192 + "'", int1 == 8192);
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + (-1L) + "'", long6 == (-1L));
        org.junit.Assert.assertTrue("'" + long10 + "' != '" + 0L + "'", long10 == 0L);
        org.junit.Assert.assertTrue("'" + state20 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.UPDATE + "'", state20.equals(com.quakearts.rest.client.net.ProgressSource.State.UPDATE));
        org.junit.Assert.assertTrue("'" + state27 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.NEW + "'", state27.equals(com.quakearts.rest.client.net.ProgressSource.State.NEW));
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str34, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test0761() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0761");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj14 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
    }

    @Test
    public void test0762() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0762");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        httpsURLConnectionImpl3.setDoOutput(false);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = httpsURLConnectionImpl3.getHeaderField(402);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0763() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0763");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        boolean boolean7 = httpsURLConnectionImpl3.getUseCaches();
        httpsURLConnectionImpl3.addRequestProperty("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]", "");
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test0764() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0764");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("hi!", (int) (short) 1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        // The following exception was thrown during execution in test generation
        try {
            long long8 = httpURLConnectionImpl5.getHeaderFieldLong("", (long) 202);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0765() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0765");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader1.set(406, "content/unknown", "hi!");
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader8 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        java.lang.String str10 = messageHeader1.getValue(0);
        java.lang.String str12 = messageHeader1.getValue(10);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "hi!" + "'", str10, "hi!");
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0766() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0766");
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
        java.io.InputStream inputStream14 = httpsURLConnectionImpl3.getErrorStream();
        // The following exception was thrown during execution in test generation
        try {
            int int15 = httpsURLConnectionImpl3.getResponseCode();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + 0L + "'", long13 == 0L);
        org.junit.Assert.assertNull(inputStream14);
    }

    @Test
    public void test0767() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0767");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream2 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream2.flush();
        posterOutputStream0.writeTo((java.io.OutputStream) posterOutputStream2);
        com.quakearts.rest.client.net.HttpCapture httpCapture5 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream6 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture5);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream7 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray8 = posterOutputStream7.toByteArray();
        byte[] byteArray9 = posterOutputStream7.toByteArray();
        httpCaptureOutputStream6.write(byteArray9);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream6.flush();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray8), "[]");
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray9), "[]");
    }

    @Test
    public void test0768() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0768");
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
        messageHeader1.add("AuthenticationHeader: prefer null", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str11);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertNotNull(strMap20);
        org.junit.Assert.assertNotNull(strMap21);
    }

    @Test
    public void test0769() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0769");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = com.quakearts.rest.client.net.ProgressMonitor.getDefault();
        com.quakearts.rest.client.net.ProgressSource progressSource1 = null;
        progressMonitor0.updateProgress(progressSource1);
        int int3 = progressMonitor0.getProgressUpdateThreshold();
        org.junit.Assert.assertNotNull(progressMonitor0);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 8192 + "'", int3 == 8192);
    }

    @Test
    public void test0770() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0770");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray1 = posterOutputStream0.toByteArray();
        posterOutputStream0.write((int) 'p');
        posterOutputStream0.write((int) '4');
        com.quakearts.rest.client.net.HttpCapture httpCapture6 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream7 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture6);
        posterOutputStream0.reset();
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray1), "[]");
    }

    @Test
    public void test0771() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0771");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.getValue(503);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap3 = messageHeader0.getHeaders();
        messageHeader0.prepend("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]", "{}");
        java.lang.String str8 = messageHeader0.getValue(303);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strMap3);
        org.junit.Assert.assertNull(str8);
    }

    @Test
    public void test0772() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0772");
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
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap15 = httpsURLConnectionImpl3.getHeaderFields();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
        org.junit.Assert.assertNotNull(strMap15);
    }

    @Test
    public void test0773() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0773");
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
        java.lang.String str50 = messageHeader28.toString();
        java.lang.String str52 = messageHeader28.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long43 + "' != '" + (-1L) + "'", long43 == (-1L));
        org.junit.Assert.assertNull(str48);
        org.junit.Assert.assertNull(str52);
    }

    @Test
    public void test0774() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0774");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        httpsURLConnectionImpl3.setReadTimeout(10);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap11 = httpsURLConnectionImpl3.getRequestProperties();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 100);
        boolean boolean14 = httpsURLConnectionImpl3.getAllowUserInteraction();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNotNull(strMap11);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test0775() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0775");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.connect();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test0776() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0776");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findValue(305);
        java.lang.String str5 = headerParser1.findValue((int) (byte) 2);
        java.lang.String str8 = headerParser1.findValue("AuthenticationHeader: prefer null", "{size=10 nkeys=1  {hi!} }");
        java.util.Iterator<java.lang.String> strItor9 = headerParser1.values();
        java.lang.String str10 = headerParser1.toString();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "{size=10 nkeys=1  {hi!} }" + "'", str8, "{size=10 nkeys=1  {hi!} }");
        org.junit.Assert.assertNotNull(strItor9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "{size=10 nkeys=1  {hi!} }" + "'", str10, "{size=10 nkeys=1  {hi!} }");
    }

    @Test
    public void test0777() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0777");
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
        com.quakearts.rest.client.net.HttpCapture httpCapture39 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream40 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) keepAliveStream36, httpCapture39);
        java.net.URL uRL41 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource43 = new com.quakearts.rest.client.net.ProgressSource(uRL41, "");
        long long44 = progressSource43.getExpected();
        boolean boolean45 = progressSource43.connected();
        com.quakearts.rest.client.net.MeteredStream meteredStream47 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) httpCaptureInputStream40, progressSource43, (long) 410);
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + false + "'", boolean38 == false);
        org.junit.Assert.assertTrue("'" + long44 + "' != '" + (-1L) + "'", long44 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean45 + "' != '" + false + "'", boolean45 == false);
    }

    @Test
    public void test0778() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0778");
        java.security.Principal principal1 = null;
        boolean boolean2 = com.quakearts.rest.client.net.https.HostnameChecker.match("GET", principal1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test0779() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0779");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.getValue(503);
        messageHeader0.prepend("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {hi!} }");
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap6 = messageHeader0.getHeaders();
        int int8 = messageHeader0.getKey("hi!");
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strMap6);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + (-1) + "'", int8 == (-1));
    }

    @Test
    public void test0780() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0780");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        java.net.URL uRL6 = null;
        java.net.Proxy proxy7 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler8 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl9 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL6, proxy7, httpsHandler8);
        javax.net.ssl.HostnameVerifier hostnameVerifier10 = httpsURLConnectionImpl9.getHostnameVerifier();
        boolean boolean11 = httpsURLConnectionImpl9.getDoInput();
        httpsURLConnectionImpl9.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream14 = httpsURLConnectionImpl9.getErrorStream();
        java.net.URL uRL15 = httpsURLConnectionImpl9.getURL();
        boolean boolean16 = httpsURLConnectionImpl9.getDoInput();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap17 = httpsURLConnectionImpl9.getRequestProperties();
        httpURLConnectionImpl5.authObj((java.lang.Object) httpsURLConnectionImpl9);
        long long19 = httpsURLConnectionImpl9.getLastModified();
        long long20 = httpsURLConnectionImpl9.getIfModifiedSince();
        httpsURLConnectionImpl9.setDoOutput(false);
        org.junit.Assert.assertNotNull(hostnameVerifier10);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertNull(inputStream14);
        org.junit.Assert.assertNull(uRL15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertNotNull(strMap17);
        org.junit.Assert.assertTrue("'" + long19 + "' != '" + 0L + "'", long19 == 0L);
        org.junit.Assert.assertTrue("'" + long20 + "' != '" + 0L + "'", long20 == 0L);
    }

    @Test
    public void test0781() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0781");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler5 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl6 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL3, proxy4, httpsHandler5);
        javax.net.ssl.HostnameVerifier hostnameVerifier7 = httpsURLConnectionImpl6.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier8 = httpsURLConnectionImpl6.getHostnameVerifier();
        httpsURLConnectionImpl6.setConnectTimeout((int) ' ');
        java.net.CacheRequest cacheRequest11 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpsURLConnectionImpl6);
        httpsURLConnectionImpl6.disconnect();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap13 = httpsURLConnectionImpl6.getRequestProperties();
        httpsURLConnectionImpl6.setFixedLengthStreamingMode(404);
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertNotNull(strMap13);
    }

    @Test
    public void test0782() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0782");
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
        boolean boolean50 = chunkedInputStream49.hurry();
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long43 + "' != '" + (-1L) + "'", long43 == (-1L));
        org.junit.Assert.assertNull(str48);
        org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + false + "'", boolean50 == false);
    }

    @Test
    public void test0783() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0783");
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
        // The following exception was thrown during execution in test generation
        try {
            int int46 = keepAliveStream36.available();
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
    }

    @Test
    public void test0784() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0784");
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
        // The following exception was thrown during execution in test generation
        try {
            chunkedInputStream41.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + false + "'", boolean42 == false);
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + false + "'", boolean43 == false);
    }

    @Test
    public void test0785() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0785");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        boolean boolean6 = httpsURLConnectionImpl3.getDefaultUseCaches();
        java.lang.String str7 = httpsURLConnectionImpl3.getRequestMethod();
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "GET" + "'", str7, "GET");
    }

    @Test
    public void test0786() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0786");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        posterOutputStream0.write(204);
        com.quakearts.rest.client.net.HttpCapture httpCapture4 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream5 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture4);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = posterOutputStream0.toString("{size=10 nkeys=1  {hi!} }");
            org.junit.Assert.fail("Expected exception of type java.io.UnsupportedEncodingException; message: {size=10 nkeys=1  {hi!} }");
        } catch (java.io.UnsupportedEncodingException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0787() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0787");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpClient httpClient3 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader4 = new com.quakearts.rest.client.net.MessageHeader();
        java.io.InputStream inputStream5 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture6 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream7 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream5, httpCapture6);
        com.quakearts.rest.client.net.HttpCapture httpCapture8 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream9 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream5, httpCapture8);
        messageHeader4.parseHeader(inputStream5);
        messageHeader4.add("GET", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.util.Iterator<java.lang.String> strItor15 = messageHeader4.multiValueIterator("");
        messageHeader4.add("GET", "GET");
        java.lang.String[] strArray23 = new java.lang.String[] { "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "", "content/unknown", "{size=10 nkeys=1  {hi!} }" };
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap24 = messageHeader4.getHeaders(strArray23);
        messageHeader4.remove("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader4.add("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "hi!");
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream30 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) httpCaptureInputStream2, httpClient3, messageHeader4);
        boolean boolean31 = chunkedInputStream30.hurry();
        com.quakearts.rest.client.net.HttpClient httpClient32 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader33 = null;
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream34 = new com.quakearts.rest.client.net.ChunkedInputStream((java.io.InputStream) chunkedInputStream30, httpClient32, messageHeader33);
        org.junit.Assert.assertNotNull(strItor15);
        org.junit.Assert.assertNotNull(strArray23);
        org.junit.Assert.assertNotNull(strMap24);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
    }

    @Test
    public void test0788() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0788");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = httpURLConnectionImpl5.getHeaderFieldKey((int) ' ');
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
    public void test0789() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0789");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        messageHeader0.set("content/unknown", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.io.InputStream inputStream4 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture5 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream6 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream4, httpCapture5);
        messageHeader0.parseHeader(inputStream4);
        java.lang.String str9 = messageHeader0.findValue("hi!");
        messageHeader0.set("{size=10 nkeys=1  {hi!} }", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        messageHeader0.set(413, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        org.junit.Assert.assertNull(str9);
    }

    @Test
    public void test0790() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0790");
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
        java.net.URL uRL17 = httpsURLConnectionImpl5.getURL();
        long long20 = httpsURLConnectionImpl5.getHeaderFieldDate("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", (long) 409);
        // The following exception was thrown during execution in test generation
        try {
            java.security.Principal principal21 = httpsURLConnectionImpl5.getLocalPrincipal();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertNull(uRL17);
        org.junit.Assert.assertTrue("'" + long20 + "' != '" + 409L + "'", long20 == 409L);
    }

    @Test
    public void test0791() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0791");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.io.InputStream inputStream1 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream3 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture2);
        com.quakearts.rest.client.net.HttpCapture httpCapture4 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream5 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture4);
        messageHeader0.parseHeader(inputStream1);
        messageHeader0.add("GET", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.util.Iterator<java.lang.String> strItor11 = messageHeader0.multiValueIterator("");
        messageHeader0.add("GET", "GET");
        java.lang.String[] strArray19 = new java.lang.String[] { "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "", "content/unknown", "{size=10 nkeys=1  {hi!} }" };
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap20 = messageHeader0.getHeaders(strArray19);
        messageHeader0.remove("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader0.setIfNotSet("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        java.io.InputStream inputStream26 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture27 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream28 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream26, httpCapture27);
        com.quakearts.rest.client.net.HttpCapture httpCapture29 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream30 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream26, httpCapture29);
        java.net.URL uRL31 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource33 = new com.quakearts.rest.client.net.ProgressSource(uRL31, "");
        long long34 = progressSource33.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream36 = new com.quakearts.rest.client.net.MeteredStream(inputStream26, progressSource33, (long) (byte) 1);
        java.net.URL uRL37 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource39 = new com.quakearts.rest.client.net.ProgressSource(uRL37, "");
        progressSource39.close();
        boolean boolean41 = progressSource39.connected();
        progressSource39.finishTracking();
        java.net.URL uRL43 = null;
        com.quakearts.rest.client.net.ProgressSource.State state46 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent49 = new com.quakearts.rest.client.net.ProgressEvent(progressSource39, uRL43, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state46, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource50 = new com.quakearts.rest.client.net.ProgressSource(progressSource39);
        com.quakearts.rest.client.net.MeteredStream meteredStream52 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream36, progressSource39, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor53 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL54 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource56 = new com.quakearts.rest.client.net.ProgressSource(uRL54, "");
        long long57 = progressSource56.getExpected();
        progressMonitor53.registerSource(progressSource56);
        java.lang.String str59 = progressSource56.toString();
        com.quakearts.rest.client.net.HttpClient httpClient61 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream62 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream36, progressSource56, (long) 203, httpClient61);
        boolean boolean63 = keepAliveStream62.markSupported();
        boolean boolean64 = keepAliveStream62.markSupported();
        java.lang.String str65 = java.net.URLConnection.guessContentTypeFromStream((java.io.InputStream) keepAliveStream62);
        com.quakearts.rest.client.net.HttpCapture httpCapture66 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream67 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) keepAliveStream62, httpCapture66);
        boolean boolean68 = keepAliveStream62.markSupported();
        // The following exception was thrown during execution in test generation
        try {
            messageHeader0.parseHeader((java.io.InputStream) keepAliveStream62);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strItor11);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertNotNull(strMap20);
        org.junit.Assert.assertTrue("'" + long34 + "' != '" + (-1L) + "'", long34 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + false + "'", boolean41 == false);
        org.junit.Assert.assertTrue("'" + long57 + "' != '" + (-1L) + "'", long57 == (-1L));
        org.junit.Assert.assertEquals("'" + str59 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str59, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean63 + "' != '" + false + "'", boolean63 == false);
        org.junit.Assert.assertTrue("'" + boolean64 + "' != '" + false + "'", boolean64 == false);
        org.junit.Assert.assertNull(str65);
        org.junit.Assert.assertTrue("'" + boolean68 + "' != '" + false + "'", boolean68 == false);
    }

    @Test
    public void test0792() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0792");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.disconnect();
        int int8 = httpsURLConnectionImpl3.getConnectTimeout();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode((int) (byte) 10);
        java.lang.String str12 = httpsURLConnectionImpl3.getRequestProperty("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj13 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0793() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0793");
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
        java.lang.String str15 = httpsURLConnectionImpl6.getContentEncoding();
        httpsURLConnectionImpl6.setDoOutput(false);
        // The following exception was thrown during execution in test generation
        try {
            java.security.cert.Certificate[] certificateArray18 = httpsURLConnectionImpl6.getServerCertificates();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
        org.junit.Assert.assertNull(str15);
    }

    @Test
    public void test0794() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0794");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        javax.net.ssl.SSLSocketFactory sSLSocketFactory9 = httpsURLConnectionImpl3.getSSLSocketFactory();
        httpsURLConnectionImpl3.setUseCaches(false);
        int int12 = httpsURLConnectionImpl3.getConnectTimeout();
        java.net.URL uRL13 = httpsURLConnectionImpl3.getURL();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertNotNull(sSLSocketFactory9);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
        org.junit.Assert.assertNull(uRL13);
    }

    @Test
    public void test0795() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0795");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource.State state9 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent12 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL6, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state9, (long) 300, (-1L));
        long long13 = progressEvent12.getExpected();
        com.quakearts.rest.client.net.ProgressSource.State state14 = progressEvent12.getState();
        java.lang.Object obj15 = progressEvent12.getSource();
        java.lang.Object obj16 = progressEvent12.getSource();
        long long17 = progressEvent12.getProgress();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-1L) + "'", long13 == (-1L));
        org.junit.Assert.assertNull(state14);
        org.junit.Assert.assertNotNull(obj15);
        org.junit.Assert.assertEquals(obj15.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj15), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj15), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNotNull(obj16);
        org.junit.Assert.assertEquals(obj16.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj16), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj16), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 300L + "'", long17 == 300L);
    }

    @Test
    public void test0796() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0796");
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
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap60 = httpsURLConnectionImpl3.getHeaderFields();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 201);
        // The following exception was thrown during execution in test generation
        try {
            int int63 = httpsURLConnectionImpl3.getResponseCode();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
        org.junit.Assert.assertTrue("'" + long25 + "' != '" + (-1L) + "'", long25 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertTrue("'" + long48 + "' != '" + (-1L) + "'", long48 == (-1L));
        org.junit.Assert.assertEquals("'" + str50 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str50, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str57);
        org.junit.Assert.assertTrue("'" + boolean59 + "' != '" + false + "'", boolean59 == false);
        org.junit.Assert.assertNotNull(strMap60);
    }

    @Test
    public void test0797() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0797");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpClient httpClient1 = null;
        com.quakearts.rest.client.net.MessageHeader messageHeader2 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str4 = messageHeader2.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.lang.String str6 = messageHeader2.getValue((int) (byte) -1);
        com.quakearts.rest.client.net.ChunkedInputStream chunkedInputStream7 = new com.quakearts.rest.client.net.ChunkedInputStream(inputStream0, httpClient1, messageHeader2);
        java.io.InputStream inputStream8 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture9 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream10 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream8, httpCapture9);
        com.quakearts.rest.client.net.HttpCapture httpCapture11 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream12 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream8, httpCapture11);
        java.net.URL uRL13 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource15 = new com.quakearts.rest.client.net.ProgressSource(uRL13, "");
        long long16 = progressSource15.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream18 = new com.quakearts.rest.client.net.MeteredStream(inputStream8, progressSource15, (long) (byte) 1);
        java.net.URL uRL19 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource21 = new com.quakearts.rest.client.net.ProgressSource(uRL19, "");
        progressSource21.close();
        boolean boolean23 = progressSource21.connected();
        progressSource21.finishTracking();
        java.net.URL uRL25 = null;
        com.quakearts.rest.client.net.ProgressSource.State state28 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent31 = new com.quakearts.rest.client.net.ProgressEvent(progressSource21, uRL25, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state28, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource32 = new com.quakearts.rest.client.net.ProgressSource(progressSource21);
        com.quakearts.rest.client.net.MeteredStream meteredStream34 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream18, progressSource21, (long) 32);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor35 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL36 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource38 = new com.quakearts.rest.client.net.ProgressSource(uRL36, "");
        long long39 = progressSource38.getExpected();
        progressMonitor35.registerSource(progressSource38);
        java.lang.String str41 = progressSource38.toString();
        com.quakearts.rest.client.net.HttpClient httpClient43 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream44 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream18, progressSource38, (long) 203, httpClient43);
        boolean boolean45 = keepAliveStream44.markSupported();
        keepAliveStream44.mark(505);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor48 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL49 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource51 = new com.quakearts.rest.client.net.ProgressSource(uRL49, "");
        long long52 = progressSource51.getExpected();
        progressMonitor48.registerSource(progressSource51);
        java.lang.String str54 = progressSource51.getContentType();
        com.quakearts.rest.client.net.MeteredStream meteredStream56 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) keepAliveStream44, progressSource51, (long) (byte) 10);
        // The following exception was thrown during execution in test generation
        try {
            messageHeader2.parseHeader((java.io.InputStream) keepAliveStream44);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertTrue("'" + long16 + "' != '" + (-1L) + "'", long16 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertTrue("'" + long39 + "' != '" + (-1L) + "'", long39 == (-1L));
        org.junit.Assert.assertEquals("'" + str41 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str41, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean45 + "' != '" + false + "'", boolean45 == false);
        org.junit.Assert.assertTrue("'" + long52 + "' != '" + (-1L) + "'", long52 == (-1L));
        org.junit.Assert.assertEquals("'" + str54 + "' != '" + "content/unknown" + "'", str54, "content/unknown");
    }

    @Test
    public void test0798() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0798");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream2 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream2.flush();
        posterOutputStream0.writeTo((java.io.OutputStream) posterOutputStream2);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream5 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream5.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream7 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream7.reset();
        posterOutputStream7.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream10 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray11 = posterOutputStream10.toByteArray();
        byte[] byteArray12 = posterOutputStream10.toByteArray();
        posterOutputStream7.write(byteArray12);
        posterOutputStream5.write(byteArray12);
        // The following exception was thrown during execution in test generation
        try {
            posterOutputStream0.write(byteArray12, 501, (int) (byte) 100);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray11), "[]");
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray12), "[]");
    }

    @Test
    public void test0799() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0799");
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
        java.net.URL uRL27 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource29 = new com.quakearts.rest.client.net.ProgressSource(uRL27, "");
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(progressSource29);
        com.quakearts.rest.client.net.MeteredStream meteredStream32 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource29, (long) 505);
        // The following exception was thrown during execution in test generation
        try {
            long long34 = meteredStream32.skip((long) 502);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0800() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0800");
        com.quakearts.rest.client.net.URLConnectionImpl.setProxiedHost("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test0801() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0801");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        int int9 = httpsURLConnectionImpl3.getReadTimeout();
        // The following exception was thrown during execution in test generation
        try {
            int int10 = httpsURLConnectionImpl3.getResponseCode();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
    }

    @Test
    public void test0802() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0802");
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
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setUseCaches(false);
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission15 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "GET" + "'", str11, "GET");
    }

    @Test
    public void test0803() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0803");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue9 = authCacheImpl0.get("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "content/unknown");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue11 = null;
        // The following exception was thrown during execution in test generation
        try {
            authCacheImpl0.put("", authCacheValue11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(authCacheValue3);
        org.junit.Assert.assertNull(authCacheValue6);
        org.junit.Assert.assertNull(authCacheValue9);
    }

    @Test
    public void test0804() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0804");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setUseCaches(false);
        java.lang.String str8 = httpsURLConnectionImpl3.getRequestMethod();
        // The following exception was thrown during execution in test generation
        try {
            long long9 = httpsURLConnectionImpl3.getExpiration();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "GET" + "'", str8, "GET");
    }

    @Test
    public void test0805() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0805");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        long long12 = httpsURLConnectionImpl3.getHeaderFieldDate("{size=10 nkeys=1  {hi!} }", 0L);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
    }

    @Test
    public void test0806() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0806");
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
        boolean boolean16 = httpsURLConnectionImpl6.getDefaultUseCaches();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap17 = httpsURLConnectionImpl6.getHeaderFields();
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertNotNull(strMap17);
    }

    @Test
    public void test0807() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0807");
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
        keepAliveStream36.mark(202);
        // The following exception was thrown during execution in test generation
        try {
            int int46 = keepAliveStream36.available();
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
    }

    @Test
    public void test0808() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0808");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        posterOutputStream0.flush();
        int int3 = posterOutputStream0.size();
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test0809() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0809");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.getValue(503);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap4 = messageHeader1.getHeaders();
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader5 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        com.quakearts.rest.client.net.HeaderParser headerParser6 = authenticationHeader5.headerParser();
        java.lang.String str7 = authenticationHeader5.scheme();
        java.lang.String str8 = authenticationHeader5.toString();
        java.lang.String str9 = authenticationHeader5.toString();
        java.lang.String str10 = authenticationHeader5.scheme();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNotNull(strMap4);
        org.junit.Assert.assertNull(headerParser6);
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "AuthenticationHeader: prefer null" + "'", str8, "AuthenticationHeader: prefer null");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "AuthenticationHeader: prefer null" + "'", str9, "AuthenticationHeader: prefer null");
        org.junit.Assert.assertNull(str10);
    }

    @Test
    public void test0810() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0810");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.disconnect();
        int int8 = httpsURLConnectionImpl3.getConnectTimeout();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = httpsURLConnectionImpl3.getContentEncoding();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test0811() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0811");
        java.lang.Boolean boolean1 = com.quakearts.rest.client.net.NetProperties.getBoolean("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
    }

    @Test
    public void test0812() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0812");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        boolean boolean10 = httpURLConnectionImpl5.streaming();
        java.io.InputStream inputStream11 = httpURLConnectionImpl5.getErrorStream();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str13 = httpURLConnectionImpl5.getHeaderField("content/unknown");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNull(inputStream11);
    }

    @Test
    public void test0813() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0813");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream2 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream2.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream4 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream4.flush();
        posterOutputStream2.writeTo((java.io.OutputStream) posterOutputStream4);
        com.quakearts.rest.client.net.HttpCapture httpCapture7 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream8 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream2, httpCapture7);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream9 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray10 = posterOutputStream9.toByteArray();
        byte[] byteArray11 = posterOutputStream9.toByteArray();
        httpCaptureOutputStream8.write(byteArray11);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream13 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray14 = posterOutputStream13.toByteArray();
        httpCaptureOutputStream8.write(byteArray14);
        // The following exception was thrown during execution in test generation
        try {
            posterOutputStream0.write(byteArray14, 408, 305);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray10), "[]");
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray11), "[]");
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray14), "[]");
    }

    @Test
    public void test0814() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0814");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        int int10 = httpURLConnectionImpl5.getReadTimeout();
        httpURLConnectionImpl5.setAuthenticationProperty("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "{size=10 nkeys=1  {hi!} }");
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
    }

    @Test
    public void test0815() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0815");
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
        httpsURLConnectionImpl3.disconnect();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNotNull(hostnameVerifier11);
    }

    @Test
    public void test0816() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0816");
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
        java.lang.Object obj12 = httpURLConnectionImpl5.authObj();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNull(obj12);
    }

    @Test
    public void test0817() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0817");
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
        java.net.URL uRL13 = httpURLConnectionImpl5.getURL();
        httpURLConnectionImpl5.setDefaultUseCaches(true);
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream16 = httpURLConnectionImpl5.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNull(uRL13);
    }

    @Test
    public void test0818() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0818");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue9 = authCacheImpl0.get("{size=10 nkeys=1  {get} }", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertNull(authCacheValue3);
        org.junit.Assert.assertNull(authCacheValue6);
        org.junit.Assert.assertNull(authCacheValue9);
    }

    @Test
    public void test0819() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0819");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("GET");
        java.lang.String str2 = headerParser1.toString();
        java.lang.String str5 = headerParser1.findValue("AuthenticationHeader: prefer null", "{}");
        int int8 = headerParser1.findInt("{size=10 nkeys=1  {hi!} }", 414);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "{size=10 nkeys=1  {get} }" + "'", str2, "{size=10 nkeys=1  {get} }");
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "{}" + "'", str5, "{}");
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 414 + "'", int8 == 414);
    }

    @Test
    public void test0820() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0820");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap1 = messageHeader0.getHeaders();
        messageHeader0.setIfNotSet("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        int int6 = messageHeader0.getKey("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap7 = messageHeader0.getHeaders();
        org.junit.Assert.assertNotNull(strMap1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + (-1) + "'", int6 == (-1));
        org.junit.Assert.assertNotNull(strMap7);
    }

    @Test
    public void test0821() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0821");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        java.net.URL uRL3 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource5 = new com.quakearts.rest.client.net.ProgressSource(uRL3, "");
        long long6 = progressSource5.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream8 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource5, (long) (short) 0);
        progressSource5.setContentType("AuthenticationHeader: prefer null");
        java.net.URL uRL11 = progressSource5.getURL();
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + (-1L) + "'", long6 == (-1L));
        org.junit.Assert.assertNull(uRL11);
    }

    @Test
    public void test0822() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0822");
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
        messageHeader1.set("{size=10 nkeys=1  {get} }", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(strArray21);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + true + "'", boolean23 == true);
    }

    @Test
    public void test0823() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0823");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        httpsURLConnectionImpl3.setConnectTimeout((int) (short) 0);
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        long long15 = httpsURLConnectionImpl3.getHeaderFieldDate("GET", (long) 410);
        boolean boolean16 = httpsURLConnectionImpl3.getDoInput();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + 410L + "'", long15 == 410L);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
    }

    @Test
    public void test0824() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0824");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue8 = authCacheImpl0.get("{}", "");
        org.junit.Assert.assertNull(authCacheValue3);
        org.junit.Assert.assertNull(authCacheValue8);
    }

    @Test
    public void test0825() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0825");
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
        com.quakearts.rest.client.net.HttpCapture httpCapture39 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream40 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) keepAliveStream36, httpCapture39);
        httpCaptureInputStream40.mark(411);
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + false + "'", boolean38 == false);
    }

    @Test
    public void test0826() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0826");
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
        java.io.InputStream inputStream12 = httpURLConnectionImpl5.getErrorStream();
        boolean boolean13 = httpURLConnectionImpl5.usingProxy();
        java.lang.Object obj14 = httpURLConnectionImpl5.authObj();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertNull(inputStream11);
        org.junit.Assert.assertNull(inputStream12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNull(obj14);
    }

    @Test
    public void test0827() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0827");
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
            httpsURLConnectionImpl3.setFixedLengthStreamingMode((long) 502);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Chunked encoding streaming mode set");
        } catch (java.lang.IllegalStateException e) {
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
    public void test0828() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0828");
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
        java.lang.Object obj20 = httpURLConnectionImpl5.authObj();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 0 + "'", int16 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState17 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState17.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertNull(obj20);
    }

    @Test
    public void test0829() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0829");
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
        java.lang.String str43 = messageHeader38.getValue((int) ' ');
        int int45 = messageHeader38.getKey("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
        org.junit.Assert.assertNull(str43);
        org.junit.Assert.assertTrue("'" + int45 + "' != '" + (-1) + "'", int45 == (-1));
    }

    @Test
    public void test0830() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0830");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.addRequestProperty("", "hi!");
        long long11 = httpsURLConnectionImpl3.getExpiration();
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + 0L + "'", long11 == 0L);
    }

    @Test
    public void test0831() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0831");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        boolean boolean8 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.setReadTimeout(414);
        boolean boolean11 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
    }

    @Test
    public void test0832() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0832");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray1 = posterOutputStream0.toByteArray();
        posterOutputStream0.write((int) 'p');
        posterOutputStream0.write((int) '4');
        java.lang.String str6 = posterOutputStream0.toString();
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray1), "[]");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "p4" + "'", str6, "p4");
    }

    @Test
    public void test0833() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0833");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        // The following exception was thrown during execution in test generation
        try {
            java.security.cert.Certificate[] certificateArray8 = httpsURLConnectionImpl3.getLocalCertificates();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0834() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0834");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        long long3 = progressSource2.getProgress();
        progressSource2.close();
        org.junit.Assert.assertTrue("'" + long3 + "' != '" + 0L + "'", long3 == 0L);
    }

    @Test
    public void test0835() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0835");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray1 = posterOutputStream0.toByteArray();
        posterOutputStream0.write((int) 'p');
        posterOutputStream0.write((int) '4');
        com.quakearts.rest.client.net.HttpCapture httpCapture6 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream7 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture6);
        posterOutputStream0.write(201);
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray1), "[]");
    }

    @Test
    public void test0836() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0836");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        boolean boolean4 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        java.net.URL uRL5 = httpsURLConnectionImpl3.getURL();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource8 = new com.quakearts.rest.client.net.ProgressSource(uRL6, "");
        progressSource8.close();
        boolean boolean10 = progressSource8.connected();
        progressSource8.finishTracking();
        java.net.URL uRL12 = null;
        com.quakearts.rest.client.net.ProgressSource.State state15 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent18 = new com.quakearts.rest.client.net.ProgressEvent(progressSource8, uRL12, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state15, (long) 300, (-1L));
        long long19 = progressEvent18.getExpected();
        com.quakearts.rest.client.net.ProgressSource.State state20 = progressEvent18.getState();
        com.quakearts.rest.client.net.ProgressSource.State state21 = progressEvent18.getState();
        com.quakearts.rest.client.net.ProgressSource.State state22 = progressEvent18.getState();
        boolean boolean23 = httpsURLConnectionImpl3.equals((java.lang.Object) state22);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
        org.junit.Assert.assertNull(uRL5);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + long19 + "' != '" + (-1L) + "'", long19 == (-1L));
        org.junit.Assert.assertNull(state20);
        org.junit.Assert.assertNull(state21);
        org.junit.Assert.assertNull(state22);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
    }

    @Test
    public void test0837() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0837");
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
            long long39 = keepAliveStream37.skip(304L);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test0838() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0838");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        posterOutputStream0.write(204);
        com.quakearts.rest.client.net.HttpCapture httpCapture4 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream5 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture4);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream5.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0839() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0839");
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
        java.lang.String str50 = messageHeader28.toString();
        messageHeader28.prepend("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long43 + "' != '" + (-1L) + "'", long43 == (-1L));
        org.junit.Assert.assertNull(str48);
    }

    @Test
    public void test0840() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0840");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler5 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl6 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL3, proxy4, httpsHandler5);
        javax.net.ssl.HostnameVerifier hostnameVerifier7 = httpsURLConnectionImpl6.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier8 = httpsURLConnectionImpl6.getHostnameVerifier();
        httpsURLConnectionImpl6.setConnectTimeout((int) ' ');
        java.net.CacheRequest cacheRequest11 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpsURLConnectionImpl6);
        httpsURLConnectionImpl6.setIfModifiedSince(100L);
        boolean boolean14 = httpsURLConnectionImpl6.getUseCaches();
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
// flaky:         org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
    }

    @Test
    public void test0841() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0841");
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
            int int51 = meteredStream10.available();
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
    public void test0842() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0842");
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
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap60 = httpsURLConnectionImpl3.getHeaderFields();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 201);
        int int65 = httpsURLConnectionImpl3.getHeaderFieldInt("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", 414);
        java.lang.String str66 = httpsURLConnectionImpl3.toString();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
// flaky:         org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
        org.junit.Assert.assertTrue("'" + long25 + "' != '" + (-1L) + "'", long25 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertTrue("'" + long48 + "' != '" + (-1L) + "'", long48 == (-1L));
        org.junit.Assert.assertEquals("'" + str50 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str50, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str57);
        org.junit.Assert.assertTrue("'" + boolean59 + "' != '" + false + "'", boolean59 == false);
        org.junit.Assert.assertNotNull(strMap60);
        org.junit.Assert.assertTrue("'" + int65 + "' != '" + 414 + "'", int65 == 414);
        org.junit.Assert.assertEquals("'" + str66 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str66, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
    }

    @Test
    public void test0843() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0843");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        int int9 = httpsURLConnectionImpl3.getConnectTimeout();
        java.io.InputStream inputStream10 = httpsURLConnectionImpl3.getErrorStream();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj11 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertNull(inputStream10);
    }

    @Test
    public void test0844() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0844");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        boolean boolean9 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.setDefaultUseCaches(true);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
// flaky:         org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0845() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0845");
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
        // The following exception was thrown during execution in test generation
        try {
            int int44 = meteredStream43.available();
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
    }

    @Test
    public void test0846() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0846");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource.State state9 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent12 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL6, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state9, (long) 300, (-1L));
        long long13 = progressEvent12.getExpected();
        com.quakearts.rest.client.net.ProgressSource.State state14 = progressEvent12.getState();
        java.lang.Object obj15 = progressEvent12.getSource();
        java.lang.Object obj16 = progressEvent12.getSource();
        java.lang.Object obj17 = progressEvent12.getSource();
        java.lang.String str18 = progressEvent12.toString();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-1L) + "'", long13 == (-1L));
        org.junit.Assert.assertNull(state14);
        org.junit.Assert.assertNotNull(obj15);
        org.junit.Assert.assertEquals(obj15.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj15), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj15), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNotNull(obj16);
        org.junit.Assert.assertEquals(obj16.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj16), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj16), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNotNull(obj17);
        org.junit.Assert.assertEquals(obj17.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj17), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj17), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]" + "'", str18, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
    }

    @Test
    public void test0847() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0847");
        java.net.URLConnection.setDefaultRequestProperty("", "{size=10 nkeys=1  {get} }");
    }

    @Test
    public void test0848() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0848");
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
        java.io.InputStream inputStream12 = httpURLConnectionImpl5.getErrorStream();
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream13 = httpURLConnectionImpl5.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertNull(inputStream11);
        org.junit.Assert.assertNull(inputStream12);
    }

    @Test
    public void test0849() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0849");
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
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean40 = keepAliveStream36.hurry();
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
    }

    @Test
    public void test0850() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0850");
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
        boolean boolean13 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertTrue("'" + long11 + "' != '" + 0L + "'", long11 == 0L);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
    }

    @Test
    public void test0851() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0851");
        com.quakearts.rest.client.net.KeepAliveCache keepAliveCache0 = new com.quakearts.rest.client.net.KeepAliveCache();
        java.lang.Object obj1 = keepAliveCache0.clone();
        java.net.URL uRL2 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource4 = new com.quakearts.rest.client.net.ProgressSource(uRL2, "");
        boolean boolean5 = progressSource4.connected();
        boolean boolean6 = keepAliveCache0.containsValue((java.lang.Object) boolean5);
        com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker8 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) 2);
        boolean boolean9 = keepAliveCache0.containsValue((java.lang.Object) hostnameChecker8);
        keepAliveCache0.clear();
        com.quakearts.rest.client.net.HttpClient httpClient11 = null;
        java.net.URL uRL12 = null;
        java.net.Proxy proxy13 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler14 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl15 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL12, proxy13, httpsHandler14);
        javax.net.ssl.HostnameVerifier hostnameVerifier16 = httpsURLConnectionImpl15.getHostnameVerifier();
        boolean boolean17 = httpsURLConnectionImpl15.getDoInput();
        httpsURLConnectionImpl15.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl15.addRequestProperty("", "hi!");
        httpsURLConnectionImpl15.setInstanceFollowRedirects(false);
        // The following exception was thrown during execution in test generation
        try {
            keepAliveCache0.remove(httpClient11, (java.lang.Object) false);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(obj1);
        org.junit.Assert.assertEquals(obj1.toString(), "{}");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj1), "{}");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj1), "{}");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(hostnameChecker8);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(hostnameVerifier16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + true + "'", boolean17 == true);
    }

    @Test
    public void test0852() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0852");
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
        keepAliveStream36.mark(300);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean42 = keepAliveStream36.hurry();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
    }

    @Test
    public void test0853() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0853");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        boolean boolean4 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setDoOutput(false);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = httpsURLConnectionImpl3.getCipherSuite();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
    }

    @Test
    public void test0854() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0854");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler7 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl8 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL3, proxy4, httpHandler7);
        int int9 = httpURLConnectionImpl8.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState10 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl8.setTunnelState(tunnelState10);
        boolean boolean12 = httpURLConnectionImpl8.streaming();
        java.io.InputStream inputStream13 = httpURLConnectionImpl8.getErrorStream();
        java.io.InputStream inputStream14 = httpURLConnectionImpl8.getErrorStream();
        java.io.InputStream inputStream15 = httpURLConnectionImpl8.getErrorStream();
        httpURLConnectionImpl8.setReadTimeout(206);
        java.net.CacheRequest cacheRequest18 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpURLConnectionImpl8);
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream19 = httpURLConnectionImpl8.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState10 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState10.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNull(inputStream13);
        org.junit.Assert.assertNull(inputStream14);
        org.junit.Assert.assertNull(inputStream15);
        org.junit.Assert.assertNull(cacheRequest18);
    }

    @Test
    public void test0855() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0855");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        long long10 = httpsURLConnectionImpl3.getDate();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 402);
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setRequestProperty("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "content/unknown");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Already connected");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + long10 + "' != '" + 0L + "'", long10 == 0L);
    }

    @Test
    public void test0856() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0856");
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
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission44 = httpURLConnectionImpl5.getPermission();
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
    public void test0857() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0857");
        java.security.Principal principal1 = null;
        boolean boolean2 = com.quakearts.rest.client.net.https.HostnameChecker.match("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]", principal1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test0858() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0858");
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
    public void test0859() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0859");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        java.net.URL uRL9 = httpsURLConnectionImpl3.getURL();
        long long10 = httpsURLConnectionImpl3.getDate();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertTrue("'" + long10 + "' != '" + 0L + "'", long10 == 0L);
    }

    @Test
    public void test0860() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0860");
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
        // The following exception was thrown during execution in test generation
        try {
            long long47 = httpCaptureInputStream45.skip((long) 400);
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
    }

    @Test
    public void test0861() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0861");
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
        java.lang.String str12 = httpsURLConnectionImpl3.getHeaderField("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setReadTimeout((int) (byte) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: timeouts can't be negative");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0862() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0862");
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
        java.net.URL uRL27 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource29 = new com.quakearts.rest.client.net.ProgressSource(uRL27, "");
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(progressSource29);
        com.quakearts.rest.client.net.MeteredStream meteredStream32 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource29, (long) 505);
        // The following exception was thrown during execution in test generation
        try {
            meteredStream32.reset();
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: Resetting to an invalid mark");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0863() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0863");
        java.lang.String str1 = com.quakearts.rest.client.net.MessageHeader.canonicalID("hi!");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "hi!" + "'", str1, "hi!");
    }

    @Test
    public void test0864() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0864");
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
        com.quakearts.rest.client.net.HttpCapture httpCapture39 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream40 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) keepAliveStream36, httpCapture39);
        // The following exception was thrown during execution in test generation
        try {
            keepAliveStream36.close();
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
    }

    @Test
    public void test0865() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0865");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        boolean boolean10 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean11 = httpsURLConnectionImpl3.getDefaultUseCaches();
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission12 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0866() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0866");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler5 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl6 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL3, proxy4, httpsHandler5);
        javax.net.ssl.HostnameVerifier hostnameVerifier7 = httpsURLConnectionImpl6.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier8 = httpsURLConnectionImpl6.getHostnameVerifier();
        httpsURLConnectionImpl6.setConnectTimeout((int) ' ');
        java.net.CacheRequest cacheRequest11 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpsURLConnectionImpl6);
        java.lang.String str13 = httpsURLConnectionImpl6.getRequestProperty("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        httpsURLConnectionImpl6.setChunkedStreamingMode(412);
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertNull(str13);
    }

    @Test
    public void test0867() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0867");
        com.quakearts.rest.client.net.KeepAliveCache keepAliveCache0 = new com.quakearts.rest.client.net.KeepAliveCache();
        java.lang.Object obj1 = keepAliveCache0.clone();
        java.net.URL uRL2 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource4 = new com.quakearts.rest.client.net.ProgressSource(uRL2, "");
        boolean boolean5 = progressSource4.connected();
        boolean boolean6 = keepAliveCache0.containsValue((java.lang.Object) boolean5);
        com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker8 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) 2);
        boolean boolean9 = keepAliveCache0.containsValue((java.lang.Object) hostnameChecker8);
        java.security.cert.X509Certificate x509Certificate11 = null;
        // The following exception was thrown during execution in test generation
        try {
            hostnameChecker8.match("com.quakearts.rest.client.net.ProgressEvent[url=null, method=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], state=UPDATE, content-type=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], progress=501, expected=304]", x509Certificate11);
            org.junit.Assert.fail("Expected exception of type java.security.cert.CertificateException; message: Illegal given domain name: com.quakearts.rest.client.net.ProgressEvent[url=null, method=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], state=UPDATE, content-type=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], progress=501, expected=304]");
        } catch (java.security.cert.CertificateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(obj1);
        org.junit.Assert.assertEquals(obj1.toString(), "{}");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj1), "{}");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj1), "{}");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(hostnameChecker8);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0868() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0868");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader0.set(406, "content/unknown", "hi!");
        messageHeader0.set(402, "GET", "");
        messageHeader0.set((int) (short) 1, "GET", "{size=10 nkeys=1  {hi!} }");
        java.lang.String str16 = messageHeader0.getValue(203);
        java.lang.String str18 = messageHeader0.getKey(301);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str16);
        org.junit.Assert.assertNull(str18);
    }

    @Test
    public void test0869() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0869");
        com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain registeredDomain1 = com.quakearts.rest.client.net.RegisteredDomainProducer.registeredDomain("{size=10 nkeys=1  {hi!} }");
        org.junit.Assert.assertNull(registeredDomain1);
    }

    @Test
    public void test0870() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0870");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        boolean boolean10 = httpURLConnectionImpl5.streaming();
        httpURLConnectionImpl5.addRequestProperty("", "hi!");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor14 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int15 = progressMonitor14.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor16 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL17 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource19 = new com.quakearts.rest.client.net.ProgressSource(uRL17, "");
        long long20 = progressSource19.getExpected();
        progressMonitor16.registerSource(progressSource19);
        progressMonitor14.updateProgress(progressSource19);
        progressSource19.beginTracking();
        long long24 = progressSource19.getProgress();
        httpURLConnectionImpl5.authObj((java.lang.Object) progressSource19);
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl5.connect();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 8192 + "'", int15 == 8192);
        org.junit.Assert.assertTrue("'" + long20 + "' != '" + (-1L) + "'", long20 == (-1L));
        org.junit.Assert.assertTrue("'" + long24 + "' != '" + 0L + "'", long24 == 0L);
    }

    @Test
    public void test0871() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0871");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler2 = null;
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl3 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler2);
        boolean boolean4 = httpURLConnectionImpl3.getDoOutput();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test0872() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0872");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.setConnectTimeout(403);
        httpsURLConnectionImpl3.setConnectTimeout(407);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0873() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0873");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader0.set(406, "content/unknown", "hi!");
        messageHeader0.set(402, "GET", "");
        java.util.Iterator<java.lang.String> strItor12 = messageHeader0.multiValueIterator("p4");
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strItor12);
    }

    @Test
    public void test0874() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0874");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        boolean boolean8 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.setReadTimeout(303);
        java.lang.String str12 = httpsURLConnectionImpl3.getHeaderFieldKey(400);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0875() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0875");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader1.set(406, "content/unknown", "hi!");
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader8 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        java.io.InputStream inputStream9 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture10 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream11 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream9, httpCapture10);
        com.quakearts.rest.client.net.HttpCapture httpCapture12 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream13 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream9, httpCapture12);
        java.net.URL uRL14 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource16 = new com.quakearts.rest.client.net.ProgressSource(uRL14, "");
        long long17 = progressSource16.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream19 = new com.quakearts.rest.client.net.MeteredStream(inputStream9, progressSource16, (long) (byte) 1);
        java.net.URL uRL20 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource22 = new com.quakearts.rest.client.net.ProgressSource(uRL20, "");
        progressSource22.close();
        boolean boolean24 = progressSource22.connected();
        progressSource22.finishTracking();
        java.net.URL uRL26 = null;
        com.quakearts.rest.client.net.ProgressSource.State state29 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent32 = new com.quakearts.rest.client.net.ProgressEvent(progressSource22, uRL26, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state29, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource33 = new com.quakearts.rest.client.net.ProgressSource(progressSource22);
        com.quakearts.rest.client.net.MeteredStream meteredStream35 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream19, progressSource22, (long) 32);
        // The following exception was thrown during execution in test generation
        try {
            messageHeader1.mergeHeader((java.io.InputStream) meteredStream19);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + (-1L) + "'", long17 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
    }

    @Test
    public void test0876() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0876");
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
        httpsURLConnectionImpl3.setReadTimeout(205);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj15 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0877() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0877");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.disconnect();
        int int8 = httpsURLConnectionImpl3.getConnectTimeout();
        httpsURLConnectionImpl3.setDefaultUseCaches(true);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str11 = httpsURLConnectionImpl3.getContentEncoding();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test0878() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0878");
        java.net.ResponseCache responseCache0 = null;
        java.net.ResponseCache.setDefault(responseCache0);
    }

    @Test
    public void test0879() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0879");
        com.quakearts.rest.client.net.URLConnectionImpl.setProxiedHost("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test0880() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0880");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 8192);
        boolean boolean11 = httpsURLConnectionImpl3.getDoInput();
        int int12 = httpsURLConnectionImpl3.getReadTimeout();
        int int13 = httpsURLConnectionImpl3.getConnectTimeout();
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission14 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
    }

    @Test
    public void test0881() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0881");
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
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream44 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream44.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream46 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream46.reset();
        posterOutputStream46.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream49 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray50 = posterOutputStream49.toByteArray();
        byte[] byteArray51 = posterOutputStream49.toByteArray();
        posterOutputStream46.write(byteArray51);
        posterOutputStream44.write(byteArray51);
        // The following exception was thrown during execution in test generation
        try {
            int int56 = meteredStream10.read(byteArray51, 0, 401);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
        org.junit.Assert.assertNotNull(byteArray50);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray50), "[]");
        org.junit.Assert.assertNotNull(byteArray51);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray51), "[]");
    }

    @Test
    public void test0882() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0882");
        com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker1 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) 2);
        java.security.cert.X509Certificate x509Certificate3 = null;
        // The following exception was thrown during execution in test generation
        try {
            hostnameChecker1.match("", x509Certificate3, false);
            org.junit.Assert.fail("Expected exception of type java.security.cert.CertificateException; message: Illegal given domain name: ");
        } catch (java.security.cert.CertificateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameChecker1);
    }

    @Test
    public void test0883() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0883");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findValue(305);
        java.lang.String str5 = headerParser1.findValue((int) (byte) 2);
        java.lang.String str8 = headerParser1.findValue("AuthenticationHeader: prefer null", "{size=10 nkeys=1  {hi!} }");
        java.util.Iterator<java.lang.String> strItor9 = headerParser1.values();
        java.util.Iterator<java.lang.String> strItor10 = headerParser1.values();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "{size=10 nkeys=1  {hi!} }" + "'", str8, "{size=10 nkeys=1  {hi!} }");
        org.junit.Assert.assertNotNull(strItor9);
        org.junit.Assert.assertNotNull(strItor10);
    }

    @Test
    public void test0884() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0884");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int1 = progressMonitor0.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressMonitor.setDefault(progressMonitor0);
        java.util.List<com.quakearts.rest.client.net.ProgressSource> progressSourceList3 = progressMonitor0.getProgressSources();
        java.net.URL uRL4 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource6 = new com.quakearts.rest.client.net.ProgressSource(uRL4, "");
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(progressSource6);
        progressMonitor0.updateProgress(progressSource6);
        java.net.URL uRL9 = null;
        boolean boolean11 = progressMonitor0.shouldMeterInput(uRL9, "content/unknown");
        java.util.List<com.quakearts.rest.client.net.ProgressSource> progressSourceList12 = progressMonitor0.getProgressSources();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8192 + "'", int1 == 8192);
        org.junit.Assert.assertNotNull(progressSourceList3);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(progressSourceList12);
    }

    @Test
    public void test0885() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0885");
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
        boolean boolean43 = chunkedInputStream41.hurry();
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + false + "'", boolean42 == false);
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + false + "'", boolean43 == false);
    }

    @Test
    public void test0886() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0886");
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
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream13 = httpURLConnectionImpl5.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
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
    public void test0887() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0887");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("hi!", (int) (short) 1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        java.lang.Object obj6 = httpURLConnectionImpl5.authObj();
        org.junit.Assert.assertNull(obj6);
    }

    @Test
    public void test0888() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0888");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findValue(305);
        java.lang.String str5 = headerParser1.findKey(415);
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HeaderParser headerParser8 = headerParser1.subsequence(302, (int) (byte) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: invalid start or end");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str5);
    }

    @Test
    public void test0889() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0889");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setUseCaches(false);
        boolean boolean8 = httpsURLConnectionImpl3.getDoOutput();
        boolean boolean9 = httpsURLConnectionImpl3.getDefaultUseCaches();
        java.io.InputStream inputStream10 = httpsURLConnectionImpl3.getErrorStream();
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        httpsURLConnectionImpl3.disconnect();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
// flaky:         org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
    }

    @Test
    public void test0890() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0890");
        boolean boolean1 = com.quakearts.rest.client.net.URLConnectionImpl.isProxiedHost("com.quakearts.rest.client.net.ProgressEvent[url=null, method=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], state=UPDATE, content-type=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], progress=501, expected=304]");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test0891() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0891");
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
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap60 = httpsURLConnectionImpl3.getHeaderFields();
        java.lang.String str61 = httpsURLConnectionImpl3.getContentEncoding();
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
        org.junit.Assert.assertNotNull(strMap60);
        org.junit.Assert.assertNull(str61);
    }

    @Test
    public void test0892() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0892");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        boolean boolean4 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setInstanceFollowRedirects(false);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
    }

    @Test
    public void test0893() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0893");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.io.InputStream inputStream1 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream3 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture2);
        com.quakearts.rest.client.net.HttpCapture httpCapture4 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream5 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture4);
        messageHeader0.parseHeader(inputStream1);
        messageHeader0.setIfNotSet("p4", "p4");
    }

    @Test
    public void test0894() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0894");
        java.lang.Boolean boolean1 = com.quakearts.rest.client.net.NetProperties.getBoolean("");
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
    }

    @Test
    public void test0895() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0895");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        int int9 = httpURLConnectionImpl5.getConnectTimeout();
        boolean boolean10 = httpURLConnectionImpl5.streaming();
        httpURLConnectionImpl5.setFixedLengthStreamingMode((long) 205);
        boolean boolean13 = httpURLConnectionImpl5.usingProxy();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }

    @Test
    public void test0896() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0896");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setUseCaches(false);
        boolean boolean8 = httpsURLConnectionImpl3.getDoOutput();
        boolean boolean9 = httpsURLConnectionImpl3.getDefaultUseCaches();
        java.io.InputStream inputStream10 = httpsURLConnectionImpl3.getErrorStream();
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        // The following exception was thrown during execution in test generation
        try {
            int int13 = httpsURLConnectionImpl3.getResponseCode();
            org.junit.Assert.fail("Expected exception of type com.quakearts.rest.client.exception.HttpClientRuntimeException; message: java.lang.NullPointerException");
        } catch (com.quakearts.rest.client.exception.HttpClientRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
    }

    @Test
    public void test0897() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0897");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setRequestMethod("{}");
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: Invalid HTTP method: {}");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
    }

    @Test
    public void test0898() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0898");
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
    }

    @Test
    public void test0899() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0899");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.io.InputStream inputStream1 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream3 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture2);
        com.quakearts.rest.client.net.HttpCapture httpCapture4 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream5 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture4);
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource8 = new com.quakearts.rest.client.net.ProgressSource(uRL6, "");
        long long9 = progressSource8.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream11 = new com.quakearts.rest.client.net.MeteredStream(inputStream1, progressSource8, (long) (byte) 1);
        java.net.URL uRL12 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource14 = new com.quakearts.rest.client.net.ProgressSource(uRL12, "");
        progressSource14.close();
        boolean boolean16 = progressSource14.connected();
        progressSource14.finishTracking();
        java.net.URL uRL18 = null;
        com.quakearts.rest.client.net.ProgressSource.State state21 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent24 = new com.quakearts.rest.client.net.ProgressEvent(progressSource14, uRL18, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state21, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource25 = new com.quakearts.rest.client.net.ProgressSource(progressSource14);
        com.quakearts.rest.client.net.MeteredStream meteredStream27 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream11, progressSource14, (long) 32);
        java.net.URL uRL28 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(uRL28, "");
        com.quakearts.rest.client.net.ProgressSource progressSource31 = new com.quakearts.rest.client.net.ProgressSource(progressSource30);
        com.quakearts.rest.client.net.MeteredStream meteredStream33 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream11, progressSource30, (long) 505);
        // The following exception was thrown during execution in test generation
        try {
            messageHeader0.parseHeader((java.io.InputStream) meteredStream33);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long9 + "' != '" + (-1L) + "'", long9 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
    }

    @Test
    public void test0900() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0900");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        boolean boolean3 = progressSource2.connected();
        progressSource2.close();
        java.lang.String str5 = progressSource2.toString();
        long long6 = progressSource2.getExpected();
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]" + "'", str5, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + (-1L) + "'", long6 == (-1L));
    }

    @Test
    public void test0901() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0901");
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
        com.quakearts.rest.client.net.HttpCapture httpCapture39 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream40 = new com.quakearts.rest.client.net.HttpCaptureInputStream((java.io.InputStream) keepAliveStream36, httpCapture39);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream41 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream41.reset();
        posterOutputStream41.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream44 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray45 = posterOutputStream44.toByteArray();
        byte[] byteArray46 = posterOutputStream44.toByteArray();
        posterOutputStream41.write(byteArray46);
        // The following exception was thrown during execution in test generation
        try {
            int int50 = httpCaptureInputStream40.read(byteArray46, 403, (int) 'a');
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
        org.junit.Assert.assertNotNull(byteArray45);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray45), "[]");
        org.junit.Assert.assertNotNull(byteArray46);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray46), "[]");
    }

    @Test
    public void test0902() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0902");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        int int9 = httpsURLConnectionImpl3.getReadTimeout();
        boolean boolean10 = httpsURLConnectionImpl3.getDoOutput();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test0903() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0903");
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
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean39 = keepAliveStream36.hurry();
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
    }

    @Test
    public void test0904() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0904");
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
        int int14 = httpsURLConnectionImpl3.getReadTimeout();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
    }

    @Test
    public void test0905() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0905");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL1 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL1, "");
        long long4 = progressSource3.getExpected();
        progressMonitor0.registerSource(progressSource3);
        progressSource3.finishTracking();
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(progressSource3);
        org.junit.Assert.assertTrue("'" + long4 + "' != '" + (-1L) + "'", long4 == (-1L));
    }

    @Test
    public void test0906() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0906");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        int int12 = httpsURLConnectionImpl3.getHeaderFieldInt("GET", 403);
        java.lang.String str14 = httpsURLConnectionImpl3.getHeaderFieldKey((int) (short) 0);
        boolean boolean15 = httpsURLConnectionImpl3.getDoOutput();
        boolean boolean16 = httpsURLConnectionImpl3.getDoInput();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 403 + "'", int12 == 403);
        org.junit.Assert.assertNull(str14);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
    }

    @Test
    public void test0907() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0907");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        java.net.URL uRL6 = null;
        java.net.Proxy proxy7 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler8 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl9 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL6, proxy7, httpsHandler8);
        javax.net.ssl.HostnameVerifier hostnameVerifier10 = httpsURLConnectionImpl9.getHostnameVerifier();
        boolean boolean11 = httpsURLConnectionImpl9.getDoInput();
        httpsURLConnectionImpl9.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream14 = httpsURLConnectionImpl9.getErrorStream();
        java.net.URL uRL15 = httpsURLConnectionImpl9.getURL();
        boolean boolean16 = httpsURLConnectionImpl9.getDoInput();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap17 = httpsURLConnectionImpl9.getRequestProperties();
        httpURLConnectionImpl5.authObj((java.lang.Object) httpsURLConnectionImpl9);
        long long19 = httpsURLConnectionImpl9.getLastModified();
        httpsURLConnectionImpl9.setFixedLengthStreamingMode((int) 'p');
        httpsURLConnectionImpl9.setAllowUserInteraction(false);
        org.junit.Assert.assertNotNull(hostnameVerifier10);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertNull(inputStream14);
        org.junit.Assert.assertNull(uRL15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertNotNull(strMap17);
        org.junit.Assert.assertTrue("'" + long19 + "' != '" + 0L + "'", long19 == 0L);
    }

    @Test
    public void test0908() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0908");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray1 = posterOutputStream0.toByteArray();
        byte[] byteArray2 = posterOutputStream0.toByteArray();
        posterOutputStream0.close();
        posterOutputStream0.write(405);
        int int6 = posterOutputStream0.size();
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray1), "[]");
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray2), "[]");
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
    }

    @Test
    public void test0909() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0909");
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
        httpsURLConnectionImpl3.setIfModifiedSince((long) 'p');
        org.junit.Assert.assertNotNull(hostnameVerifier4);
// flaky:         org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0910() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0910");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.addRequestProperty("", "hi!");
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap11 = httpsURLConnectionImpl3.getHeaderFields();
        int int12 = httpsURLConnectionImpl3.getConnectTimeout();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNotNull(strMap11);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
    }

    @Test
    public void test0911() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0911");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str24 = httpsURLConnectionImpl5.getResponseMessage();
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
    }

    @Test
    public void test0912() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0912");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.getValue(503);
        messageHeader0.prepend("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {hi!} }");
        messageHeader0.prepend("", "hi!");
        org.junit.Assert.assertNull(str2);
    }

    @Test
    public void test0913() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0913");
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
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap17 = httpsURLConnectionImpl3.getRequestProperties();
        boolean boolean18 = httpsURLConnectionImpl3.getAllowUserInteraction();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
        org.junit.Assert.assertTrue("'" + long16 + "' != '" + 408L + "'", long16 == 408L);
        org.junit.Assert.assertNotNull(strMap17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
    }

    @Test
    public void test0914() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0914");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        boolean boolean10 = httpURLConnectionImpl5.streaming();
        int int11 = httpURLConnectionImpl5.getReadTimeout();
        boolean boolean12 = httpURLConnectionImpl5.usingProxy();
        boolean boolean13 = httpURLConnectionImpl5.getDoOutput();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }

    @Test
    public void test0915() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0915");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        javax.net.ssl.HostnameVerifier hostnameVerifier6 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setConnectTimeout(415);
        boolean boolean9 = httpsURLConnectionImpl3.getDoOutput();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 'p');
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0916() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0916");
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
        java.io.InputStream inputStream12 = httpURLConnectionImpl5.getErrorStream();
        boolean boolean13 = httpURLConnectionImpl5.usingProxy();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str15 = httpURLConnectionImpl5.getHeaderField((int) (byte) 2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertNull(inputStream11);
        org.junit.Assert.assertNull(inputStream12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }

    @Test
    public void test0917() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0917");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.addRequestProperty("", "hi!");
        int int13 = httpsURLConnectionImpl3.getHeaderFieldInt("hi!", 100);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(404);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 100 + "'", int13 == 100);
    }

    @Test
    public void test0918() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0918");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        posterOutputStream0.write(204);
        com.quakearts.rest.client.net.HttpCapture httpCapture4 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream5 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture4);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream6 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream6.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream8 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream8.flush();
        posterOutputStream6.writeTo((java.io.OutputStream) posterOutputStream8);
        com.quakearts.rest.client.net.HttpCapture httpCapture11 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream12 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream6, httpCapture11);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream13 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray14 = posterOutputStream13.toByteArray();
        byte[] byteArray15 = posterOutputStream13.toByteArray();
        httpCaptureOutputStream12.write(byteArray15);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream17 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray18 = posterOutputStream17.toByteArray();
        httpCaptureOutputStream12.write(byteArray18);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream5.write(byteArray18, (int) '4', 400);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 52");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray14);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray14), "[]");
        org.junit.Assert.assertNotNull(byteArray15);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray15), "[]");
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray18), "[]");
    }

    @Test
    public void test0919() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0919");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        java.net.URL uRL9 = httpsURLConnectionImpl3.getURL();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap10 = httpsURLConnectionImpl3.getHeaderFields();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertNotNull(strMap10);
    }

    @Test
    public void test0920() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0920");
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
    public void test0921() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0921");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader1.set(406, "content/unknown", "hi!");
        messageHeader1.prepend("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "");
        java.lang.String[] strArray21 = new java.lang.String[] { "{size=10 nkeys=1  {hi!} }", "GET", "{size=10 nkeys=1  {get} }", "{size=10 nkeys=1  {hi!} }", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "AuthenticationHeader: prefer null", "AuthenticationHeader: prefer null", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "hi!", "{size=10 nkeys=1  {hi!} }" };
        java.util.LinkedHashSet<java.lang.String> strSet22 = new java.util.LinkedHashSet<java.lang.String>();
        boolean boolean23 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strSet22, strArray21);
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader24 = new com.quakearts.rest.client.net.http.AuthenticationHeader("", messageHeader1, (java.util.Set<java.lang.String>) strSet22);
        int int26 = messageHeader1.getKey("");
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNotNull(strArray21);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + true + "'", boolean23 == true);
        org.junit.Assert.assertTrue("'" + int26 + "' != '" + (-1) + "'", int26 == (-1));
    }

    @Test
    public void test0922() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0922");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        java.lang.String str6 = httpsURLConnectionImpl3.getRequestMethod();
        // The following exception was thrown during execution in test generation
        try {
            long long7 = httpsURLConnectionImpl3.getContentLengthLong();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "GET" + "'", str6, "GET");
    }

    @Test
    public void test0923() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0923");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        long long8 = httpsURLConnectionImpl3.getDate();
        boolean boolean9 = httpsURLConnectionImpl3.getDefaultUseCaches();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + 0L + "'", long8 == 0L);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0924() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0924");
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
        // The following exception was thrown during execution in test generation
        try {
            long long45 = keepAliveStream36.skip((long) 8192);
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
    }

    @Test
    public void test0925() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0925");
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
        java.io.InputStream inputStream12 = httpURLConnectionImpl5.getErrorStream();
        boolean boolean13 = httpURLConnectionImpl5.usingProxy();
        boolean boolean14 = httpURLConnectionImpl5.streaming();
        boolean boolean15 = httpURLConnectionImpl5.streaming();
        httpURLConnectionImpl5.setDoInput(false);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertNull(inputStream11);
        org.junit.Assert.assertNull(inputStream12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0926() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0926");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        httpsURLConnectionImpl3.setReadTimeout(10);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap11 = httpsURLConnectionImpl3.getRequestProperties();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 100);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj14 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNotNull(strMap11);
    }

    @Test
    public void test0927() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0927");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource.State state9 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent12 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL6, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state9, (long) 300, (-1L));
        long long13 = progressEvent12.getExpected();
        com.quakearts.rest.client.net.ProgressSource.State state14 = progressEvent12.getState();
        java.lang.Object obj15 = progressEvent12.getSource();
        java.lang.Object obj16 = progressEvent12.getSource();
        java.lang.Object obj17 = progressEvent12.getSource();
        java.net.URL uRL18 = progressEvent12.getURL();
        java.lang.String str19 = progressEvent12.getContentType();
        long long20 = progressEvent12.getExpected();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-1L) + "'", long13 == (-1L));
        org.junit.Assert.assertNull(state14);
        org.junit.Assert.assertNotNull(obj15);
        org.junit.Assert.assertEquals(obj15.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj15), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj15), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNotNull(obj16);
        org.junit.Assert.assertEquals(obj16.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj16), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj16), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNotNull(obj17);
        org.junit.Assert.assertEquals(obj17.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj17), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj17), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(uRL18);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str19, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertTrue("'" + long20 + "' != '" + (-1L) + "'", long20 == (-1L));
    }

    @Test
    public void test0928() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0928");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        boolean boolean3 = progressSource2.connected();
        progressSource2.close();
        java.lang.String str5 = progressSource2.toString();
        com.quakearts.rest.client.net.ProgressSource progressSource6 = new com.quakearts.rest.client.net.ProgressSource(progressSource2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]" + "'", str5, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test0929() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0929");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        boolean boolean6 = httpURLConnectionImpl5.usingProxy();
        httpURLConnectionImpl5.setDoInput(false);
        httpURLConnectionImpl5.setUseCaches(false);
        httpURLConnectionImpl5.setRequestMethod("GET");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0930() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0930");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 8192);
        boolean boolean11 = httpsURLConnectionImpl3.getDoInput();
        int int12 = httpsURLConnectionImpl3.getReadTimeout();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
    }

    @Test
    public void test0931() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0931");
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
        long long16 = httpsURLConnectionImpl6.getExpiration();
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long16 + "' != '" + 0L + "'", long16 == 0L);
    }

    @Test
    public void test0932() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0932");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream3 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture2);
        posterOutputStream0.write(200);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = posterOutputStream0.toString("{}");
            org.junit.Assert.fail("Expected exception of type java.io.UnsupportedEncodingException; message: {}");
        } catch (java.io.UnsupportedEncodingException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0933() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0933");
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
    }

    @Test
    public void test0934() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0934");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int1 = progressMonitor0.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressMonitor.setDefault(progressMonitor0);
        com.quakearts.rest.client.net.ProgressListener progressListener3 = null;
        progressMonitor0.removeProgressListener(progressListener3);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8192 + "'", int1 == 8192);
    }

    @Test
    public void test0935() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0935");
        javax.net.ssl.SSLSocketFactory sSLSocketFactory0 = javax.net.ssl.HttpsURLConnection.getDefaultSSLSocketFactory();
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sSLSocketFactory0);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sSLSocketFactory0);
        org.junit.Assert.assertNotNull(sSLSocketFactory0);
    }

    @Test
    public void test0936() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0936");
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
        httpsURLConnectionImpl3.setReadTimeout(205);
        java.lang.String str15 = httpsURLConnectionImpl3.toString();
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        httpsURLConnectionImpl3.setDoInput(false);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str15, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
    }

    @Test
    public void test0937() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0937");
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
            keepAliveStream37.reset();
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: mark/reset not supported");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test0938() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0938");
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
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0939() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0939");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.getValue(503);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap4 = messageHeader1.getHeaders();
        messageHeader1.set("{size=10 nkeys=1  {hi!} }", "content/unknown");
        com.quakearts.rest.client.net.MessageHeader messageHeader9 = new com.quakearts.rest.client.net.MessageHeader();
        messageHeader9.set("content/unknown", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.io.InputStream inputStream13 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture14 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream15 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream13, httpCapture14);
        messageHeader9.parseHeader(inputStream13);
        java.lang.String str18 = messageHeader9.findValue("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        java.lang.String[] strArray29 = new java.lang.String[] { "GET", "{size=10 nkeys=1  {hi!} }", "GET", "AuthenticationHeader: prefer null", "{size=10 nkeys=1  {get} }", "", "", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "content/unknown", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" };
        java.util.LinkedHashSet<java.lang.String> strSet30 = new java.util.LinkedHashSet<java.lang.String>();
        boolean boolean31 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strSet30, strArray29);
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader32 = new com.quakearts.rest.client.net.http.AuthenticationHeader("", messageHeader9, (java.util.Set<java.lang.String>) strSet30);
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader33 = new com.quakearts.rest.client.net.http.AuthenticationHeader("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]", messageHeader1, (java.util.Set<java.lang.String>) strSet30);
        boolean boolean34 = authenticationHeader33.hasPreferredParserPresent();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNotNull(strMap4);
        org.junit.Assert.assertNull(str18);
        org.junit.Assert.assertNotNull(strArray29);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + true + "'", boolean31 == true);
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + false + "'", boolean34 == false);
    }

    @Test
    public void test0940() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0940");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray1 = posterOutputStream0.toByteArray();
        java.lang.String str3 = posterOutputStream0.toString((int) (short) 10);
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray1), "[]");
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "" + "'", str3, "");
    }

    @Test
    public void test0941() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0941");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.getValue(503);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap4 = messageHeader1.getHeaders();
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader5 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        com.quakearts.rest.client.net.HeaderParser headerParser6 = authenticationHeader5.headerParser();
        java.lang.String str7 = authenticationHeader5.scheme();
        com.quakearts.rest.client.net.HeaderParser headerParser8 = authenticationHeader5.headerParser();
        com.quakearts.rest.client.net.HeaderParser headerParser9 = authenticationHeader5.headerParser();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNotNull(strMap4);
        org.junit.Assert.assertNull(headerParser6);
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertNull(headerParser8);
        org.junit.Assert.assertNull(headerParser9);
    }

    @Test
    public void test0942() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0942");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        boolean boolean10 = httpURLConnectionImpl5.streaming();
        java.io.InputStream inputStream11 = httpURLConnectionImpl5.getErrorStream();
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream12 = httpURLConnectionImpl5.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNull(inputStream11);
    }

    @Test
    public void test0943() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0943");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray1 = posterOutputStream0.toByteArray();
        byte[] byteArray2 = posterOutputStream0.toByteArray();
        posterOutputStream0.close();
        posterOutputStream0.close();
        posterOutputStream0.close();
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray1), "[]");
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray2), "[]");
    }

    @Test
    public void test0944() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0944");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader1.set(406, "content/unknown", "hi!");
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader8 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        boolean boolean9 = authenticationHeader8.hasPreferredParserPresent();
        com.quakearts.rest.client.net.HeaderParser headerParser10 = authenticationHeader8.headerParser();
        java.lang.String str11 = authenticationHeader8.raw();
        boolean boolean12 = authenticationHeader8.hasPreferredParserPresent();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(headerParser10);
        org.junit.Assert.assertNull(str11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test0945() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0945");
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
        // The following exception was thrown during execution in test generation
        try {
            int int48 = httpURLConnectionImpl5.getHeaderFieldInt("content/unknown", 204);
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
    public void test0946() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0946");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        boolean boolean8 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.setUseCaches(true);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0947() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0947");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.io.InputStream inputStream1 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream3 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture2);
        com.quakearts.rest.client.net.HttpCapture httpCapture4 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream5 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture4);
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource8 = new com.quakearts.rest.client.net.ProgressSource(uRL6, "");
        long long9 = progressSource8.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream11 = new com.quakearts.rest.client.net.MeteredStream(inputStream1, progressSource8, (long) (byte) 1);
        java.net.URL uRL12 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource14 = new com.quakearts.rest.client.net.ProgressSource(uRL12, "");
        progressSource14.close();
        boolean boolean16 = progressSource14.connected();
        progressSource14.finishTracking();
        java.net.URL uRL18 = null;
        com.quakearts.rest.client.net.ProgressSource.State state21 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent24 = new com.quakearts.rest.client.net.ProgressEvent(progressSource14, uRL18, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state21, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource25 = new com.quakearts.rest.client.net.ProgressSource(progressSource14);
        com.quakearts.rest.client.net.MeteredStream meteredStream27 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream11, progressSource14, (long) 32);
        // The following exception was thrown during execution in test generation
        try {
            messageHeader0.mergeHeader((java.io.InputStream) meteredStream11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long9 + "' != '" + (-1L) + "'", long9 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
    }

    @Test
    public void test0948() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0948");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.setConnectTimeout(403);
        // The following exception was thrown during execution in test generation
        try {
            java.security.cert.Certificate[] certificateArray9 = httpsURLConnectionImpl3.getLocalCertificates();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0949() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0949");
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
        httpsURLConnectionImpl3.setReadTimeout(402);
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream15 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.openConnectionCheckRedirects((java.net.URLConnection) httpsURLConnectionImpl3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
    }

    @Test
    public void test0950() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0950");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 8192);
        boolean boolean11 = httpsURLConnectionImpl3.getDoInput();
        int int12 = httpsURLConnectionImpl3.getReadTimeout();
        int int13 = httpsURLConnectionImpl3.getConnectTimeout();
        javax.net.ssl.HostnameVerifier hostnameVerifier14 = null;
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setHostnameVerifier(hostnameVerifier14);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: no HostnameVerifier specified");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
    }

    @Test
    public void test0951() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0951");
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
        long long12 = httpsURLConnectionImpl3.getIfModifiedSince();
        java.lang.String str14 = httpsURLConnectionImpl3.getRequestProperty("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=UPDATE, content-type=content/unknown, progress=0, expected=32]");
        long long17 = httpsURLConnectionImpl3.getHeaderFieldDate("p4", (long) 's');
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(strMap11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
        org.junit.Assert.assertNull(str14);
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 115L + "'", long17 == 115L);
    }

    @Test
    public void test0952() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0952");
        boolean boolean1 = com.quakearts.rest.client.net.IPAddressUtil.isIPv4LiteralAddress("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=UPDATE, content-type=content/unknown, progress=0, expected=32]");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test0953() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0953");
        java.lang.String str1 = java.net.URLConnection.getDefaultRequestProperty("content/unknown");
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test0954() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0954");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler5 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl6 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL3, proxy4, httpsHandler5);
        javax.net.ssl.HostnameVerifier hostnameVerifier7 = httpsURLConnectionImpl6.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier8 = httpsURLConnectionImpl6.getHostnameVerifier();
        httpsURLConnectionImpl6.setConnectTimeout((int) ' ');
        java.net.CacheRequest cacheRequest11 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpsURLConnectionImpl6);
        httpsURLConnectionImpl6.disconnect();
        httpsURLConnectionImpl6.setUseCaches(false);
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
    }

    @Test
    public void test0955() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0955");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int1 = progressMonitor0.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor2 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL3 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource5 = new com.quakearts.rest.client.net.ProgressSource(uRL3, "");
        long long6 = progressSource5.getExpected();
        progressMonitor2.registerSource(progressSource5);
        progressMonitor0.unregisterSource(progressSource5);
        java.util.List<com.quakearts.rest.client.net.ProgressSource> progressSourceList9 = progressMonitor0.getProgressSources();
        java.util.List<com.quakearts.rest.client.net.ProgressSource> progressSourceList10 = progressMonitor0.getProgressSources();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8192 + "'", int1 == 8192);
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + (-1L) + "'", long6 == (-1L));
        org.junit.Assert.assertNotNull(progressSourceList9);
        org.junit.Assert.assertNotNull(progressSourceList10);
    }

    @Test
    public void test0956() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0956");
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
        java.net.URL uRL27 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource29 = new com.quakearts.rest.client.net.ProgressSource(uRL27, "");
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(progressSource29);
        com.quakearts.rest.client.net.MeteredStream meteredStream32 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource29, (long) 505);
        // The following exception was thrown during execution in test generation
        try {
            long long34 = meteredStream10.skip((long) '4');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0957() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0957");
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
    }

    @Test
    public void test0958() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0958");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        java.net.CookieHandler cookieHandler10 = httpURLConnectionImpl5.getCookieHandler();
        // The following exception was thrown during execution in test generation
        try {
            long long13 = httpURLConnectionImpl5.getHeaderFieldLong("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", (long) '#');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(cookieHandler10);
    }

    @Test
    public void test0959() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0959");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findKey(305);
        java.lang.String str5 = headerParser1.findValue("{size=10 nkeys=1  {hi!} }");
        java.lang.String str6 = headerParser1.toString();
        int int9 = headerParser1.findInt("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]", 404);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "{size=10 nkeys=1  {hi!} }" + "'", str6, "{size=10 nkeys=1  {hi!} }");
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 404 + "'", int9 == 404);
    }

    @Test
    public void test0960() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0960");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = posterOutputStream0.toString("");
            org.junit.Assert.fail("Expected exception of type java.io.UnsupportedEncodingException; message: ");
        } catch (java.io.UnsupportedEncodingException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0961() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0961");
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
        java.net.URL uRL27 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource29 = new com.quakearts.rest.client.net.ProgressSource(uRL27, "");
        com.quakearts.rest.client.net.ProgressSource progressSource30 = new com.quakearts.rest.client.net.ProgressSource(progressSource29);
        com.quakearts.rest.client.net.MeteredStream meteredStream32 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream10, progressSource29, (long) 505);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean33 = meteredStream10.markSupported();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0962() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0962");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setUseCaches(false);
        boolean boolean8 = httpsURLConnectionImpl3.getDoOutput();
        long long9 = httpsURLConnectionImpl3.getIfModifiedSince();
        // The following exception was thrown during execution in test generation
        try {
            int int12 = httpsURLConnectionImpl3.getHeaderFieldInt("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", 502);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + long9 + "' != '" + 0L + "'", long9 == 0L);
    }

    @Test
    public void test0963() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0963");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.net.URL uRL3 = null;
        com.quakearts.rest.client.net.ProgressSource.State state6 = com.quakearts.rest.client.net.ProgressSource.State.UPDATE;
        com.quakearts.rest.client.net.ProgressEvent progressEvent9 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL3, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", state6, (long) 501, (long) 304);
        long long10 = progressEvent9.getExpected();
        java.lang.Object obj11 = progressEvent9.getSource();
        long long12 = progressEvent9.getProgress();
        org.junit.Assert.assertTrue("'" + state6 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.UPDATE + "'", state6.equals(com.quakearts.rest.client.net.ProgressSource.State.UPDATE));
        org.junit.Assert.assertTrue("'" + long10 + "' != '" + 304L + "'", long10 == 304L);
        org.junit.Assert.assertNotNull(obj11);
        org.junit.Assert.assertEquals(obj11.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj11), "com.quakearts.rest.client.net.ProgressSource[url=null, method=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj11), "com.quakearts.rest.client.net.ProgressSource[url=null, method=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 501L + "'", long12 == 501L);
    }

    @Test
    public void test0964() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0964");
        java.net.URL uRL0 = null;
        java.net.URL uRL5 = null;
        java.net.Proxy proxy6 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler9 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl10 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL5, proxy6, httpHandler9);
        int int11 = httpURLConnectionImpl10.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState12 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl10.setTunnelState(tunnelState12);
        boolean boolean14 = httpURLConnectionImpl10.streaming();
        java.io.InputStream inputStream15 = httpURLConnectionImpl10.getErrorStream();
        java.io.InputStream inputStream16 = httpURLConnectionImpl10.getErrorStream();
        java.io.InputStream inputStream17 = httpURLConnectionImpl10.getErrorStream();
        boolean boolean18 = httpURLConnectionImpl10.usingProxy();
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient19 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, "content/unknown", 201, false, (int) (byte) 100, httpURLConnectionImpl10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState12 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState12.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNull(inputStream15);
        org.junit.Assert.assertNull(inputStream16);
        org.junit.Assert.assertNull(inputStream17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
    }

    @Test
    public void test0965() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0965");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue8 = authCacheImpl0.get("content/unknown", "");
        org.junit.Assert.assertNull(authCacheValue3);
        org.junit.Assert.assertNull(authCacheValue8);
    }

    @Test
    public void test0966() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0966");
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
        java.net.URL uRL13 = httpURLConnectionImpl5.getURL();
        httpURLConnectionImpl5.setDefaultUseCaches(true);
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream16 = httpURLConnectionImpl5.getInputStream();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNull(uRL13);
    }

    @Test
    public void test0967() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0967");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream2 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream2.flush();
        posterOutputStream0.writeTo((java.io.OutputStream) posterOutputStream2);
        com.quakearts.rest.client.net.HttpCapture httpCapture5 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream6 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture5);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream7 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray8 = posterOutputStream7.toByteArray();
        byte[] byteArray9 = posterOutputStream7.toByteArray();
        httpCaptureOutputStream6.write(byteArray9);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream11 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray12 = posterOutputStream11.toByteArray();
        httpCaptureOutputStream6.write(byteArray12);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream6.flush();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray8), "[]");
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray9), "[]");
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray12), "[]");
    }

    @Test
    public void test0968() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0968");
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
        long long13 = httpsURLConnectionImpl3.getExpiration();
        java.net.URL uRL14 = null;
        java.net.Proxy proxy15 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler18 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl19 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL14, proxy15, httpHandler18);
        int int20 = httpURLConnectionImpl19.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState21 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl19.setTunnelState(tunnelState21);
        boolean boolean23 = httpURLConnectionImpl19.streaming();
        boolean boolean24 = httpURLConnectionImpl19.streaming();
        java.lang.Class<?> wildcardClass25 = httpURLConnectionImpl19.getClass();
        java.lang.Class[] classArray26 = new java.lang.Class[] { wildcardClass25 };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj27 = httpsURLConnectionImpl3.getContent(classArray26);
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(uRL12);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + 0L + "'", long13 == 0L);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + 0 + "'", int20 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState21 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState21.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNotNull(wildcardClass25);
        org.junit.Assert.assertNotNull(classArray26);
    }

    @Test
    public void test0969() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0969");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission10 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0970() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0970");
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
        java.net.URL uRL48 = httpURLConnectionImpl5.getURL();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNull(str18);
        org.junit.Assert.assertNotNull(strArray36);
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + true + "'", boolean38 == true);
        org.junit.Assert.assertNull(uRL48);
    }

    @Test
    public void test0971() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0971");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        boolean boolean10 = httpsURLConnectionImpl3.getDoInput();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap11 = httpsURLConnectionImpl3.getHeaderFields();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(strMap11);
    }

    @Test
    public void test0972() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0972");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.io.InputStream inputStream1 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream3 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture2);
        com.quakearts.rest.client.net.HttpCapture httpCapture4 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream5 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture4);
        messageHeader0.parseHeader(inputStream1);
        messageHeader0.reset();
    }

    @Test
    public void test0973() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0973");
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
        java.lang.String str44 = java.net.URLConnection.guessContentTypeFromStream((java.io.InputStream) chunkedInputStream41);
        // The following exception was thrown during execution in test generation
        try {
            int int45 = chunkedInputStream41.read();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + false + "'", boolean42 == false);
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + false + "'", boolean43 == false);
        org.junit.Assert.assertNull(str44);
    }

    @Test
    public void test0974() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0974");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream5 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream5.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture7 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream8 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream5, httpCapture7);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream9 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray10 = posterOutputStream9.toByteArray();
        httpCaptureOutputStream8.write(byteArray10);
        // The following exception was thrown during execution in test generation
        try {
            int int12 = httpCaptureInputStream4.read(byteArray10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray10), "[]");
    }

    @Test
    public void test0975() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0975");
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
        int int15 = httpURLConnectionImpl9.getReadTimeout();
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient16 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, proxy1, 0, true, httpURLConnectionImpl9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState11 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState11.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNull(inputStream14);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
    }

    @Test
    public void test0976() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0976");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource.State state9 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent12 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL6, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state9, (long) 300, (-1L));
        long long13 = progressEvent12.getExpected();
        com.quakearts.rest.client.net.ProgressSource.State state14 = progressEvent12.getState();
        java.lang.Object obj15 = progressEvent12.getSource();
        java.lang.String str16 = progressEvent12.toString();
        java.lang.Object obj17 = progressEvent12.getSource();
        java.lang.Object obj18 = progressEvent12.getSource();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-1L) + "'", long13 == (-1L));
        org.junit.Assert.assertNull(state14);
        org.junit.Assert.assertNotNull(obj15);
        org.junit.Assert.assertEquals(obj15.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj15), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj15), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]" + "'", str16, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        org.junit.Assert.assertNotNull(obj17);
        org.junit.Assert.assertEquals(obj17.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj17), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj17), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNotNull(obj18);
        org.junit.Assert.assertEquals(obj18.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj18), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj18), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test0977() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0977");
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
        boolean boolean14 = httpsURLConnectionImpl3.getUseCaches();
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setRequestMethod("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]");
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: Invalid HTTP method: com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test0978() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0978");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setChunkedStreamingMode(408);
        int int8 = httpsURLConnectionImpl3.getReadTimeout();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test0979() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0979");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue4 = authCacheImpl0.get("hi!", "GET");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = null;
        // The following exception was thrown during execution in test generation
        try {
            authCacheImpl0.put("{}", authCacheValue6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(authCacheValue4);
    }

    @Test
    public void test0980() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0980");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(412);
        long long10 = httpsURLConnectionImpl3.getHeaderFieldDate("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", 100L);
        java.lang.String str11 = httpsURLConnectionImpl3.getContentEncoding();
        org.junit.Assert.assertTrue("'" + long10 + "' != '" + 100L + "'", long10 == 100L);
        org.junit.Assert.assertNull(str11);
    }

    @Test
    public void test0981() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0981");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader1.set(406, "content/unknown", "hi!");
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader8 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        messageHeader1.set("AuthenticationHeader: prefer null", "{}");
        org.junit.Assert.assertNull(str3);
    }

    @Test
    public void test0982() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0982");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj18 = httpsURLConnectionImpl6.getContent();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + (-1L) + "'", long15 == (-1L));
        org.junit.Assert.assertNull(str16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
    }

    @Test
    public void test0983() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0983");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.getValue(503);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap4 = messageHeader1.getHeaders();
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader5 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        com.quakearts.rest.client.net.HeaderParser headerParser6 = authenticationHeader5.headerParser();
        com.quakearts.rest.client.net.HeaderParser headerParser7 = authenticationHeader5.headerParser();
        com.quakearts.rest.client.net.HeaderParser headerParser8 = authenticationHeader5.headerParser();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNotNull(strMap4);
        org.junit.Assert.assertNull(headerParser6);
        org.junit.Assert.assertNull(headerParser7);
        org.junit.Assert.assertNull(headerParser8);
    }

    @Test
    public void test0984() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0984");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource.State state9 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent12 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL6, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state9, (long) 300, (-1L));
        java.lang.Object obj13 = progressEvent12.getSource();
        java.lang.String str14 = progressEvent12.getMethod();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(obj13);
        org.junit.Assert.assertEquals(obj13.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj13), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj13), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "hi!" + "'", str14, "hi!");
    }

    @Test
    public void test0985() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0985");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = null;
        authCacheImpl0.remove("hi!", authCacheValue6);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue9 = null;
        authCacheImpl0.remove("p4", authCacheValue9);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue13 = authCacheImpl0.get("com.quakearts.rest.client.net.ProgressEvent[url=null, method=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], state=UPDATE, content-type=com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1], progress=501, expected=304]", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertNull(authCacheValue3);
        org.junit.Assert.assertNull(authCacheValue13);
    }

    @Test
    public void test0986() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0986");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.getValue(503);
        messageHeader1.add("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "");
        java.lang.String[] strArray11 = new java.lang.String[] { "", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" };
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap12 = messageHeader1.getHeaders(strArray11);
        com.quakearts.rest.client.net.MessageHeader messageHeader14 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str16 = messageHeader14.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader14.set(406, "content/unknown", "hi!");
        messageHeader14.prepend("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "");
        java.lang.String[] strArray34 = new java.lang.String[] { "{size=10 nkeys=1  {hi!} }", "GET", "{size=10 nkeys=1  {get} }", "{size=10 nkeys=1  {hi!} }", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "AuthenticationHeader: prefer null", "AuthenticationHeader: prefer null", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "hi!", "{size=10 nkeys=1  {hi!} }" };
        java.util.LinkedHashSet<java.lang.String> strSet35 = new java.util.LinkedHashSet<java.lang.String>();
        boolean boolean36 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strSet35, strArray34);
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader37 = new com.quakearts.rest.client.net.http.AuthenticationHeader("", messageHeader14, (java.util.Set<java.lang.String>) strSet35);
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader38 = new com.quakearts.rest.client.net.http.AuthenticationHeader("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", messageHeader1, (java.util.Set<java.lang.String>) strSet35);
        com.quakearts.rest.client.net.HeaderParser headerParser39 = authenticationHeader38.headerParser();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertNotNull(strMap12);
        org.junit.Assert.assertNull(str16);
        org.junit.Assert.assertNotNull(strArray34);
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + true + "'", boolean36 == true);
        org.junit.Assert.assertNull(headerParser39);
    }

    @Test
    public void test0987() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0987");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        int int10 = httpURLConnectionImpl5.getReadTimeout();
        boolean boolean11 = httpURLConnectionImpl5.usingProxy();
        httpURLConnectionImpl5.setIfModifiedSince((long) 100);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0988() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0988");
        boolean boolean1 = com.quakearts.rest.client.net.IPAddressUtil.isIPv6LiteralAddress("{size=10 nkeys=1  {get} }");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test0989() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0989");
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
            long long52 = meteredStream10.skip((long) (short) 100);
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
    public void test0990() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0990");
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
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean41 = keepAliveStream36.hurry();
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
    }

    @Test
    public void test0991() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0991");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        java.net.CookieHandler cookieHandler10 = httpURLConnectionImpl5.getCookieHandler();
        // The following exception was thrown during execution in test generation
        try {
            long long13 = httpURLConnectionImpl5.getHeaderFieldDate("hi!", (long) 305);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(cookieHandler10);
    }

    @Test
    public void test0992() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0992");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.disconnect();
        int int8 = httpsURLConnectionImpl3.getConnectTimeout();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 403);
        java.lang.String str12 = httpsURLConnectionImpl3.getRequestProperty("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0993() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0993");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.ResponseCache.setDefault((java.net.ResponseCache) responseCacheImpl0);
        java.net.URI uRI2 = null;
        java.net.URL uRL3 = null;
        java.net.Proxy proxy4 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler5 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl6 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL3, proxy4, httpsHandler5);
        javax.net.ssl.HostnameVerifier hostnameVerifier7 = httpsURLConnectionImpl6.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier8 = httpsURLConnectionImpl6.getHostnameVerifier();
        httpsURLConnectionImpl6.setConnectTimeout((int) ' ');
        java.net.CacheRequest cacheRequest11 = responseCacheImpl0.put(uRI2, (java.net.URLConnection) httpsURLConnectionImpl6);
        boolean boolean12 = httpsURLConnectionImpl6.getDoInput();
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
    }

    @Test
    public void test0994() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0994");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        boolean boolean10 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setConnectTimeout(501);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test0995() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0995");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        javax.net.ssl.HostnameVerifier hostnameVerifier6 = httpsURLConnectionImpl3.getHostnameVerifier();
        // The following exception was thrown during execution in test generation
        try {
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap7 = httpsURLConnectionImpl3.getHeaderFields();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
    }

    @Test
    public void test0996() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0996");
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
        boolean boolean16 = httpURLConnectionImpl9.streaming();
        java.io.InputStream inputStream17 = httpURLConnectionImpl9.getErrorStream();
        java.net.CookieHandler cookieHandler18 = httpURLConnectionImpl9.getCookieHandler();
        java.io.InputStream inputStream19 = httpURLConnectionImpl9.getErrorStream();
        boolean boolean20 = httpURLConnectionImpl9.usingProxy();
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient21 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, proxy1, 415, true, httpURLConnectionImpl9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState11 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState11.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNull(inputStream14);
        org.junit.Assert.assertNull(inputStream15);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNull(inputStream17);
        org.junit.Assert.assertNull(cookieHandler18);
        org.junit.Assert.assertNull(inputStream19);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
    }

    @Test
    public void test0997() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0997");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        // The following exception was thrown during execution in test generation
        try {
            long long6 = httpsURLConnectionImpl3.getLastModified();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0998() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0998");
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
        // The following exception was thrown during execution in test generation
        try {
            chunkedInputStream41.close();
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
    public void test0999() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test0999");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        java.io.InputStream inputStream10 = httpURLConnectionImpl5.getErrorStream();
        int int11 = httpURLConnectionImpl5.getReadTimeout();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap12 = httpURLConnectionImpl5.getRequestProperties();
        // The following exception was thrown during execution in test generation
        try {
            long long13 = httpURLConnectionImpl5.getExpiration();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNotNull(strMap12);
    }

    @Test
    public void test1000() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest1.test1000");
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
        byte[] byteArray44 = new byte[] { (byte) 1, (byte) 1, (byte) 0, (byte) 2 };
        // The following exception was thrown during execution in test generation
        try {
            int int45 = keepAliveStream36.read(byteArray44);
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
        org.junit.Assert.assertNotNull(byteArray44);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray44), "[1, 1, 0, 2]");
    }
}

package com.quakearts.rest.client.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test0001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0001");
        int int0 = java.net.HttpURLConnection.HTTP_MULT_CHOICE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 300 + "'", int0 == 300);
    }

    @Test
    public void test0002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0002");
        int int0 = java.net.HttpURLConnection.HTTP_NOT_AUTHORITATIVE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 203 + "'", int0 == 203);
    }

    @Test
    public void test0003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0003");
        int int0 = java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 401 + "'", int0 == 401);
    }

    @Test
    public void test0004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0004");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        byte[] byteArray6 = new byte[] { (byte) 10, (byte) 1, (byte) 10 };
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream2.write(byteArray6, 0, (int) '#');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray6), "[10, 1, 10]");
    }

    @Test
    public void test0005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0005");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        // The following exception was thrown during execution in test generation
        try {
            java.security.cert.Certificate[] certificateArray5 = httpsURLConnectionImpl3.getLocalCertificates();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
    }

    @Test
    public void test0006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0006");
        byte byte0 = com.quakearts.rest.client.net.https.HostnameChecker.TYPE_LDAP;
        org.junit.Assert.assertTrue("'" + byte0 + "' != '" + (byte) 2 + "'", byte0 == (byte) 2);
    }

    @Test
    public void test0007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0007");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream2.flush();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0008");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        // The following exception was thrown during execution in test generation
        try {
            long long8 = httpsURLConnectionImpl3.getHeaderFieldLong("hi!", (long) (short) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0009");
        com.quakearts.rest.client.net.http.AuthCache authCache0 = null;
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache(authCache0);
    }

    @Test
    public void test0010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0010");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        java.net.URL uRL9 = httpsURLConnectionImpl3.getURL();
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission10 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNull(uRL9);
    }

    @Test
    public void test0011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0011");
        int int0 = java.net.HttpURLConnection.HTTP_BAD_REQUEST;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 400 + "'", int0 == 400);
    }

    @Test
    public void test0012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0012");
        char char0 = com.quakearts.rest.client.net.http.AuthenticationInfo.SERVER_AUTHENTICATION;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 's' + "'", char0 == 's');
    }

    @Test
    public void test0013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0013");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj6 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0014");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        java.lang.Class class8 = null;
        java.lang.Class[] classArray9 = new java.lang.Class[] { class8 };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj10 = httpsURLConnectionImpl3.getContent(classArray9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNotNull(classArray9);
    }

    @Test
    public void test0015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0015");
        int int0 = java.net.HttpURLConnection.HTTP_NOT_FOUND;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 404 + "'", int0 == 404);
    }

    @Test
    public void test0016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0016");
        int int0 = java.net.HttpURLConnection.HTTP_CONFLICT;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 409 + "'", int0 == 409);
    }

    @Test
    public void test0017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0017");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(100);
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setChunkedStreamingMode((int) ' ');
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Fixed length streaming mode set");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0018");
        com.quakearts.rest.client.net.ThreadLocalCoders.cleanUpThreadLocal();
    }

    @Test
    public void test0019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0019");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
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
    public void test0020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0020");
        java.net.URL uRL0 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.net.URI uRI1 = com.quakearts.rest.client.net.URIUtil.toURI(uRL0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0021");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(100);
        // The following exception was thrown during execution in test generation
        try {
            java.security.cert.Certificate[] certificateArray10 = httpsURLConnectionImpl3.getLocalCertificates();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0022");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        java.net.URL uRL9 = httpsURLConnectionImpl3.getURL();
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream10 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.openConnectionCheckRedirects((java.net.URLConnection) httpsURLConnectionImpl3);
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNull(uRL9);
    }

    @Test
    public void test0023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0023");
        org.slf4j.Logger logger0 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass1 = logger0.getClass();
        org.junit.Assert.assertNotNull(logger0);
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test0024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0024");
        boolean boolean1 = com.quakearts.rest.client.net.URLConnectionImpl.isProxiedHost("");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test0025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0025");
        java.nio.charset.CharsetEncoder charsetEncoder1 = com.quakearts.rest.client.net.ThreadLocalCoders.encoderFor((java.lang.Object) ' ');
        org.junit.Assert.assertNull(charsetEncoder1);
    }

    @Test
    public void test0026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0026");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream9 = httpsURLConnectionImpl3.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
    }

    @Test
    public void test0027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0027");
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
    public void test0028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0028");
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
            java.lang.String str12 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0029");
        int int0 = java.net.HttpURLConnection.HTTP_USE_PROXY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 305 + "'", int0 == 305);
    }

    @Test
    public void test0030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0030");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        // The following exception was thrown during execution in test generation
        try {
            int int8 = httpsURLConnectionImpl3.getResponseCode();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0031");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        java.net.URL uRL9 = httpsURLConnectionImpl3.getURL();
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream10 = httpsURLConnectionImpl3.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNull(uRL9);
    }

    @Test
    public void test0032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0032");
        java.lang.String str1 = com.quakearts.rest.client.net.NetProperties.get("");
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test0033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0033");
        int int0 = java.net.HttpURLConnection.HTTP_UNAVAILABLE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 503 + "'", int0 == 503);
    }

    @Test
    public void test0034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0034");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader2 = new com.quakearts.rest.client.net.http.AuthenticationHeader("", messageHeader1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0035");
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
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission13 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0036");
        com.quakearts.rest.client.net.http.AuthScheme authScheme0 = com.quakearts.rest.client.net.http.AuthScheme.BASIC;
        org.junit.Assert.assertTrue("'" + authScheme0 + "' != '" + com.quakearts.rest.client.net.http.AuthScheme.BASIC + "'", authScheme0.equals(com.quakearts.rest.client.net.http.AuthScheme.BASIC));
    }

    @Test
    public void test0037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0037");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        // The following exception was thrown during execution in test generation
        try {
            long long4 = httpCaptureInputStream2.skip((long) 409);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0038");
        java.security.Principal principal1 = null;
        boolean boolean2 = com.quakearts.rest.client.net.https.HostnameChecker.match("", principal1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test0039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0039");
        boolean boolean1 = com.quakearts.rest.client.net.IPAddressUtil.isIPv4LiteralAddress("GET");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test0040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0040");
        int int0 = java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 500 + "'", int0 == 500);
    }

    @Test
    public void test0041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0041");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl4 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient5 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, proxy1, (int) (short) 10, true, httpURLConnectionImpl4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0042");
        int int0 = com.quakearts.rest.client.net.NetworkClient.DEFAULT_CONNECT_TIMEOUT;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-1) + "'", int0 == (-1));
    }

    @Test
    public void test0043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0043");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        java.net.URL uRL9 = httpsURLConnectionImpl3.getURL();
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream10 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.openConnectionCheckRedirects((java.net.URLConnection) httpsURLConnectionImpl3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertNull(uRL9);
    }

    @Test
    public void test0044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0044");
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
            long long9 = httpsURLConnectionImpl3.getExpiration();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test0045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0045");
        com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain.Type type0 = com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain.Type.PRIVATE;
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain.Type.PRIVATE + "'", type0.equals(com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain.Type.PRIVATE));
    }

    @Test
    public void test0046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0046");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setRequestMethod("hi!");
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: Invalid HTTP method: hi!");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0047");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream5 = httpsURLConnectionImpl3.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
    }

    @Test
    public void test0048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0048");
        java.lang.String str1 = com.quakearts.rest.client.net.MessageHeader.canonicalID("");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "" + "'", str1, "");
    }

    @Test
    public void test0049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0049");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.connect();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0050");
        com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain.Type type0 = com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain.Type.ICANN;
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain.Type.ICANN + "'", type0.equals(com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain.Type.ICANN));
    }

    @Test
    public void test0051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0051");
        int int0 = java.net.HttpURLConnection.HTTP_ENTITY_TOO_LARGE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 413 + "'", int0 == 413);
    }

    @Test
    public void test0052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0052");
        com.quakearts.rest.client.net.http.HttpHandler httpHandler2 = new com.quakearts.rest.client.net.http.HttpHandler("hi!", 413);
    }

    @Test
    public void test0053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0053");
        int int0 = java.net.HttpURLConnection.HTTP_NOT_ACCEPTABLE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 406 + "'", int0 == 406);
    }

    @Test
    public void test0054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0054");
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
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream12 = httpsURLConnectionImpl6.getInputStream();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
    }

    @Test
    public void test0055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0055");
        int int0 = java.net.HttpURLConnection.HTTP_FORBIDDEN;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 403 + "'", int0 == 403);
    }

    @Test
    public void test0056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0056");
        java.lang.String str1 = java.net.URLConnection.guessContentTypeFromName("GET");
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test0057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0057");
        int int0 = java.net.HttpURLConnection.HTTP_OK;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 200 + "'", int0 == 200);
    }

    @Test
    public void test0058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0058");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream4 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.openConnectionCheckRedirects((java.net.URLConnection) httpsURLConnectionImpl3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0059");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureInputStream2.reset();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0060");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        httpsURLConnectionImpl3.setRequestProperty("hi!", "");
        // The following exception was thrown during execution in test generation
        try {
            long long11 = httpsURLConnectionImpl3.getExpiration();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0061");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient6 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, "", (int) ' ', false, (int) (byte) -1, httpURLConnectionImpl5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0062");
        java.net.URLConnection uRLConnection0 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream1 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.openConnectionCheckRedirects(uRLConnection0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0063");
        java.net.ResponseCache responseCache0 = java.net.ResponseCache.getDefault();
        java.net.ResponseCache.setDefault(responseCache0);
        org.junit.Assert.assertNotNull(responseCache0);
    }

    @Test
    public void test0064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0064");
        int int0 = java.net.HttpURLConnection.HTTP_GONE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 410 + "'", int0 == 410);
    }

    @Test
    public void test0065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0065");
        java.security.Principal principal0 = null;
        java.lang.String str1 = com.quakearts.rest.client.net.https.HostnameChecker.getServerName(principal0);
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test0066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0066");
        int int0 = java.net.HttpURLConnection.HTTP_MOVED_PERM;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 301 + "'", int0 == 301);
    }

    @Test
    public void test0067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0067");
        java.net.URLConnection.setDefaultAllowUserInteraction(true);
    }

    @Test
    public void test0068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0068");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        org.slf4j.Logger logger10 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass11 = logger10.getClass();
        org.slf4j.Logger logger12 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass13 = logger12.getClass();
        org.slf4j.Logger logger14 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass15 = logger14.getClass();
        org.slf4j.Logger logger16 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass17 = logger16.getClass();
        org.slf4j.Logger logger18 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass19 = logger18.getClass();
        org.slf4j.Logger logger20 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass21 = logger20.getClass();
        java.lang.Class[] classArray22 = new java.lang.Class[] { wildcardClass11, wildcardClass13, wildcardClass15, wildcardClass17, wildcardClass19, wildcardClass21 };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj23 = httpsURLConnectionImpl3.getContent(classArray22);
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNotNull(logger10);
        org.junit.Assert.assertNotNull(wildcardClass11);
        org.junit.Assert.assertNotNull(logger12);
        org.junit.Assert.assertNotNull(wildcardClass13);
        org.junit.Assert.assertNotNull(logger14);
        org.junit.Assert.assertNotNull(wildcardClass15);
        org.junit.Assert.assertNotNull(logger16);
        org.junit.Assert.assertNotNull(wildcardClass17);
        org.junit.Assert.assertNotNull(logger18);
        org.junit.Assert.assertNotNull(wildcardClass19);
        org.junit.Assert.assertNotNull(logger20);
        org.junit.Assert.assertNotNull(wildcardClass21);
        org.junit.Assert.assertNotNull(classArray22);
    }

    @Test
    public void test0069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0069");
        boolean boolean0 = java.net.HttpURLConnection.getFollowRedirects();
        org.junit.Assert.assertTrue("'" + boolean0 + "' != '" + true + "'", boolean0 == true);
    }

    @Test
    public void test0070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0070");
        com.quakearts.rest.client.net.http.AuthScheme authScheme0 = com.quakearts.rest.client.net.http.AuthScheme.DIGEST;
        org.junit.Assert.assertTrue("'" + authScheme0 + "' != '" + com.quakearts.rest.client.net.http.AuthScheme.DIGEST + "'", authScheme0.equals(com.quakearts.rest.client.net.http.AuthScheme.DIGEST));
    }

    @Test
    public void test0071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0071");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setIfModifiedSince(0L);
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission11 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test0072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0072");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        long long3 = progressSource2.getProgress();
        progressSource2.updateProgress((long) 500, (long) 200);
        org.junit.Assert.assertTrue("'" + long3 + "' != '" + 0L + "'", long3 == 0L);
    }

    @Test
    public void test0073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0073");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        java.net.URL uRL9 = httpsURLConnectionImpl3.getURL();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj10 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNull(uRL9);
    }

    @Test
    public void test0074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0074");
        int int0 = java.net.HttpURLConnection.HTTP_RESET;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 205 + "'", int0 == 205);
    }

    @Test
    public void test0075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0075");
        com.quakearts.rest.client.net.http.AuthCacheValue.Type type0 = com.quakearts.rest.client.net.http.AuthCacheValue.Type.SERVER;
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + com.quakearts.rest.client.net.http.AuthCacheValue.Type.SERVER + "'", type0.equals(com.quakearts.rest.client.net.http.AuthCacheValue.Type.SERVER));
    }

    @Test
    public void test0076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0076");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl3 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient4 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, proxy1, (int) '4', httpURLConnectionImpl3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0077");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        byte[] byteArray3 = new byte[] {};
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream2.write(byteArray3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray3), "[]");
    }

    @Test
    public void test0078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0078");
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
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream12 = httpsURLConnectionImpl6.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
    }

    @Test
    public void test0079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0079");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setReadTimeout(503);
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.connect();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0080");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        // The following exception was thrown during execution in test generation
        try {
            int int3 = httpCaptureInputStream2.available();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0081");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        byte[] byteArray9 = new byte[] { (byte) -1, (byte) -1, (byte) 1, (byte) 10, (byte) 2, (byte) 10 };
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream2.write(byteArray9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray9), "[-1, -1, 1, 10, 2, 10]");
    }

    @Test
    public void test0082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0082");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.addRequestProperty("", "hi!");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str11 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0083");
        int int0 = java.net.HttpURLConnection.HTTP_PAYMENT_REQUIRED;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 402 + "'", int0 == 402);
    }

    @Test
    public void test0084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0084");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.addRequestProperty("", "hi!");
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream11 = httpsURLConnectionImpl3.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0085");
        int int0 = java.net.HttpURLConnection.HTTP_SERVER_ERROR;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 500 + "'", int0 == 500);
    }

    @Test
    public void test0086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0086");
        int int0 = java.net.HttpURLConnection.HTTP_CLIENT_TIMEOUT;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 408 + "'", int0 == 408);
    }

    @Test
    public void test0087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0087");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureInputStream2.reset();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0088");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str5 = httpsURLConnectionImpl3.getContentEncoding();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
    }

    @Test
    public void test0089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0089");
        int int0 = java.net.HttpURLConnection.HTTP_CREATED;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 201 + "'", int0 == 201);
    }

    @Test
    public void test0090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0090");
        int int0 = java.net.HttpURLConnection.HTTP_BAD_METHOD;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 405 + "'", int0 == 405);
    }

    @Test
    public void test0091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0091");
        int int0 = com.quakearts.rest.client.net.NetworkClient.DEFAULT_READ_TIMEOUT;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + (-1) + "'", int0 == (-1));
    }

    @Test
    public void test0092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0092");
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
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission13 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0093");
        boolean boolean0 = java.net.URLConnection.getDefaultAllowUserInteraction();
        org.junit.Assert.assertTrue("'" + boolean0 + "' != '" + true + "'", boolean0 == true);
    }

    @Test
    public void test0094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0094");
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
            httpsURLConnectionImpl5.setRequestProperty("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Already connected");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
    }

    @Test
    public void test0095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0095");
        java.net.HttpURLConnection.setFollowRedirects(false);
    }

    @Test
    public void test0096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0096");
        int int0 = java.net.HttpURLConnection.HTTP_NO_CONTENT;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 204 + "'", int0 == 204);
    }

    @Test
    public void test0097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0097");
        int int0 = java.net.HttpURLConnection.HTTP_PARTIAL;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 206 + "'", int0 == 206);
    }

    @Test
    public void test0098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0098");
        com.quakearts.rest.client.net.ProgressSource.State state0 = com.quakearts.rest.client.net.ProgressSource.State.DELETE;
        org.junit.Assert.assertTrue("'" + state0 + "' != '" + com.quakearts.rest.client.net.ProgressSource.State.DELETE + "'", state0.equals(com.quakearts.rest.client.net.ProgressSource.State.DELETE));
    }

    @Test
    public void test0099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0099");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str12 = httpsURLConnectionImpl3.getCipherSuite();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNotNull(strMap11);
    }

    @Test
    public void test0100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0100");
        int int0 = java.net.HttpURLConnection.HTTP_LENGTH_REQUIRED;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 411 + "'", int0 == 411);
    }

    @Test
    public void test0101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0101");
        byte byte0 = com.quakearts.rest.client.net.https.HostnameChecker.TYPE_TLS;
        org.junit.Assert.assertTrue("'" + byte0 + "' != '" + (byte) 1 + "'", byte0 == (byte) 1);
    }

    @Test
    public void test0102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0102");
        java.lang.String str1 = com.quakearts.rest.client.net.MessageHeader.canonicalID("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str1, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
    }

    @Test
    public void test0103() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0103");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        httpsURLConnectionImpl3.setInstanceFollowRedirects(true);
        // The following exception was thrown during execution in test generation
        try {
            int int8 = httpsURLConnectionImpl3.getContentLength();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0104() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0104");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        boolean boolean3 = progressSource2.connected();
        boolean boolean4 = progressSource2.connected();
        java.lang.String str5 = progressSource2.getMethod();
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
    }

    @Test
    public void test0105() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0105");
        com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain registeredDomain1 = com.quakearts.rest.client.net.RegisteredDomainProducer.registeredDomain("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(registeredDomain1);
    }

    @Test
    public void test0106() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0106");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        httpsURLConnectionImpl3.setRequestProperty("hi!", "");
        httpsURLConnectionImpl3.setReadTimeout((int) (short) 100);
        // The following exception was thrown during execution in test generation
        try {
            int int15 = httpsURLConnectionImpl3.getHeaderFieldInt("", 413);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0107() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0107");
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
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream12 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.openConnectionCheckRedirects((java.net.URLConnection) httpsURLConnectionImpl3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0108() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0108");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getAllowUserInteraction();
        // The following exception was thrown during execution in test generation
        try {
            long long7 = httpsURLConnectionImpl3.getLastModified();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
    }

    @Test
    public void test0109() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0109");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        byte[] byteArray11 = new byte[] { (byte) 1, (byte) 10, (byte) 100, (byte) -1, (byte) 1, (byte) 100 };
        // The following exception was thrown during execution in test generation
        try {
            int int12 = httpCaptureInputStream4.read(byteArray11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray11), "[1, 10, 100, -1, 1, 100]");
    }

    @Test
    public void test0110() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0110");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream4 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture3);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream4.flush();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0111() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0111");
        int int0 = java.net.HttpURLConnection.HTTP_UNSUPPORTED_TYPE;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 415 + "'", int0 == 415);
    }

    @Test
    public void test0112() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0112");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue2 = null;
        // The following exception was thrown during execution in test generation
        try {
            authCacheImpl0.put("", authCacheValue2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0113() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0113");
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
        // The following exception was thrown during execution in test generation
        try {
            java.security.cert.Certificate[] certificateArray14 = httpsURLConnectionImpl3.getLocalCertificates();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
    }

    @Test
    public void test0114() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0114");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean5 = httpCaptureInputStream4.markSupported();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0115() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0115");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setIfModifiedSince(0L);
        // The following exception was thrown during execution in test generation
        try {
            long long11 = httpsURLConnectionImpl3.getDate();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test0116() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0116");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        java.net.URL uRL9 = httpsURLConnectionImpl3.getURL();
        httpsURLConnectionImpl3.setInstanceFollowRedirects(false);
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream12 = httpsURLConnectionImpl3.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertNull(uRL9);
    }

    @Test
    public void test0117() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0117");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue2 = null;
        // The following exception was thrown during execution in test generation
        try {
            authCacheImpl0.put("", authCacheValue2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0118() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0118");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = httpsURLConnectionImpl3.getHeaderFieldKey(206);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
    }

    @Test
    public void test0119() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0119");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream4 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture3);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream4.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0120() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0120");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("GET");
        java.lang.String str4 = headerParser1.findValue("hi!", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.lang.String str6 = headerParser1.findKey(415);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str4, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str6);
    }

    @Test
    public void test0121() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0121");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = null;
        java.lang.String[] strArray4 = new java.lang.String[] { "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "GET" };
        java.util.LinkedHashSet<java.lang.String> strSet5 = new java.util.LinkedHashSet<java.lang.String>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strSet5, strArray4);
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader7 = new com.quakearts.rest.client.net.http.AuthenticationHeader("", messageHeader1, (java.util.Set<java.lang.String>) strSet5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
    }

    @Test
    public void test0122() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0122");
        com.quakearts.rest.client.net.http.HttpHandler httpHandler2 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (byte) 100);
    }

    @Test
    public void test0123() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0123");
        java.net.URLConnection.setDefaultAllowUserInteraction(false);
    }

    @Test
    public void test0124() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0124");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        httpsURLConnectionImpl3.setRequestProperty("hi!", "");
        httpsURLConnectionImpl3.setReadTimeout((int) (short) 100);
        // The following exception was thrown during execution in test generation
        try {
            int int13 = httpsURLConnectionImpl3.getResponseCode();
            org.junit.Assert.fail("Expected exception of type com.quakearts.rest.client.exception.HttpClientRuntimeException; message: java.lang.NullPointerException");
        } catch (com.quakearts.rest.client.exception.HttpClientRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0125() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0125");
        java.io.PrintStream printStream0 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.ChunkedOutputStream chunkedOutputStream2 = new com.quakearts.rest.client.net.ChunkedOutputStream(printStream0, 8192);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Null output stream");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0126() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0126");
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
            int int11 = meteredStream10.available();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
    }

    @Test
    public void test0127() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0127");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureInputStream4.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0128() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0128");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(100);
        httpsURLConnectionImpl3.setDefaultUseCaches(true);
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setRequestMethod("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: Invalid HTTP method: com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0129() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0129");
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
            java.io.InputStream inputStream14 = httpsURLConnectionImpl3.getInputStream();
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
    public void test0130() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0130");
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
            java.lang.Object obj19 = httpsURLConnectionImpl5.getContent();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertNull(str18);
    }

    @Test
    public void test0131() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0131");
        com.quakearts.rest.client.net.ProgressMeteringPolicy progressMeteringPolicy0 = null;
        com.quakearts.rest.client.net.ProgressMonitor.setMeteringPolicy(progressMeteringPolicy0);
    }

    @Test
    public void test0132() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0132");
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
            long long12 = meteredStream10.skip((long) 401);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
    }

    @Test
    public void test0133() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0133");
        boolean boolean1 = com.quakearts.rest.client.net.IPAddressUtil.isIPv6LiteralAddress("hi!");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test0134() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0134");
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
            meteredStream10.mark((int) 'a');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
    }

    @Test
    public void test0135() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0135");
        int int0 = java.net.HttpURLConnection.HTTP_ACCEPTED;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 202 + "'", int0 == 202);
    }

    @Test
    public void test0136() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0136");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("GET");
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HeaderParser headerParser4 = headerParser1.subsequence(1, (int) (byte) 2);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: invalid start or end");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0137() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0137");
        int int0 = java.net.HttpURLConnection.HTTP_BAD_GATEWAY;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 502 + "'", int0 == 502);
    }

    @Test
    public void test0138() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0138");
        com.quakearts.rest.client.net.http.AuthScheme authScheme0 = com.quakearts.rest.client.net.http.AuthScheme.UNKNOWN;
        org.junit.Assert.assertTrue("'" + authScheme0 + "' != '" + com.quakearts.rest.client.net.http.AuthScheme.UNKNOWN + "'", authScheme0.equals(com.quakearts.rest.client.net.http.AuthScheme.UNKNOWN));
    }

    @Test
    public void test0139() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0139");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        java.net.URL uRL9 = httpsURLConnectionImpl3.getURL();
        java.lang.Class<?> wildcardClass10 = httpsURLConnectionImpl3.getClass();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertNotNull(wildcardClass10);
    }

    @Test
    public void test0140() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0140");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream2.flush();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0141() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0141");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        long long6 = httpsURLConnectionImpl3.getIfModifiedSince();
        // The following exception was thrown during execution in test generation
        try {
            int int9 = httpsURLConnectionImpl3.getHeaderFieldInt("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", (int) (byte) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + 0L + "'", long6 == 0L);
    }

    @Test
    public void test0142() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0142");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl6.setRequestMethod("content/unknown");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connect in progress");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
    }

    @Test
    public void test0143() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0143");
        java.net.URL uRL0 = null;
        java.lang.String str1 = com.quakearts.rest.client.net.IPAddressUtil.checkAuthority(uRL0);
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test0144() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0144");
        java.util.Optional<com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain> registeredDomainOptional1 = com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain.from("content/unknown");
        org.junit.Assert.assertNotNull(registeredDomainOptional1);
    }

    @Test
    public void test0145() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0145");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream4 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture3);
        byte[] byteArray6 = new byte[] { (byte) -1 };
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream4.write(byteArray6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray6), "[-1]");
    }

    @Test
    public void test0146() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0146");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        boolean boolean8 = httpsURLConnectionImpl3.getAllowUserInteraction();
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setConnectTimeout((int) (short) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: timeouts can't be negative");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0147() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0147");
        int int0 = java.net.HttpURLConnection.HTTP_SEE_OTHER;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 303 + "'", int0 == 303);
    }

    @Test
    public void test0148() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0148");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean3 = httpCaptureInputStream2.markSupported();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0149() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0149");
        java.lang.String str1 = java.net.URLConnection.guessContentTypeFromName("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test0150() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0150");
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
            java.lang.String str11 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type com.quakearts.rest.client.exception.HttpClientRuntimeException; message: java.lang.NullPointerException");
        } catch (com.quakearts.rest.client.exception.HttpClientRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0151() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0151");
        java.io.PrintStream printStream0 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.ChunkedOutputStream chunkedOutputStream2 = new com.quakearts.rest.client.net.ChunkedOutputStream(printStream0, 413);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Null output stream");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0152() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0152");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setDoInput(false);
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream10 = httpsURLConnectionImpl3.getInputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: Cannot read from URLConnection if doInput=false (call setDoInput(true))");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0153() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0153");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl6.addRequestProperty("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal character(s) in message header field: com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
    }

    @Test
    public void test0154() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0154");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission8 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0155() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0155");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = httpsURLConnectionImpl3.getHeaderField((int) '#');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0156() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0156");
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
            java.lang.String str11 = java.net.URLConnection.guessContentTypeFromStream((java.io.InputStream) meteredStream10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
    }

    @Test
    public void test0157() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0157");
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
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        // The following exception was thrown during execution in test generation
        try {
            long long14 = httpsURLConnectionImpl3.getDate();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0158() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0158");
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
            httpsURLConnectionImpl3.connect();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
    }

    @Test
    public void test0159() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0159");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        int int2 = posterOutputStream0.size();
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
    }

    @Test
    public void test0160() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0160");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl4 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient5 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, proxy1, 500, true, httpURLConnectionImpl4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0161() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0161");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream6 = httpsURLConnectionImpl3.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0162() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0162");
        com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain registeredDomain1 = com.quakearts.rest.client.net.RegisteredDomainProducer.registeredDomain("GET");
        org.junit.Assert.assertNull(registeredDomain1);
    }

    @Test
    public void test0163() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0163");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setConnectTimeout((int) ' ');
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission8 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0164() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0164");
        int int0 = java.net.HttpURLConnection.HTTP_PRECON_FAILED;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 412 + "'", int0 == 412);
    }

    @Test
    public void test0165() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0165");
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
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission18 = httpsURLConnectionImpl5.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "GET" + "'", str17, "GET");
    }

    @Test
    public void test0166() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0166");
        java.net.FileNameMap fileNameMap0 = null;
        java.net.URLConnection.setFileNameMap(fileNameMap0);
    }

    @Test
    public void test0167() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0167");
        int int0 = java.net.HttpURLConnection.HTTP_NOT_IMPLEMENTED;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 501 + "'", int0 == 501);
    }

    @Test
    public void test0168() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0168");
        int int0 = java.net.HttpURLConnection.HTTP_REQ_TOO_LONG;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 414 + "'", int0 == 414);
    }

    @Test
    public void test0169() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0169");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean5 = httpCaptureInputStream4.markSupported();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0170() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0170");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        byte[] byteArray12 = new byte[] { (byte) -1 };
        // The following exception was thrown during execution in test generation
        try {
            int int15 = meteredStream10.read(byteArray12, 0, 411);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray12), "[-1]");
    }

    @Test
    public void test0171() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0171");
        com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain registeredDomain1 = com.quakearts.rest.client.net.RegisteredDomainProducer.registeredDomain("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertNull(registeredDomain1);
    }

    @Test
    public void test0172() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0172");
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
            long long9 = httpsURLConnectionImpl3.getExpiration();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test0173() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0173");
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
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        boolean boolean14 = httpsURLConnectionImpl3.getUseCaches();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test0174() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0174");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        messageHeader0.set("content/unknown", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.io.PrintStream printStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            messageHeader0.print(printStream4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0175() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0175");
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
        // The following exception was thrown during execution in test generation
        try {
            long long13 = httpsURLConnectionImpl3.getHeaderFieldDate("content/unknown", (long) 411);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test0176() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0176");
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
        java.lang.String str15 = progressEvent12.toString();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-1L) + "'", long13 == (-1L));
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + 300L + "'", long14 == 300L);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]" + "'", str15, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
    }

    @Test
    public void test0177() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0177");
        java.lang.String str1 = com.quakearts.rest.client.net.MessageHeader.canonicalID("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]" + "'", str1, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
    }

    @Test
    public void test0178() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0178");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        httpsURLConnectionImpl3.setInstanceFollowRedirects(true);
        // The following exception was thrown during execution in test generation
        try {
            long long8 = httpsURLConnectionImpl3.getLastModified();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0179() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0179");
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
            java.lang.String str13 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
    }

    @Test
    public void test0180() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0180");
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker1 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Unknown check type: -1");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0181() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0181");
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
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream14 = httpsURLConnectionImpl6.getInputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
    }

    @Test
    public void test0182() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0182");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        // The following exception was thrown during execution in test generation
        try {
            java.security.Principal principal6 = httpsURLConnectionImpl3.getPeerPrincipal();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0183() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0183");
        int int0 = java.net.HttpURLConnection.HTTP_VERSION;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 505 + "'", int0 == 505);
    }

    @Test
    public void test0184() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0184");
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState0 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.TUNNELING;
        org.junit.Assert.assertTrue("'" + tunnelState0 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.TUNNELING + "'", tunnelState0.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.TUNNELING));
    }

    @Test
    public void test0185() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0185");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        boolean boolean7 = httpsURLConnectionImpl3.getUseCaches();
        // The following exception was thrown during execution in test generation
        try {
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap8 = httpsURLConnectionImpl3.getHeaderFields();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test0186() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0186");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream2.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0187() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0187");
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
            hostnameChecker8.match("hi!", x509Certificate11, true);
            org.junit.Assert.fail("Expected exception of type java.security.cert.CertificateException; message: Illegal given domain name: hi!");
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
    public void test0188() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0188");
        java.io.PrintStream printStream0 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.ChunkedOutputStream chunkedOutputStream2 = new com.quakearts.rest.client.net.ChunkedOutputStream(printStream0, (int) (byte) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Null output stream");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0189() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0189");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setUseCaches(false);
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.connect();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0190() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0190");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        int int1 = posterOutputStream0.size();
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
    }

    @Test
    public void test0191() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0191");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        // The following exception was thrown during execution in test generation
        try {
            int int6 = httpsURLConnectionImpl3.getHeaderFieldInt("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", 1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0192() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0192");
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
        httpsURLConnectionImpl5.setUseCaches(true);
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
    }

    @Test
    public void test0193() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0193");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        java.net.URL uRL5 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource7 = new com.quakearts.rest.client.net.ProgressSource(uRL5, "");
        long long8 = progressSource7.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream10 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource7, (long) (byte) 1);
        byte[] byteArray11 = new byte[] {};
        // The following exception was thrown during execution in test generation
        try {
            int int14 = meteredStream10.read(byteArray11, (int) (short) 0, 413);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray11), "[]");
    }

    @Test
    public void test0194() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0194");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream2.write((int) (short) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0195() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0195");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        byte[] byteArray8 = new byte[] { (byte) -1, (byte) 100, (byte) -1 };
        // The following exception was thrown during execution in test generation
        try {
            int int9 = httpCaptureInputStream4.read(byteArray8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray8), "[-1, 100, -1]");
    }

    @Test
    public void test0196() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0196");
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
            meteredStream10.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
    }

    @Test
    public void test0197() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0197");
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
            boolean boolean11 = meteredStream10.markSupported();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
    }

    @Test
    public void test0198() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0198");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        boolean boolean9 = httpsURLConnectionImpl3.getAllowUserInteraction();
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.connect();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0199() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0199");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        byte[] byteArray7 = new byte[] { (byte) 0, (byte) 100 };
        // The following exception was thrown during execution in test generation
        try {
            int int8 = httpCaptureInputStream4.read(byteArray7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray7), "[0, 100]");
    }

    @Test
    public void test0200() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0200");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl6.connect();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
    }

    @Test
    public void test0201() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0201");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj6 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0202() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0202");
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
        httpsURLConnectionImpl6.setAllowUserInteraction(true);
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
    }

    @Test
    public void test0203() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0203");
        int int0 = java.net.HttpURLConnection.HTTP_GATEWAY_TIMEOUT;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 504 + "'", int0 == 504);
    }

    @Test
    public void test0204() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0204");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        byte[] byteArray3 = new byte[] {};
        // The following exception was thrown during execution in test generation
        try {
            int int4 = httpCaptureInputStream2.read(byteArray3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray3), "[]");
    }

    @Test
    public void test0205() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0205");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = com.quakearts.rest.client.net.HttpCapture.getCapture(uRL0);
        org.junit.Assert.assertNull(httpCapture1);
    }

    @Test
    public void test0206() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0206");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        java.lang.String str2 = posterOutputStream0.toString();
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "" + "'", str2, "");
    }

    @Test
    public void test0207() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0207");
        com.quakearts.rest.client.net.RegisteredDomainProducer registeredDomainProducer0 = new com.quakearts.rest.client.net.RegisteredDomainProducer();
    }

    @Test
    public void test0208() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0208");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream2.write(10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0209() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0209");
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
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream15 = httpsURLConnectionImpl6.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
    }

    @Test
    public void test0210() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0210");
        com.quakearts.rest.client.net.URLConnectionImpl.setProxiedHost("{size=10 nkeys=1  {hi!} }");
    }

    @Test
    public void test0211() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0211");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource.State state9 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent12 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL6, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state9, (long) 300, (-1L));
        java.lang.Object obj13 = progressEvent12.getSource();
        com.quakearts.rest.client.net.ProgressSource.State state14 = progressEvent12.getState();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(obj13);
        org.junit.Assert.assertEquals(obj13.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj13), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj13), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(state14);
    }

    @Test
    public void test0212() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0212");
        boolean boolean1 = com.quakearts.rest.client.net.URLConnectionImpl.isProxiedHost("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test0213() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0213");
        java.net.ContentHandlerFactory contentHandlerFactory0 = null;
        java.net.URLConnection.setContentHandlerFactory(contentHandlerFactory0);
    }

    @Test
    public void test0214() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0214");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl6.connect();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
    }

    @Test
    public void test0215() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0215");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = posterOutputStream0.toString("{size=10 nkeys=1  {hi!} }");
            org.junit.Assert.fail("Expected exception of type java.io.UnsupportedEncodingException; message: {size=10 nkeys=1  {hi!} }");
        } catch (java.io.UnsupportedEncodingException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0216() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0216");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setConnectTimeout((int) ' ');
        java.lang.String str8 = httpsURLConnectionImpl3.toString();
        httpsURLConnectionImpl3.setAllowUserInteraction(true);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str11 = httpsURLConnectionImpl3.getContentEncoding();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
    }

    @Test
    public void test0217() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0217");
        java.lang.Boolean boolean1 = com.quakearts.rest.client.net.NetProperties.getBoolean("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
    }

    @Test
    public void test0218() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0218");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient6 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, "{size=10 nkeys=1  {hi!} }", (int) (byte) 100, false, 409, httpURLConnectionImpl5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0219() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0219");
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
            java.lang.Object obj10 = httpsURLConnectionImpl3.getContent();
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
    public void test0220() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0220");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        byte[] byteArray10 = new byte[] { (byte) 2, (byte) 0, (byte) 10, (byte) 0, (byte) 10 };
        // The following exception was thrown during execution in test generation
        try {
            int int11 = httpCaptureInputStream4.read(byteArray10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray10), "[2, 0, 10, 0, 10]");
    }

    @Test
    public void test0221() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0221");
        com.quakearts.rest.client.net.KeepAliveCache keepAliveCache0 = new com.quakearts.rest.client.net.KeepAliveCache();
        java.lang.Object obj1 = keepAliveCache0.clone();
        java.net.URL uRL2 = null;
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl3 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.URI uRI4 = null;
        java.net.URL uRL5 = null;
        java.net.Proxy proxy6 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler7 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl8 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL5, proxy6, httpsHandler7);
        javax.net.ssl.HostnameVerifier hostnameVerifier9 = httpsURLConnectionImpl8.getHostnameVerifier();
        boolean boolean10 = httpsURLConnectionImpl8.getDoInput();
        httpsURLConnectionImpl8.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl8.addRequestProperty("", "hi!");
        int int18 = httpsURLConnectionImpl8.getHeaderFieldInt("hi!", 100);
        java.net.CacheRequest cacheRequest19 = responseCacheImpl3.put(uRI4, (java.net.URLConnection) httpsURLConnectionImpl8);
        httpsURLConnectionImpl8.setFixedLengthStreamingMode((long) 203);
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient22 = keepAliveCache0.get(uRL2, (java.lang.Object) 203);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(obj1);
        org.junit.Assert.assertEquals(obj1.toString(), "{}");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj1), "{}");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj1), "{}");
        org.junit.Assert.assertNotNull(hostnameVerifier9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 100 + "'", int18 == 100);
        org.junit.Assert.assertNull(cacheRequest19);
    }

    @Test
    public void test0222() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0222");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type com.quakearts.rest.client.exception.HttpClientRuntimeException; message: java.lang.NullPointerException");
        } catch (com.quakearts.rest.client.exception.HttpClientRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0223() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0223");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.disconnect();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = httpsURLConnectionImpl3.getContentType();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0224() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0224");
        boolean boolean1 = com.quakearts.rest.client.net.IPAddressUtil.isIPv4LiteralAddress("content/unknown");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test0225() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0225");
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
            java.io.InputStream inputStream18 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.openConnectionCheckRedirects((java.net.URLConnection) httpsURLConnectionImpl5);
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
    public void test0226() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0226");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        java.lang.String str7 = httpsURLConnectionImpl3.getRequestProperty("hi!");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = httpsURLConnectionImpl3.getHeaderFieldKey(0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNull(str7);
    }

    @Test
    public void test0227() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0227");
        int int0 = java.net.HttpURLConnection.HTTP_NOT_MODIFIED;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 304 + "'", int0 == 304);
    }

    @Test
    public void test0228() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0228");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setConnectTimeout((int) ' ');
        java.lang.String str8 = httpsURLConnectionImpl3.toString();
        httpsURLConnectionImpl3.setChunkedStreamingMode((int) (byte) -1);
        // The following exception was thrown during execution in test generation
        try {
            java.security.Principal principal11 = httpsURLConnectionImpl3.getPeerPrincipal();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
    }

    @Test
    public void test0229() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0229");
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
            java.security.cert.Certificate[] certificateArray14 = httpsURLConnectionImpl3.getLocalCertificates();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertNull(inputStream13);
    }

    @Test
    public void test0230() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0230");
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
            meteredStream26.mark(413);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0231() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0231");
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
            long long10 = httpsURLConnectionImpl3.getDate();
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
    public void test0232() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0232");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream3 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture2);
        byte[] byteArray7 = new byte[] { (byte) 10, (byte) 1, (byte) 100 };
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream3.write(byteArray7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray7), "[10, 1, 100]");
    }

    @Test
    public void test0233() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0233");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        httpsURLConnectionImpl3.setChunkedStreamingMode(10);
        java.lang.String str10 = httpsURLConnectionImpl3.getHeaderFieldKey(402);
        httpsURLConnectionImpl3.setDefaultUseCaches(false);
        java.lang.String str14 = httpsURLConnectionImpl3.getHeaderField(201);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj15 = httpsURLConnectionImpl3.getContent();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNull(str14);
    }

    @Test
    public void test0234() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0234");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = httpsURLConnectionImpl3.getContentType();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0235() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0235");
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
        httpsURLConnectionImpl3.setUseCaches(true);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 403 + "'", int12 == 403);
        org.junit.Assert.assertNull(str14);
    }

    @Test
    public void test0236() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0236");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue4 = authCacheImpl0.get("hi!", "GET");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = null;
        // The following exception was thrown during execution in test generation
        try {
            authCacheImpl0.put("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", authCacheValue6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(authCacheValue4);
    }

    @Test
    public void test0237() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0237");
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
            int int27 = meteredStream10.read();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0238() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0238");
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
            boolean boolean27 = meteredStream10.markSupported();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0239() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0239");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        boolean boolean8 = httpsURLConnectionImpl3.getAllowUserInteraction();
        // The following exception was thrown during execution in test generation
        try {
            int int9 = httpsURLConnectionImpl3.getResponseCode();
            org.junit.Assert.fail("Expected exception of type com.quakearts.rest.client.exception.HttpClientRuntimeException; message: java.lang.NullPointerException");
        } catch (com.quakearts.rest.client.exception.HttpClientRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0240() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0240");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        javax.net.ssl.HostnameVerifier hostnameVerifier6 = httpsURLConnectionImpl3.getHostnameVerifier();
        // The following exception was thrown during execution in test generation
        try {
            long long7 = httpsURLConnectionImpl3.getExpiration();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
    }

    @Test
    public void test0241() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0241");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("", "");
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
    public void test0242() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0242");
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
            java.security.Permission permission10 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0243() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0243");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl5.connect();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0244() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0244");
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker1 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) 10);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Unknown check type: 10");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0245() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0245");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str11 = httpsURLConnectionImpl3.getContentEncoding();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test0246() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0246");
        java.io.PrintStream printStream0 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.ChunkedOutputStream chunkedOutputStream2 = new com.quakearts.rest.client.net.ChunkedOutputStream(printStream0, 412);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Null output stream");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0247() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0247");
        com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain registeredDomain1 = com.quakearts.rest.client.net.RegisteredDomainProducer.registeredDomain("{size=10 nkeys=1  {get} }");
        org.junit.Assert.assertNull(registeredDomain1);
    }

    @Test
    public void test0248() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0248");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream6 = httpURLConnectionImpl5.getInputStream();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0249() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0249");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        // The following exception was thrown during execution in test generation
        try {
            long long4 = httpCaptureInputStream2.skip((long) 411);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0250() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0250");
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
            long long11 = httpsURLConnectionImpl3.getHeaderFieldDate("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", (long) 401);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0251() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0251");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        // The following exception was thrown during execution in test generation
        try {
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap6 = httpURLConnectionImpl5.getHeaderFields();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0252() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0252");
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
            long long10 = httpsURLConnectionImpl3.getDate();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0253() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0253");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl5.setRequestMethod("hi!");
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: Invalid HTTP method: hi!");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
    }

    @Test
    public void test0254() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0254");
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
            hostnameChecker8.match("", x509Certificate11);
            org.junit.Assert.fail("Expected exception of type java.security.cert.CertificateException; message: Illegal given domain name: ");
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
    public void test0255() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0255");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        boolean boolean6 = httpURLConnectionImpl5.usingProxy();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = httpURLConnectionImpl5.getHeaderFieldKey(10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0256() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0256");
        com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker1 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) 2);
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
    public void test0257() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0257");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        boolean boolean8 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.setRequestMethod("GET");
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission11 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0258() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0258");
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
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream27 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray28 = posterOutputStream27.toByteArray();
        // The following exception was thrown during execution in test generation
        try {
            int int31 = meteredStream10.read(byteArray28, 204, 501);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(byteArray28);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray28), "[]");
    }

    @Test
    public void test0259() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0259");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.disconnect();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type com.quakearts.rest.client.exception.HttpClientRuntimeException; message: java.lang.NullPointerException");
        } catch (com.quakearts.rest.client.exception.HttpClientRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0260() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0260");
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
            java.lang.Object obj17 = httpsURLConnectionImpl6.getContent();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
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
    public void test0261() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0261");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        boolean boolean4 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        // The following exception was thrown during execution in test generation
        try {
            long long5 = httpsURLConnectionImpl3.getLastModified();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test0262() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0262");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap1 = messageHeader0.getHeaders();
        messageHeader0.add("{size=10 nkeys=1  {get} }", "content/unknown");
        org.junit.Assert.assertNotNull(strMap1);
    }

    @Test
    public void test0263() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0263");
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
            long long12 = httpsURLConnectionImpl3.getHeaderFieldDate("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", (long) 200);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0264() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0264");
        java.lang.String str1 = com.quakearts.rest.client.net.NetProperties.get("{size=10 nkeys=1  {hi!} }");
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test0265() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0265");
        boolean boolean1 = com.quakearts.rest.client.net.IPAddressUtil.isIPv4LiteralAddress("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test0266() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0266");
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
            meteredStream10.mark(405);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0267() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0267");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        posterOutputStream0.flush();
        byte[] byteArray4 = new byte[] { (byte) -1 };
        // The following exception was thrown during execution in test generation
        try {
            posterOutputStream0.write(byteArray4, 402, 32);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray4), "[-1]");
    }

    @Test
    public void test0268() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0268");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str15 = httpsURLConnectionImpl6.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
    }

    @Test
    public void test0269() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0269");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int1 = progressMonitor0.getProgressUpdateThreshold();
        java.util.List<com.quakearts.rest.client.net.ProgressSource> progressSourceList2 = progressMonitor0.getProgressSources();
        java.net.URL uRL3 = null;
        boolean boolean5 = progressMonitor0.shouldMeterInput(uRL3, "{size=10 nkeys=1  {hi!} }");
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8192 + "'", int1 == 8192);
        org.junit.Assert.assertNotNull(progressSourceList2);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test0270() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0270");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream5 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray6 = posterOutputStream5.toByteArray();
        // The following exception was thrown during execution in test generation
        try {
            int int9 = httpCaptureInputStream4.read(byteArray6, 415, 504);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray6), "[]");
    }

    @Test
    public void test0271() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0271");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream3 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray4 = posterOutputStream3.toByteArray();
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream2.write(byteArray4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray4), "[]");
    }

    @Test
    public void test0272() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0272");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setConnectTimeout((int) ' ');
        java.lang.String str8 = httpsURLConnectionImpl3.toString();
        httpsURLConnectionImpl3.setChunkedStreamingMode((int) (byte) -1);
        int int11 = httpsURLConnectionImpl3.getConnectTimeout();
        // The following exception was thrown during execution in test generation
        try {
            int int12 = httpsURLConnectionImpl3.getResponseCode();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 32 + "'", int11 == 32);
    }

    @Test
    public void test0273() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0273");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        long long6 = httpsURLConnectionImpl3.getIfModifiedSince();
        java.io.InputStream inputStream7 = httpsURLConnectionImpl3.getErrorStream();
        // The following exception was thrown during execution in test generation
        try {
            long long8 = httpsURLConnectionImpl3.getExpiration();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + 0L + "'", long6 == 0L);
        org.junit.Assert.assertNull(inputStream7);
    }

    @Test
    public void test0274() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0274");
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
            meteredStream10.mark(503);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0275() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0275");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        java.net.URL uRL8 = httpsURLConnectionImpl3.getURL();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(uRL8);
    }

    @Test
    public void test0276() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0276");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream4 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture3);
        byte[] byteArray5 = new byte[] {};
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream4.write(byteArray5, 502, 206);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray5), "[]");
    }

    @Test
    public void test0277() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0277");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        httpsURLConnectionImpl3.setIfModifiedSince((long) 8192);
        boolean boolean11 = httpsURLConnectionImpl3.getDoOutput();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0278() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0278");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader1.set(406, "content/unknown", "hi!");
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader8 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        java.lang.String str9 = authenticationHeader8.raw();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str9);
    }

    @Test
    public void test0279() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0279");
        com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker1 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) 2);
        java.security.cert.X509Certificate x509Certificate3 = null;
        // The following exception was thrown during execution in test generation
        try {
            hostnameChecker1.match("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", x509Certificate3, true);
            org.junit.Assert.fail("Expected exception of type java.security.cert.CertificateException; message: Illegal given domain name: com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        } catch (java.security.cert.CertificateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameChecker1);
    }

    @Test
    public void test0280() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0280");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        java.net.URL uRL3 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource5 = new com.quakearts.rest.client.net.ProgressSource(uRL3, "");
        long long6 = progressSource5.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream8 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource5, (long) (short) 0);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean9 = meteredStream8.markSupported();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + (-1L) + "'", long6 == (-1L));
    }

    @Test
    public void test0281() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0281");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        httpsURLConnectionImpl3.setInstanceFollowRedirects(true);
        // The following exception was thrown during execution in test generation
        try {
            long long8 = httpsURLConnectionImpl3.getLastModified();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0282() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0282");
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
            meteredStream26.reset();
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: Resetting to an invalid mark");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0283() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0283");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        httpsURLConnectionImpl3.setDoOutput(false);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = httpsURLConnectionImpl3.getHeaderField(305);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0284() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0284");
        boolean boolean1 = com.quakearts.rest.client.net.URLConnectionImpl.isProxiedHost("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test0285() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0285");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(100);
        // The following exception was thrown during execution in test generation
        try {
            java.security.cert.Certificate[] certificateArray10 = httpsURLConnectionImpl3.getServerCertificates();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0286() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0286");
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
        boolean boolean12 = httpsURLConnectionImpl3.getDoOutput();
        long long13 = httpsURLConnectionImpl3.getLastModified();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + 0L + "'", long13 == 0L);
    }

    @Test
    public void test0287() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0287");
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
            int int27 = meteredStream10.available();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0288() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0288");
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
            java.io.InputStream inputStream15 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.openConnectionCheckRedirects((java.net.URLConnection) httpsURLConnectionImpl3);
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
    public void test0289() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0289");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        byte[] byteArray8 = new byte[] { (byte) -1, (byte) 1, (byte) 1, (byte) 2, (byte) -1 };
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream2.write(byteArray8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray8), "[-1, 1, 1, 2, -1]");
    }

    @Test
    public void test0290() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0290");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        boolean boolean9 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.addRequestProperty("", "");
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission13 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
// flaky:         org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0291() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0291");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        messageHeader0.remove("AuthenticationHeader: prefer null");
    }

    @Test
    public void test0292() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0292");
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
            java.lang.String str14 = httpURLConnectionImpl5.getHeaderField(403);
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
    public void test0293() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0293");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        posterOutputStream0.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream3 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray4 = posterOutputStream3.toByteArray();
        // The following exception was thrown during execution in test generation
        try {
            posterOutputStream0.write(byteArray4, 414, (int) (byte) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray4), "[]");
    }

    @Test
    public void test0294() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0294");
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
            httpsURLConnectionImpl3.connect();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
// flaky:         org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test0295() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0295");
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
        httpsURLConnectionImpl3.setReadTimeout(409);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNotNull(sSLSocketFactory10);
    }

    @Test
    public void test0296() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0296");
        com.quakearts.rest.client.net.KeepAliveCache keepAliveCache0 = new com.quakearts.rest.client.net.KeepAliveCache();
        java.util.Optional<com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain> registeredDomainOptional2 = com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain.from("");
        boolean boolean3 = keepAliveCache0.equals((java.lang.Object) registeredDomainOptional2);
        keepAliveCache0.clear();
        int int5 = keepAliveCache0.size();
        keepAliveCache0.run();
        org.junit.Assert.assertNotNull(registeredDomainOptional2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
    }

    @Test
    public void test0297() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0297");
        com.quakearts.rest.client.net.KeepAliveCache keepAliveCache0 = new com.quakearts.rest.client.net.KeepAliveCache();
        java.lang.Object obj1 = keepAliveCache0.clone();
        java.lang.Object obj2 = keepAliveCache0.clone();
        org.junit.Assert.assertNotNull(obj1);
        org.junit.Assert.assertEquals(obj1.toString(), "{}");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj1), "{}");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj1), "{}");
        org.junit.Assert.assertNotNull(obj2);
        org.junit.Assert.assertEquals(obj2.toString(), "{}");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj2), "{}");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj2), "{}");
    }

    @Test
    public void test0298() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0298");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        java.net.URL uRL3 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource5 = new com.quakearts.rest.client.net.ProgressSource(uRL3, "");
        long long6 = progressSource5.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream8 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource5, (long) (short) 0);
        // The following exception was thrown during execution in test generation
        try {
            meteredStream8.reset();
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: Resetting to an invalid mark");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + (-1L) + "'", long6 == (-1L));
    }

    @Test
    public void test0299() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0299");
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
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test0300() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0300");
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
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap13 = httpURLConnectionImpl5.getHeaderFields();
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
    public void test0301() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0301");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        boolean boolean6 = httpURLConnectionImpl5.usingProxy();
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl5.setAuthenticationProperty("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "AuthenticationHeader: prefer null");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal character(s) in message header field: com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0302() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0302");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = null;
        java.lang.String[] strArray7 = new java.lang.String[] { "GET", "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "hi!", "GET" };
        java.util.LinkedHashSet<java.lang.String> strSet8 = new java.util.LinkedHashSet<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strSet8, strArray7);
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader10 = new com.quakearts.rest.client.net.http.AuthenticationHeader("AuthenticationHeader: prefer null", messageHeader1, (java.util.Set<java.lang.String>) strSet8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test0303() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0303");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        // The following exception was thrown during execution in test generation
        try {
            int int5 = httpCaptureInputStream4.available();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0304() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0304");
        char char0 = com.quakearts.rest.client.net.http.AuthenticationInfo.PROXY_AUTHENTICATION;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'p' + "'", char0 == 'p');
    }

    @Test
    public void test0305() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0305");
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
        java.lang.String str15 = httpsURLConnectionImpl3.getContentEncoding();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 403 + "'", int12 == 403);
        org.junit.Assert.assertNull(str14);
        org.junit.Assert.assertNull(str15);
    }

    @Test
    public void test0306() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0306");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str37 = java.net.URLConnection.guessContentTypeFromStream((java.io.InputStream) meteredStream10);
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
    public void test0307() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0307");
        int int0 = java.net.HttpURLConnection.HTTP_MOVED_TEMP;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 302 + "'", int0 == 302);
    }

    @Test
    public void test0308() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0308");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setConnectTimeout((int) ' ');
        java.lang.String str8 = httpsURLConnectionImpl3.toString();
        httpsURLConnectionImpl3.setChunkedStreamingMode((int) (byte) -1);
        int int11 = httpsURLConnectionImpl3.getConnectTimeout();
        java.net.URL uRL12 = httpsURLConnectionImpl3.getURL();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 32 + "'", int11 == 32);
        org.junit.Assert.assertNull(uRL12);
    }

    @Test
    public void test0309() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0309");
        com.quakearts.rest.client.net.KeepAliveCache keepAliveCache0 = new com.quakearts.rest.client.net.KeepAliveCache();
        java.lang.Object obj2 = null;
        boolean boolean3 = keepAliveCache0.remove((java.lang.Object) true, obj2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
    }

    @Test
    public void test0310() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0310");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.getValue(503);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap3 = messageHeader0.getHeaders();
        messageHeader0.setIfNotSet("AuthenticationHeader: prefer null", "{}");
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strMap3);
    }

    @Test
    public void test0311() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0311");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findValue(305);
        java.lang.String str5 = headerParser1.findValue((int) (byte) 2);
        java.lang.String str8 = headerParser1.findValue("GET", "");
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
    }

    @Test
    public void test0312() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0312");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findValue(305);
        java.lang.String str5 = headerParser1.findValue((int) (byte) 2);
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HeaderParser headerParser8 = headerParser1.subsequence((int) (short) -1, 303);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: invalid start or end");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNull(str5);
    }

    @Test
    public void test0313() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0313");
        java.net.URLConnection.setDefaultRequestProperty("GET", "AuthenticationHeader: prefer null");
    }

    @Test
    public void test0314() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0314");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setFixedLengthStreamingMode(0);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Chunked encoding streaming mode set");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
    }

    @Test
    public void test0315() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0315");
        com.quakearts.rest.client.net.KeepAliveCache keepAliveCache0 = new com.quakearts.rest.client.net.KeepAliveCache();
        java.util.Optional<com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain> registeredDomainOptional2 = com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain.from("");
        boolean boolean3 = keepAliveCache0.equals((java.lang.Object) registeredDomainOptional2);
        keepAliveCache0.clear();
        int int5 = keepAliveCache0.size();
        com.quakearts.rest.client.net.HttpClient httpClient6 = null;
        // The following exception was thrown during execution in test generation
        try {
            keepAliveCache0.remove(httpClient6, (java.lang.Object) (short) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(registeredDomainOptional2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
    }

    @Test
    public void test0316() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0316");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream4 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture3);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream4.flush();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0317() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0317");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = posterOutputStream0.toString("GET");
            org.junit.Assert.fail("Expected exception of type java.io.UnsupportedEncodingException; message: GET");
        } catch (java.io.UnsupportedEncodingException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0318() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0318");
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
        java.io.PrintStream printStream19 = null;
        // The following exception was thrown during execution in test generation
        try {
            messageHeader0.print(printStream19);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + (-1L) + "'", long15 == (-1L));
    }

    @Test
    public void test0319() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0319");
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
        java.io.InputStream inputStream19 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture20 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream21 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream19, httpCapture20);
        com.quakearts.rest.client.net.HttpCapture httpCapture22 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream23 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream19, httpCapture22);
        java.net.URL uRL24 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource26 = new com.quakearts.rest.client.net.ProgressSource(uRL24, "");
        long long27 = progressSource26.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream29 = new com.quakearts.rest.client.net.MeteredStream(inputStream19, progressSource26, (long) (byte) 1);
        java.net.URL uRL30 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource32 = new com.quakearts.rest.client.net.ProgressSource(uRL30, "");
        progressSource32.close();
        boolean boolean34 = progressSource32.connected();
        progressSource32.finishTracking();
        java.net.URL uRL36 = null;
        com.quakearts.rest.client.net.ProgressSource.State state39 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent42 = new com.quakearts.rest.client.net.ProgressEvent(progressSource32, uRL36, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state39, (long) 300, (-1L));
        com.quakearts.rest.client.net.ProgressSource progressSource43 = new com.quakearts.rest.client.net.ProgressSource(progressSource32);
        com.quakearts.rest.client.net.MeteredStream meteredStream45 = new com.quakearts.rest.client.net.MeteredStream((java.io.InputStream) meteredStream29, progressSource32, (long) 32);
        // The following exception was thrown during execution in test generation
        try {
            messageHeader0.parseHeader((java.io.InputStream) meteredStream29);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long15 + "' != '" + (-1L) + "'", long15 == (-1L));
        org.junit.Assert.assertTrue("'" + long27 + "' != '" + (-1L) + "'", long27 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + false + "'", boolean34 == false);
    }

    @Test
    public void test0320() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0320");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream3 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray4 = posterOutputStream3.toByteArray();
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream2.write(byteArray4, (int) (byte) 1, 405);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 1");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray4);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray4), "[]");
    }

    @Test
    public void test0321() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0321");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        boolean boolean4 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test0322() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0322");
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
            httpURLConnectionImpl5.addRequestProperty("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal character(s) in message header field: com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
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
    public void test0323() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0323");
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
            java.io.InputStream inputStream13 = httpURLConnectionImpl5.getInputStream();
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
    public void test0324() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0324");
        com.quakearts.rest.client.net.http.AuthCacheValue.Type type0 = com.quakearts.rest.client.net.http.AuthCacheValue.Type.PROXY;
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + com.quakearts.rest.client.net.http.AuthCacheValue.Type.PROXY + "'", type0.equals(com.quakearts.rest.client.net.http.AuthCacheValue.Type.PROXY));
    }

    @Test
    public void test0325() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0325");
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
            long long14 = httpsURLConnectionImpl6.getExpiration();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
    }

    @Test
    public void test0326() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0326");
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
            meteredStream10.mark(305);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0327() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0327");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader1.set(406, "content/unknown", "hi!");
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader8 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        java.lang.String str10 = messageHeader1.getValue(0);
        java.lang.String str12 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.io.PrintStream printStream13 = null;
        // The following exception was thrown during execution in test generation
        try {
            messageHeader1.print(printStream13);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "hi!" + "'", str10, "hi!");
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0328() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0328");
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
            httpURLConnectionImpl5.setRequestProperty("AuthenticationHeader: prefer null", "hi!");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Illegal character(s) in message header field: AuthenticationHeader: prefer null");
        } catch (java.lang.IllegalArgumentException e) {
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
    public void test0329() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0329");
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
    public void test0330() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0330");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray1 = posterOutputStream0.toByteArray();
        byte[] byteArray2 = posterOutputStream0.toByteArray();
        java.lang.String str3 = posterOutputStream0.toString();
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray1), "[]");
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray2), "[]");
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "" + "'", str3, "");
    }

    @Test
    public void test0331() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0331");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        boolean boolean10 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.disconnect();
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream12 = httpsURLConnectionImpl3.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test0332() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0332");
        com.quakearts.rest.client.net.https.HostnameChecker hostnameChecker1 = com.quakearts.rest.client.net.https.HostnameChecker.getInstance((byte) 2);
        java.security.cert.X509Certificate x509Certificate3 = null;
        // The following exception was thrown during execution in test generation
        try {
            hostnameChecker1.match("{size=10 nkeys=1  {get} }", x509Certificate3, true);
            org.junit.Assert.fail("Expected exception of type java.security.cert.CertificateException; message: Illegal given domain name: {size=10 nkeys=1  {get} }");
        } catch (java.security.cert.CertificateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameChecker1);
    }

    @Test
    public void test0333() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0333");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream4 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture3);
        byte[] byteArray6 = new byte[] { (byte) 100 };
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream4.write(byteArray6);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray6), "[100]");
    }

    @Test
    public void test0334() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0334");
        java.io.PrintStream printStream0 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.ChunkedOutputStream chunkedOutputStream2 = new com.quakearts.rest.client.net.ChunkedOutputStream(printStream0, 403);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Null output stream");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0335() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0335");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        javax.net.ssl.SSLSocketFactory sSLSocketFactory9 = httpsURLConnectionImpl3.getSSLSocketFactory();
        java.net.URL uRL10 = null;
        java.net.Proxy proxy11 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler12 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl13 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL10, proxy11, httpsHandler12);
        javax.net.ssl.HostnameVerifier hostnameVerifier14 = httpsURLConnectionImpl13.getHostnameVerifier();
        httpsURLConnectionImpl13.setAllowUserInteraction(false);
        boolean boolean17 = httpsURLConnectionImpl13.getDefaultUseCaches();
        httpsURLConnectionImpl13.disconnect();
        java.net.URL uRL19 = httpsURLConnectionImpl13.getURL();
        java.lang.Class<?> wildcardClass20 = httpsURLConnectionImpl13.getClass();
        java.net.URL uRL21 = null;
        java.net.Proxy proxy22 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler23 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl24 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL21, proxy22, httpsHandler23);
        javax.net.ssl.HostnameVerifier hostnameVerifier25 = httpsURLConnectionImpl24.getHostnameVerifier();
        httpsURLConnectionImpl24.setAllowUserInteraction(false);
        boolean boolean28 = httpsURLConnectionImpl24.getDefaultUseCaches();
        httpsURLConnectionImpl24.disconnect();
        java.net.URL uRL30 = httpsURLConnectionImpl24.getURL();
        java.lang.Class<?> wildcardClass31 = httpsURLConnectionImpl24.getClass();
        java.net.URL uRL32 = null;
        java.net.Proxy proxy33 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler34 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl35 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL32, proxy33, httpsHandler34);
        javax.net.ssl.HostnameVerifier hostnameVerifier36 = httpsURLConnectionImpl35.getHostnameVerifier();
        httpsURLConnectionImpl35.setAllowUserInteraction(false);
        boolean boolean39 = httpsURLConnectionImpl35.getDefaultUseCaches();
        httpsURLConnectionImpl35.disconnect();
        java.net.URL uRL41 = httpsURLConnectionImpl35.getURL();
        java.lang.Class<?> wildcardClass42 = httpsURLConnectionImpl35.getClass();
        java.net.URL uRL43 = null;
        java.net.Proxy proxy44 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler45 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl46 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL43, proxy44, httpsHandler45);
        javax.net.ssl.HostnameVerifier hostnameVerifier47 = httpsURLConnectionImpl46.getHostnameVerifier();
        httpsURLConnectionImpl46.setAllowUserInteraction(false);
        boolean boolean50 = httpsURLConnectionImpl46.getDefaultUseCaches();
        httpsURLConnectionImpl46.disconnect();
        java.net.URL uRL52 = httpsURLConnectionImpl46.getURL();
        java.lang.Class<?> wildcardClass53 = httpsURLConnectionImpl46.getClass();
        java.net.URL uRL54 = null;
        java.net.Proxy proxy55 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler56 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl57 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL54, proxy55, httpsHandler56);
        javax.net.ssl.HostnameVerifier hostnameVerifier58 = httpsURLConnectionImpl57.getHostnameVerifier();
        httpsURLConnectionImpl57.setAllowUserInteraction(false);
        boolean boolean61 = httpsURLConnectionImpl57.getDefaultUseCaches();
        httpsURLConnectionImpl57.disconnect();
        java.net.URL uRL63 = httpsURLConnectionImpl57.getURL();
        java.lang.Class<?> wildcardClass64 = httpsURLConnectionImpl57.getClass();
        java.net.URL uRL65 = null;
        java.net.Proxy proxy66 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler67 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl68 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL65, proxy66, httpsHandler67);
        javax.net.ssl.HostnameVerifier hostnameVerifier69 = httpsURLConnectionImpl68.getHostnameVerifier();
        httpsURLConnectionImpl68.setAllowUserInteraction(false);
        boolean boolean72 = httpsURLConnectionImpl68.getDefaultUseCaches();
        httpsURLConnectionImpl68.disconnect();
        java.net.URL uRL74 = httpsURLConnectionImpl68.getURL();
        java.lang.Class<?> wildcardClass75 = httpsURLConnectionImpl68.getClass();
        java.lang.Class[] classArray76 = new java.lang.Class[] { wildcardClass20, wildcardClass31, wildcardClass42, wildcardClass53, wildcardClass64, wildcardClass75 };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj77 = httpsURLConnectionImpl3.getContent(classArray76);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
// flaky:         org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertNotNull(sSLSocketFactory9);
        org.junit.Assert.assertNotNull(hostnameVerifier14);
// flaky:         org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + true + "'", boolean17 == true);
        org.junit.Assert.assertNull(uRL19);
        org.junit.Assert.assertNotNull(wildcardClass20);
        org.junit.Assert.assertNotNull(hostnameVerifier25);
// flaky:         org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + true + "'", boolean28 == true);
        org.junit.Assert.assertNull(uRL30);
        org.junit.Assert.assertNotNull(wildcardClass31);
        org.junit.Assert.assertNotNull(hostnameVerifier36);
// flaky:         org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + true + "'", boolean39 == true);
        org.junit.Assert.assertNull(uRL41);
        org.junit.Assert.assertNotNull(wildcardClass42);
        org.junit.Assert.assertNotNull(hostnameVerifier47);
// flaky:         org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + true + "'", boolean50 == true);
        org.junit.Assert.assertNull(uRL52);
        org.junit.Assert.assertNotNull(wildcardClass53);
        org.junit.Assert.assertNotNull(hostnameVerifier58);
// flaky:         org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + true + "'", boolean61 == true);
        org.junit.Assert.assertNull(uRL63);
        org.junit.Assert.assertNotNull(wildcardClass64);
        org.junit.Assert.assertNotNull(hostnameVerifier69);
// flaky:         org.junit.Assert.assertTrue("'" + boolean72 + "' != '" + true + "'", boolean72 == true);
        org.junit.Assert.assertNull(uRL74);
        org.junit.Assert.assertNotNull(wildcardClass75);
        org.junit.Assert.assertNotNull(classArray76);
    }

    @Test
    public void test0336() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0336");
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
    public void test0337() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0337");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "AuthenticationHeader: prefer null");
    }

    @Test
    public void test0338() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0338");
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
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission15 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
// flaky:         org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test0339() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0339");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setRequestMethod("{size=10 nkeys=1  {hi!} }");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connect in progress");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0340() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0340");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL1 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL1, "");
        long long4 = progressSource3.getExpected();
        progressMonitor0.registerSource(progressSource3);
        progressSource3.setContentType("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + long4 + "' != '" + (-1L) + "'", long4 == (-1L));
    }

    @Test
    public void test0341() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0341");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray1 = posterOutputStream0.toByteArray();
        byte[] byteArray2 = posterOutputStream0.toByteArray();
        int int3 = posterOutputStream0.size();
        org.junit.Assert.assertNotNull(byteArray1);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray1), "[]");
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray2), "[]");
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test0342() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0342");
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
            java.lang.String str14 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
    }

    @Test
    public void test0343() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0343");
        java.lang.String str1 = java.net.URLConnection.getDefaultRequestProperty("{}");
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test0344() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0344");
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
            boolean boolean38 = keepAliveStream36.hurry();
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
    public void test0345() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0345");
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
            int int42 = chunkedInputStream41.read();
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
    public void test0346() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0346");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream3 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture2);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream4 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray5 = posterOutputStream4.toByteArray();
        byte[] byteArray6 = posterOutputStream4.toByteArray();
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream3.write(byteArray6, 400, (int) 's');
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray5), "[]");
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray6), "[]");
    }

    @Test
    public void test0347() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0347");
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
            java.security.cert.Certificate[] certificateArray14 = httpsURLConnectionImpl3.getLocalCertificates();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
    }

    @Test
    public void test0348() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0348");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        boolean boolean8 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.setRequestMethod("GET");
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream11 = httpsURLConnectionImpl3.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0349() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0349");
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
            java.io.InputStream inputStream10 = httpsURLConnectionImpl3.getInputStream();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0350() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0350");
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
        // The following exception was thrown during execution in test generation
        try {
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap13 = httpsURLConnectionImpl3.getHeaderFields();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test0351() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0351");
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
        java.net.URL uRL12 = httpsURLConnectionImpl3.getURL();
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream13 = httpsURLConnectionImpl3.getInputStream();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str11, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertNull(uRL12);
    }

    @Test
    public void test0352() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0352");
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
            boolean boolean27 = meteredStream26.markSupported();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0353() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0353");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.getValue(503);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap4 = messageHeader1.getHeaders();
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader5 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        com.quakearts.rest.client.net.HeaderParser headerParser6 = authenticationHeader5.headerParser();
        com.quakearts.rest.client.net.HeaderParser headerParser7 = authenticationHeader5.headerParser();
        boolean boolean8 = authenticationHeader5.hasPreferredParserPresent();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNotNull(strMap4);
        org.junit.Assert.assertNull(headerParser6);
        org.junit.Assert.assertNull(headerParser7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0354() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0354");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str13 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
    }

    @Test
    public void test0355() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0355");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("GET");
        java.lang.String str4 = headerParser1.findValue("hi!", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.lang.String str6 = headerParser1.findValue("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        java.lang.String str8 = headerParser1.findValue((int) (byte) 0);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str4, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertNull(str8);
    }

    @Test
    public void test0356() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0356");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.addRequestProperty("", "hi!");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str11 = httpsURLConnectionImpl3.getCipherSuite();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0357() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0357");
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
    public void test0358() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0358");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl5.doTunneling();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
    }

    @Test
    public void test0359() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0359");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(100);
        httpsURLConnectionImpl3.setDefaultUseCaches(true);
        // The following exception was thrown during execution in test generation
        try {
            int int12 = httpsURLConnectionImpl3.getResponseCode();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test0360() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0360");
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
            meteredStream10.mark(0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0361() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0361");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.io.InputStream inputStream1 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream3 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture2);
        com.quakearts.rest.client.net.HttpCapture httpCapture4 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream5 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream1, httpCapture4);
        messageHeader0.parseHeader(inputStream1);
        java.util.Iterator<java.lang.String> strItor8 = messageHeader0.multiValueIterator("content/unknown");
        org.junit.Assert.assertNotNull(strItor8);
    }

    @Test
    public void test0362() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0362");
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
        // The following exception was thrown during execution in test generation
        try {
            meteredStream10.mark((int) (byte) 100);
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
    public void test0363() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0363");
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
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode((long) (byte) 0);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0364() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0364");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str15 = httpsURLConnectionImpl3.getCipherSuite();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
    }

    @Test
    public void test0365() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0365");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int1 = progressMonitor0.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressListener progressListener2 = null;
        progressMonitor0.addProgressListener(progressListener2);
        com.quakearts.rest.client.net.ProgressListener progressListener4 = null;
        progressMonitor0.addProgressListener(progressListener4);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8192 + "'", int1 == 8192);
    }

    @Test
    public void test0366() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0366");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream4 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture3);
        byte[] byteArray11 = new byte[] { (byte) 2, (byte) 100, (byte) -1, (byte) 0, (byte) 0, (byte) 1 };
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream4.write(byteArray11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray11), "[2, 100, -1, 0, 0, 1]");
    }

    @Test
    public void test0367() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0367");
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
            java.io.OutputStream outputStream19 = httpURLConnectionImpl5.getOutputStream();
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
    public void test0368() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0368");
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
        boolean boolean15 = httpsURLConnectionImpl6.getAllowUserInteraction();
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
        org.junit.Assert.assertTrue("'" + long12 + "' != '" + 0L + "'", long12 == 0L);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0369() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0369");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream3 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture2);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream4 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray5 = posterOutputStream4.toByteArray();
        httpCaptureOutputStream3.write(byteArray5);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream7 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray8 = posterOutputStream7.toByteArray();
        byte[] byteArray9 = posterOutputStream7.toByteArray();
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream3.write(byteArray9, 403, 404);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: 403");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray5), "[]");
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray8), "[]");
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray9), "[]");
    }

    @Test
    public void test0370() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0370");
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
        int int22 = messageHeader0.getKey("hi!");
        org.junit.Assert.assertNotNull(strItor11);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertNotNull(strMap20);
        org.junit.Assert.assertTrue("'" + int22 + "' != '" + (-1) + "'", int22 == (-1));
    }

    @Test
    public void test0371() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0371");
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
        // The following exception was thrown during execution in test generation
        try {
            int int12 = httpsURLConnectionImpl3.getResponseCode();
            org.junit.Assert.fail("Expected exception of type com.quakearts.rest.client.exception.HttpClientRuntimeException; message: java.lang.NullPointerException");
        } catch (com.quakearts.rest.client.exception.HttpClientRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + long9 + "' != '" + 0L + "'", long9 == 0L);
    }

    @Test
    public void test0372() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0372");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        httpsURLConnectionImpl3.setInstanceFollowRedirects(true);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = httpsURLConnectionImpl3.getHeaderFieldKey(411);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0373() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0373");
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
            java.io.OutputStream outputStream15 = httpsURLConnectionImpl3.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
    }

    @Test
    public void test0374() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0374");
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
            boolean boolean44 = meteredStream10.markSupported();
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
    public void test0375() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0375");
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
            java.lang.String str10 = httpsURLConnectionImpl3.getHeaderField(400);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0376() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0376");
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
    public void test0377() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0377");
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
            java.io.InputStream inputStream13 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.openConnectionCheckRedirects((java.net.URLConnection) httpsURLConnectionImpl3);
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNotNull(sSLSocketFactory11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
    }

    @Test
    public void test0378() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0378");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setUseCaches(false);
        boolean boolean8 = httpsURLConnectionImpl3.getDoOutput();
        java.net.URL uRL9 = null;
        java.net.Proxy proxy10 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler11 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl12 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL9, proxy10, httpsHandler11);
        javax.net.ssl.HostnameVerifier hostnameVerifier13 = httpsURLConnectionImpl12.getHostnameVerifier();
        httpsURLConnectionImpl12.setAllowUserInteraction(false);
        boolean boolean16 = httpsURLConnectionImpl12.getDefaultUseCaches();
        httpsURLConnectionImpl12.disconnect();
        java.net.URL uRL18 = httpsURLConnectionImpl12.getURL();
        java.lang.Class<?> wildcardClass19 = httpsURLConnectionImpl12.getClass();
        java.lang.Class[] classArray20 = new java.lang.Class[] { wildcardClass19 };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj21 = httpsURLConnectionImpl3.getContent(classArray20);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(hostnameVerifier13);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertNull(uRL18);
        org.junit.Assert.assertNotNull(wildcardClass19);
        org.junit.Assert.assertNotNull(classArray20);
    }

    @Test
    public void test0379() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0379");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        posterOutputStream0.flush();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str4 = posterOutputStream0.toString("GET");
            org.junit.Assert.fail("Expected exception of type java.io.UnsupportedEncodingException; message: GET");
        } catch (java.io.UnsupportedEncodingException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0380() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0380");
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
            java.lang.String str13 = httpURLConnectionImpl5.getHeaderField(200);
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
    public void test0381() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0381");
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
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream11 = httpsURLConnectionImpl3.getInputStream();
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
    public void test0382() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0382");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader1.set(406, "content/unknown", "hi!");
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader8 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        boolean boolean9 = authenticationHeader8.hasPreferredParserPresent();
        com.quakearts.rest.client.net.HeaderParser headerParser10 = authenticationHeader8.headerParser();
        java.lang.String str11 = authenticationHeader8.scheme();
        boolean boolean12 = authenticationHeader8.hasPreferredParserPresent();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(headerParser10);
        org.junit.Assert.assertNull(str11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test0383() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0383");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream2 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream2.flush();
        posterOutputStream0.writeTo((java.io.OutputStream) posterOutputStream2);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = posterOutputStream2.toString("hi!");
            org.junit.Assert.fail("Expected exception of type java.io.UnsupportedEncodingException; message: hi!");
        } catch (java.io.UnsupportedEncodingException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0384() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0384");
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
        // The following exception was thrown during execution in test generation
        try {
            int int12 = httpsURLConnectionImpl3.getResponseCode();
            org.junit.Assert.fail("Expected exception of type com.quakearts.rest.client.exception.HttpClientRuntimeException; message: java.lang.NullPointerException");
        } catch (com.quakearts.rest.client.exception.HttpClientRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
// flaky:         org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0385() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0385");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue6 = authCacheImpl0.get("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue9 = authCacheImpl0.get("{size=10 nkeys=1  {hi!} }", "GET");
        org.junit.Assert.assertNull(authCacheValue3);
        org.junit.Assert.assertNull(authCacheValue6);
        org.junit.Assert.assertNull(authCacheValue9);
    }

    @Test
    public void test0386() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0386");
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
        byte[] byteArray45 = posterOutputStream44.toByteArray();
        byte[] byteArray46 = posterOutputStream44.toByteArray();
        // The following exception was thrown during execution in test generation
        try {
            int int47 = meteredStream10.read(byteArray46);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
        org.junit.Assert.assertNotNull(byteArray45);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray45), "[]");
        org.junit.Assert.assertNotNull(byteArray46);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray46), "[]");
    }

    @Test
    public void test0387() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0387");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        httpsURLConnectionImpl3.setChunkedStreamingMode((int) '#');
    }

    @Test
    public void test0388() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0388");
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
        byte[] byteArray45 = new byte[] { (byte) 1, (byte) 100, (byte) 100, (byte) 0, (byte) -1 };
        // The following exception was thrown during execution in test generation
        try {
            int int48 = keepAliveStream36.read(byteArray45, 505, 206);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertNotNull(byteArray45);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray45), "[1, 100, 100, 0, -1]");
    }

    @Test
    public void test0389() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0389");
        java.net.URLConnection.setDefaultRequestProperty("AuthenticationHeader: prefer null", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
    }

    @Test
    public void test0390() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0390");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        boolean boolean9 = httpsURLConnectionImpl3.getAllowUserInteraction();
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream10 = httpsURLConnectionImpl3.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
// flaky:         org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0391() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0391");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream4 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture3);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream5 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray6 = posterOutputStream5.toByteArray();
        byte[] byteArray7 = posterOutputStream5.toByteArray();
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream4.write(byteArray7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray6), "[]");
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray7), "[]");
    }

    @Test
    public void test0392() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0392");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        httpsURLConnectionImpl3.setUseCaches(true);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = httpsURLConnectionImpl3.getHeaderFieldKey(408);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0393() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0393");
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
        org.slf4j.Logger logger17 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass18 = logger17.getClass();
        java.net.URL uRL19 = null;
        java.net.Proxy proxy20 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler21 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl22 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL19, proxy20, httpsHandler21);
        javax.net.ssl.HostnameVerifier hostnameVerifier23 = httpsURLConnectionImpl22.getHostnameVerifier();
        httpsURLConnectionImpl22.setAllowUserInteraction(false);
        boolean boolean26 = httpsURLConnectionImpl22.getDefaultUseCaches();
        httpsURLConnectionImpl22.disconnect();
        java.net.URL uRL28 = httpsURLConnectionImpl22.getURL();
        java.lang.Class<?> wildcardClass29 = httpsURLConnectionImpl22.getClass();
        java.net.URL uRL30 = null;
        java.net.Proxy proxy31 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler32 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl33 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL30, proxy31, httpsHandler32);
        javax.net.ssl.HostnameVerifier hostnameVerifier34 = httpsURLConnectionImpl33.getHostnameVerifier();
        httpsURLConnectionImpl33.setAllowUserInteraction(false);
        boolean boolean37 = httpsURLConnectionImpl33.getDefaultUseCaches();
        httpsURLConnectionImpl33.disconnect();
        java.net.URL uRL39 = httpsURLConnectionImpl33.getURL();
        java.lang.Class<?> wildcardClass40 = httpsURLConnectionImpl33.getClass();
        org.slf4j.Logger logger41 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass42 = logger41.getClass();
        java.net.URL uRL43 = null;
        java.net.Proxy proxy44 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler45 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl46 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL43, proxy44, httpsHandler45);
        javax.net.ssl.HostnameVerifier hostnameVerifier47 = httpsURLConnectionImpl46.getHostnameVerifier();
        httpsURLConnectionImpl46.setAllowUserInteraction(false);
        boolean boolean50 = httpsURLConnectionImpl46.getDefaultUseCaches();
        httpsURLConnectionImpl46.disconnect();
        java.net.URL uRL52 = httpsURLConnectionImpl46.getURL();
        java.lang.Class<?> wildcardClass53 = httpsURLConnectionImpl46.getClass();
        java.net.URL uRL54 = null;
        java.net.Proxy proxy55 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler56 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl57 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL54, proxy55, httpsHandler56);
        javax.net.ssl.HostnameVerifier hostnameVerifier58 = httpsURLConnectionImpl57.getHostnameVerifier();
        httpsURLConnectionImpl57.setAllowUserInteraction(false);
        boolean boolean61 = httpsURLConnectionImpl57.getDefaultUseCaches();
        httpsURLConnectionImpl57.disconnect();
        java.net.URL uRL63 = httpsURLConnectionImpl57.getURL();
        java.lang.Class<?> wildcardClass64 = httpsURLConnectionImpl57.getClass();
        java.lang.Class[] classArray65 = new java.lang.Class[] { wildcardClass18, wildcardClass29, wildcardClass40, wildcardClass42, wildcardClass53, wildcardClass64 };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj66 = httpsURLConnectionImpl5.getContent(classArray65);
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertNotNull(logger17);
        org.junit.Assert.assertNotNull(wildcardClass18);
        org.junit.Assert.assertNotNull(hostnameVerifier23);
// flaky:         org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNull(uRL28);
        org.junit.Assert.assertNotNull(wildcardClass29);
        org.junit.Assert.assertNotNull(hostnameVerifier34);
// flaky:         org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertNull(uRL39);
        org.junit.Assert.assertNotNull(wildcardClass40);
        org.junit.Assert.assertNotNull(logger41);
        org.junit.Assert.assertNotNull(wildcardClass42);
        org.junit.Assert.assertNotNull(hostnameVerifier47);
// flaky:         org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + false + "'", boolean50 == false);
        org.junit.Assert.assertNull(uRL52);
        org.junit.Assert.assertNotNull(wildcardClass53);
        org.junit.Assert.assertNotNull(hostnameVerifier58);
// flaky:         org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + false + "'", boolean61 == false);
        org.junit.Assert.assertNull(uRL63);
        org.junit.Assert.assertNotNull(wildcardClass64);
        org.junit.Assert.assertNotNull(classArray65);
    }

    @Test
    public void test0394() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0394");
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
            java.security.Permission permission11 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
// flaky:         org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
    }

    @Test
    public void test0395() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0395");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        long long7 = httpsURLConnectionImpl3.getIfModifiedSince();
        // The following exception was thrown during execution in test generation
        try {
            long long8 = httpsURLConnectionImpl3.getContentLengthLong();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + long7 + "' != '" + 0L + "'", long7 == 0L);
    }

    @Test
    public void test0396() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0396");
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
            int int43 = chunkedInputStream41.read();
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
    public void test0397() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0397");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = httpURLConnectionImpl5.getHeaderFieldKey(100);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test0398() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0398");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "AuthenticationHeader: prefer null", (long) 201);
    }

    @Test
    public void test0399() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0399");
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
        posterOutputStream44.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture46 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream47 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream44, httpCapture46);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream48 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray49 = posterOutputStream48.toByteArray();
        httpCaptureOutputStream47.write(byteArray49);
        // The following exception was thrown during execution in test generation
        try {
            int int51 = httpCaptureInputStream43.read(byteArray49);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str40);
        org.junit.Assert.assertNotNull(byteArray49);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray49), "[]");
    }

    @Test
    public void test0400() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0400");
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
        byte[] byteArray38 = null;
        // The following exception was thrown during execution in test generation
        try {
            int int41 = keepAliveStream36.read(byteArray38, (int) (short) 1, 403);
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
    public void test0401() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0401");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        boolean boolean8 = httpsURLConnectionImpl3.getDefaultUseCaches();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = httpsURLConnectionImpl3.getContentEncoding();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
    }

    @Test
    public void test0402() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0402");
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
        boolean boolean13 = httpURLConnectionImpl8.streaming();
        int int14 = httpURLConnectionImpl8.getReadTimeout();
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient15 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, proxy1, 500, httpURLConnectionImpl8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState10 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState10.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
    }

    @Test
    public void test0403() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0403");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader1.set(406, "content/unknown", "hi!");
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader8 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        boolean boolean9 = authenticationHeader8.hasPreferredParserPresent();
        com.quakearts.rest.client.net.HeaderParser headerParser10 = authenticationHeader8.headerParser();
        java.lang.String str11 = authenticationHeader8.scheme();
        java.lang.String str12 = authenticationHeader8.toString();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(headerParser10);
        org.junit.Assert.assertNull(str11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "AuthenticationHeader: prefer null" + "'", str12, "AuthenticationHeader: prefer null");
    }

    @Test
    public void test0404() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0404");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        java.net.URL uRL3 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource5 = new com.quakearts.rest.client.net.ProgressSource(uRL3, "");
        long long6 = progressSource5.getExpected();
        com.quakearts.rest.client.net.MeteredStream meteredStream8 = new com.quakearts.rest.client.net.MeteredStream(inputStream0, progressSource5, (long) (short) 0);
        // The following exception was thrown during execution in test generation
        try {
            meteredStream8.mark(0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + (-1L) + "'", long6 == (-1L));
    }

    @Test
    public void test0405() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0405");
        java.security.Principal principal1 = null;
        boolean boolean2 = com.quakearts.rest.client.net.https.HostnameChecker.match("hi!", principal1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test0406() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0406");
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
    public void test0407() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0407");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        boolean boolean10 = httpsURLConnectionImpl3.getAllowUserInteraction();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test0408() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0408");
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
            hostnameChecker8.match("content/unknown", x509Certificate11, false);
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
    public void test0409() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0409");
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
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
    }

    @Test
    public void test0410() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0410");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getInstanceFollowRedirects();
        long long7 = httpsURLConnectionImpl3.getIfModifiedSince();
        // The following exception was thrown during execution in test generation
        try {
            int int8 = httpsURLConnectionImpl3.getContentLength();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + long7 + "' != '" + 0L + "'", long7 == 0L);
    }

    @Test
    public void test0411() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0411");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setChunkedStreamingMode((int) (short) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Fixed length streaming mode set");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertNotNull(sSLSocketFactory10);
    }

    @Test
    public void test0412() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0412");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.getValue(503);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap4 = messageHeader1.getHeaders();
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader5 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        com.quakearts.rest.client.net.HeaderParser headerParser6 = authenticationHeader5.headerParser();
        java.lang.String str7 = authenticationHeader5.scheme();
        com.quakearts.rest.client.net.HeaderParser headerParser8 = authenticationHeader5.headerParser();
        java.lang.String str9 = authenticationHeader5.raw();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNotNull(strMap4);
        org.junit.Assert.assertNull(headerParser6);
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertNull(headerParser8);
        org.junit.Assert.assertNull(str9);
    }

    @Test
    public void test0413() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0413");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        boolean boolean6 = httpsURLConnectionImpl3.getAllowUserInteraction();
        // The following exception was thrown during execution in test generation
        try {
            int int9 = httpsURLConnectionImpl3.getHeaderFieldInt("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", (int) ' ');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0414() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0414");
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
        // The following exception was thrown during execution in test generation
        try {
            long long13 = httpsURLConnectionImpl6.getLastModified();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
    }

    @Test
    public void test0415() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0415");
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
            java.io.OutputStream outputStream11 = httpsURLConnectionImpl3.getOutputStream();
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
    public void test0416() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0416");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        boolean boolean8 = httpsURLConnectionImpl3.getAllowUserInteraction();
        httpsURLConnectionImpl3.setReadTimeout(303);
        httpsURLConnectionImpl3.setDoInput(false);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0417() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0417");
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
        httpsURLConnectionImpl6.setDefaultUseCaches(false);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str16 = httpsURLConnectionImpl6.getContentType();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
    }

    @Test
    public void test0418() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0418");
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
            httpURLConnectionImpl5.doTunneling();
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
    public void test0419() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0419");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream2.write(200);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0420() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0420");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream4 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture3);
        byte[] byteArray10 = new byte[] { (byte) 0, (byte) 1, (byte) 0, (byte) -1, (byte) -1 };
        // The following exception was thrown during execution in test generation
        try {
            int int13 = httpCaptureInputStream4.read(byteArray10, 505, 404);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray10);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray10), "[0, 1, 0, -1, -1]");
    }

    @Test
    public void test0421() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0421");
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
        long long17 = progressEvent12.getProgress();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-1L) + "'", long13 == (-1L));
        org.junit.Assert.assertNull(state14);
        org.junit.Assert.assertNotNull(obj15);
        org.junit.Assert.assertEquals(obj15.toString(), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj15), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj15), "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]" + "'", str16, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        org.junit.Assert.assertTrue("'" + long17 + "' != '" + 300L + "'", long17 == 300L);
    }

    @Test
    public void test0422() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0422");
        com.quakearts.rest.client.net.MessageHeader messageHeader1 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str3 = messageHeader1.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader1.set(406, "content/unknown", "hi!");
        com.quakearts.rest.client.net.http.AuthenticationHeader authenticationHeader8 = new com.quakearts.rest.client.net.http.AuthenticationHeader("content/unknown", messageHeader1);
        boolean boolean9 = authenticationHeader8.hasPreferredParserPresent();
        com.quakearts.rest.client.net.HeaderParser headerParser10 = authenticationHeader8.headerParser();
        java.lang.String str11 = authenticationHeader8.scheme();
        java.lang.String str12 = authenticationHeader8.scheme();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(headerParser10);
        org.junit.Assert.assertNull(str11);
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test0423() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0423");
        java.io.InputStream inputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream2 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream0, httpCapture1);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean3 = httpCaptureInputStream2.markSupported();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0424() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0424");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        boolean boolean6 = httpURLConnectionImpl5.usingProxy();
        httpURLConnectionImpl5.setDoInput(false);
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream9 = httpURLConnectionImpl5.getOutputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test0425() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0425");
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
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream3.close();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray9), "[]");
    }

    @Test
    public void test0426() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0426");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("GET");
        java.lang.String str3 = headerParser1.findKey(1);
        java.util.Iterator<java.lang.String> strItor4 = headerParser1.keys();
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNotNull(strItor4);
    }

    @Test
    public void test0427() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0427");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        progressSource2.finishTracking();
        java.net.URL uRL6 = null;
        com.quakearts.rest.client.net.ProgressSource.State state9 = null;
        com.quakearts.rest.client.net.ProgressEvent progressEvent12 = new com.quakearts.rest.client.net.ProgressEvent(progressSource2, uRL6, "hi!", "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", state9, (long) 300, (-1L));
        java.net.URL uRL13 = progressEvent12.getURL();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNull(uRL13);
    }

    @Test
    public void test0428() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0428");
        java.io.OutputStream outputStream0 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture1 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream2 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture1);
        com.quakearts.rest.client.net.HttpCapture httpCapture3 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream4 = new com.quakearts.rest.client.net.HttpCaptureOutputStream(outputStream0, httpCapture3);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream5 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray6 = posterOutputStream5.toByteArray();
        byte[] byteArray7 = posterOutputStream5.toByteArray();
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream4.write(byteArray7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray6), "[]");
        org.junit.Assert.assertNotNull(byteArray7);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray7), "[]");
    }

    @Test
    public void test0429() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0429");
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
        httpsURLConnectionImpl17.setAllowUserInteraction(false);
        boolean boolean21 = httpsURLConnectionImpl17.getDefaultUseCaches();
        httpsURLConnectionImpl17.disconnect();
        java.net.URL uRL23 = httpsURLConnectionImpl17.getURL();
        java.lang.Class<?> wildcardClass24 = httpsURLConnectionImpl17.getClass();
        org.slf4j.Logger logger25 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass26 = logger25.getClass();
        java.net.URL uRL27 = null;
        java.net.Proxy proxy28 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler29 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl30 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL27, proxy28, httpsHandler29);
        javax.net.ssl.HostnameVerifier hostnameVerifier31 = httpsURLConnectionImpl30.getHostnameVerifier();
        httpsURLConnectionImpl30.setAllowUserInteraction(false);
        boolean boolean34 = httpsURLConnectionImpl30.getDefaultUseCaches();
        httpsURLConnectionImpl30.disconnect();
        java.net.URL uRL36 = httpsURLConnectionImpl30.getURL();
        java.lang.Class<?> wildcardClass37 = httpsURLConnectionImpl30.getClass();
        org.slf4j.Logger logger38 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.getHttpLogger();
        java.lang.Class<?> wildcardClass39 = logger38.getClass();
        java.lang.Class[] classArray40 = new java.lang.Class[] { wildcardClass13, wildcardClass24, wildcardClass26, wildcardClass37, wildcardClass39 };
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj41 = httpsURLConnectionImpl3.getContent(classArray40);
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
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertNull(uRL23);
        org.junit.Assert.assertNotNull(wildcardClass24);
        org.junit.Assert.assertNotNull(logger25);
        org.junit.Assert.assertNotNull(wildcardClass26);
        org.junit.Assert.assertNotNull(hostnameVerifier31);
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + false + "'", boolean34 == false);
        org.junit.Assert.assertNull(uRL36);
        org.junit.Assert.assertNotNull(wildcardClass37);
        org.junit.Assert.assertNotNull(logger38);
        org.junit.Assert.assertNotNull(wildcardClass39);
        org.junit.Assert.assertNotNull(classArray40);
    }

    @Test
    public void test0430() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0430");
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
            long long13 = httpURLConnectionImpl5.getContentLengthLong();
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
    public void test0431() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0431");
        java.net.URLConnection.setDefaultRequestProperty("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "");
    }

    @Test
    public void test0432() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0432");
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
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str17 = httpURLConnectionImpl5.getHeaderField("");
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
        org.junit.Assert.assertNull(inputStream15);
    }

    @Test
    public void test0433() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0433");
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
            java.lang.String str20 = httpURLConnectionImpl5.getHeaderField("");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
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
    public void test0434() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0434");
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
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream14 = httpsURLConnectionImpl3.getInputStream();
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
    public void test0435() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0435");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = null;
        com.quakearts.rest.client.net.ProgressMonitor.setDefault(progressMonitor0);
    }

    @Test
    public void test0436() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0436");
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
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream39 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream39.reset();
        posterOutputStream39.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream42 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray43 = posterOutputStream42.toByteArray();
        byte[] byteArray44 = posterOutputStream42.toByteArray();
        posterOutputStream39.write(byteArray44);
        // The following exception was thrown during execution in test generation
        try {
            int int46 = keepAliveStream36.read(byteArray44);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNotNull(byteArray43);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray43), "[]");
        org.junit.Assert.assertNotNull(byteArray44);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray44), "[]");
    }

    @Test
    public void test0437() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0437");
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
        java.io.InputStream inputStream13 = httpURLConnectionImpl8.getErrorStream();
        httpURLConnectionImpl8.disconnect();
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HttpClient httpClient15 = com.quakearts.rest.client.net.HttpClient.createNew(uRL0, proxy1, 0, httpURLConnectionImpl8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState10 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState10.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNull(inputStream13);
    }

    @Test
    public void test0438() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0438");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setDoOutput(true);
        boolean boolean8 = httpsURLConnectionImpl3.getAllowUserInteraction();
        long long9 = httpsURLConnectionImpl3.getIfModifiedSince();
        httpsURLConnectionImpl3.setDoInput(false);
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream12 = httpsURLConnectionImpl3.getOutputStream();
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
    public void test0439() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0439");
        com.quakearts.rest.client.net.KeepAliveCache keepAliveCache0 = new com.quakearts.rest.client.net.KeepAliveCache();
        java.util.Optional<com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain> registeredDomainOptional2 = com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain.from("");
        boolean boolean3 = keepAliveCache0.equals((java.lang.Object) registeredDomainOptional2);
        com.quakearts.rest.client.net.HeaderParser headerParser5 = new com.quakearts.rest.client.net.HeaderParser("GET");
        boolean boolean6 = keepAliveCache0.containsValue((java.lang.Object) headerParser5);
        keepAliveCache0.clear();
        java.net.URL uRL8 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource10 = new com.quakearts.rest.client.net.ProgressSource(uRL8, "");
        boolean boolean11 = keepAliveCache0.containsValue((java.lang.Object) "");
        org.junit.Assert.assertNotNull(registeredDomainOptional2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0440() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0440");
        java.lang.Boolean boolean1 = com.quakearts.rest.client.net.NetProperties.getBoolean("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
    }

    @Test
    public void test0441() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0441");
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
        long long37 = progressSource30.getProgress();
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + long31 + "' != '" + (-1L) + "'", long31 == (-1L));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str33, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + long37 + "' != '" + 0L + "'", long37 == 0L);
    }

    @Test
    public void test0442() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0442");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.io.PrintStream printStream3 = null;
        // The following exception was thrown during execution in test generation
        try {
            messageHeader0.print(printStream3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str2);
    }

    @Test
    public void test0443() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0443");
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
        boolean boolean13 = httpsURLConnectionImpl3.getUseCaches();
        // The following exception was thrown during execution in test generation
        try {
            java.io.OutputStream outputStream14 = httpsURLConnectionImpl3.getOutputStream();
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
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }

    @Test
    public void test0444() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0444");
        com.quakearts.rest.client.net.http.ResponseCacheImpl responseCacheImpl0 = new com.quakearts.rest.client.net.http.ResponseCacheImpl();
        java.net.URI uRI1 = null;
        java.net.URL uRL2 = null;
        java.net.Proxy proxy3 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler4 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl5 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL2, proxy3, httpsHandler4);
        javax.net.ssl.HostnameVerifier hostnameVerifier6 = httpsURLConnectionImpl5.getHostnameVerifier();
        boolean boolean7 = httpsURLConnectionImpl5.getDoInput();
        boolean boolean8 = httpsURLConnectionImpl5.getInstanceFollowRedirects();
        httpsURLConnectionImpl5.setChunkedStreamingMode(10);
        java.lang.String str12 = httpsURLConnectionImpl5.getHeaderFieldKey(402);
        javax.net.ssl.SSLSocketFactory sSLSocketFactory13 = httpsURLConnectionImpl5.getSSLSocketFactory();
        java.net.CacheRequest cacheRequest14 = responseCacheImpl0.put(uRI1, (java.net.URLConnection) httpsURLConnectionImpl5);
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertNotNull(sSLSocketFactory13);
        org.junit.Assert.assertNull(cacheRequest14);
    }

    @Test
    public void test0445() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0445");
        com.quakearts.rest.client.net.KeepAliveCache keepAliveCache0 = new com.quakearts.rest.client.net.KeepAliveCache();
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
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor28 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL29 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource31 = new com.quakearts.rest.client.net.ProgressSource(uRL29, "");
        long long32 = progressSource31.getExpected();
        progressMonitor28.registerSource(progressSource31);
        java.lang.String str34 = progressSource31.toString();
        com.quakearts.rest.client.net.HttpClient httpClient36 = null;
        com.quakearts.rest.client.net.KeepAliveStream keepAliveStream37 = new com.quakearts.rest.client.net.KeepAliveStream((java.io.InputStream) meteredStream11, progressSource31, (long) 203, httpClient36);
        boolean boolean38 = keepAliveStream37.markSupported();
        keepAliveStream37.mark(505);
        boolean boolean41 = keepAliveCache0.containsKey((java.lang.Object) 505);
        org.junit.Assert.assertTrue("'" + long9 + "' != '" + (-1L) + "'", long9 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + long32 + "' != '" + (-1L) + "'", long32 == (-1L));
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" + "'", str34, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + false + "'", boolean38 == false);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + false + "'", boolean41 == false);
    }

    @Test
    public void test0446() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0446");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl6.setRequestMethod("{size=10 nkeys=1  {hi!} }");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connect in progress");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + 100L + "'", long14 == 100L);
    }

    @Test
    public void test0447() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0447");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        httpsURLConnectionImpl3.addRequestProperty("", "hi!");
        int int13 = httpsURLConnectionImpl3.getHeaderFieldInt("hi!", 100);
        long long14 = httpsURLConnectionImpl3.getDate();
        boolean boolean15 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setInstanceFollowRedirects(true);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 100 + "'", int13 == 100);
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + 0L + "'", long14 == 0L);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + true + "'", boolean15 == true);
    }

    @Test
    public void test0448() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0448");
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
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap11 = httpsURLConnectionImpl3.getRequestProperties();
        // The following exception was thrown during execution in test generation
        try {
            java.security.cert.Certificate[] certificateArray12 = httpsURLConnectionImpl3.getLocalCertificates();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(strMap11);
    }

    @Test
    public void test0449() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0449");
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
        java.lang.String str15 = progressEvent12.toString();
        long long16 = progressEvent12.getProgress();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + long13 + "' != '" + (-1L) + "'", long13 == (-1L));
        org.junit.Assert.assertNull(state14);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]" + "'", str15, "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        org.junit.Assert.assertTrue("'" + long16 + "' != '" + 300L + "'", long16 == 300L);
    }

    @Test
    public void test0450() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0450");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.http.HttpHandler httpHandler4 = new com.quakearts.rest.client.net.http.HttpHandler("", (int) (short) -1);
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl httpURLConnectionImpl5 = new com.quakearts.rest.client.net.http.HttpURLConnectionImpl(uRL0, proxy1, httpHandler4);
        int int6 = httpURLConnectionImpl5.getReadTimeout();
        com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState tunnelState7 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP;
        httpURLConnectionImpl5.setTunnelState(tunnelState7);
        boolean boolean9 = httpURLConnectionImpl5.streaming();
        boolean boolean10 = httpURLConnectionImpl5.streaming();
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
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test0451() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0451");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setAllowUserInteraction(false);
        boolean boolean7 = httpsURLConnectionImpl3.getDefaultUseCaches();
        httpsURLConnectionImpl3.disconnect();
        java.net.URL uRL9 = httpsURLConnectionImpl3.getURL();
        httpsURLConnectionImpl3.setInstanceFollowRedirects(false);
        int int12 = httpsURLConnectionImpl3.getReadTimeout();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(uRL9);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
    }

    @Test
    public void test0452() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0452");
        java.net.HttpURLConnection.setFollowRedirects(true);
    }

    @Test
    public void test0453() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0453");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        messageHeader0.set(406, "content/unknown", "hi!");
        messageHeader0.set(402, "GET", "");
        messageHeader0.set((int) (short) 1, "GET", "{size=10 nkeys=1  {hi!} }");
        messageHeader0.setIfNotSet("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "{size=10 nkeys=1  {hi!} }");
        org.junit.Assert.assertNull(str2);
    }

    @Test
    public void test0454() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0454");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.reset();
        com.quakearts.rest.client.net.HttpCapture httpCapture2 = null;
        com.quakearts.rest.client.net.HttpCaptureOutputStream httpCaptureOutputStream3 = new com.quakearts.rest.client.net.HttpCaptureOutputStream((java.io.OutputStream) posterOutputStream0, httpCapture2);
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream4 = new com.quakearts.rest.client.net.PosterOutputStream();
        byte[] byteArray5 = posterOutputStream4.toByteArray();
        httpCaptureOutputStream3.write(byteArray5);
        // The following exception was thrown during execution in test generation
        try {
            httpCaptureOutputStream3.flush();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray5);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray5), "[]");
    }

    @Test
    public void test0455() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0455");
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
        com.quakearts.rest.client.net.ProgressListener progressListener11 = null;
        progressMonitor0.addProgressListener(progressListener11);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8192 + "'", int1 == 8192);
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + (-1L) + "'", long6 == (-1L));
    }

    @Test
    public void test0456() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0456");
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
            long long34 = meteredStream10.skip((long) 206);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0457() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0457");
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
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream12 = com.quakearts.rest.client.net.http.HttpURLConnectionImpl.openConnectionCheckRedirects((java.net.URLConnection) httpsURLConnectionImpl3);
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
    }

    @Test
    public void test0458() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0458");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource2 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "");
        progressSource2.close();
        boolean boolean4 = progressSource2.connected();
        boolean boolean5 = progressSource2.connected();
        java.lang.String str6 = progressSource2.toString();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]" + "'", str6, "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test0459() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0459");
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
    public void test0460() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0460");
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
        java.lang.String str19 = httpsURLConnectionImpl5.getRequestProperty("");
        httpsURLConnectionImpl5.setIfModifiedSince(203L);
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "GET" + "'", str17, "GET");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "hi!" + "'", str19, "hi!");
    }

    @Test
    public void test0461() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0461");
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
        java.net.URL uRL12 = httpsURLConnectionImpl3.getURL();
        // The following exception was thrown during execution in test generation
        try {
            java.security.Principal principal13 = httpsURLConnectionImpl3.getLocalPrincipal();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: connection not yet open");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str8, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str11, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
        org.junit.Assert.assertNull(uRL12);
    }

    @Test
    public void test0462() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0462");
        java.net.URL uRL0 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource3 = new com.quakearts.rest.client.net.ProgressSource(uRL0, "", 0L);
    }

    @Test
    public void test0463() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0463");
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
            httpCaptureInputStream43.close();
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
    public void test0464() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0464");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap8 = httpsURLConnectionImpl3.getHeaderFields();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNotNull(strMap8);
    }

    @Test
    public void test0465() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0465");
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
        // The following exception was thrown during execution in test generation
        try {
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap22 = httpURLConnectionImpl5.getHeaderFields();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 0 + "'", int16 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState17 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState17.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
    }

    @Test
    public void test0466() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0466");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.getValue(503);
        messageHeader0.prepend("{size=10 nkeys=1  {hi!} }", "{size=10 nkeys=1  {hi!} }");
        messageHeader0.setIfNotSet("com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null", "AuthenticationHeader: prefer null");
        messageHeader0.add("{}", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
        org.junit.Assert.assertNull(str2);
    }

    @Test
    public void test0467() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0467");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor0 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int1 = progressMonitor0.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressMonitor.setDefault(progressMonitor0);
        java.net.URL uRL3 = null;
        boolean boolean5 = progressMonitor0.shouldMeterInput(uRL3, "GET");
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor6 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int7 = progressMonitor6.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressListener progressListener8 = null;
        progressMonitor6.addProgressListener(progressListener8);
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor10 = new com.quakearts.rest.client.net.ProgressMonitor();
        int int11 = progressMonitor10.getProgressUpdateThreshold();
        com.quakearts.rest.client.net.ProgressMonitor progressMonitor12 = new com.quakearts.rest.client.net.ProgressMonitor();
        java.net.URL uRL13 = null;
        com.quakearts.rest.client.net.ProgressSource progressSource15 = new com.quakearts.rest.client.net.ProgressSource(uRL13, "");
        long long16 = progressSource15.getExpected();
        progressMonitor12.registerSource(progressSource15);
        progressMonitor10.updateProgress(progressSource15);
        progressSource15.beginTracking();
        progressMonitor6.updateProgress(progressSource15);
        progressSource15.updateProgress((long) 204, (long) (byte) 10);
        progressMonitor0.updateProgress(progressSource15);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 8192 + "'", int1 == 8192);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 8192 + "'", int7 == 8192);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 8192 + "'", int11 == 8192);
        org.junit.Assert.assertTrue("'" + long16 + "' != '" + (-1L) + "'", long16 == (-1L));
    }

    @Test
    public void test0468() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0468");
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
        long long14 = httpsURLConnectionImpl3.getHeaderFieldLong("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", (long) (byte) 100);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNotNull(strMap11);
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + 100L + "'", long14 == 100L);
    }

    @Test
    public void test0469() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0469");
        java.io.PrintStream printStream0 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.ChunkedOutputStream chunkedOutputStream2 = new com.quakearts.rest.client.net.ChunkedOutputStream(printStream0, 504);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Null output stream");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0470() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0470");
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
        long long14 = httpsURLConnectionImpl3.getHeaderFieldDate("GET", 100L);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertNotNull(hostnameVerifier11);
        org.junit.Assert.assertTrue("'" + long14 + "' != '" + 100L + "'", long14 == 100L);
    }

    @Test
    public void test0471() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0471");
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
            int int42 = chunkedInputStream41.available();
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
    public void test0472() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0472");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl3.setFixedLengthStreamingMode((long) 1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Chunked encoding streaming mode set");
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
    public void test0473() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0473");
        int int0 = java.net.HttpURLConnection.HTTP_PROXY_AUTH;
        org.junit.Assert.assertTrue("'" + int0 + "' != '" + 407 + "'", int0 == 407);
    }

    @Test
    public void test0474() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0474");
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream0 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream0.flush();
        com.quakearts.rest.client.net.PosterOutputStream posterOutputStream2 = new com.quakearts.rest.client.net.PosterOutputStream();
        posterOutputStream2.flush();
        posterOutputStream0.writeTo((java.io.OutputStream) posterOutputStream2);
        byte[] byteArray11 = new byte[] { (byte) 2, (byte) 100, (byte) 1, (byte) 1, (byte) 10, (byte) 0 };
        // The following exception was thrown during execution in test generation
        try {
            posterOutputStream0.write(byteArray11, 206, 415);
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: null");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(byteArray11);
        org.junit.Assert.assertEquals(java.util.Arrays.toString(byteArray11), "[2, 100, 1, 1, 10, 0]");
    }

    @Test
    public void test0475() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0475");
        com.quakearts.rest.client.net.ProgressSource progressSource0 = null;
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.ProgressSource progressSource1 = new com.quakearts.rest.client.net.ProgressSource(progressSource0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test0476() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0476");
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
        java.lang.String str18 = progressEvent12.getContentType();
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
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null" + "'", str18, "com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null");
    }

    @Test
    public void test0477() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0477");
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
            httpURLConnectionImpl5.doTunneling();
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
    public void test0478() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0478");
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
        int int12 = httpURLConnectionImpl5.getConnectTimeout();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
    }

    @Test
    public void test0479() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0479");
        java.lang.String str1 = com.quakearts.rest.client.net.NetProperties.get("com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test0480() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0480");
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
        // The following exception was thrown during execution in test generation
        try {
            httpsURLConnectionImpl6.setChunkedStreamingMode((int) (byte) 2);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: Fixed length streaming mode set");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier7);
        org.junit.Assert.assertNotNull(hostnameVerifier8);
        org.junit.Assert.assertNull(cacheRequest11);
    }

    @Test
    public void test0481() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0481");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        long long6 = httpsURLConnectionImpl3.getIfModifiedSince();
        java.io.InputStream inputStream7 = httpsURLConnectionImpl3.getErrorStream();
        // The following exception was thrown during execution in test generation
        try {
            java.security.Permission permission8 = httpsURLConnectionImpl3.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + long6 + "' != '" + 0L + "'", long6 == 0L);
        org.junit.Assert.assertNull(inputStream7);
    }

    @Test
    public void test0482() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0482");
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
        // The following exception was thrown during execution in test generation
        try {
            httpURLConnectionImpl5.setRequestMethod("{size=10 nkeys=1  {hi!} }");
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: Invalid HTTP method: {size=10 nkeys=1  {hi!} }");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNull(inputStream10);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
    }

    @Test
    public void test0483() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0483");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        boolean boolean5 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(0L);
        java.io.InputStream inputStream8 = httpsURLConnectionImpl3.getErrorStream();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = httpsURLConnectionImpl3.getResponseMessage();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
    }

    @Test
    public void test0484() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0484");
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
        java.lang.String str16 = httpsURLConnectionImpl3.getHeaderField(0);
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap17 = httpsURLConnectionImpl3.getRequestProperties();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 403 + "'", int12 == 403);
        org.junit.Assert.assertNull(str14);
        org.junit.Assert.assertNull(str16);
        org.junit.Assert.assertNotNull(strMap17);
    }

    @Test
    public void test0485() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0485");
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
            meteredStream10.mark(304);
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
    public void test0486() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0486");
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
        boolean boolean12 = httpsURLConnectionImpl3.getUseCaches();
        httpsURLConnectionImpl3.setUseCaches(false);
        boolean boolean15 = httpsURLConnectionImpl3.getAllowUserInteraction();
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(uRL11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0487() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0487");
        com.quakearts.rest.client.net.HeaderParser headerParser1 = new com.quakearts.rest.client.net.HeaderParser("hi!");
        java.lang.String str3 = headerParser1.findKey(305);
        int int6 = headerParser1.findInt("hi!", 32);
        // The following exception was thrown during execution in test generation
        try {
            com.quakearts.rest.client.net.HeaderParser headerParser9 = headerParser1.subsequence((int) (byte) 0, 301);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: invalid start or end");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 32 + "'", int6 == 32);
    }

    @Test
    public void test0488() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0488");
        java.util.Optional<com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain> registeredDomainOptional1 = com.quakearts.rest.client.net.RegisteredDomainProducer.RegisteredDomain.from("AuthenticationHeader: prefer null");
        org.junit.Assert.assertNotNull(registeredDomainOptional1);
    }

    @Test
    public void test0489() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0489");
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
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream22 = httpURLConnectionImpl5.getInputStream();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState7 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState7.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 0 + "'", int16 == 0);
        org.junit.Assert.assertTrue("'" + tunnelState17 + "' != '" + com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP + "'", tunnelState17.equals(com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.SETUP));
    }

    @Test
    public void test0490() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0490");
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
    public void test0491() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0491");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.findValue("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]");
        java.lang.String str4 = messageHeader0.getValue((int) (byte) -1);
        java.io.InputStream inputStream5 = null;
        com.quakearts.rest.client.net.HttpCapture httpCapture6 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream7 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream5, httpCapture6);
        com.quakearts.rest.client.net.HttpCapture httpCapture8 = null;
        com.quakearts.rest.client.net.HttpCaptureInputStream httpCaptureInputStream9 = new com.quakearts.rest.client.net.HttpCaptureInputStream(inputStream5, httpCapture8);
        // The following exception was thrown during execution in test generation
        try {
            messageHeader0.parseHeader((java.io.InputStream) httpCaptureInputStream9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str4);
    }

    @Test
    public void test0492() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0492");
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
            httpsURLConnectionImpl3.setFixedLengthStreamingMode((long) (byte) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: invalid content length");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(inputStream8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertNull(inputStream10);
    }

    @Test
    public void test0493() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0493");
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
            java.security.Permission permission17 = httpsURLConnectionImpl6.getPermission();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
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
    public void test0494() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0494");
        com.quakearts.rest.client.net.MessageHeader messageHeader0 = new com.quakearts.rest.client.net.MessageHeader();
        java.lang.String str2 = messageHeader0.getValue(503);
        messageHeader0.add("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]", "");
        java.lang.String[] strArray10 = new java.lang.String[] { "", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]", "", "com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=NEW, content-type=content/unknown, progress=0, expected=-1]" };
        java.util.Map<java.lang.String, java.util.List<java.lang.String>> strMap11 = messageHeader0.getHeaders(strArray10);
        java.io.PrintStream printStream12 = null;
        // The following exception was thrown during execution in test generation
        try {
            messageHeader0.print(printStream12);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertNotNull(strMap11);
    }

    @Test
    public void test0495() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0495");
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
        java.lang.String str15 = httpsURLConnectionImpl3.getHeaderField("AuthenticationHeader: prefer null");
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNull(str15);
    }

    @Test
    public void test0496() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0496");
        java.lang.Boolean boolean1 = com.quakearts.rest.client.net.NetProperties.getBoolean("{size=10 nkeys=1  {hi!} }");
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
    }

    @Test
    public void test0497() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0497");
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
            meteredStream10.mark(202);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + long8 + "' != '" + (-1L) + "'", long8 == (-1L));
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test0498() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0498");
        java.net.URL uRL0 = null;
        java.net.Proxy proxy1 = null;
        com.quakearts.rest.client.net.https.HttpsHandler httpsHandler2 = null;
        com.quakearts.rest.client.net.https.HttpsURLConnectionImpl httpsURLConnectionImpl3 = new com.quakearts.rest.client.net.https.HttpsURLConnectionImpl(uRL0, proxy1, httpsHandler2);
        javax.net.ssl.HostnameVerifier hostnameVerifier4 = httpsURLConnectionImpl3.getHostnameVerifier();
        javax.net.ssl.HostnameVerifier hostnameVerifier5 = httpsURLConnectionImpl3.getHostnameVerifier();
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(401);
        httpsURLConnectionImpl3.setFixedLengthStreamingMode(10);
        boolean boolean10 = httpsURLConnectionImpl3.getDoInput();
        httpsURLConnectionImpl3.disconnect();
        javax.net.ssl.HostnameVerifier hostnameVerifier12 = javax.net.ssl.HttpsURLConnection.getDefaultHostnameVerifier();
        httpsURLConnectionImpl3.setHostnameVerifier(hostnameVerifier12);
        org.junit.Assert.assertNotNull(hostnameVerifier4);
        org.junit.Assert.assertNotNull(hostnameVerifier5);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(hostnameVerifier12);
    }

    @Test
    public void test0499() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0499");
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
        // The following exception was thrown during execution in test generation
        try {
            java.io.InputStream inputStream19 = httpsURLConnectionImpl5.getInputStream();
            org.junit.Assert.fail("Expected exception of type java.net.ProtocolException; message: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)");
        } catch (java.net.ProtocolException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(hostnameVerifier6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 100 + "'", int15 == 100);
        org.junit.Assert.assertNull(cacheRequest16);
    }

    @Test
    public void test0500() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test0500");
        com.quakearts.rest.client.net.http.AuthCacheImpl authCacheImpl0 = new com.quakearts.rest.client.net.http.AuthCacheImpl();
        com.quakearts.rest.client.net.http.AuthCacheValue.setAuthCache((com.quakearts.rest.client.net.http.AuthCache) authCacheImpl0);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue3 = null;
        authCacheImpl0.remove("", authCacheValue3);
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue7 = authCacheImpl0.get("", "com.quakearts.rest.client.net.ProgressEvent[url=null, method=hi!, state=null, content-type=com.quakearts.rest.client.net.https.DelegateHttpsURLConnection:null, progress=300, expected=-1]");
        com.quakearts.rest.client.net.http.AuthCacheValue authCacheValue9 = null;
        authCacheImpl0.remove("hi!", authCacheValue9);
        org.junit.Assert.assertNull(authCacheValue7);
    }
}

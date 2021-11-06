package com.quakearts.rest.client.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ErrorTest0 {

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test1");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str1 = java.net.URLConnection.guessContentTypeFromName("content/unknown");
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test2");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str1 = java.net.URLConnection.guessContentTypeFromName("");
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test3");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str1 = java.net.URLConnection.guessContentTypeFromName("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=CONNECTED, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test4");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str1 = java.net.URLConnection.guessContentTypeFromName("hi!");
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test5");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str1 = java.net.URLConnection.guessContentTypeFromName("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=DELETE, content-type=content/unknown, progress=0, expected=-1]");
    }

    @Test
    public void test6() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test6");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str1 = java.net.URLConnection.guessContentTypeFromName("com.quakearts.rest.client.net.ProgressSource[url=null, method=, state=UPDATE, content-type=content/unknown, progress=0, expected=32]");
    }
}


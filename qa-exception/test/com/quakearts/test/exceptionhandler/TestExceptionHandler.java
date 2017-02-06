package com.quakearts.test.exceptionhandler;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.quakearts.common.exceptionhandler.ExceptionHandler;
import com.quakearts.common.exceptionhandler.ExceptionHandlerUtil;
import com.quakearts.common.exceptionhandler.annotations.Handler;
import com.quakearts.common.exceptionhandler.annotations.Handlers;

public class TestExceptionHandler {

	public static boolean hasIOExceptionHandlerRun, hasMultipleHandlerClassRun, 
		hasIOExceptionHandlerWithSpecificCallerRun;

	@Test
	public void testExceptionHandler() {
		hasIOExceptionHandlerRun = false;
		ExceptionHandlerUtil.handleExceptionForThisClass(new IOException());
		assertTrue(hasIOExceptionHandlerRun);
	}

	@Test
	public void testExceptionHandlerForChildException() {
		hasIOExceptionHandlerRun = false;
		ExceptionHandlerUtil.handleExceptionForThisClass(new FileNotFoundException());
		assertTrue(hasIOExceptionHandlerRun);
	}
	
	@Test
	public void testDefaultExceptionHandler() throws Exception {
		hasIOExceptionHandlerRun = false;
		ExceptionHandlerUtil.handleExceptionForThisClass(new Exception());
		assertTrue(!hasIOExceptionHandlerRun);
	}

	@Test
	public void testSpecificExceptionHandlerShouldNotRun() throws Exception {
		new TestSpecific().runExceptionTest();
	}
	
	@Test
	public void testSpecificExceptionHandler() throws Exception {
		new TestSpecific().runIOExceptionTest();
	}
	
	@Test
	public void testSpecificChildExceptionHandler() throws Exception {
		new TestSpecificChild().runIOExceptionTest();
	}

	@Test
	public void testSpecificChildAndRelatedChildExceptionHandler() throws Exception {
		new TestSpecificChild().runExceptionTest();
	}

	@Test
	public void testMultipleHandlerClass() throws Exception {
		hasMultipleHandlerClassRun = false;
		ExceptionHandlerUtil.handleException(new NullPointerException());
		assertTrue(hasMultipleHandlerClassRun);
		
		hasMultipleHandlerClassRun = false;
		ExceptionHandlerUtil.handleException(new IllegalAccessException());
		assertTrue(hasMultipleHandlerClassRun);
		
		hasMultipleHandlerClassRun = false;
		ExceptionHandlerUtil.handleException(new NumberFormatException());
		assertTrue(hasMultipleHandlerClassRun);
	}

	@Handler(exceptionClass=IOException.class)
	public static class TestHandler implements ExceptionHandler{

		@Override
		public void handleException(Exception e, Object... params) {
			hasIOExceptionHandlerRun = true;
		}	
	}
	
	public static class TestSpecific {
		void runExceptionTest(){
			hasIOExceptionHandlerWithSpecificCallerRun = false;
			ExceptionHandlerUtil.handleExceptionForThisClass(new Exception());
			assertTrue(!hasIOExceptionHandlerWithSpecificCallerRun);			
		}

		void runIOExceptionTest(){
			hasIOExceptionHandlerWithSpecificCallerRun = false;
			ExceptionHandlerUtil.handleExceptionForThisClass(new IOException());
			assertTrue(hasIOExceptionHandlerWithSpecificCallerRun);			
		}
	}
	
	public static class TestSpecificChild extends TestSpecific {
		@Override
		void runIOExceptionTest(){
			hasIOExceptionHandlerWithSpecificCallerRun = false;
			ExceptionHandlerUtil.handleExceptionForThisClass(new IOException());
			assertTrue(hasIOExceptionHandlerWithSpecificCallerRun);			
		}

		void runIOChildExceptionTest(){
			hasIOExceptionHandlerWithSpecificCallerRun = false;
			ExceptionHandlerUtil.handleExceptionForThisClass(new FileNotFoundException());
			assertTrue(hasIOExceptionHandlerWithSpecificCallerRun);			
		}
	}
	
	@Handler(exceptionClass=IOException.class, relatedClass=TestSpecific.class)
	public static class TestSpecificHandler implements ExceptionHandler{

		@Override
		public void handleException(Exception e, Object... params) {
			hasIOExceptionHandlerWithSpecificCallerRun = true;
		}	
	}
	
	@Handlers({
		@Handler(exceptionClass=IllegalAccessException.class),
		@Handler(exceptionClass=NullPointerException.class),
		@Handler(exceptionClass=NumberFormatException.class)})
	public static class TestHandlersHandler implements ExceptionHandler{

		@Override
		public void handleException(Exception e, Object... params) {
			hasMultipleHandlerClassRun = true;			
		}
		
	}
	
}

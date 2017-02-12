package com.quakearts.test.classpathscanner;
import static org.junit.Assert.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.Test;

import com.quakearts.classpathscanner.ClasspathScanner;
import com.quakearts.classpathscanner.FilterImpl;
import com.quakearts.classpathscanner.listener.ClassAnnotationScanningListener;

public class TestClasspathScanner {
	
	@Test
	public void testScan() {
		TestClassAnnotationScanningListener listener = new TestClassAnnotationScanningListener();
		ClasspathScanner scanner = new ClasspathScanner();
		scanner.addAnnotationListener(listener);
		
		scanner.scan();
		assertTrue(listener.hasRun);
	}
	
	@Test
	public void testFilterScan() {
		TestClassAnnotationScanningListener listener = new TestClassAnnotationScanningListener();
		ClasspathScanner scanner = new ClasspathScanner();
		scanner.addAnnotationListener(listener);
		scanner.setFilter(new FilterImpl(new String[]{"com.quakearts.test.classpathscanner"}));
		scanner.scan();
		assertTrue(!listener.hasRun);
	}
	
}

class TestClassAnnotationScanningListener implements ClassAnnotationScanningListener{

	boolean hasRun;
	
	@Override
	public String[] getAnnotationsToListenFor() {
		return new String[]{TestAnnotation.class.getName()};
	}

	@Override
	public void handle(String className, String annotation) {
		hasRun = true;
		assertTrue(className.equals(TestClass.class.getName()));
	}
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface TestAnnotation{	
}

@TestAnnotation
class TestClass{
}
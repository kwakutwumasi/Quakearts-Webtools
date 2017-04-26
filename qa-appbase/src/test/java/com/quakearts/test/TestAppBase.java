package com.quakearts.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.quakearts.appbase.Main;
import static org.hamcrest.core.Is.*;

public class TestAppBase {

	@Test
	public void testAppBase() {
		try {
			Main.main(new String[]{"com.quakearts.test.TestMainBean","dontWaitInMain"});
			Thread.sleep(10000);
			assertThat(TestInjectImpl.done, is(true));
			assertThat(TestProductImpl.done, is(true));
		} catch (Throwable e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles ");
		}
	}

}

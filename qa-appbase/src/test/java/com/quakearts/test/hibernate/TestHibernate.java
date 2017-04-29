package com.quakearts.test.hibernate;

import static org.junit.Assert.*;

import org.junit.Test;

import com.quakearts.appbase.Main;

public class TestHibernate {

	@Test
	public void test() {
		try {
			Main.main(new String[]{"com.quakearts.test.TestMainBean","-dontwaitinmain"});
			Thread.sleep(10000);
		} catch (Exception e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles testing Hibernate");
		}
	}

}

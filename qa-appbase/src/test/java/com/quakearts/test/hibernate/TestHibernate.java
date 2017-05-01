package com.quakearts.test.hibernate;

//import static org.junit.Assert.*;

import org.junit.Test;

import com.quakearts.appbase.Main;

public class TestHibernate {

	@Test
	public void testName() throws Exception {
		try {
			Main.main(new String[]{"com.quakearts.test.hibernate.TestHibernateMainBean","-dontwaitinmain"});
		} catch (IllegalStateException e) {
		}
	}
	
}

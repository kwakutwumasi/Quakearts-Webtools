package com.quakearts.test.hibernate;

import com.quakearts.appbase.Main;

public class TestHibernate {

	public static void main(String[] args) {
		try {
			Main.main(new String[]{"com.quakearts.test.hibernate.TestHibernateMainBean","-dontwaitinmain"});
		} catch (IllegalStateException e) {
		}
	}

}

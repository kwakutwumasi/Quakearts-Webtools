package com.quakearts.test;

import com.quakearts.appbase.Main;

public class TestAppBase {

	public static void main(String[] args) {
		try {
			Main.main(new String[]{"com.quakearts.test.TestMainBean","-dontwaitinmain"});
			Thread.sleep(10000);
			assert(TestInjectImpl.done);
			assert(TestProductImpl.done);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}

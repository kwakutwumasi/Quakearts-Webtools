package com.quakearts.test;

import com.quakearts.appbase.Main;

public class TestAppBase {

	public void init(){
		System.out.println("Started");
	}
	
	public static void main(String[] args) {
		Main.main(new String[]{"com.quakearts.test.TestAppBase"});
	}
}

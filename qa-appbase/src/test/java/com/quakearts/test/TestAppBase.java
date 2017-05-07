package com.quakearts.test;

import static org.junit.Assert.*;

//import org.junit.Test;

import com.quakearts.appbase.Main;
import static org.hamcrest.core.Is.*;

public class TestAppBase {

//	@Test
//	public void testAppBase() {
	public static void main(String[] args){
		try {
			Main.main(new String[]{"com.quakearts.test.TestMainBean",
					"-dontwaitinmain"});
			Thread.sleep(10000);
		} catch (IllegalStateException | InterruptedException e) {
		}
		assertThat(TestInjectImpl.done, is(true));
		assertThat(TestProductImpl.done, is(true));
	}

}

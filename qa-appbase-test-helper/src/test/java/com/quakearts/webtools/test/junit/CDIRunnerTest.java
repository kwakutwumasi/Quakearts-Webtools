package com.quakearts.webtools.test.junit;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.quakearts.webtools.test.CDIRunner;
import com.quakearts.webtools.test.helpers.TestInject;

@RunWith(CDIRunner.class)
public class CDIRunnerTest {

	@Inject
	private TestInject inject;
	
	@Test
	public void test() throws Exception{
		assertThat(inject, is(notNullValue()));
		assertThat(inject.sayHello(), is("Hi! Runner is working!"));
	}

}

package com.quakearts.appbase.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;
import com.quakearts.appbase.spi.ContextDependencySpi;
import com.quakearts.appbase.spi.impl.WeldContextDependencySpiImpl;
import com.quakearts.appbase.test.experiments.TestAppBase;
import com.quakearts.appbase.test.experiments.TestInjectImpl;

public class TestWeldContextDependencySpiImpl {

	@Test
	public void testDependencyInjection() {
		ContextDependencySpi dependencySpi = new WeldContextDependencySpiImpl();
		dependencySpi.initiateContextDependency();
		assertThat(dependencySpi.getBeanManager() != null, is(true));
		
		TestAppBase appBase = dependencySpi.getMainSingleton(TestAppBase.class);
		
		appBase.init();
		
		assertThat(TestInjectImpl.saidHello(), is(true));
		
		assertThat(TestInjectImpl.appBaseloaded(), is(true));

		dependencySpi.shutDownContextDependency();
	}

}

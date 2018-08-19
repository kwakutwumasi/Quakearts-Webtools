package com.quakearts.appbase.test.helpers.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ClassLoaderRule implements TestRule {

	private ClassLoader classLoader;
	
	public ClassLoaderRule replaceClassLoaderWith(ClassLoader classLoader){
		this.classLoader = classLoader;
		return this;
	}
		
	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				boolean run = classLoader!=null;
				if(run) {
					ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
					Thread.currentThread().setContextClassLoader(classLoader);
					classLoader = oldClassLoader;
				}
				try {
					base.evaluate();
				} finally {
					if(run)
						Thread.currentThread().setContextClassLoader(classLoader);
				}
			}
		};
	}

}

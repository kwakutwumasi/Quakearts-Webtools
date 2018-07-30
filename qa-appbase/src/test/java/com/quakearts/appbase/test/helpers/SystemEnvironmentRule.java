package com.quakearts.appbase.test.helpers;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class SystemEnvironmentRule implements TestRule {

	static Map<String, String> exposedEnv, copyOfEnv;
	
	@SuppressWarnings("unchecked")
	@Override
	public Statement apply(Statement base, Description description) {
		Class<?> unmodifiableMapClass = System.getenv().getClass();
		
		try {
			Field mapField = unmodifiableMapClass.getDeclaredField("m");
			mapField.setAccessible(true);
			
			exposedEnv = (Map<String, String>) mapField.get(System.getenv());
			copyOfEnv = new HashMap<>(exposedEnv);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException("Unable to get map field", e);
		}
		
		return base;
	}

	public TemporaryBuilder set(String variable) {
		return new TemporaryBuilder(variable);
	}
	
	public class TemporaryBuilder {
		private String variable;
		private TemporaryBuilder(String variable) {
			this.variable = variable;
		}
		
		public SystemEnvironmentRule as(String newValue) {
			exposedEnv.put(variable, newValue);
			return SystemEnvironmentRule.this;
		}
	}
	
	public void reset() {
		exposedEnv.clear();
		exposedEnv.putAll(copyOfEnv);
	}
	
	public void clearAll() {
		exposedEnv.clear();
	}
}

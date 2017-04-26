package com.quakearts.test.hibernate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 525749375461516969L;
	@Id
	int id;
	String testName;
	boolean testBoolean;
	double testDouble;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public boolean isTestBoolean() {
		return testBoolean;
	}

	public void setTestBoolean(boolean testBoolean) {
		this.testBoolean = testBoolean;
	}

	public double getTestDouble() {
		return testDouble;
	}

	public void setTestDouble(double testDouble) {
		this.testDouble = testDouble;
	}

}

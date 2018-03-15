package com.quakearts.appbase.test.experiments;

public interface TestTransaction {
	void completeTransaction();
	void continueTransaction();
	void beginTransaction();
}
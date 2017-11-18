package com.quakearts.utilities.test.beans;

public class ShowPrintout {

	public static void main(String[] args) {
		System.out.println(new TestCommandExecutor().printUsage());
		System.out.println(new TestCommandExecutor2().printUsage());
	}

}

package com.quakearts.tools.test.generator.bean.helper;

public class Tuple<C1,C2> {

	private C1 first;
	private C2 second;
	
	public Tuple(C1 first, C2 second) {
		this.first = first;
		this.second = second;
	}

	public Tuple() {
	}
	
	public C1 getFirst() {
		return first;
	}

	public C2 getSecond() {
		return second;
	}
	
	public void setSecond(C2 second) {
		this.second = second;
	}

}

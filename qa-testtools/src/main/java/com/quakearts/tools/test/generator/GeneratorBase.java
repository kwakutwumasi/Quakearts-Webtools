package com.quakearts.tools.test.generator;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public abstract class GeneratorBase<T> implements Generator<T> {
	protected final static Random random = new SecureRandom();
	protected String fieldName;
	protected Stack<GeneratorBase<T>> callStack = new Stack<>();
	private int maxCallDepth = 2;
	
	protected int getMaxCallDepth() {
		return maxCallDepth;
	}
	
	@Override
	public Generator<T> useField(String fieldName) {
		this.fieldName = fieldName;
		return this;
	}
	
	public GeneratorBase<T> useMaxCallDepth(int maxCallDepth) {
		this.maxCallDepth = maxCallDepth;
		return this;
	}
	
	@Override
	public final Collection<T> generateRandom(final int size) {
		if(callStack.size()>getMaxCallDepth())//Prevent too many recursive calls to the singleton generator
			return Collections.emptyList();

		callStack.push(this);
		ArrayList<T> arrayList = new ArrayList<>(size);
		for(int count = 0; count< size; count++){
			T generated = generateRandom();
			if(generated==null)
				break;
			
			arrayList.add(generated);
		}
		callStack.pop();
		return arrayList;
	}
}

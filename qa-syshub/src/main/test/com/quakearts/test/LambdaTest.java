/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LambdaTest {

	public LambdaTest() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3),
				new ThreadPoolExecutor.CallerRunsPolicy()){
			@Override
			public void execute(Runnable command) {
				System.out.println("Lambda instance:"+command.hashCode());
				super.execute(command);
			}
		};
		
		System.out.println(hashCode());
		
		for(int i=0; i<10;i++){
			executor.execute(()->{
				System.out.println("Hi! I'm a lambda");
			});
		}
		
		executor.shutdown();
	}
	
	public static void main(String[] args) {
		new LambdaTest();
	}
}

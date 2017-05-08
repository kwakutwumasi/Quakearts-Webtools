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
package com.quakearts.syshub.core.scheduler.impl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RunnableJob implements Job {

	public static final String RUNNABLE = "com.quakearts.syshub.RUNNABLE";
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		 Runnable runnable = (Runnable) context.get(RUNNABLE);
		 new Thread(runnable).start();
	}

}

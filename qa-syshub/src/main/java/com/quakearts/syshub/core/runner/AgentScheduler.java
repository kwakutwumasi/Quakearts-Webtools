/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.syshub.core.runner;

import java.util.List;

/**Interface implemented to return scheduler state
 * @author kwakutwumasi-afriyie
 *
 */
public interface AgentScheduler {
	/**Getter for scheduler state
	 * @return the scheduler state
	 */
	SchedulerState getState();
	/**
	 * @return Statistics that can be displayed in the application monitor
	 */
	List<Statistic> getStatistics();
	/**Enumeration of scheduler states
	 * @author kwakutwumasi-afriyie
	 *
	 */
	public static enum SchedulerState {
		/**The scheduler has no state
		 */
		None,
		/**The scheduler has been started
		 */
		Started,
		/**The scheduler has been stopped
		 */
		Stopped,
		/**The scheduler is running
		 */
		Running,
		/**The scheduler is sleeping
		 */
		Sleeping
	}
}

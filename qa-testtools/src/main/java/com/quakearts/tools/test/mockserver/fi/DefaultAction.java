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
package com.quakearts.tools.test.mockserver.fi;

import com.quakearts.tools.test.mockserver.context.ProcessingContext;
import com.quakearts.tools.test.mockserver.exception.MockServerProcessingException;

/**Functional Interface for defining actions to peform during 
 * each mock server request response cycle
 * @author kwakutwumasi-afriyie
 *
 */
@FunctionalInterface
public interface DefaultAction {
	/**The action to perform
	 * @param context the {@linkplain ProcessingContext}
	 * @throws MockServerProcessingException if there is an error during processing
	 */
	void performAction(ProcessingContext context) throws MockServerProcessingException;
}

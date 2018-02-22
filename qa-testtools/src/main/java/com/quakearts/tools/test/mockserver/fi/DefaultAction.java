package com.quakearts.tools.test.mockserver.fi;

import com.quakearts.tools.test.mockserver.context.ProcessingContext;
import com.quakearts.tools.test.mockserver.exception.MockServerProcessingException;

@FunctionalInterface
public interface DefaultAction {
	void performAction(ProcessingContext context) throws MockServerProcessingException;
}

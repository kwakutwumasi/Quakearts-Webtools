package com.quakearts.utilities.test.beans;

import com.quakearts.utilities.annotation.CommandMetadata;
import com.quakearts.utilities.exception.CommandParameterException;
import com.quakearts.utilities.impl.CommandBase;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

@CommandMetadata("testExecutor3")
public class TestCommandExecutor3 extends CommandBase {

	@Override
	public void execute() throws CommandParameterException {
		assertThat(getCommandParameters().size(), is(1));
		assertThat(getCommandParameters().get(0).getValues().size(), is(2));
		assertThat(getCommandParameters().get(0).getValues().get(0), is("test1"));
		assertThat(getCommandParameters().get(0).getValues().get(1), is("test2"));		
	}

}

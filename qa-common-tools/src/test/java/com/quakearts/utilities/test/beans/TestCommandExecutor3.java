package com.quakearts.utilities.test.beans;

import com.quakearts.utilities.CommandParameter;
import com.quakearts.utilities.annotation.CommandMetadata;
import com.quakearts.utilities.exception.CommandParameterException;
import com.quakearts.utilities.impl.CommandBase;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

@CommandMetadata("testExecutor3")
public class TestCommandExecutor3 extends CommandBase {

	@Override
	public void execute() throws CommandParameterException {
		assertThat(getCommandParametersMap().get(CommandParameter.DEFAULT).getValues().size(), is(2));
		assertThat(getCommandParametersMap().get(CommandParameter.DEFAULT).getValues().get(0), is("test1"));
		assertThat(getCommandParametersMap().get(CommandParameter.DEFAULT).getValues().get(1), is("test2"));		
	}

}

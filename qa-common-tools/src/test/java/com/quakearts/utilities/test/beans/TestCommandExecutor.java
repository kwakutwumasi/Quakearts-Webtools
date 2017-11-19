package com.quakearts.utilities.test.beans;

import com.quakearts.utilities.annotation.CommandMetadata;
import com.quakearts.utilities.annotation.CommandParameterMetadata;
import com.quakearts.utilities.exception.CommandParameterException;
import com.quakearts.utilities.impl.CommandBase;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

@CommandMetadata(value="testExecutor", parameters={
		@CommandParameterMetadata("v"),
		@CommandParameterMetadata(value = "f", required = true),
		@CommandParameterMetadata(value = "test1", alias="alias1", format = "format1"),
		@CommandParameterMetadata("test2"),
		@CommandParameterMetadata(value = "test3", format = "format2", description = "description1")
})
public class TestCommandExecutor extends CommandBase {

	public TestCommandExecutor() {
	}

	@Override
	public void execute() throws CommandParameterException {
		assertThat(getCommandParametersMap() != null, is(true));
		assertThat(getCommandParametersMap().get("v").getValue() == null, is(true));
		assertThat(getCommandParametersMap().get("f").getValue() == null, is(true));
		assertThat(getCommandParametersMap().get("test1").getValue(), is("value1"));
		assertThat(getCommandParametersMap().get("test2").getValue() == null, is(true));
		assertThat(getCommandParametersMap().get("test3").getValue(), is("value2"));
	}

}

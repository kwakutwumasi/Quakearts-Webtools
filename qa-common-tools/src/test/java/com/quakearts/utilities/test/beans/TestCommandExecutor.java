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
		@CommandParameterMetadata(value = "test1", format = "format1"),
		@CommandParameterMetadata("test2"),
		@CommandParameterMetadata(value = "test3", format = "format2", description = "description1")
})
public class TestCommandExecutor extends CommandBase {

	public TestCommandExecutor() {
	}

	@Override
	public void execute() throws CommandParameterException {
		assertThat(getCommandParameters() != null, is(true));
		assertThat(getCommandParameters().size(), is(5));
		assertThat(getCommandParameters().get(0).getName(), is("v"));
		assertThat(getCommandParameters().get(0).getValue() == null, is(true));
		assertThat(getCommandParameters().get(1).getName(), is("f"));
		assertThat(getCommandParameters().get(1).getValue() == null, is(true));
		assertThat(getCommandParameters().get(2).getName(), is("test1"));
		assertThat(getCommandParameters().get(2).getValue(), is("value1"));
		assertThat(getCommandParameters().get(3).getName(), is("test2"));
		assertThat(getCommandParameters().get(3).getValue() == null, is(true));
		assertThat(getCommandParameters().get(4).getName(), is("test3"));
		assertThat(getCommandParameters().get(4).getValue(), is("value2"));
	}

}

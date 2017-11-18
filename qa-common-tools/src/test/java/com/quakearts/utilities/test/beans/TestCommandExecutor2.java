package com.quakearts.utilities.test.beans;

import com.quakearts.utilities.annotation.CommandMetadata;
import com.quakearts.utilities.annotation.CommandParameterMetadata;
import com.quakearts.utilities.exception.CommandParameterException;
import com.quakearts.utilities.impl.CommandBase;

@CommandMetadata(value="testExecutor2", descritpion="testDescritpion", 
	additionalInfo = "additionalInfo", examples = "examples",
	parameters={
		@CommandParameterMetadata(value="parameter1", alias="alias1", description="description1", format="format1", required=true),
		@CommandParameterMetadata(value="parameter2", description="description2", linkedParameters="parameter3"),
		@CommandParameterMetadata(value="parameter3", format="format4"),
		@CommandParameterMetadata(value="parameter4", alias=""),
})
public class TestCommandExecutor2 extends CommandBase {

	public TestCommandExecutor2() {
	}

	@Override
	public void execute() throws CommandParameterException {
	}

}

package com.quakearts.utilities.test.beans;

import com.quakearts.utilities.annotation.CommandMetadata;
import com.quakearts.utilities.annotation.CommandParameterMetadata;
import com.quakearts.utilities.exception.CommandParameterException;
import com.quakearts.utilities.impl.CommandBase;

@CommandMetadata(value="testExecutor4",parameters= {@CommandParameterMetadata(value="test1", required=true, canOmitName=true)})
public class TestCommandExecutor4 extends CommandBase {

	@Override
	public void execute() throws CommandParameterException {
	}

}

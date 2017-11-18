package com.quakearts.utilities.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.Test;

import com.quakearts.utilities.CommandMain;
import com.quakearts.utilities.CommandMetadataAnnotationScanningListener;
import com.quakearts.utilities.annotation.CommandMetadata;
import com.quakearts.utilities.test.beans.TestCommandExecutor;
import com.quakearts.utilities.test.beans.TestCommandExecutor2;

public class TestCommand {

	@Test
	public void testCommand() {
		String[] args1 = {TestCommandExecutor.class.getName(),"{vf}","-test1","value1","-test2","-test3","value2"};
		CommandMain.main(args1);
	}

	@Test(expected=RuntimeException.class)
	public void testNoCommandError() throws Exception {
		CommandMain.main(new String[]{});
	}
	
	@Test(expected=RuntimeException.class)
	public void testNotFoundCommand() throws Exception {
		CommandMain.main(new String[]{"com.test.NotExists"});
	}
	
	@Test(expected=RuntimeException.class)
	public void testClassCastCommand() throws Exception {
		CommandMain.main(new String[]{"java.lang.String"});
	}

	@Test
	public void testRequiredParameters() throws Exception {
		PrintStream oldOut = System.out, oldError = System.err;		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PrintStream replaceOutput = new PrintStream(bos);
		System.setOut(replaceOutput);
		System.setErr(replaceOutput);
		
		CommandMain.main(new String[]{TestCommandExecutor2.class.getName()});
		CommandMain.main(new String[]{TestCommandExecutor2.class.getName(),"-parameter1","-parameter3"});

		String output = new String(bos.toByteArray());
		System.setErr(oldError);
		System.setOut(oldOut);
		
		assertThat(output.contains("Missing required parameter: parameter1"), is(true));
		assertThat(output.contains("Missing required parameter: parameter2"), is(true));
	}
	
	@Test
	public void testScripting() throws Exception {
		new CommandMetadataAnnotationScanningListener().handle(TestCommandExecutor.class.getName(),
				CommandMetadata.class.getName());
		
		File testExecutor = new File("testExecutor");
		assertThat(testExecutor.exists(), is(true));
		testExecutor.delete();

		File testExecutorBat = new File("testExecutor.bat");
		assertThat(testExecutorBat.exists(), is(true));
		testExecutorBat.delete();
	}
}

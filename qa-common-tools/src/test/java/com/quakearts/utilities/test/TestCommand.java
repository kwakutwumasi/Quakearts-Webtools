package com.quakearts.utilities.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

import com.quakearts.utilities.CommandMain;
import com.quakearts.utilities.CommandMetadataAnnotationScanningListener;
import com.quakearts.utilities.annotation.CommandMetadata;
import com.quakearts.utilities.test.beans.TestCommandExecutor;
import com.quakearts.utilities.test.beans.TestCommandExecutor2;
import com.quakearts.utilities.test.beans.TestCommandExecutor3;
import com.quakearts.utilities.test.beans.TestCommandExecutor4;

public class TestCommand {

	@Test
	public void testCommand() {
		CommandMain.main(new String[]{TestCommandExecutor.class.getName(),"{vf}","-test1","value1","-test2","-test3","value2"});
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
		CommandMain.main(new String[]{TestCommandExecutor2.class.getName(),"-parameter1","-parameter2"});

		String output = new String(bos.toByteArray());
		System.setErr(oldError);
		System.setOut(oldOut);
		
		assertThat(output.contains("Invalid parameter 'parameter1'. The parameter is required"), is(true));
		assertThat(output.contains("Invalid parameter 'parameter2'. The parameter 'parameter3' is required"), is(true));
	}
	
	@Test
	public void testHelp() throws Exception {
		PrintStream oldOut = System.out, oldError = System.err;		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PrintStream replaceOutput = new PrintStream(bos);
		System.setOut(replaceOutput);
		System.setErr(replaceOutput);

		CommandMain.main(new String[]{TestCommandExecutor2.class.getName(),"-help"});

		String output = new String(bos.toByteArray());
		System.setErr(oldError);
		System.setOut(oldOut);
		
		assertThat(output.startsWith("Usage"), is(true));
	}
	
	@Test
	public void testNoParameterNames() throws Exception {
		CommandMain.main(new String[] {TestCommandExecutor3.class.getName(),"test1","test2"});
	}
	
	@Test
	public void testParameterAlias() throws Exception {
		CommandMain.main(new String[]{TestCommandExecutor.class.getName(),"{vf}","-alias1","value1","-test2","-test3","value2"});		
	}
	
	@Test
	public void testScripting() throws Exception {
		new CommandMetadataAnnotationScanningListener("test-common-tools.jar", ".").handle(TestCommandExecutor.class.getName(),
				CommandMetadata.class.getName());
		
		File testExecutor = new File("testExecutor");
		testExecutor.deleteOnExit();
		assertThat(testExecutor.exists(), is(true));

		try(FileInputStream fis = new FileInputStream(testExecutor)){
			String content;
			byte[] contentBytes = new byte[fis.available()];
			
			fis.read(contentBytes);
			
			content = new String(contentBytes);
			
			assertThat(content, is("#!/bin/bash\njava -jar test-common-tools.jar com.quakearts.utilities.test.beans.TestCommandExecutor \"$@\""));
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
		File testExecutorBat = new File("testExecutor.bat");
		testExecutorBat.deleteOnExit();
		assertThat(testExecutorBat.exists(), is(true));
	}
	
	@Test
	public void testCanOmitNamesRequired() throws Exception {
		PrintStream oldOut = System.out, oldError = System.err;		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PrintStream replaceOutput = new PrintStream(bos);
		System.setOut(replaceOutput);
		System.setErr(replaceOutput);
		
		CommandMain.main(new String[] {TestCommandExecutor4.class.getName(),"value1"});
	
		String output = new String(bos.toByteArray());
		System.setErr(oldError);
		System.setOut(oldOut);
		
		assertThat(output.isEmpty(), is(true));
	}

}

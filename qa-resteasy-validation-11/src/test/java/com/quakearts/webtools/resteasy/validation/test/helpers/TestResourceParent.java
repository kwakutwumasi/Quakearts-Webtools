package com.quakearts.webtools.resteasy.validation.test.helpers;

import javax.validation.constraints.Size;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public abstract class TestResourceParent {

	private static boolean optionsWorked;

	public static void reset() {
		optionsWorked = false;
	}
	
	@OPTIONS
	@Produces(MediaType.TEXT_PLAIN)
	@ValidateOnExecution(type=ExecutableType.NONE)
	public @Size(min=10) String options() {
		optionsWorked = true;
		return "OK";
	}
	
	public static boolean optionsWorked() {
		return optionsWorked;
	}
}

package com.quakearts.webtools.resteasy.validation.test.helpers;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.quakearts.appbase.cdi.annotation.Transactional;
import com.quakearts.appbase.cdi.annotation.Transactional.TransactionType;
import com.quakearts.webtools.resteasy.test.experiments.TestInject;

@Path("/test")
public class TestResource extends TestResourceParent {
	
	@Inject
	private TestInject testInject;
	private static boolean getterWorked;
	private static boolean getworked;
	private static boolean postworked;
	
	public static void reset() {
		getworked = false;
		postworked = false;
		getterWorked = false;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@ValidateOnExecution(type=ExecutableType.GETTER_METHODS)
	public @NotNull String getTest() {
		getterWorked = true;
		return "OK";
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String get(@DecimalMin(value="1")@PathParam("id") int id){
		getworked = true;
		return "OK";
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public String put(TestParameter parameter) {
		testInject.processTestParameter(parameter);
		return "OK";
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN) @Transactional(TransactionType.SINGLETON)
	public @Size(max=10) String post(@Valid TestParameter parameter) {
		postworked=true;
		return "OK";
	}
	
	public static boolean getworked() {
		return getworked;
	}
	
	public static boolean postworked() {
		return postworked;
	}
	
	public static boolean getterWorked() {
		return getterWorked;
	}
}

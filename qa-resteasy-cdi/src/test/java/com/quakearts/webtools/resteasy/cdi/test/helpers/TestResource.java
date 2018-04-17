package com.quakearts.webtools.resteasy.cdi.test.helpers;

import javax.inject.Inject;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.quakearts.appbase.cdi.annotation.Transaction;
import com.quakearts.appbase.cdi.annotation.Transactional;
import com.quakearts.appbase.cdi.annotation.Transactional.TransactionType;
import com.quakearts.webtools.resteasy.cdi.test.experiments.TestInject;

@Path("/test")
public class TestResource {
	
	private static boolean transactionWorked;
	private static String content;
	
	@Inject
	private TestInject testService;
	
	@Inject @Transaction
	private UserTransaction transaction;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String get(){
		testService.sayHello();
		return "OK";
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN) @Transactional(TransactionType.SINGLETON)
	public TestParameter post(TestParameter parameter) {
		try {
			transactionWorked = transaction != null && transaction.getStatus() == Status.STATUS_ACTIVE;
			content = parameter.getContent();
		} catch (SystemException e) {
		}
		return parameter;
	}
	
	public static boolean transactionWorked() {
		return transactionWorked;
	}
	
	public static String getContent() {
		return content;
	}
}

package com.quakearts.appbase.test.helpers;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import com.quakearts.appbase.cdi.annotation.Transaction;
import com.quakearts.appbase.test.experiments.TestInject;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6291977668315266725L;
	@Inject
	TestInject inject;
	
	@Inject @Transaction
	UserTransaction transaction;

	private static boolean transactionLoaded;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		inject.sayHello();
		inject.testTransaction();
		transactionLoaded = transaction != null;
		resp.getWriter().print("OK");
		resp.getWriter().flush();
		resp.getWriter().close();
	}
	
	public static boolean transactionLoaded() {
		return transactionLoaded;
	}
}

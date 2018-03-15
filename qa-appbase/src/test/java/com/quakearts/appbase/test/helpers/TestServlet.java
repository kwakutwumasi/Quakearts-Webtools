package com.quakearts.appbase.test.helpers;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.quakearts.appbase.cdi.annotation.Transaction;
import com.quakearts.appbase.cdi.annotation.Transactional;
import com.quakearts.appbase.cdi.annotation.Transactional.TransactionType;
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

	private static boolean transactionWorked;

	@Override @Transactional(TransactionType.SINGLETON)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		inject.sayHello();
		inject.testTransaction();
		try {
			transactionWorked = transaction != null && transaction.getStatus() == Status.STATUS_ACTIVE;
		} catch (SystemException e) {
			throw new ServletException(e);
		}
		resp.getWriter().print("OK");
		resp.getWriter().flush();
		resp.getWriter().close();
	}
	
	public static boolean transactionWorked() {
		return transactionWorked;
	}
}

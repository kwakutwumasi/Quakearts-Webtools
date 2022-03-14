package com.quakearts.webapp.facelets.bootstrap.test;

import java.io.Serializable;
import java.util.Date;

public class ActionLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Date actionDate;
	String actionHost;
	String action;

	public Date getActionDate() {
		return actionDate;
	}

	public ActionLog withActionDate(Date actionDate) {
		this.actionDate = actionDate;
		return this;
	}

	public String getActionHost() {
		return actionHost;
	}

	public ActionLog withActionHost(String actionHost) {
		this.actionHost = actionHost;
		return this;
	}

	public String getAction() {
		return action;
	}

	public ActionLog withAction(String action) {
		this.action = action;
		return this;
	}

}

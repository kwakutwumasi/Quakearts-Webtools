/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.sql.DataSource;
import java.util.logging.Logger;

public class AutoCompleteBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -495885669752659887L;
	protected String jndi, column, table, search;
	private List<String> list;
	protected boolean like;
	protected static final Logger log = Logger.getLogger(AutoCompleteBean.class.getName());
	
	public String getJndi() {
		return jndi;
	}
	
	public void setJndi(String jndi) {
		this.jndi = jndi;
	}
	
	public String getColumn() {
		return column;
	}
	
	public void setColumn(String column) {
		this.column = column;
	}
		
	public String getTable() {
		return table;
	}
	
	public void setTable(String statement) {
		this.table = statement;
	}
	
	public void loadAjax(AjaxBehaviorEvent event){
		ActionEvent actionEvent = new ActionEvent(event.getComponent());
		actionEvent.setPhaseId(event.getPhaseId());
		load(actionEvent);
	}
	
	public void load(ActionEvent event){
		if(jndi == null || column == null || table == null || search == null)
			return;
		DataSource ds;
		Connection con = null;
		try {
			ds = (DataSource) UtilityMethods.getInitialContext().lookup(jndi);
			con = ds.getConnection();
			PreparedStatement ps = getPreparedStatement(con);
			ps.setString(1, like?"%"+search+"%":search);
			
			ResultSet rs = ps.executeQuery();
			processResults(rs);
			
		} catch (Exception e) {
			log.severe("Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
			}
		}
	}

	protected void processResults(ResultSet rs) throws SQLException {
		List<String> addlist = getList(); 
		if(rs.next()){
			do {
				addlist.add(rs.getString(column));
			} while (rs.next());
		}
	}

	protected PreparedStatement getPreparedStatement(Connection con) throws SQLException {
		return con.prepareStatement("SELECT "+column+" FROM "+table+" WHERE "+column+(like?" like ":"=")+" ?");
	}

	public void setSearch(String value) {
		this.search = value;
	}

	public String getSearch() {
		return search;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

	public boolean isLike() {
		return like;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public List<String> getList() {
		if(list==null)
			 list = new ArrayList<String>();
		
		return list;
	}
}

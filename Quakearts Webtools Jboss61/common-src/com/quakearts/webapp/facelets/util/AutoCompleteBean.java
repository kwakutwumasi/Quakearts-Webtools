package com.quakearts.webapp.facelets.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class AutoCompleteBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -495885669752659887L;
	private String jndi, column, table, search;
	private List<String> list;
	private boolean like;
	private static final Logger log = Logger.getLogger(AutoCompleteBean.class);
	
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
		
	public void load(ActionEvent event){
		if(jndi == null || column == null || table == null || search == null)
			return;
		DataSource ds;
		Connection con = null;
		try {
			ds = (DataSource) UtilityMethods.getInitialContext().lookup(jndi);
			con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT "+column+" FROM "+table+" WHERE "+column+(like?" like ":"=")+" ?");
			ps.setString(1, like?"%"+search+"%":search);
			
			ResultSet rs = ps.executeQuery();
			List<String> addlist = getList(); 
			if(rs.next()){
				do {
					addlist.add(rs.getString(column));
				} while (rs.next());
			}
			
		} catch (Exception e) {
			log.error("Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
			}
		}
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

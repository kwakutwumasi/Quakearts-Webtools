package com.quakearts.webapp.facelets.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class AutoCompleteSelectItemsBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -495885669752659887L;
	private String jndi, column, label, table, search, key;
	private boolean like;
	private SelectItem[] selectItems = new SelectItem[]{new SelectItem(".....",".....")};
	private static final Logger log = Logger.getLogger(AutoCompleteSelectItemsBean.class);
	
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
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getTable() {
		return table;
	}
	
	public void setTable(String statement) {
		this.table = statement;
	}
	
	public SelectItem[] getSelectItems() {
		return selectItems;
	}
	
	public void setSelectItems(SelectItem[] selectItems) {
		this.selectItems = selectItems;
	}
	
	public void load(ActionEvent event){
		if(jndi == null || column == null || label == null || table == null || search == null)
			return;
		DataSource ds;
		Connection con = null;
		try {
			ds = (DataSource) UtilityMethods.getInitialContext().lookup(jndi);
			con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT "+label+", "+column+" FROM "+table+" WHERE "+(key==null?column:key)+(like?" like ":" = ")+"?");
			ps.setString(1, like?"%"+search+"%":search);
			
			ResultSet rs = ps.executeQuery();
			List<SelectItem> list = new ArrayList<SelectItem>();
			if(rs.next()){
				do {
					list.add(new SelectItem(rs.getString(column), rs.getString(label)));
				} while (rs.next());
			}
			
			selectItems = list.toArray(new SelectItem[list.size()]);
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

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}

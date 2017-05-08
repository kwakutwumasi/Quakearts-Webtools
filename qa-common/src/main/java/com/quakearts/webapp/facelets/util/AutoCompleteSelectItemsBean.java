/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

public class AutoCompleteSelectItemsBean extends AutoCompleteBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -495885669752659887L;
	private String key, label;
	private SelectItem[] selectItems = new SelectItem[]{new SelectItem(".....",".....")};
		
	public SelectItem[] getSelectItems() {
		return selectItems;
	}
	
	public void setSelectItems(SelectItem[] selectItems) {
		this.selectItems = selectItems;
	}
	
	@Override
	protected PreparedStatement getPreparedStatement(Connection con) throws SQLException {
		return con.prepareStatement("SELECT "+label+", "+column+" FROM "+table+" WHERE "+(key==null?column:key)+(like?" like ":" = ")+"?");
	}

	@Override
	protected void processResults(ResultSet rs) throws SQLException {
		List<SelectItem> list = new ArrayList<SelectItem>();
		if(rs.next()){
			do {
				list.add(new SelectItem(rs.getString(column), rs.getString(label)));
			} while (rs.next());
		}
		
		selectItems = list.toArray(new SelectItem[list.size()]);
	}
	
	public void load(ActionEvent event){
		if(label == null)
			return;
		super.load(event);
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
}

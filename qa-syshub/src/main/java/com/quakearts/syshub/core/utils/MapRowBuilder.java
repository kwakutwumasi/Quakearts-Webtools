package com.quakearts.syshub.core.utils;

import java.util.HashMap;
import java.util.Map;

public class MapRowBuilder {

	private MapRowBuilder() {
	}
	
	public static MapRowBuilder create() {
		return new MapRowBuilder();
	}
	
	public RowBuilder row() {
		return new RowBuilder();
	}
	
	public class RowBuilder {
		private Map<String, Object> row = new HashMap<>();
		private RowBuilder() {
		}
		
		public RowBuilder addColumn(String name, Object value) {
			row.put(name, value);
			return this;
		}
		
		public Map<String, Object> thenBuild(){
			return row;
		}
	}	
}

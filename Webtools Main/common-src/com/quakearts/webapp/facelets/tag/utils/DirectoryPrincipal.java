package com.quakearts.webapp.facelets.tag.utils;

public enum DirectoryPrincipal {
	USER,
	NAME,
	GRADE,
	POSITION,
	DEPARTMENT,
	UNIT,
	BRANCH,
	EMAIL;
	
	public static String toString(DirectoryPrincipal principal){
	switch (principal) {
		case USER:
			return "QUAKEARTS.user";
		case NAME:
			return "QUAKEARTS.name";
		case GRADE:
			return "QUAKEARTS.grade";
		case POSITION:
			return "QUAKEARTS.post";
		case DEPARTMENT:
			return "QUAKEARTS.dept";
		case UNIT:
			return "QUAKEARTS.unit";
		case BRANCH:
			return "QUAKEARTS.bran";
		case EMAIL:
			return "QUAKEARTS.mail";
		default:
			return "";
		}	
	}
	
	public String toString(){
		return toString(this);
	}
}

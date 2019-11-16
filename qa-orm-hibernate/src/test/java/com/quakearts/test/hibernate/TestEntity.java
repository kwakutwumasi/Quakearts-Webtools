package com.quakearts.test.hibernate;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

public class TestEntity extends TestParentEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3583049982782103466L;
	@Id
	private int id;
	@Column(length = 10)
	private String stringColumn1;
	private String stringColumn2;
	private String stringColumn3;

	@Transient
	private String stringColumn5;

	@Size(max = 10)
	private String stringColumn10;
	@Size
	private String stringColumn11;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStringColumn1() {
		return stringColumn1;
	}

	public void setStringColumn1(String stringColumn1) {
		this.stringColumn1 = stringColumn1;
	}

	@Column(length=20)
	public String getStringColumn2() {
		return stringColumn2;
	}

	public void setStringColumn2(String stringColumn2) {
		this.stringColumn2 = stringColumn2;
	}

	public String getStringColumn3() {
		return stringColumn3;
	}

	@Column(length=30)
	public void setStringColumn3(String stringColumn3) {
		this.stringColumn3 = stringColumn3;
	}

	public String getStringColumn5() {
		return stringColumn5;
	}

	public void setStringColumn5(String stringColumn5) {
		this.stringColumn5 = stringColumn5;
	}

	private HashMap<String, String> cache = new HashMap<>();

	@Transient
	public String getStringColumn6() {
		return cache.get("6");
	}
	
	public void setStringColumn6(String string) {
		cache.put("6", string);
	}
	
	public String getStringColumn12(){
		return cache.get("12");
	}
	
	@Transient
	public void setStringColumn12(String value){
		cache.put("12", value);
	}
	
	@Column(length = 10)
	public String getStringColumn13(){
		return cache.get("13");
	}
	
	public void setStringColumn13(String value){
		cache.put("13", value);
	}
	
	public String getStringColumn14(){
		return cache.get("14");
	}
	
	@Column(length = 10)
	public void setStringColumn14(String value){
		cache.put("14", value);
	}

	public String getStringColumn15(){
		return cache.get("15");
	}
	
	@Size(max = 10)
	public void setStringColumn15(String value){
		cache.put("15", value);
	}

	@Size(max = 10)
	public String getStringColumn16(){
		return cache.get("15");
	}
	
	public void setStringColumn16(String value){
		cache.put("15", value);
	}

	public String getStringColumn10() {
		return stringColumn10;
	}
	
	public void setStringColumn10(String stringColumn10) {
		this.stringColumn10 = stringColumn10;
	}
	
	public String getStringColumn11() {
		return stringColumn11;
	}
	
	public void setStringColumn11(String stringColumn11) {
		this.stringColumn11 = stringColumn11;
	}
}

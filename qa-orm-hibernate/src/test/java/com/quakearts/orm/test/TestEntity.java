package com.quakearts.orm.test;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

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
	private String stringColumn6;

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

	@Transient
	public String getStringColumn6() {
		return stringColumn6;
	}

	public void setStringColumn6(String stringColumn6) {
		this.stringColumn6 = stringColumn6;
	}

}

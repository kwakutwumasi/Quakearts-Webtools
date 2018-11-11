package com.quakearts.test.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Transient;

public class TestParentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -633827964821256528L;

	@Column
	private String stringColumn4;
	private String stringColumn7;
	@Transient
	private String stringColumn8;
	private String stringColumn9;

	public String getStringColumn4() {
		return stringColumn4;
	}

	public void setStringColumn4(String stringColumn4) {
		this.stringColumn4 = stringColumn4;
	}

	public String getStringColumn7() {
		return stringColumn7;
	}

	@Column(length=40)
	public void setStringColumn7(String stringColumn7) {
		this.stringColumn7 = stringColumn7;
	}

	public String getStringColumn8() {
		return stringColumn8;
	}

	public void setStringColumn8(String stringColumn8) {
		this.stringColumn8 = stringColumn8;
	}

	public String getStringColumn9() {
		return stringColumn9;
	}

	@Transient
	public void setStringColumn9(String stringColumn9) {
		this.stringColumn9 = stringColumn9;
	}

}

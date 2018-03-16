package com.quakearts.appbase.test.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("testRequest")
@RequestScoped
public class TestRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1457271262196005158L;

	public String getHashCode() {
		return Integer.toHexString(hashCode());
	}
}

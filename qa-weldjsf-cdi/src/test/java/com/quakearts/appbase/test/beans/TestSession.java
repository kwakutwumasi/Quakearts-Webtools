package com.quakearts.appbase.test.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("testSession")
@SessionScoped
public class TestSession implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8993371625672930869L;

	public String getHashCode() {
		return Integer.toHexString(hashCode());
	}
}

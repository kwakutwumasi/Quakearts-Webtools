package com.quakearts.webtools.resteasy.validation;

import java.io.Serializable;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.jboss.resteasy.api.validation.SimpleViolationsContainer;

public class AppBaseViolationsContainer extends SimpleViolationsContainer implements Serializable {

	private static final long serialVersionUID = 5310680220754582329L;

	public AppBaseViolationsContainer(Object target) {
		super(target);
	}

	public AppBaseViolationsContainer(Set<ConstraintViolation<Object>> cvs) {
		super(cvs);
	}
}

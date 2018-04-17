package com.quakearts.webtools.resteasy.validation.test.helpers;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TestConstraintExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		StringBuilder exceptionBuilder = new StringBuilder(exception.getMessage());
		exception.getConstraintViolations().forEach((violation)->{
			exceptionBuilder.append("\n").append(violation.getMessage());
		});
		return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
	}

}

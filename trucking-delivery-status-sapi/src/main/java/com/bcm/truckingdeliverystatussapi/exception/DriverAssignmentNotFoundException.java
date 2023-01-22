package com.bcm.truckingdeliverystatussapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class DriverAssignmentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 417687750900587753L;

	public DriverAssignmentNotFoundException(String message) {
		super(message);
	}
}

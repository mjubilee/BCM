package com.bcm.truckingdeliverystatussapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class StatusNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7012670743167582314L;

	public StatusNotFoundException(String message) {
		super(message);
	}
}

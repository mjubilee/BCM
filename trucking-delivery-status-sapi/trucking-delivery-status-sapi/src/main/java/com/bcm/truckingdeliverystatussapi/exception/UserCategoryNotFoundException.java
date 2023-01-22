package com.bcm.truckingdeliverystatussapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class UserCategoryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3106391154186635491L;

	public UserCategoryNotFoundException(String message) {
		super(message);
	}
}

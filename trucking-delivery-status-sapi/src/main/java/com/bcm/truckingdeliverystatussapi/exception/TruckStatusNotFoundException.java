package com.bcm.truckingdeliverystatussapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class TruckStatusNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4513819815057297479L;

	public TruckStatusNotFoundException(String message) {
		super(message);
	}
}

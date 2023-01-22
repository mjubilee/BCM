package com.bcm.truckingdeliverystatussapi.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityErrorHandler  extends ResponseEntityExceptionHandler {

	@ExceptionHandler({Exception.class})
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		ErrorDetails error = new ErrorDetails("ALL001", ex.getMessage(), request.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({EmployeeNotFoundException.class})
	public final ResponseEntity<ErrorDetails> handleEmployeeNotFoundException(Exception ex, WebRequest request) throws EmployeeNotFoundException {
		ErrorDetails error = new ErrorDetails("EMP001", ex.getMessage(), request.getSessionId(), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}
		
	@ExceptionHandler({TruckNotFoundException.class})
	public final ResponseEntity<ErrorDetails> handletrucknNotFoundException(Exception ex, WebRequest request) throws TruckNotFoundException {
		ErrorDetails error = new ErrorDetails("EMP001", ex.getMessage(), request.getSessionId(), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorDetails error = new ErrorDetails("INV001", ex.getMessage(), request.getSessionId(), LocalDateTime.now());
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
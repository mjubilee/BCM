package com.bcm.truckingdeliverystatuspapi.deferred;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import com.bcm.truckingdeliverystatuspapi.model.EmployeeResponse;

import rx.Observable;

@Component
public class EmployeeDeferred {
	static Logger log = LoggerFactory.getLogger(EmployeeDeferred.class);

	private static final Duration DEFAULT_RESPONSE_TIME = Duration.ofMinutes(2L);
	
	public DeferredResult< ResponseEntity<List<EmployeeResponse>> > deferResponseList( Observable <List<EmployeeResponse> > observable ) {
		log.info( "EmployeeDeferred : deferResponse -- Defer an async call");
		
		DeferredResult< ResponseEntity<List<EmployeeResponse>> > deferredResult = new DeferredResult(DEFAULT_RESPONSE_TIME.toMillis());
		
		observable.subscribe( (apiResponse) -> {
			ResponseEntity<List<EmployeeResponse>> responseData = new ResponseEntity<List<EmployeeResponse>>(apiResponse, HttpStatus.OK);
			deferredResult.setResult(responseData);
		},//OnNext 
				deferredResult::setErrorResult //OnError
		);
		return deferredResult;
	}
	
	public DeferredResult< ResponseEntity<EmployeeResponse> > deferResponse( Observable <EmployeeResponse > observable ) {
		log.info( "EmployeeDeferred : deferResponse -- Defer an async call");
		
		DeferredResult< ResponseEntity<EmployeeResponse> > deferredResult = new DeferredResult(DEFAULT_RESPONSE_TIME.toMillis());
		
		observable.subscribe( (apiResponse) -> {
			ResponseEntity<EmployeeResponse> responseData = new ResponseEntity<EmployeeResponse>(apiResponse, HttpStatus.OK);
			deferredResult.setResult(responseData);
		},//OnNext 
				deferredResult::setErrorResult //OnError
		);
		return deferredResult;
	}
}

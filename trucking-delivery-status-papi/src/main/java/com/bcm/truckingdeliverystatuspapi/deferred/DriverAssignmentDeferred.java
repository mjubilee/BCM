package com.bcm.truckingdeliverystatuspapi.deferred;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import com.bcm.truckingdeliverystatuspapi.model.DriverAssignmentResponse;

import rx.Observable;

@Component
public class DriverAssignmentDeferred {
	static Logger log = LoggerFactory.getLogger(DriverAssignmentDeferred.class);

	private static final Duration DEFAULT_RESPONSE_TIME = Duration.ofMinutes(2L);
	
	public DeferredResult< ResponseEntity<List<DriverAssignmentResponse>> > deferResponseList( Observable <List<DriverAssignmentResponse> > observable ) {
		log.info( "DriverAssignmentDeferred : deferResponse -- Defer an async call");
		
		DeferredResult< ResponseEntity<List<DriverAssignmentResponse>> > deferredResult = new DeferredResult(DEFAULT_RESPONSE_TIME.toMillis());
		
		observable.subscribe( (apiResponse) -> {
			ResponseEntity<List<DriverAssignmentResponse>> responseData = new ResponseEntity<List<DriverAssignmentResponse>>(apiResponse, HttpStatus.OK);
			deferredResult.setResult(responseData);
		},//OnNext 
				deferredResult::setErrorResult //OnError
		);
		return deferredResult;
	}
	
	public DeferredResult< ResponseEntity<DriverAssignmentResponse> > deferResponse( Observable <DriverAssignmentResponse > observable ) {
		log.info( "DriverAssignmentDeferred : deferResponse -- Defer an async call");
		
		DeferredResult< ResponseEntity<DriverAssignmentResponse> > deferredResult = new DeferredResult(DEFAULT_RESPONSE_TIME.toMillis());
		
		observable.subscribe( (apiResponse) -> {
			ResponseEntity<DriverAssignmentResponse> responseData = new ResponseEntity<DriverAssignmentResponse>(apiResponse, HttpStatus.OK);
			deferredResult.setResult(responseData);
		},//OnNext 
				deferredResult::setErrorResult //OnError
		);
		return deferredResult;
	}
}

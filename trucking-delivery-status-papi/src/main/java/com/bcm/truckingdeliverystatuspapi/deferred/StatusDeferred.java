package com.bcm.truckingdeliverystatuspapi.deferred;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import com.bcm.truckingdeliverystatuspapi.model.StatusResponse;

import rx.Observable;

@Component
public class StatusDeferred {
	static Logger log = LoggerFactory.getLogger(StatusDeferred.class);

	private static final Duration DEFAULT_RESPONSE_TIME = Duration.ofMinutes(2L);
	
	public DeferredResult< ResponseEntity<List<StatusResponse>> > deferResponseList( Observable <List<StatusResponse> > observable ) {
		log.info( "StatusDeferred : deferResponse -- Defer an async call");
		
		DeferredResult< ResponseEntity<List<StatusResponse>> > deferredResult = new DeferredResult(DEFAULT_RESPONSE_TIME.toMillis());
		
		observable.subscribe( (apiResponse) -> {
			ResponseEntity<List<StatusResponse>> responseData = new ResponseEntity<List<StatusResponse>>(apiResponse, HttpStatus.OK);
			deferredResult.setResult(responseData);
		},//OnNext 
				deferredResult::setErrorResult //OnError
		);
		return deferredResult;
	}
	
	public DeferredResult< ResponseEntity<StatusResponse> > deferResponse( Observable <StatusResponse > observable ) {
		log.info( "StatusDeferred : deferResponse -- Defer an async call");
		
		DeferredResult< ResponseEntity<StatusResponse> > deferredResult = new DeferredResult(DEFAULT_RESPONSE_TIME.toMillis());
		
		observable.subscribe( (apiResponse) -> {
			ResponseEntity<StatusResponse> responseData = new ResponseEntity<StatusResponse>(apiResponse, HttpStatus.OK);
			deferredResult.setResult(responseData);
		},//OnNext 
				deferredResult::setErrorResult //OnError
		);
		return deferredResult;
	}
}

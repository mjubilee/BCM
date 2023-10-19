package com.bcm.truckingdeliverystatuspapi.deferred;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import com.bcm.truckingdeliverystatuspapi.model.TruckStatusResponse;
import com.bcm.truckingdeliverystatuspapi.model.VehicleResponse;

import rx.Observable;

@Component
public class TruckStatusDeferred {
	static Logger log = LoggerFactory.getLogger(TruckStatusDeferred.class);

	private static final Duration DEFAULT_RESPONSE_TIME = Duration.ofMinutes(2L);
	
	public DeferredResult< ResponseEntity<List<TruckStatusResponse>> > deferResponseList( Observable <List<TruckStatusResponse> > observable ) {
		log.info( "TruckStatusDeferred : deferResponse -- Defer an async call");
		
		DeferredResult< ResponseEntity<List<TruckStatusResponse>> > deferredResult = new DeferredResult(DEFAULT_RESPONSE_TIME.toMillis());
		
		observable.subscribe( (apiResponse) -> {
			ResponseEntity<List<TruckStatusResponse>> responseData = new ResponseEntity<List<TruckStatusResponse>>(apiResponse, HttpStatus.OK);
			deferredResult.setResult(responseData);
		},//OnNext 
				deferredResult::setErrorResult //OnError
		);
		return deferredResult;
	}
	
	public DeferredResult< ResponseEntity<TruckStatusResponse> > deferResponse( Observable <TruckStatusResponse > observable ) {
		log.info( "TruckStatusDeferred : deferResponse -- Defer an async call");
		
		DeferredResult< ResponseEntity<TruckStatusResponse> > deferredResult = new DeferredResult(DEFAULT_RESPONSE_TIME.toMillis());
		
		observable.subscribe( (apiResponse) -> {
			ResponseEntity<TruckStatusResponse> responseData = new ResponseEntity<TruckStatusResponse>(apiResponse, HttpStatus.OK);
			deferredResult.setResult(responseData);
		},//OnNext 
				deferredResult::setErrorResult //OnError
		);
		return deferredResult;
	}
}

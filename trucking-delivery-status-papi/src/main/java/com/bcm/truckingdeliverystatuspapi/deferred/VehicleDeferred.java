package com.bcm.truckingdeliverystatuspapi.deferred;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import com.bcm.truckingdeliverystatuspapi.model.VehicleResponse;

import rx.Observable;

@Component
public class VehicleDeferred {
	static Logger log = LoggerFactory.getLogger(VehicleDeferred.class);

	private static final Duration DEFAULT_RESPONSE_TIME = Duration.ofMinutes(2L);
	
	public DeferredResult< ResponseEntity<List<VehicleResponse>> > deferResponseList( Observable <List<VehicleResponse> > observable ) {
		log.info( "VehicleDeferred : deferResponse -- Defer an async call");
		
		DeferredResult< ResponseEntity<List<VehicleResponse>> > deferredResult = new DeferredResult(DEFAULT_RESPONSE_TIME.toMillis());
		
		observable.subscribe( (apiResponse) -> {
			ResponseEntity<List<VehicleResponse>> responseData = new ResponseEntity<List<VehicleResponse>>(apiResponse, HttpStatus.OK);
			deferredResult.setResult(responseData);
		},//OnNext 
				deferredResult::setErrorResult //OnError
		);
		return deferredResult;
	}
	
	public DeferredResult< ResponseEntity<VehicleResponse> > deferResponse( Observable <VehicleResponse > observable ) {
		log.info( "VehicleDeferred : deferResponse -- Defer an async call");
		
		DeferredResult< ResponseEntity<VehicleResponse> > deferredResult = new DeferredResult(DEFAULT_RESPONSE_TIME.toMillis());
		
		observable.subscribe( (apiResponse) -> {
			ResponseEntity<VehicleResponse> responseData = new ResponseEntity<VehicleResponse>(apiResponse, HttpStatus.OK);
			deferredResult.setResult(responseData);
		},//OnNext 
				deferredResult::setErrorResult //OnError
		);
		return deferredResult;
	}
}

package com.bcm.truckingdeliverystatuspapi.deferred;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import com.bcm.truckingdeliverystatuspapi.model.UserCategoryResponse;
import rx.Observable;

@Component
public class UserCategoryDeferred {
	static Logger log = LoggerFactory.getLogger(UserCategoryDeferred.class);

	private static final Duration DEFAULT_RESPONSE_TIME = Duration.ofMinutes(2L);
	
	public DeferredResult< ResponseEntity<List<UserCategoryResponse>> > deferResponseList( Observable <List<UserCategoryResponse> > observable ) {
		log.info( "ManagePersonDeferred : deferResponse -- Defer an async call");
		
		DeferredResult< ResponseEntity<List<UserCategoryResponse>> > deferredResult = new DeferredResult(DEFAULT_RESPONSE_TIME.toMillis());
		
		observable.subscribe( (apiResponse) -> {
			ResponseEntity<List<UserCategoryResponse>> responseData = new ResponseEntity<List<UserCategoryResponse>>(apiResponse, HttpStatus.OK);
			deferredResult.setResult(responseData);
		},//OnNext 
				deferredResult::setErrorResult //OnError
		);
		return deferredResult;
	}
	
	public DeferredResult< ResponseEntity<UserCategoryResponse> > deferResponse( Observable <UserCategoryResponse > observable ) {
		log.info( "ManagePersonDeferred : deferResponse -- Defer an async call");
		
		DeferredResult< ResponseEntity<UserCategoryResponse> > deferredResult = new DeferredResult(DEFAULT_RESPONSE_TIME.toMillis());
		
		observable.subscribe( (apiResponse) -> {
			ResponseEntity<UserCategoryResponse> responseData = new ResponseEntity<UserCategoryResponse>(apiResponse, HttpStatus.OK);
			deferredResult.setResult(responseData);
		},//OnNext 
				deferredResult::setErrorResult //OnError
		);
		return deferredResult;
	}
}

package com.bcm.truckingdeliverystatuspapi.controlller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import com.bcm.truckingdeliverystatuspapi.client.UserCategoryClient;
import com.bcm.truckingdeliverystatuspapi.configuration.EndpointConfiguration;
import com.bcm.truckingdeliverystatuspapi.deferred.UserCategoryDeferred;
import com.bcm.truckingdeliverystatuspapi.model.UserCategoryRequest;
import com.bcm.truckingdeliverystatuspapi.model.UserCategoryResponse;

import jakarta.validation.Valid;


@RestController
public class UserCategoryController {

	Logger log = LoggerFactory.getLogger(UserCategoryController.class);
	
	@Autowired
	private Environment environment;

	@Autowired
	private EndpointConfiguration endpointConfiguration;
	
	@Autowired
	UserCategoryClient userCategoryClient;
	

	@Autowired
	UserCategoryDeferred userCategoryDeferred;
	
	@GetMapping(path = "/user-categories")
	public DeferredResult< ResponseEntity<List<UserCategoryResponse> >> retrieveUserCategoryList() {

		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");
		
		this.log.info( host + " -- " + port + " -- trucking-delivery-status-papi -- retrieveUserCategoryList -- Retrieve user category list");
		
		String endpoint = endpointConfiguration.getUserCategory();

		return userCategoryDeferred.deferResponseList(userCategoryClient.submitRequestList(endpoint));
	}
	
	@GetMapping(path = "/user-categories/{id}")
	public DeferredResult< ResponseEntity<UserCategoryResponse> > retrieveUserCategor(@PathVariable String id) {

		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");
		
		this.log.info( host + " -- " + port + " -- trucking-delivery-status-papi -- retrieveUserCategoryList -- Retrieve user category list");
		
		String endpoint = endpointConfiguration.getUserCategory() + '/' + id;

		return userCategoryDeferred.deferResponse(userCategoryClient.submitRequest(endpoint));
	}
	
	@PutMapping("/user-categories")
	public DeferredResult< ResponseEntity<UserCategoryResponse> > updateUserCategory(@Valid @RequestBody UserCategoryRequest request) {		
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-papi -- updateUserCategory -- update an user category");
		
		String endpoint = endpointConfiguration.getUserCategory();

		return userCategoryDeferred.deferResponse(userCategoryClient.updateRequest(endpoint, request));
	}
	
	@PostMapping("/user-categories")
	public DeferredResult< ResponseEntity<UserCategoryResponse> > insertUserCategory(@Valid @RequestBody UserCategoryRequest request) {		
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-papi -- updateUserCategory -- update an user category");
		
		String endpoint = endpointConfiguration.getUserCategory();

		return userCategoryDeferred.deferResponse(userCategoryClient.createRequest(endpoint, request));
	}
}

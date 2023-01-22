package com.bcm.truckingdeliverystatussapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bcm.truckingdeliverystatussapi.exception.UserCategoryNotFoundException;
import com.bcm.truckingdeliverystatussapi.model.UserCategory;
import com.bcm.truckingdeliverystatussapi.repository.UserCategoryRepository;

import jakarta.validation.Valid;

@RestController
public class UserCategoryController {
	Logger log = LoggerFactory.getLogger(UserCategoryController.class);
	
	@Autowired
	private Environment environment;

	@Autowired
	private UserCategoryRepository userCategoryRepo;
	
	@GetMapping("/user-categories")
	public ResponseEntity<List<UserCategory>> retrieveUserCategoryList() {
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");
		
		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- retrieveUserCategoryList -- Retrieve all user categories");
		
		return new ResponseEntity<List<UserCategory>>(this.userCategoryRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/user-categories/{id}")
	public ResponseEntity<UserCategory> retrieveUserCategoryById(@PathVariable Long id) {		
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- retrieveUserCategoryById -- Retrieve specific user category");
		
		Optional<UserCategory> userCategory = this.userCategoryRepo.findById(id);

		if (userCategory.isEmpty()) {			
			throw new UserCategoryNotFoundException("Data with id = " + id + " can not be found.");
		}
		return new ResponseEntity<UserCategory>(userCategory.get(), HttpStatus.OK);
	}
	
	@PostMapping("/user-categories")
	public ResponseEntity<UserCategory> insertUserCategory(@Valid @RequestBody UserCategory userCategory) {		
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- insertUserCategory -- add a new user category");
		
		UserCategory savedUserCategory = userCategoryRepo.save(userCategory);				
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUserCategory.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/user-categories/{id}")
	public ResponseEntity<UserCategory> removeUserCategory(@PathVariable Long id) {
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- removeUserCategory -- remove an user category");
		
		Optional<UserCategory> queryUserCategory = this.userCategoryRepo.findById(id);

		if (queryUserCategory.isEmpty()) {			
			throw new UserCategoryNotFoundException("Data with id = " + id + " can not be found.");
		}
		
		//instead of deleting the record, the userCategory will be disactivate
		//this.userCategoryRepo.delete(userCategory.get());
		queryUserCategory.get().setActive(false);
		this.userCategoryRepo.save(queryUserCategory.get());	
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(queryUserCategory.get().getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/user-categories")
	public ResponseEntity<UserCategory> updateUserCategory(@Valid @RequestBody UserCategory userCategory) {		
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- updateUserCategory -- update an user category");
		
		Optional<UserCategory> queryUserCategory = this.userCategoryRepo.findById(userCategory.getId());

		if (queryUserCategory.isEmpty()) {			
			throw new UserCategoryNotFoundException("Data with id = " + userCategory.getId() + " can not be found.");
		}
		
		queryUserCategory.get().setName(userCategory.getName());
		
		UserCategory savedUserCategory = userCategoryRepo.save(queryUserCategory.get());				
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUserCategory.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bcm.truckingdeliverystatussapi.exception.StatusNotFoundException;
import com.bcm.truckingdeliverystatussapi.model.Status;
import com.bcm.truckingdeliverystatussapi.repository.StatusRepository;

import jakarta.validation.Valid;

@RestController
public class StatusController {
	Logger log = LoggerFactory.getLogger(StatusController.class);
	
	@Autowired
	private Environment environment;

	@Autowired
	private StatusRepository statusRepo;
	
	@GetMapping("/statuses")
	public ResponseEntity<List<Status>> retrieveStatusList() {
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");
		
		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- retrieveStatusList -- Retrieve all statuses");
		
		return new ResponseEntity<List<Status>>(this.statusRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/statuses/{id}")
	public ResponseEntity<Status> retrieveStatusById(@PathVariable Long id) {		
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- retrieveStatusById -- Retrieve specific status");
		
		Optional<Status> status = this.statusRepo.findById(id);

		if (status.isEmpty()) {			
			throw new StatusNotFoundException("Data with id = " + id + " can not be found.");
		}
		return new ResponseEntity<Status>(status.get(), HttpStatus.OK);
	}
	
	@PostMapping("/statuses")
	public ResponseEntity<Status> insertStatus(@Valid @RequestBody Status status) {		
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- insertStatus -- add a new status");
		
		Status savedStatus = statusRepo.save(status);				
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedStatus.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
		
	@PutMapping("/statuses")
	public ResponseEntity<Status> updateStatus(@Valid @RequestBody Status status) {		
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- updateStatus -- update an status");
		
		Optional<Status> queryStatus = this.statusRepo.findById(status.getId());

		if (queryStatus.isEmpty()) {			
			throw new StatusNotFoundException("Data with id = " + status.getId() + " can not be found.");
		}
		
		queryStatus.get().setDescription(status.getDescription());
		queryStatus.get().setOperated(status.isOperated());
		
		Status savedStatus = statusRepo.save(queryStatus.get());				
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedStatus.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
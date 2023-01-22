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

import com.bcm.truckingdeliverystatussapi.exception.DriverAssignmentNotFoundException;
import com.bcm.truckingdeliverystatussapi.model.DriverAssignment;
import com.bcm.truckingdeliverystatussapi.repository.DriverAssignmentRepository;

import jakarta.validation.Valid;

@RestController
public class DriverAssignmentController {
	Logger log = LoggerFactory.getLogger(DriverAssignmentController.class);
	
	@Autowired private Environment environment;
	@Autowired private DriverAssignmentRepository driverAssigmentRepo;

	private String port;
	private String host;
	
	private void setupLocalVariable() {
		this.port = environment.getProperty("local.server.port");
		this.host = environment.getProperty("HOSTNAME");
	}
	
	@GetMapping("/driver-assignments")
	public ResponseEntity<List<DriverAssignment>> retrieveDriverAssignmentList() {
		setupLocalVariable();		
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-sapi -- retrieveDriverAssignmentList -- Retrieve all driver assigment");
		
		return new ResponseEntity<List<DriverAssignment>>(this.driverAssigmentRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/driver-assignments/{id}")
	public ResponseEntity<DriverAssignment> retrieveDriverAssignmentById(@PathVariable Long id) {	
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-sapi -- retrieveDriverAssignmentById -- Retrieve specific driver assignment");
		
		Optional<DriverAssignment> driverAssignment = this.driverAssigmentRepo.findById(id);
		if (driverAssignment.isEmpty()) {			
			throw new DriverAssignmentNotFoundException("Data with id = " + id + " can not be found.");
		}
		
		return new ResponseEntity<DriverAssignment>(driverAssignment.get(), HttpStatus.OK);
	}
	
	@PostMapping("/driver-assignments")
	public ResponseEntity<DriverAssignment> insertDriverAssignment(@Valid @RequestBody DriverAssignment driverAssignment) {	
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-sapi -- insertDriverAssignment -- add a new driver assignment");
		
		DriverAssignment savedDriverAssignment = driverAssigmentRepo.save(driverAssignment);				
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedDriverAssignment.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/driver-assignments/{id}")
	public ResponseEntity<DriverAssignment> removeDriverAssignment(@PathVariable Long id) {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-sapi -- removeDriverAssignment -- remove an driver assignment");
		
		Optional<DriverAssignment> queryDriverAssignment = this.driverAssigmentRepo.findById(id);
		if (queryDriverAssignment.isEmpty()) {			
			throw new DriverAssignmentNotFoundException("Data with id = " + id + " can not be found.");
		}
		
		this.driverAssigmentRepo.delete(queryDriverAssignment.get());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(queryDriverAssignment.get().getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/driver-assignments")
	public ResponseEntity<DriverAssignment> updateDriverAssignment(@Valid @RequestBody DriverAssignment driverAssignment) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-sapi -- updateDriverAssignment -- update an driver assignment");
		
		Optional<DriverAssignment> queryDriverAssignment = this.driverAssigmentRepo.findById(driverAssignment.getId());
		if (queryDriverAssignment.isEmpty()) {			
			throw new DriverAssignmentNotFoundException("Data with id = " + driverAssignment.getId() + " can not be found.");
		}
		
		queryDriverAssignment.get().setAssignment_date_time(driverAssignment.getAssignment_date_time());
		queryDriverAssignment.get().setDriver_id(driverAssignment.getDriver_id());
		queryDriverAssignment.get().setTruck_id(driverAssignment.getTruck_id());
		
		DriverAssignment savedDriverAssignment = driverAssigmentRepo.save(queryDriverAssignment.get());				
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedDriverAssignment.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
}

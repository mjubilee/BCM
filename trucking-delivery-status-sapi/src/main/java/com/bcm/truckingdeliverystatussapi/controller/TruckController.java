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


import com.bcm.truckingdeliverystatussapi.exception.TruckNotFoundException;
import com.bcm.truckingdeliverystatussapi.model.Truck;
import com.bcm.truckingdeliverystatussapi.repository.TruckRepository;

import jakarta.validation.Valid;

@RestController
public class TruckController {
	Logger log = LoggerFactory.getLogger(TruckController.class);
	
	@Autowired private Environment environment;
	@Autowired private TruckRepository truckRepo;
	
	private String port;
	private String host;
	
	private void setupLocalVariable() {
		this.port = environment.getProperty("local.server.port");
		this.host = environment.getProperty("HOSTNAME");
	}
	
	@GetMapping("/trucks")
	public ResponseEntity<List<Truck>> retrieveTruckList() {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-sapi -- retrieveTruckList -- Retrieve all trucks");
		
		return new ResponseEntity<List<Truck>>(this.truckRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/trucks/{id}")
	public ResponseEntity<Truck> retrieveTruckById(@PathVariable Long id) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-sapi -- retrieveTruckById -- Retrieve specific truck");
		
		Optional<Truck> truck = this.truckRepo.findById(id);
		if (truck.isEmpty()) {			
			throw new TruckNotFoundException("Data with id = " + id + " can not be found.");
		}
		
		return new ResponseEntity<Truck>(truck.get(), HttpStatus.OK);
	}
	
	@PostMapping("/trucks")
	public ResponseEntity<Truck> insertTruck(@Valid @RequestBody Truck truck) {		
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-sapi -- insertTruck -- add a new truck");
		
		Truck savedTruck = truckRepo.save(truck);				
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedTruck.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/trucks/{id}")
	public ResponseEntity<Truck> removeTruck(@PathVariable Long id) {
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-sapi -- removeTruck -- remove a truck");
		
		Optional<Truck> queryTruck = this.truckRepo.findById(id);
		if (queryTruck.isEmpty()) {			
			throw new TruckNotFoundException("Data with id = " + id + " can not be found.");
		}
		
		queryTruck.get().setActive(false);
		this.truckRepo.save(queryTruck.get());	
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(queryTruck.get().getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/trucks")
	public ResponseEntity<Truck> updateTruck(@Valid @RequestBody Truck truck) {	
		setupLocalVariable();
		this.log.info( this.host + " -- " + this.port + " -- trucking-delivery-status-sapi -- updateTruck -- update an truck");
		
		Optional<Truck> queryTruck = this.truckRepo.findById(truck.getId());
		if (queryTruck.isEmpty()) {			
			throw new TruckNotFoundException("Data with id = " + truck.getId() + " can not be found.");
		}
		
		queryTruck.get().setModel(truck.getModel());
		queryTruck.get().setVin(truck.getVin());
		queryTruck.get().setYear_built(truck.getYear_built());
		
		Truck savedtruck = truckRepo.save(queryTruck.get());				
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedtruck.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
}

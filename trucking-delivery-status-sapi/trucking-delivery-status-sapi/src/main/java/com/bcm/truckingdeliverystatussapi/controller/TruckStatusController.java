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

import com.bcm.truckingdeliverystatussapi.exception.TruckStatusNotFoundException;
import com.bcm.truckingdeliverystatussapi.model.TruckStatus;
import com.bcm.truckingdeliverystatussapi.repository.TruckStatusRepository;

import jakarta.validation.Valid;

@RestController
public class TruckStatusController {
	Logger log = LoggerFactory.getLogger(TruckStatusController.class);
	
	@Autowired
	private Environment environment;

	@Autowired
	private TruckStatusRepository truckStatusRepo;
	
	@GetMapping("/truck-statuses")
	public ResponseEntity<List<TruckStatus>> retrieveTruckStatusList() {
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");
		
		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- retrieveTruckStatusList -- Retrieve all truck statuses");
		
		return new ResponseEntity<List<TruckStatus>>(this.truckStatusRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/truck-statuses/{id}")
	public ResponseEntity<TruckStatus> retrieveTruckStatusById(@PathVariable Long id) {		
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- retrieveTruckStatusById -- Retrieve specific truck status");
		
		Optional<TruckStatus> truckStatus = this.truckStatusRepo.findById(id);

		if (truckStatus.isEmpty()) {			
			throw new TruckStatusNotFoundException("Data with id = " + id + " can not be found.");
		}
		return new ResponseEntity<TruckStatus>(truckStatus.get(), HttpStatus.OK);
	}
	
	@PostMapping("/truck-statuses")
	public ResponseEntity<TruckStatus> insertTruckStatus(@Valid @RequestBody TruckStatus truckStatus) {		
		String port = environment.getProperty("local.server.port");
		String host = environment.getProperty("HOSTNAME");

		this.log.info( host + " -- " + port + " -- trucking-delivery-status-sapi -- insertTruckStatus -- add a new truck status");
		
		TruckStatus savedTruckStatus = truckStatusRepo.save(truckStatus);				
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedTruckStatus.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
}
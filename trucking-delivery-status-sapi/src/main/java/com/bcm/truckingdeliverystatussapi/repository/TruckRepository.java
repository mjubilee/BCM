package com.bcm.truckingdeliverystatussapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcm.truckingdeliverystatussapi.model.Truck;

public interface TruckRepository extends JpaRepository<Truck, Long> {

}

package com.bcm.truckingdeliverystatussapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcm.truckingdeliverystatussapi.model.TruckStatus;

public interface TruckStatusRepository extends JpaRepository<TruckStatus, Long> {

}
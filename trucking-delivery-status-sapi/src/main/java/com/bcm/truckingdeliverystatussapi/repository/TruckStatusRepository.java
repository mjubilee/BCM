package com.bcm.truckingdeliverystatussapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcm.truckingdeliverystatussapi.model.TruckStatus;

public interface TruckStatusRepository extends JpaRepository<TruckStatus, Long> {

	List<TruckStatus> findByTruckId(Long truckId);
}
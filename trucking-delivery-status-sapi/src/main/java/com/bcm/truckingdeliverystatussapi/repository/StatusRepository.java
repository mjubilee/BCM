package com.bcm.truckingdeliverystatussapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcm.truckingdeliverystatussapi.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
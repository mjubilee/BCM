package com.bcm.truckingdeliverystatussapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcm.truckingdeliverystatussapi.model.DriverAssignment;

public interface DriverAssignmentRepository extends JpaRepository<DriverAssignment, Long> {

}
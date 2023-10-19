package com.bcm.truckingdeliverystatuspapi.model;

import java.time.LocalDateTime;

public class DriverAssignmentResponse {
	private Long id;
	private Long driver_id;
	private Long truck_id;
	private LocalDateTime assignment_date_time;
	private boolean active;
	
	public DriverAssignmentResponse() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getDriver_id() {
		return driver_id;
	}
	
	public void setDriver_id(Long driver_id) {
		this.driver_id = driver_id;
	}
	
	public Long getTruck_id() {
		return truck_id;
	}
	
	public void setTruck_id(Long truck_id) {
		this.truck_id = truck_id;
	}
	
	public LocalDateTime getAssignment_date_time() {
		return assignment_date_time;
	}
	
	public void setAssignment_date_time(LocalDateTime assignment_date_time) {
		this.assignment_date_time = assignment_date_time;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}

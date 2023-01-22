package com.bcm.truckingdeliverystatussapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TruckStatus {
	@Id
	private Long id;
	private Long truck_id;
	private Long status_id;
	private LocalDateTime status_update_time;
	
	public TruckStatus() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getTruck_id() {
		return truck_id;
	}
	
	public void setTruck_id(Long truck_id) {
		this.truck_id = truck_id;
	}
	
	public Long getStatus_id() {
		return status_id;
	}
	
	public void setStatus_id(Long status_id) {
		this.status_id = status_id;
	}
	
	public LocalDateTime getStatus_update_time() {
		return status_update_time;
	}
	
	public void setStatus_update_time(LocalDateTime status_update_time) {
		this.status_update_time = status_update_time;
	}
	
}

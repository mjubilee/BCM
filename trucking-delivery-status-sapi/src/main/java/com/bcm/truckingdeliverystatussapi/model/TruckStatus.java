package com.bcm.truckingdeliverystatussapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TruckStatus {
	@Id
	@GeneratedValue
	private Long id;
	private Long truckId;
	private Long statusId;
	private LocalDateTime statusUpdateTime;
	private boolean isLastStatus;
	
	public TruckStatus() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getTruckId() {
		return truckId;
	}

	public void setTruckId(Long truckId) {
		this.truckId = truckId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public LocalDateTime getStatusUpdateTime() {
		return statusUpdateTime;
	}

	public void setStatusUpdateTime(LocalDateTime statusUpdateTime) {
		this.statusUpdateTime = statusUpdateTime;
	}

	public boolean isLastStatus() {
		return isLastStatus;
	}

	public void setLastStatus(boolean isLastStatus) {
		this.isLastStatus = isLastStatus;
	}
	
}

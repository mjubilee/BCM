package com.bcm.truckingdeliverystatussapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserCategory {
	@Id
	private Long id;
	private String name;
	private boolean active;
	
	public UserCategory() {
		super();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
}

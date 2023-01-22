package com.bcm.truckingdeliverystatussapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {
	@Id
	private Long id;
	private String name;
	private String driver_licence;
	private Long category_id;
	
	@JsonIgnore
	private boolean active;
	
	public Employee() {
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
	
	public String getDriver_licence() {
		return driver_licence;
	}
	
	public void setDriver_licence(String driver_licence) {
		this.driver_licence = driver_licence;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}

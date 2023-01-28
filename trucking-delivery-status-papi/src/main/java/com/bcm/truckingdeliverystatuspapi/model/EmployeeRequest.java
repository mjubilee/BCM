package com.bcm.truckingdeliverystatuspapi.model;

public class EmployeeRequest {
	private Long id;
	private String name;
	private String driverLicence;
	private Long categoryId;
	private boolean active;
	
	public EmployeeRequest() {
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
	
	public String getDriverLicence() {
		return driverLicence;
	}
	
	public void setDriverLicence(String driverLicence) {
		this.driverLicence = driverLicence;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}

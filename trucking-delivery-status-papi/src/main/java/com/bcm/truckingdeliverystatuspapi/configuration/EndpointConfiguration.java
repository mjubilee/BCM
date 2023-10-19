package com.bcm.truckingdeliverystatuspapi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "endpoint.sapi")
@Component
public class EndpointConfiguration {
	private String userCategory;
	private String truck;
	private String employee;
	private String status;
	private String truckStatus;
	private String driverAssignment;
	
	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}
	
	public String getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}

	public String getTruck() {
		return truck;
	}

	public void setTruck(String truck) {
		this.truck = truck;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTruckStatus() {
		return truckStatus;
	}

	public void setTruckStatus(String truckStatus) {
		this.truckStatus = truckStatus;
	}

	public String getDriverAssignment() {
		return driverAssignment;
	}

	public void setDriverAssignment(String driverAssignment) {
		this.driverAssignment = driverAssignment;
	}
	
	
}

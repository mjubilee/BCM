package com.bcm.truckingdeliverystatuspapi.model;

public class VehicleRequest {
	private Long id;
	private String model;
	private String year_built;
	private String vin;
	
	public VehicleRequest() {
		super();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getYear_built() {
		return year_built;
	}
	
	public void setYear_built(String year_built) {
		this.year_built = year_built;
	}
	
	public String getVin() {
		return vin;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}
}

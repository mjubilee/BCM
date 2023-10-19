package com.bcm.truckingdeliverystatuspapi.model;

public class StatusRequest {
	private Long id;
	private String description;
	private boolean operated;
	
	public StatusRequest() {
		super();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isOperated() {
		return operated;
	}
	
	public void setOperated(boolean operated) {
		this.operated = operated;
	}
}

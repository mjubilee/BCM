package com.bcm.truckingdeliverystatuspapi.model;

public class UserCategoryRequest {
	private Long id;
	private String name;
	
	public UserCategoryRequest() {
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

}

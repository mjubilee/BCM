package com.bcm.truckingdeliverystatuspapi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "endpoint.sapi")
@Component
public class EndpointConfiguration {
	private String userCategory;

	public String getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}
	
	
}

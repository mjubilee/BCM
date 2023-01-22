package com.bcm.truckingdeliverystatuspapi.client;

import java.util.List;

import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvokerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bcm.truckingdeliverystatuspapi.model.UserCategoryRequest;
import com.bcm.truckingdeliverystatuspapi.model.UserCategoryResponse;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import rx.Observable;

@Component
public class UserCategoryClient {
	Logger log = LoggerFactory.getLogger(UserCategoryClient.class);
	
	public Observable<List<UserCategoryResponse>> submitRequestList(String endpoint) {
		log.info( "UserCategoryClient : submitRequestList -- Retrieve list of user category");

		Client client = ClientBuilder.newClient();
		client.register(RxObservableInvokerProvider.class);
		
		WebTarget webTarget = client.target(endpoint);
		
		RxObservableInvoker builder = webTarget.request()
				.rx(RxObservableInvoker.class);
		
		return builder.get()
				.map( res -> {
			return res.readEntity(List.class);
			});
	}
	
	public Observable<UserCategoryResponse> submitRequest(String endpoint) {
		log.info( "UserCategoryClient : submitRequestAsync -- Retrieve specific user category");

		Client client = ClientBuilder.newClient();
		client.register(RxObservableInvokerProvider.class);
		
		WebTarget webTarget = client.target(endpoint);
		
		RxObservableInvoker builder = webTarget.request()
				.rx(RxObservableInvoker.class);
		
		return builder.get()
				.map( res -> {
			return res.readEntity(UserCategoryResponse.class);
			});
	}
	
	public Observable<UserCategoryResponse> updateRequest(String endpoint, UserCategoryRequest request) {
		log.info( "UserCategoryClient : submitRequestAsync -- Retrieve specific user category");

		Client client = ClientBuilder.newClient();
		client.register(RxObservableInvokerProvider.class);
		
		WebTarget webTarget = client.target(endpoint);
		
		RxObservableInvoker builder = webTarget.request()
				.rx(RxObservableInvoker.class);
		
		return builder.put(Entity.entity(request, MediaType.APPLICATION_JSON))
				.map( res -> {
			return res.readEntity(UserCategoryResponse.class);
			});
	}
	
	public Observable<UserCategoryResponse> createRequest(String endpoint, UserCategoryRequest request) {
		log.info( "UserCategoryClient : submitRequestAsync -- Retrieve specific user category");

		Client client = ClientBuilder.newClient();
		client.register(RxObservableInvokerProvider.class);
		
		WebTarget webTarget = client.target(endpoint);
		
		RxObservableInvoker builder = webTarget.request()
				.rx(RxObservableInvoker.class);
		
		return builder.post(Entity.entity(request, MediaType.APPLICATION_JSON))
				.map( res -> {
			return res.readEntity(UserCategoryResponse.class);
			});
	}
}

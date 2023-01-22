package com.bcm.truckingdeliverystatuspapi.client;

import java.util.List;

import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvokerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bcm.truckingdeliverystatuspapi.model.UserCategoryRequest;
import com.bcm.truckingdeliverystatuspapi.model.UserCategoryResponse;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import rx.Observable;

@Component
public class UserCategoryClient {
	Logger log = LoggerFactory.getLogger(UserCategoryClient.class);
	
	public Observable<List<UserCategoryResponse>> submitRequestList(String endpoint) {
		log.info( "UserCategoryClient : submitRequestList -- Retrieve list of user category");

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.get()
				.map( res -> {
					return res.readEntity(List.class);
				});
	}
	
	public Observable<UserCategoryResponse> submitRequest(String endpoint) {
		log.info( "UserCategoryClient : submitRequestAsync -- Retrieve specific user category");

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.get()
				.map( res -> {
					return res.readEntity(UserCategoryResponse.class);
				});
	}
	
	public Observable<UserCategoryResponse> updateRequest(String endpoint, UserCategoryRequest request) {
		log.info( "UserCategoryClient : submitRequestAsync -- Retrieve specific user category");

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.put(Entity.entity(request, MediaType.APPLICATION_JSON))
				.map( res -> {
					return res.readEntity(UserCategoryResponse.class);
				});
	}
	
	public Observable<UserCategoryResponse> createRequest(String endpoint, UserCategoryRequest request) {
		log.info( "UserCategoryClient : submitRequestAsync -- Retrieve specific user category");

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.post(Entity.entity(request, MediaType.APPLICATION_JSON))
				.map( res -> {
					return res.readEntity(UserCategoryResponse.class);
				});
	}
}

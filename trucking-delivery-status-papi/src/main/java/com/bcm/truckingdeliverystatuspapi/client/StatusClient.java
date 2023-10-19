package com.bcm.truckingdeliverystatuspapi.client;

import java.util.List;

import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvokerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bcm.truckingdeliverystatuspapi.model.StatusRequest;
import com.bcm.truckingdeliverystatuspapi.model.StatusResponse;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import rx.Observable;

@Component
public class StatusClient {
	Logger log = LoggerFactory.getLogger(StatusClient.class);
	
	public Observable<List<StatusResponse>> submitRequestList(String endpoint) {
		log.info( "StatusClient : submitRequestList -- Retrieve list of status - " + endpoint);

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
	
	public Observable<StatusResponse> submitRequest(String endpoint) {
		log.info( "StatusClient : submitRequestAsync -- Retrieve specific status - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.get()
				.map( res -> {
					return res.readEntity(StatusResponse.class);
				});
	}
	
	public Observable<StatusResponse> updateRequest(String endpoint, StatusRequest request) {
		log.info( "StatusClient : updateRequest -- update a status - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.put(Entity.entity(request, MediaType.APPLICATION_JSON))
				.map( res -> {
					return res.readEntity(StatusResponse.class);
				});
	}
	
	public Observable<StatusResponse> createRequest(String endpoint, StatusRequest request) {
		log.info( "StatusClient : createRequest -- add a new status - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.post(Entity.entity(request, MediaType.APPLICATION_JSON))
				.map( res -> {
					return res.readEntity(StatusResponse.class);
				});
	}
}

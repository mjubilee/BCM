package com.bcm.truckingdeliverystatuspapi.client;

import java.util.List;

import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvokerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bcm.truckingdeliverystatuspapi.model.TruckStatusRequest;
import com.bcm.truckingdeliverystatuspapi.model.TruckStatusResponse;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import rx.Observable;

@Component
public class TruckStatusClient {
	Logger log = LoggerFactory.getLogger(TruckStatusClient.class);
	
	public Observable<List<TruckStatusResponse>> submitRequestList(String endpoint) {
		log.info( "TruckStatusClient : submitRequestList -- Retrieve list of truck status - " + endpoint);

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
	
	public Observable<TruckStatusResponse> submitRequest(String endpoint) {
		log.info( "TruckStatusClient : submitRequestAsync -- Retrieve specific truck status - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.get()
				.map( res -> {
					return res.readEntity(TruckStatusResponse.class);
				});
	}
	
	public Observable<TruckStatusResponse> updateRequest(String endpoint, TruckStatusRequest request) {
		log.info( "TruckStatusClient : updateRequest -- update a truck status - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.put(Entity.entity(request, MediaType.APPLICATION_JSON))
				.map( res -> {
					return res.readEntity(TruckStatusResponse.class);
				});
	}
	
	public Observable<TruckStatusResponse> createRequest(String endpoint, TruckStatusRequest request) {
		log.info( "TruckStatusClient : createRequest -- add a new truck status - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.post(Entity.entity(request, MediaType.APPLICATION_JSON))
				.map( res -> {
					return res.readEntity(TruckStatusResponse.class);
				});
	}
}

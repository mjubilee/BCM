package com.bcm.truckingdeliverystatuspapi.client;

import java.util.List;

import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvokerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bcm.truckingdeliverystatuspapi.model.VehicleRequest;
import com.bcm.truckingdeliverystatuspapi.model.VehicleResponse;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import rx.Observable;

@Component
public class VehicleClient {
	Logger log = LoggerFactory.getLogger(VehicleClient.class);
	
	public Observable<List<VehicleResponse>> submitRequestList(String endpoint) {
		log.info( "VehicleClient : submitRequestList -- Retrieve list of vehicle - " + endpoint);

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
	
	public Observable<VehicleResponse> submitRequest(String endpoint) {
		log.info( "VehicleClient : submitRequestAsync -- Retrieve specific vehicle - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.get()
				.map( res -> {
					return res.readEntity(VehicleResponse.class);
				});
	}
	
	public Observable<VehicleResponse> updateRequest(String endpoint, VehicleRequest request) {
		log.info( "VehicleClient : updateRequest -- update a vehicle - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.put(Entity.entity(request, MediaType.APPLICATION_JSON))
				.map( res -> {
					return res.readEntity(VehicleResponse.class);
				});
	}
	
	public Observable<VehicleResponse> createRequest(String endpoint, VehicleRequest request) {
		log.info( "VehicleClient : createRequest -- add a new vehicle - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.post(Entity.entity(request, MediaType.APPLICATION_JSON))
				.map( res -> {
					return res.readEntity(VehicleResponse.class);
				});
	}
}

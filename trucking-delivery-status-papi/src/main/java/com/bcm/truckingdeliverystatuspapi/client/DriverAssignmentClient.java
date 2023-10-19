package com.bcm.truckingdeliverystatuspapi.client;

import java.util.List;

import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvokerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bcm.truckingdeliverystatuspapi.model.DriverAssignmentRequest;
import com.bcm.truckingdeliverystatuspapi.model.DriverAssignmentResponse;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import rx.Observable;

@Component
public class DriverAssignmentClient {
Logger log = LoggerFactory.getLogger(DriverAssignmentClient.class);
	
	public Observable<List<DriverAssignmentResponse>> submitRequestList(String endpoint) {
		log.info( "DriverAssignmentClient : submitRequestList -- Retrieve list of driver assignment - " + endpoint);

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
	
	public Observable<DriverAssignmentResponse> submitRequest(String endpoint) {
		log.info( "DriverAssignmentClient : submitRequestAsync -- Retrieve specific driver assignment - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.get()
				.map( res -> {
					return res.readEntity(DriverAssignmentResponse.class);
				});
	}
	
	public Observable<DriverAssignmentResponse> updateRequest(String endpoint, DriverAssignmentRequest request) {
		log.info( "DriverAssignmentClient : submitRequestAsync -- Retrieve specific driver assignment - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.put(Entity.entity(request, MediaType.APPLICATION_JSON))
				.map( res -> {
					return res.readEntity(DriverAssignmentResponse.class);
				});
	}
	
	public Observable<DriverAssignmentResponse> createRequest(String endpoint, DriverAssignmentRequest request) {
		log.info( "DriverAssignmentClient : createRequest -- add a new driver assignment - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.post(Entity.entity(request, MediaType.APPLICATION_JSON))
				.map( res -> {
					return res.readEntity(DriverAssignmentResponse.class);
				});
	}
}

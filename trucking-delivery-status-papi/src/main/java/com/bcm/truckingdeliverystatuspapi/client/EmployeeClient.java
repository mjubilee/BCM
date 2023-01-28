package com.bcm.truckingdeliverystatuspapi.client;

import java.util.List;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvokerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bcm.truckingdeliverystatuspapi.model.EmployeeRequest;
import com.bcm.truckingdeliverystatuspapi.model.EmployeeResponse;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import rx.Observable;

@Component
public class EmployeeClient {
	Logger log = LoggerFactory.getLogger(EmployeeClient.class);
	
	public Observable<List<EmployeeResponse>> submitRequestList(String endpoint) {
		log.info( "EmployeeClient : submitRequestList -- Retrieve list of employee - " + endpoint);

//		Client client = ClientBuilder.newClient();
//		client.register(RxObservableInvokerProvider.class);
//		log.info( "EmployeeClient1 : submitRequestList -- Retrieve list of employee - " + endpoint);
//		
//		WebTarget webTarget = client.target(endpoint);
//		log.info( "EmployeeClient2 : submitRequestList -- Retrieve list of employee - " + endpoint);
//		
//		RxObservableInvoker builder = webTarget.request()
//				.rx(RxObservableInvoker.class);
//		log.info( "EmployeeClient3 : submitRequestList -- Retrieve list of employee - " + endpoint);
//		
//		return builder.get()
//				.map( res -> {
//			return res.readEntity(List.class);
//			});
		
		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request(MediaType.APPLICATION_JSON)
				.rx(RxObservableInvoker.class)
				.get()
				.map( res -> {
					return res.readEntity(List.class);
				});
	}
	
	public Observable<EmployeeResponse> submitRequest(String endpoint) {
		log.info( "EmployeeClient : submitRequestAsync -- Retrieve specific employee - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request(MediaType.APPLICATION_JSON)
				.rx(RxObservableInvoker.class)
				.get()
				.map( res -> {
					log.info(res.readEntity(EmployeeResponse.class).toString());
					return res.readEntity(EmployeeResponse.class);
				});
	}
	
	public Observable<EmployeeResponse> updateRequest(String endpoint, EmployeeRequest request) {
		log.info( "EmployeeClient : updateRequest -- update a employee - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request(MediaType.APPLICATION_JSON)
				.rx(RxObservableInvoker.class)
				.put(Entity.entity(request, MediaType.APPLICATION_JSON))
				.map( res -> {
					return res.readEntity(EmployeeResponse.class);
				});
	}
	
	public Observable<EmployeeResponse> createRequest(String endpoint, EmployeeRequest request) {
		log.info( "EmployeeClient : createRequest -- add a new employee - " + endpoint);

		return ClientBuilder.newClient()
				.register(RxObservableInvokerProvider.class)
				.target(endpoint)
				.request()
				.rx(RxObservableInvoker.class)
				.post(Entity.entity(request, MediaType.APPLICATION_JSON))
				.map( res -> {
					return res.readEntity(EmployeeResponse.class);
				});
	}
}

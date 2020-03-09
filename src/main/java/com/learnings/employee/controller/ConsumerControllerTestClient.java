package com.learnings.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;


public class ConsumerControllerTestClient {

	@Autowired
	// private DiscoveryClient discoveryClient;
	private LoadBalancerClient discoveryClient;

	@Value("${service.id}")
	private String serviceId;

	public void getEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("BG: service id-->" + serviceId);

		// List<ServiceInstance> clientInfo = discoveryClient.getInstances(serviceId);
		// ServiceInstance serviceInstance = clientInfo == null ? null :
		// clientInfo.get(0);
		ServiceInstance serviceInstance = discoveryClient.choose(serviceId);
		String baseUrl = serviceInstance == null ? "http://localhost:8080/employee"
				: serviceInstance.getUri().toString() + "/employee";
System.out.println(serviceInstance.getUri().toString());
		ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHttpEntity(), String.class);

		System.out.println(response.getBody());
	}

	private HttpEntity<?> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}

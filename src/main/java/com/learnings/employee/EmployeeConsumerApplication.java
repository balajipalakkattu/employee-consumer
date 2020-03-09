package com.learnings.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.learnings.employee.controller.ConsumerControllerTestClient;

@SpringBootApplication
public class EmployeeConsumerApplication {

	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(EmployeeConsumerApplication.class, args);
		ConsumerControllerTestClient consumerControllerClient= context.getBean(ConsumerControllerTestClient.class);
		System.out.println(consumerControllerClient);
		consumerControllerClient.getEmployee();
	}
	
	@Bean
	public  ConsumerControllerTestClient  consumerControllerClient()
	{
		return  new ConsumerControllerTestClient();
	}

}

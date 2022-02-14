package com.repo.mongo.demo;

import com.repo.mongo.demo.controller.ControllerCustomerInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableEurekaClient

public class CustomerMongoApplication  {

	public static void main(String[] args) {
		SpringApplication.run(CustomerMongoApplication.class, args);
	}

}

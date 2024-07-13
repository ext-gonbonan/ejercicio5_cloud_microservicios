package com.formacion.inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication(scanBasePackages = { "com.formacion.controller", "com.formacion.service", "com.formacion.inicio" })

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@LoadBalanced
	@Bean
	public RestClient restClient() {
		return RestClient.create();
	}

}

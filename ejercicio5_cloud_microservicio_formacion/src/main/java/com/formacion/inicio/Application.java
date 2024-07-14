package com.formacion.inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


//@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = { "com.formacion.controller", "com.formacion.service", "com.formacion.inicio" })

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        //return new RestTemplate();
		// aumentamos el tiempo de espera para la conexión (corregir errores de peticiones)
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
	    factory.setConnectTimeout(2000);
	    factory.setReadTimeout(2000);
	    return new RestTemplate(factory);
    }

}

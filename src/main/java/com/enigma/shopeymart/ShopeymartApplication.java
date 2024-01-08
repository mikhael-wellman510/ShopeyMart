package com.enigma.shopeymart;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@OpenAPIDefinition
public class ShopeymartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopeymartApplication.class, args);
		System.out.println("Aplikasi Berjalan");
	}

	//Todo -> Rest Template
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}

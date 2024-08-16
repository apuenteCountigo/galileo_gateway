package com.formacionbdi.springboot.app.gateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@EnableEurekaClient
@EntityScan({"com.galileo.cu.commons.models"})
@CrossOrigin(origins = "*")
@SpringBootApplication
public class SpringbootServicioGatewayServerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioGatewayServerApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("GATEWAY V-0531-1350 CrossOrigin fallo");		
	}
}

package com.invoice.rsbcurd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
@ComponentScan
public class InvoiceServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceServicesApplication.class, args);
	}
}
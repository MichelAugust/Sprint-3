package com.api.estados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ApiEstadosBrasilApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiEstadosBrasilApplication.class, args);
	}

}

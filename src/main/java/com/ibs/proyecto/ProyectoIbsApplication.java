package com.ibs.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.ibs.proyecto.model")
@EnableJpaRepositories(basePackages = "com.ibs.proyecto.repository")
public class ProyectoIbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoIbsApplication.class, args);
	}

}

package com.msvc.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * EnableEurekaClient no es ncesario por la version de spring, por tal motivo
 * no se coloca y el servicio funciona normal
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UsuarioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioServiceApplication.class, args);
	}

}

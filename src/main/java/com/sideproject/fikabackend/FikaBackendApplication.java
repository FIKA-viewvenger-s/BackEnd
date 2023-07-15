package com.sideproject.fikabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableJpaAuditing
@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class FikaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FikaBackendApplication.class, args);
	}

}

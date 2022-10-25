package com.example.zavrsni_projekat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.zavrsni_projekat.repository")
@EntityScan("com.example.zavrsni_projekat.model")
public class ZavrsniProjekatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZavrsniProjekatApplication.class, args);
	}

}

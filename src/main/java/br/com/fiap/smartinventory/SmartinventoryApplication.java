package br.com.fiap.smartinventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SmartinventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartinventoryApplication.class, args);
	}

}

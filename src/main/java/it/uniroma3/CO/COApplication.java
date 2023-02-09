package it.uniroma3.CO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class COApplication {

	public static void main(String[] args) {
		SpringApplication.run(COApplication.class, args);
	}

}

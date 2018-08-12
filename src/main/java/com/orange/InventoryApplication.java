package com.orange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String x = passwordEncoder.encode("123456");
//		for(int i = 0; i < 10;i++)
//		System.err.println(x);

	}

}

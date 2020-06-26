package com.customer.batch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CustomerJobApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CustomerJobApplication.class, args);
	}
}

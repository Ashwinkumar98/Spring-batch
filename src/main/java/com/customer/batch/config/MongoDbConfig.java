package com.customer.batch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/*
 * Mongo DB connection configuration
 * */

@Configuration
@EnableMongoRepositories(basePackages = "com.customer.batch.repo")
public class MongoDbConfig {
	
	@Value("${mongo.connection}")
	private String mongoConnection;
	
	@Value("${mongo.database}")
	private String databaseName;
	
	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create(mongoConnection);
	}
	
	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(),databaseName);
	} 
}

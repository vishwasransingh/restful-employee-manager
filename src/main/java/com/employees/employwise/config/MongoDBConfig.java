package com.employees.employwise.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "com.employees.employwise.repository.EmployeeRepository")
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "mydatabase";
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }
}

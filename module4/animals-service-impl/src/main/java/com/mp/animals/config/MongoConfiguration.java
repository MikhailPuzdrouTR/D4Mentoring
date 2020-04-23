package com.mp.animals.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {

    @Bean
    MongoClient mongoClient() {
        return new MongoClient();
    }

    @Bean
    MongoDatabase mongoDatabase(MongoClient mongoClient) {
        return mongoClient.getDatabase("animals");
    }
}

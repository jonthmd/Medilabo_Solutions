package com.medilabo.note;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        MongoCredential credential = MongoCredential.createCredential(
                "root",
                "admin",
                "MotDePasseSimple123".toCharArray()
        );

        ServerAddress serverAddress = new ServerAddress("localhost", 27017);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(List.of(serverAddress)))
                .credential(credential)
                .build();

        return MongoClients.create(settings);
    }
}

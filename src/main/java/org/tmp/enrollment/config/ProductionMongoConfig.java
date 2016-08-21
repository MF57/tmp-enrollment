package org.tmp.enrollment.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;


@Configuration
@EnableMongoRepositories({"org.tmp"})
@Profile("production")
public class ProductionMongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "tmp-enrollment";
    }

    @Override
    public Mongo mongo() throws Exception {
        MongoCredential credential = MongoCredential.createCredential(
                "root", "admin", "root123".toCharArray());
        ServerAddress serverAddress = new ServerAddress("51.255.48.55", 27017);
        MongoClient mongoClient = new MongoClient(serverAddress, Collections.singletonList(credential));
        return mongoClient;
    }

}

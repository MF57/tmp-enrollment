package org.tmp.enrollment.plumbing.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by mf57 on 14.08.2016.
 */
@Configuration
@EnableMongoRepositories({"org.tmp"})
@Profile({"development", "integration"})
public class DevMongoConfig extends BaseConfig {

    @Override
    protected String getDatabaseName() {
        return "tmp-enrollment";
    }

    @Override
    public Mongo mongo() throws Exception {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoClient.getDatabase(getDatabaseName()).drop();
        return mongoClient;
    }
}

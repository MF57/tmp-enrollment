package org.tmp.enrollment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by mf57 on 14.08.2016.
 */
@Configuration
@EnableMongoRepositories({"org.tmp"})
public class MongoConfig {
}

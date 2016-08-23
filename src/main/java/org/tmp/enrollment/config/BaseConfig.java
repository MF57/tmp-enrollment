package org.tmp.enrollment.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

public abstract class BaseConfig extends AbstractMongoConfiguration {

    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(corsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        return registration;
    }

    @Bean(name = "corsFilter")
    public CORSFilter corsFilter() {
        return new CORSFilter();
    }
}

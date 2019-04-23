package com.ravisha.aws.elasticcache.rest;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main spring bootup class for triggering the whole application.
 */
@SpringBootApplication(scanBasePackages = {"com.ravisha.aws.elasticcache.rest.controller"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@ComponentScan
public class AWSElasticSearchApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AWSElasticSearchApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}
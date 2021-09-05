package com.example.springmultithread.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//TODO CONFIGURE AWS RDS CONNECTION BEANS

/**@EnableJPARepositories is unnecessary with spring-boot-starter-data-jpa dependency.
 *
 */
@Configuration
public class RdsConfig {

    @Bean
    public AmazonRDS amazonRds(){
        return AmazonRDSClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();
    }
}

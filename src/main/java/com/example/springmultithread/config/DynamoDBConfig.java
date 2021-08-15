package com.example.springmultithread.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableDynamoDBRepositories
        (basePackages = "com.example.springmultithread.repo")
public class DynamoDBConfig {

    /*@Value annotation injects values from our properties file.
    In this case we will be retrieving the aws endpoint, accessKey, and secretKey*/

    @Value("${amazon.dynamodb.endpoint}")
    private String dbEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String accessKey;

    @Value("${amazon.aws.secretkey}")
    private String secretKey;
    /*beans are objects created by spring that can be accessed as classes*/
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        System.out.println(dbEndpoint);
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        "http://localhost:8000", "us-west-2"))
                .build();
    }

    @Bean
    @Primary//must designate dynamodbmapper as primary because of conflict with awk sdk version?
    public DynamoDBMapper dynamoDBMapper(){
        return new DynamoDBMapper(amazonDynamoDB());
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(accessKey,secretKey);
    }
}

package com.example.springmultithread.config;

import com.example.springmultithread.webCrawl.Crawler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Creates and manages Crawler beans
 * @return Crawler
 */
@Configuration
@ComponentScan
public class CrawlerConfig {

    @Bean
    public Crawler crawler(){
        Crawler crawler = new Crawler();
        //setConcurrency assigns amount of CrawlerControllers operating within Crawler.
        crawler.setConcurrency(30);
        return crawler;
    }

}

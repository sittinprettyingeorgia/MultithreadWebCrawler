package com.example.springmultithread.config;

import com.example.springmultithread.webCrawl.Crawler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
public class CrawlerConfig {
    //declare beans

    @Bean
    public Crawler crawler(){
        Crawler crawler = new Crawler();
        //setConcurrency assigns amount of concurrent crawlercontrollers to run
        crawler.setConcurrency(10);
        return crawler;
    }

}

package com.example.springmultithread;

import com.example.springmultithread.config.MainConfig;
import com.example.springmultithread.webCrawl.Crawler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringMultiThreadApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        /*Crawler crawler = context.getBean("crawler", Crawler.class);
        crawler.search("https://www.walmart.com/", "the");*/

        SpringApplication.run(SpringMultiThreadApplication.class, args);
    }

}

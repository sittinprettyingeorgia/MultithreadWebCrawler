package com.example.springmultithread.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CrawlerConfig.class})
public class MainConfig {

}

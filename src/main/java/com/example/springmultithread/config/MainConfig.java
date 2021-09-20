package com.example.springmultithread.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * holds all config files for the application. Config files for specific classes are imported into
 * the main config class.
 */
@Configuration
@Import({CrawlerConfig.class, RdsConfig.class})
public class MainConfig {

}

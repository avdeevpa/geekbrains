package com.geekbrains.tasktracker.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:private.properties")
@ComponentScan("com.geekbrains.tasktracker")
public class AppConfig implements WebMvcConfigurer {
}

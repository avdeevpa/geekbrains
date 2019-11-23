package com.geekbrains.learning.tasktracker.resources;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.geekbrains.learning.tasktracker"})
public class SpringConfig {
    @Bean(value = "singleton")
    public SessionFactory sessionFactory() {
        return HybernateFactory.getInstance().getSessionFactory();
    }
}

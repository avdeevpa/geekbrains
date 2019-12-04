package com.geekbrains.tasktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;

@SpringBootApplication
		//(exclude = FlywayAutoConfiguration.class)
public class TaskTrackerApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskTrackerApplication.class, args);
	}

}

package com.geekbrains.learning.tasktracker;

import com.geekbrains.learning.tasktracker.resources.SpringConfig;
import com.geekbrains.learning.tasktracker.services.TaskService;
import com.geekbrains.learning.tasktracker.entities.Task;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        TaskService taskService = context.getBean("taskService", TaskService.class);
        taskService.addEdtTasks(new Task("Test", "Owner", "Test task"));
        taskService.printTasks();
        context.close();

    }

}

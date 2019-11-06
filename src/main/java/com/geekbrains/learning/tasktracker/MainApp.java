package com.geekbrains.learning.tasktracker;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;
import com.geekbrains.learning.tasktracker.storage.Task;
import com.geekbrains.learning.tasktracker.storage.TaskRepository;

public class MainApp {
    public static void main(String[] args) {
        TaskService tracker = new TaskService(new TaskRepository());
        tracker.addToTasks(new Task("Задача 1", "Тестер", "Переполнение списка"));
        tracker.addToTasks(new Task("Задача 2", "Тестер", "Переполнение списка"));
        tracker.addToTasks(new Task("Задача", "Тестер", "Переполнение списка"));
        tracker.printTasks();
        tracker.deleteTask("0");
        tracker.deleteTask("Задача");
        System.out.println("======");
        tracker.printTasks();
        System.out.println("-----");
        try {
            tracker.getTaskById(1L).assign("Некто");
        } catch (TTStorageException e) {
            e.printStackTrace();
        }
        System.out.println(tracker.getTaskById(1L));
    }
}

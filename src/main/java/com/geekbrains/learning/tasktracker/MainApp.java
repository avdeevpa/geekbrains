package com.geekbrains.learning.tasktracker;

import com.geekbrains.learning.tasktracker.storage.Task;

public class MainApp {
    public static void main(String[] args) {
        TaskService tracker = new TaskService();
        tracker.addToTasks(new Task("Задача 1", "Тестер", "Переполнение списка"));
        tracker.addToTasks(new Task("Задача 2", "Тестер", "Переполнение списка"));
        tracker.addToTasks(new Task("Задача", "Тестер", "Переполнение списка"));
        tracker.printTasks();
        tracker.deleteTask("0");
        tracker.deleteTask("Задача");
        System.out.println("======");
        tracker.printTasks();
        System.out.println("-----");
        tracker.getTaskById(1L).assign("Некто");
        System.out.println(tracker.getTaskById(1L));
    }
}

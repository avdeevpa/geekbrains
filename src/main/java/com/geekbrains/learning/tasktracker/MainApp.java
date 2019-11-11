package com.geekbrains.learning.tasktracker;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;
import com.geekbrains.learning.tasktracker.storage.Task;
import com.geekbrains.learning.tasktracker.storage.TaskListRepository;
import com.geekbrains.learning.tasktracker.storage.TaskRepository;

public class MainApp {
    public static void main(String[] args) {
        //TaskService tracker = new TaskService(new TaskRepository());
        TaskService tracker = new TaskService(new TaskListRepository());
        tracker.addEdtTasks(new Task("Задача 1", "Тестер", "Переполнение списка"));
        tracker.addEdtTasks(new Task("Задача 2", "Тестер", "Переполнение списка"));
        tracker.addEdtTasks(new Task("Задача", "Тестер", "Переполнение списка"));
        tracker.addEdtTasks(new Task("Задача", "Тестер", "Переполнение списка"));
        tracker.printTasks();
        tracker.deleteTask("2");
        tracker.deleteTask("Задача");
        System.out.println("======");
        tracker.printTasks();
        System.out.println("-----");
        try {
            Task editableTask = tracker.getTaskById(1L);
            editableTask.assign("Некто");
            tracker.addEdtTasks(editableTask);
        } catch (TTStorageException e) {
            System.out.println("Не удалось назначить задачу: " + e.getMessage());
        }
        tracker.printTasks();
    }
}

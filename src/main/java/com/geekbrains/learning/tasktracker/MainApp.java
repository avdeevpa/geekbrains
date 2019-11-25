package com.geekbrains.learning.tasktracker;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;
import com.geekbrains.learning.tasktracker.storage.*;

public class MainApp {
    static TaskService tracker = new TaskService(new TaskDBRepository(true));;

    public static void main(String[] args) throws TTStorageException {
        tracker.addEdtTasks(new Task("Задача 1", "Алиса", "Описание"));
        tracker.addEdtTasks(new Task("Задача", "Алиса", "Описание"));
        tracker.addEdtTasks(new Task("Задача", "Алиса", "Описание"));
        tracker.addEdtTasks(new Task("Задание", "Алиса", "Описание"));

        System.out.println(tracker.getTaskById(1L));
        tracker.deleteTask("1");
        tracker.deleteTask("Задача");
        tracker.printTasks();
    }

    static void prepareTaskTracker(){
        Task task;
        //TaskService tracker = new TaskService(new TaskRepository());
        task = new Task("Задача 1", "Алиса", "Описание");
        tracker.addEdtTasks(task);

        task = new Task("Задача 2", "Боб", "Описание");
        task.setStatus(Task.Status.ASSIGNED);
        tracker.addEdtTasks(task);

        task = new Task("Задача 3", "Чарли", "Описание");
        task.setStatus(Task.Status.ASSIGNED);
        tracker.addEdtTasks(task);

        task = new Task("Задача 4", "Дейв", "Описание");
        task.setStatus(Task.Status.INPROGRESS);
        tracker.addEdtTasks(task);

        task = new Task("Задача 5", "Кэрол", "Описание");
        task.setStatus(Task.Status.COMPLETED);
        tracker.addEdtTasks(task);

        task = new Task("Задача 6", "Чак", "Описание");
        task.setStatus(Task.Status.REJECTED);
        tracker.addEdtTasks(task);

    }
}

package com.geekbrains.learning.lesson03.service;

public interface TaskInterface {
    long addTask(Task task);
    Task getTask(Long id);
    Task[] getTasks();
    void deleteTask(Long id);
    void deleteTask(String caption);
}

package com.geekbrains.learning.tasktracker.storage;

public interface TaskInterface {
    long addTask(Task task);
    Task getTask(Long id);
    Task[] getTasks();
    void deleteTask(Long id);
    void deleteTask(String caption);
}

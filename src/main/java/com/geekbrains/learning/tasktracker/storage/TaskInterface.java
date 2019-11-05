package com.geekbrains.learning.tasktracker.storage;

import com.geekbrains.learning.tasktracker.exceptions.TTException;

public interface TaskInterface {
    Task addTask(Task task);
    Task getTask(Long id);
    Task[] getTasks();
    void deleteTask(Long id);
    void deleteTask(String caption);
}

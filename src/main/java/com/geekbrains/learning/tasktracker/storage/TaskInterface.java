package com.geekbrains.learning.tasktracker.storage;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;

public interface TaskInterface {
    Task addTask(Task task) throws TTStorageException;
    Task getTask(Long id);
    Task[] getTasks();
    void deleteTask(Long id);
    void deleteTask(String caption);
}

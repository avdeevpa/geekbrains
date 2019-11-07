package com.geekbrains.learning.tasktracker.storage;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;

public interface TaskInterface {
    Task addTask(Task task) throws TTStorageException;
    Task getTask(Long id) throws TTStorageException;
    Task[] getTasks();
    void deleteTask(Long id) throws TTStorageException;
    void deleteTask(String caption) throws TTStorageException;
}

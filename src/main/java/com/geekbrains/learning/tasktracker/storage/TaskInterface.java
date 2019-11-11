package com.geekbrains.learning.tasktracker.storage;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;

import java.util.List;

public interface TaskInterface {
    Task addEdtTask(Task task) throws TTStorageException;
    Task getTask(Long id) throws TTStorageException;
    List<Task> getTasks();
    void deleteTask(Long id) throws TTStorageException;
    void deleteTask(String caption) throws TTStorageException;
}

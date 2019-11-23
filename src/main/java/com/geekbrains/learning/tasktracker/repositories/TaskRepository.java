package com.geekbrains.learning.tasktracker.repositories;

import com.geekbrains.learning.tasktracker.entities.Task;

import java.util.List;

public interface TaskRepository {
    Task addEdtTask(Task task);
    Task getTask(Long id);
    List<Task> getTasks();
    void deleteTask(Long id);
    void deleteTask(String caption);
}

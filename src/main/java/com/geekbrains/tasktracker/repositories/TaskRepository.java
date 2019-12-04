package com.geekbrains.tasktracker.repositories;

import com.geekbrains.tasktracker.entities.Task;

import java.util.List;

public interface TaskRepository {
    Task addEdtTask(Task task);
    Task getTask(Long id);
    List<Task> getTasks();
    List<Task> getTasksFiltred(Task sample);
    void deleteTask(Long id);
}

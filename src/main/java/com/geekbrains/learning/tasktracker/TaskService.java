package com.geekbrains.learning.tasktracker;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;
import com.geekbrains.learning.tasktracker.storage.Task;
import com.geekbrains.learning.tasktracker.storage.TaskInterface;
import com.geekbrains.learning.tasktracker.storage.TaskRepository;

class TaskService {
    private TaskInterface storage;

    public TaskService(TaskInterface storage) {
        this.storage = storage;
    }

    void addToTasks(Task task) {
        try {
            Task result = storage.addTask(task);
            System.out.println(String.format("Задача с Id:%d добавлена.", result.getId()));
        } catch (TTStorageException e) {
            System.out.println(e.getMessage());
        }
    }

    void printTasks() {
        for (Task task : storage.getTasks()) {
            if (task != null && task.getId() != null) {
                System.out.println(task);
            }
        }
    }

    void deleteTask(String idents) {
        try {
            Long id = Long.parseLong(idents);
            storage.deleteTask(id);
        } catch (NumberFormatException e) {
            storage.deleteTask(idents);
        }
    }

    Task getTaskById(Long id) {
        return storage.getTask(id);
    }

}

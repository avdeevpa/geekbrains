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
            try {
                storage.deleteTask(id);
            } catch (TTStorageException e) {
                System.out.println(e.getMessage());
            }
        } catch (NumberFormatException e) {
            try {
                storage.deleteTask(idents);
            } catch (TTStorageException ex) {
                System.out.println(e.getMessage());
            }
        }
    }

    Task getTaskById(Long id){
        try {
            return storage.getTask(id);
        } catch (TTStorageException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}

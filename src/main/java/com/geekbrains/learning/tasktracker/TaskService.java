package com.geekbrains.learning.tasktracker;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;
import com.geekbrains.learning.tasktracker.storage.Task;
import com.geekbrains.learning.tasktracker.storage.TaskInterface;

class TaskService {
    private TaskInterface storage;

    public TaskService(TaskInterface storage) {
        this.storage = storage;
    }

    void addEdtTasks(Task task) {
        try {
            Task result = storage.addEdtTask(task);
            System.out.println(String.format("Задача с Id:%d добавлена/обновлена.", result.getId()));
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
                System.out.println(ex.getMessage());
            }
        }
    }

    Task getTaskById(Long id) throws TTStorageException {
        return storage.getTask(id);
    }

}

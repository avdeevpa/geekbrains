package com.geekbrains.learning.lesson03;

import com.geekbrains.learning.lesson03.service.Task;
import com.geekbrains.learning.lesson03.service.TaskInterface;
import com.geekbrains.learning.lesson03.service.TaskRepository;

class TaskService {
    private TaskInterface storage = new TaskRepository();

    void addToTasks(Task task) {
        long result = storage.addTask(task);
        System.out.println(
                result < 0 ? "Список задач заполнен" : String.format("Задача %d добавлена.", result)
        );
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
        } catch (Exception e) {
            storage.deleteTask(idents);
        }
    }

    Task getTaskById(Long id) {
        return storage.getTask(id);
    }

}

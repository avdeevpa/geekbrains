package com.geekbrains.learning.tasktracker.services;

import com.geekbrains.learning.tasktracker.entities.Task;
import com.geekbrains.learning.tasktracker.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service(value = "taskService")
public class TaskService {
    private TaskRepository storage;

    @Autowired
    public void setStorage(TaskRepository storage) {
        this.storage = storage;
    }

    public TaskService() {

    }

    public List<Task> getTasks() {
        return storage.getTasks();
    }

    public void addEdtTasks(Task task) {
        Task result = storage.addEdtTask(task);
    }

    public void printTasks() {
        for (Task task : storage.getTasks()) {
            if (task != null && task.getId() != null) {
                System.out.println(task);
            }
        }
    }

    public void deleteTask(String idents) {
        try {
            Long id = Long.parseLong(idents);
            storage.deleteTask(id);
        } catch (NumberFormatException e) {
            storage.deleteTask(idents);
        }
    }

    public Task getTaskById(Long id){
        return storage.getTask(id);
    }

    public List<Task> getTaskByStatus(Task.Status status) {
        return storage.getTasks().stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    public boolean isTaskExists(Long id) {
        return storage.getTasks().stream()
                .anyMatch(task -> task.getId().equals(id));
    }

    public List<Task> getSortedTaskList() {
        return storage.getTasks().stream()
                .sorted((o1, o2) -> o1.getStatus().getSortOrder() - o2.getStatus().getSortOrder())
                .collect(Collectors.toList());
    }

    public Map<Task.Status, Long> countOfAllStatus() {
        return storage.getTasks().stream()
                .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
    }

    public long countOfStatus(Task.Status status) {
        return countOfAllStatus().get(status);
    }

}

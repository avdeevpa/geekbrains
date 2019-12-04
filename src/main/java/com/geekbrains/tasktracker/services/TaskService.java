package com.geekbrains.tasktracker.services;

import com.geekbrains.tasktracker.entities.Task;
import com.geekbrains.tasktracker.repositories.TaskRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service(value = "taskService")
@NoArgsConstructor
public class TaskService {
    private TaskRepository storage;

    @Autowired
    public void setStorage(TaskRepository storage) {
        this.storage = storage;
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

    public void deleteTask(Long id) {
        storage.deleteTask(id);
    }

    public Task getTaskById(Long id){
        return storage.getTask(id);
    }

    public List<Task> getTaskBySample(Task sample) {
        return storage.getTasksFiltred(sample).stream()
                .sorted((o1, o2) -> (int)(o1.getId() - o2.getId()))
                .collect(Collectors.toList());
    }

    public boolean isTaskExists(Long id) {
        return storage.getTasks().stream()
                .anyMatch(task -> task.getId().equals(id));
    }

    public List<Task> getSortedTaskList() {
        return storage.getTasks().stream()
                .sorted((o1, o2) -> (int)(o1.getId() - o2.getId()))
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

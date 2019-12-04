package com.geekbrains.tasktracker.services;

import com.geekbrains.tasktracker.entities.Task;
import com.geekbrains.tasktracker.repositories.TaskRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
        return storage.findAll().stream()
                .sorted((o1, o2) -> (int)(o1.getId() - o2.getId()))
                .collect(Collectors.toList());
    }

    public List<Task> getTasks(Specification<Task> spec) {
        return storage.findAll(spec).stream()
                .sorted((o1, o2) -> (int)(o1.getId() - o2.getId()))
                .collect(Collectors.toList());
    }

    // todo: return task
    public void addEdtTasks(Task task) {
        storage.save(task);
    }

    public void deleteTask(Long id) {
        storage.deleteById(id);
    }

    public Task getTaskById(Long id){
        return storage.findById(id).get();
    }

    public boolean isTaskExists(Long id) {
        return storage.existsById(id);
    }

    public Map<Task.Status, Long> countOfAllStatus() {
        return storage.findAll().stream()
                .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
    }

    public long countOfStatus(Task.Status status) {
        return countOfAllStatus().get(status);
    }

}

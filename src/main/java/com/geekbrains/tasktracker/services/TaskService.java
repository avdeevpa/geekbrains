package com.geekbrains.tasktracker.services;

import com.geekbrains.tasktracker.entities.Task;
import com.geekbrains.tasktracker.repositories.TaskRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Task> getTasks(Specification<Task> spec, Pageable pagable) {
        return storage.findAll(spec, pagable);
    }

    public List<Task> getTasks(Specification<Task> spec) {
        return storage.findAll(spec, Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<Task> getTasks() {
        return getTasks(null);
    }

    public Task addEdtTasks(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Task.Status.CREATED);
        }
        return storage.save(task);
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

    //    public Page<Product> getAllProducts(Specification<Product> spec, Pageable pageable) {
    //        return productRepository.findAll(spec, pageable);
    //    }
}

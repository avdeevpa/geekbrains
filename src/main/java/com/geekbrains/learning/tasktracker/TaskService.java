package com.geekbrains.learning.tasktracker;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;
import com.geekbrains.learning.tasktracker.storage.FileSimpleService;
import com.geekbrains.learning.tasktracker.storage.Task;
import com.geekbrains.learning.tasktracker.storage.TaskInterface;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskService {
    private TaskInterface storage;

    public TaskService(TaskInterface storage) {
        this.storage = storage;
    }

    List<Task> getTasks() {
        return storage.getTasks();
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

    List<Task> getTaskByStatus(Task.Status status){
        return storage.getTasks().stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    boolean isTaskExists(Long id){
        return storage.getTasks().stream()
                .anyMatch(task -> task.getId().equals(id));
    }

    List<Task> getSortedTaskList() {
        return storage.getTasks().stream()
                .sorted((o1, o2) -> o1.getStatus().getSortOrder() - o2.getStatus().getSortOrder())
                .collect(Collectors.toList());
    }

    Map<Task.Status, Long> countOfAllStatus() {
        return storage.getTasks().stream()
                .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
    }

    long countOfStatus(Task.Status status) {
        return countOfAllStatus().get(status);
    }

    static String listBeautifier(String name, List<Task> list) {
        return
                String.format("----- \"%s\" -----\n", name) +
                list.stream()
                    .map(Task::toString)
                    .collect(Collectors.joining("\n")) +
                "\n";
    }

    void saveRepository(String filename) {
        FileSimpleService.trackerSave(filename, storage);
    }

    void restoreRepository(String filename) {
        storage = FileSimpleService.trackerRestore(filename);
    }

}

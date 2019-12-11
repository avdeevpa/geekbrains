package com.geekbrains.server.controllers;

import com.geekbrains.gwt.common.dtos.TaskDTO;
import com.geekbrains.server.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class TaskController {
    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<TaskDTO> getAllTasks(@RequestParam Map<String, String> params) {
        return taskService.getTasks(params).stream()
                .map(task -> task.toDto())
                .collect(Collectors.toList());
    }

    @DeleteMapping("/tasks")
    public void deleteTask(@PathParam("id") Long id) {
        taskService.deleteTask(id);
    }

}



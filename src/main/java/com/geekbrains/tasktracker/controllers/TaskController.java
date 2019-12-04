package com.geekbrains.tasktracker.controllers;

import com.geekbrains.tasktracker.entities.Task;
import com.geekbrains.tasktracker.repositories.specifications.TaskSpecifications;
import com.geekbrains.tasktracker.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path = "/")
    public String showTasks(
            Model model,
            @RequestParam Map<String, String> params
    ) {
        Specification<Task> spec = Specification.where(null);
        if (params.get("status") != null && params.get("status").length() != 0) {
            spec = spec.and(TaskSpecifications.statusEq(Task.Status.valueOf(params.get("status"))));
        }
        if (params.get("assigned")  != null && params.get("assigned").length() != 0) {
            spec = spec.and(TaskSpecifications.assignedContains(params.get("assigned")));
        }
        if (params.get("id")  != null && params.get("id").length() != 0) {
            spec = spec.and(TaskSpecifications.idEq(Long.getLong(params.get("id"))));
        }
        if (params.get("owner") != null && params.get("owner").length() != 0) {
            spec = spec.and(TaskSpecifications.ownerContains(params.get("owner")));
        }
        if (params.get("description") != null && params.get("description").length() != 0) {
            spec = spec.and(TaskSpecifications.descriptionContains(params.get("owner")));
        }

        model.addAllAttributes(params);
        model.addAttribute("tasks", taskService.getTasks(spec));
        return "tasks";
    }

    @GetMapping(path = "/edit")
    public String taskEditForm(Model model, @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            model.addAttribute("task", taskService.getTaskById(id));
        } else {
            model.addAttribute("task", new Task());
        }
        return "tasks_form";
    }

    @PostMapping(path = "/edit")
    public String taskEditFormProc(@ModelAttribute("task") Task task, @RequestParam(value = "delete_action", required = false) String deleteAction) {
        if (deleteAction != null && task.getId() != null) {
            taskService.deleteTask(task.getId());
        } else {
            taskService.addEdtTasks(task);
        }
        return "redirect:/tasks/";
    }
}


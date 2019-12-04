package com.geekbrains.tasktracker.controllers;

import com.geekbrains.tasktracker.entities.Task;
import com.geekbrains.tasktracker.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "caption", required = false) String caption,
            @RequestParam(value = "status", required = false) Task.Status status,
            @RequestParam(value = "assigned", required = false) String assigned,
            @RequestParam(value = "owner", required = false) String owner,
            @RequestParam(value = "description", required = false) String description
    ) {
        if (status != null | assigned != null | owner != null | id != null | caption != null | description != null) {
            Task sample = new Task();
            sample.setId(id);
            sample.setCaption(caption);
            sample.setStatus(status);
            sample.setAssigned(assigned);
            sample.setOwner(owner);
            sample.setDescription(description);

            model.addAttribute("id", id);
            model.addAttribute("caption", caption);
            model.addAttribute("status", status);
            model.addAttribute("assigned", assigned);
            model.addAttribute("owner", owner);
            model.addAttribute("description", description);

            model.addAttribute("tasks", taskService.getTaskBySample(sample));
        } else {
            model.addAttribute("tasks", taskService.getSortedTaskList());
        }
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


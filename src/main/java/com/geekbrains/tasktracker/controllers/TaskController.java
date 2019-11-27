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

    @GetMapping("/")
    public String showAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getSortedTaskList());
        return "tasks";
    }

    @RequestMapping(path = "/bystatus/{status}", method = RequestMethod.GET)
    public String showTaskByStatus(Model model, @PathVariable Task.Status status) {
        model.addAttribute("tasks", taskService.getTaskByStatus(status));
        return "tasks";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String taskAddForm(@ModelAttribute("task") Task task) {
        return "tasks_form";
    }

    @RequestMapping(path = "/task_form_processing", method = RequestMethod.POST)
    public String taskAddFormProc(@ModelAttribute("task") Task task) {
        taskService.addEdtTasks(task);
        return "redirect:/tasks/";
    }

}

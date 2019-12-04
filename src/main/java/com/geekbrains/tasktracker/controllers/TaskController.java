package com.geekbrains.tasktracker.controllers;

import com.geekbrains.tasktracker.entities.Task;
import com.geekbrains.tasktracker.entities.validations.TaskAddEdtGroup;
import com.geekbrains.tasktracker.repositories.specifications.TaskSpecifications;
import com.geekbrains.tasktracker.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public String showTasks(Model model, @RequestParam Map<String, String> params) {
        int page = 1;
        int tasksPerPage = 3;
        if (params.get("page") != null && params.get("page").length() != 0) {
            page = Integer.parseInt(params.get("page"));
        }

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

        Page<Task> taskPage = taskService.getTasks(spec, PageRequest.of(page - 1,  tasksPerPage, Sort.Direction.ASC, "id"));
        model.addAttribute("taskPage", taskPage);

        model.addAllAttributes(params);
        //model.addAttribute("tasks", taskService.getTasks(spec));
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
    public String taskEditFormProc(
            @ModelAttribute("task") @Validated(value = {TaskAddEdtGroup.class}) Task task,
            BindingResult bindingResult,
            @RequestParam(value = "delete_action", required = false) String deleteAction
    ) {
        if (deleteAction != null && task.getId() != null) {
            taskService.deleteTask(task.getId());
        } else {
            if (bindingResult.hasErrors()) {
                return "tasks_form";
            }
            taskService.addEdtTasks(task);
        }
        return "redirect:/tasks/";
    }
}


package com.geekbrains.tasktracker.controllers;

import com.geekbrains.tasktracker.entities.Role;
import com.geekbrains.tasktracker.entities.User;
import com.geekbrains.tasktracker.services.RoleService;
import com.geekbrains.tasktracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users/roles")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(path = "/")
    public String showRoles(Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return "roles";
    }

    @GetMapping(path = "/edit")
    public String roleEditForm(Model model, @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            model.addAttribute("role", roleService.getRoleById(id));
        } else {
            model.addAttribute("role", new Role());
        }
        return "roles_form";
    }

    @PostMapping(path = "/edit")
    public String roleEditFormProc(
            @ModelAttribute("role") Role role,
            @RequestParam(value = "delete_action", required = false) String deleteAction
    ) {
        if (deleteAction != null && role.getId() != null) {
            roleService.deleteRole(role.getId());
        } else {
            roleService.addEdtRole(role);
        }
        return "redirect:/users/roles/";
    }

}

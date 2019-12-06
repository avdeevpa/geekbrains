package com.geekbrains.tasktracker.controllers;

import com.geekbrains.tasktracker.entities.Role;
import com.geekbrains.tasktracker.entities.Task;
import com.geekbrains.tasktracker.entities.User;
import com.geekbrains.tasktracker.entities.validations.TaskAddEdtGroup;
import com.geekbrains.tasktracker.services.RoleService;
import com.geekbrains.tasktracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(path = "/")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping(path = "/edit")
    public String userEditForm(Model model, @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            model.addAttribute("user", userService.getUserById(id));
        } else {
            model.addAttribute("user", new User());
        }
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        return "users_form";
    }

    @PostMapping(path = "/edit")
    public String userEditFormProc(
            @ModelAttribute("user")  User user,
            @RequestParam Map<String, String> params,
            @RequestParam(value = "delete_action", required = false) String deleteAction
    ) {
        if (deleteAction != null && user.getId() != null) {
            userService.deleteUser(user.getId());
        } else {
            System.out.println(params.get("role"));
            System.out.println(user.getRoles());
            //user.setRoles(roleService.getRoleById(Long.parseLong(params.get("roles"))));
            userService.addEdtUser(user);
        }
        return "redirect:/users/";
    }

}

package com.geekbrains.server.controllers;

import com.geekbrains.gwt.common.dtos.UserDTO;
import com.geekbrains.server.services.RoleService;
import com.geekbrains.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
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

    @GetMapping(path = "/users")
    public List<UserDTO> showUsers() {
        return userService.getUsers().stream()
                .map(user -> user.toDto())
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/users/executors")
    public List<UserDTO> showExecutors() {
        return userService.getExecutors().stream()
                .map(user -> user.toDto())
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/users/initiators")
    public List<UserDTO> showInitiators() {
        return userService.getInitiators().stream()
                .map(user -> user.toDto())
                .collect(Collectors.toList());
    }

}

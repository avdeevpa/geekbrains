package com.geekbrains.server.controllers;

import com.geekbrains.gwt.common.dtos.RoleDTO;
import com.geekbrains.server.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class RoleController {
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(path = "/roles")
    public List<RoleDTO> showRoles() {
        return roleService.getRoles().stream()
                .map(role -> role.toDto())
                .collect(Collectors.toList());
    }

}

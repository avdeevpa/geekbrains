package com.geekbrains.tasktracker.repositories;

import com.geekbrains.tasktracker.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

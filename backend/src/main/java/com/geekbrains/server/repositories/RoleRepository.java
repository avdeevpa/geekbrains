package com.geekbrains.server.repositories;

import com.geekbrains.gwt.common.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

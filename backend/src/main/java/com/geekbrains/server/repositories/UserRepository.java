package com.geekbrains.server.repositories;

import com.geekbrains.gwt.common.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, CrudRepository<User, Long> {
    User findOneByUsername(String username);
}

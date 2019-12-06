package com.geekbrains.tasktracker.repositories;

import com.geekbrains.tasktracker.entities.Task;
import com.geekbrains.tasktracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}

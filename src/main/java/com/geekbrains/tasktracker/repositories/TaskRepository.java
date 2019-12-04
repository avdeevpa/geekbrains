package com.geekbrains.tasktracker.repositories;

import com.geekbrains.tasktracker.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task>, PagingAndSortingRepository<Task, Long> {
}

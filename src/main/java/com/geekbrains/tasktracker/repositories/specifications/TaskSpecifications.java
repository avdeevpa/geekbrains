package com.geekbrains.tasktracker.repositories.specifications;

import com.geekbrains.tasktracker.entities.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {
    public static Specification<Task> titleContains(String word) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), "%" + word.toUpperCase() + "%");
    }

    public static Specification<Task> statusEq(Task.Status status) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Task> assignedContains(String word) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.upper(root.get("assigned")), "%" + word.toUpperCase() + "%");
    }

    public static Specification<Task> idEq(Long id) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Task> ownerContains(String word) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.upper(root.get("owner")), "%" + word.toUpperCase() + "%");
    }

    public static Specification<Task> descriptionContains(String word) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.upper(root.get("description")), "%" + word.toUpperCase() + "%");
    }

}

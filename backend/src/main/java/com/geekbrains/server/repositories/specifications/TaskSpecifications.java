package com.geekbrains.server.repositories.specifications;

import com.geekbrains.gwt.common.entities.Task;
import com.geekbrains.gwt.common.entities.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

public class TaskSpecifications {
    public static Specification<Task> captionContains(String word) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.upper(root.get("caption")), "%" + word.toUpperCase() + "%");
    }

    public static Specification<Task> statusEq(Task.Status status) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Task> assignedContains(String word) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> {
            Join<Task, User> owner = root.join("assigned", JoinType.LEFT);
            return criteriaBuilder.like(criteriaBuilder.upper(owner.get("username")), "%" + word.toUpperCase() +"%");
        };
    }

    public static Specification<Task> idEq(Long id) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Task> ownerContains(String word) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> {
            Join<Task, User> owner = root.join("owner", JoinType.LEFT);
            return criteriaBuilder.like(criteriaBuilder.upper(owner.get("username")), "%" + word.toUpperCase() +"%");
        };
    }

    public static Specification<Task> descriptionContains(String word) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.upper(root.get("description")), "%" + word.toUpperCase() + "%");
    }

}

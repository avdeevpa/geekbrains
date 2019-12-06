package com.geekbrains.tasktracker.repositories.specifications;

import com.geekbrains.tasktracker.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> isInitiator() {
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.isTrue(root.get("canInitiate"));
    }

    public static Specification<User> isExecutor() {
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.isTrue(root.get("canExecute"));
    }
}

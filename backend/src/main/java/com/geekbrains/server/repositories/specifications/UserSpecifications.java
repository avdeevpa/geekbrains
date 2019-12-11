package com.geekbrains.server.repositories.specifications;

import com.geekbrains.gwt.common.entities.User;
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

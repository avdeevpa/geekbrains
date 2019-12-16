package com.geekbrains.server.services;

import com.geekbrains.gwt.common.entities.User;
import com.geekbrains.server.repositories.UserRepository;
import com.geekbrains.server.repositories.specifications.UserSpecifications;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "userService")
@NoArgsConstructor
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User addEdtUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<User> getInitiators() {
        return userRepository.findAll(UserSpecifications.isInitiator(), Sort.by(Sort.Direction.ASC, "username"));
    }

    public List<User> getExecutors() {
        return userRepository.findAll(UserSpecifications.isExecutor(), Sort.by(Sort.Direction.ASC, "username"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

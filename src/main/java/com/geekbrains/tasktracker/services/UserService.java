package com.geekbrains.tasktracker.services;

import com.geekbrains.tasktracker.entities.User;
import com.geekbrains.tasktracker.repositories.UserRepository;
import com.geekbrains.tasktracker.repositories.specifications.UserSpecifications;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userRepository.findAll(UserSpecifications.isInitiator(), Sort.by(Sort.Direction.ASC, "username"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

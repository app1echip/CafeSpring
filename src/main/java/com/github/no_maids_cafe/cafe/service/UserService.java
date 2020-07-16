package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.User;
import com.github.no_maids_cafe.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    public String update(User user) {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }

    public String delete(User user) {
        try {
            userRepository.delete(user);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }

    public boolean authenticate(String username, String password) {
        User user = findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean add(User user) {
        if (user.getId() != null || userRepository.findByUsername(user.getUsername()) != null)
            return false;
        user.setId(UUID.randomUUID().toString());
        return update(user) == "success";
    }

    public UserDetails getDetails(User user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
}

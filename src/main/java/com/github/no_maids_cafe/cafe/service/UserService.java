package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.User;
import com.github.no_maids_cafe.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User get(String username, String password) {
        return userRepository.getByUsernameAndPassword(username, password);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}

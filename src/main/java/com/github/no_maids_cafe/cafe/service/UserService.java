package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.User;
import com.github.no_maids_cafe.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    public String update(User user) {
        if (user.getId() == null) {
            user.setId(new User().getId());
        }
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
}

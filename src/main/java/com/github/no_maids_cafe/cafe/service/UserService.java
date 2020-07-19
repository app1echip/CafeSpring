package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.User;
import com.github.no_maids_cafe.cafe.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserRoleService roles;

    public Iterable<User> list() {
        return repository.findAll();
    }

    public void update(User user) {
        repository.save(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public boolean authenticate(String username, String password) {
        User user = findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public String getIdByUsername(String username) {
        User user = findByUsername(username);
        if (user == null)
            return null;
        return user.getId();
    }

    public boolean add(User user) {
        user.setId(null);
        String username = user.getUsername();
        String password = user.getPassword();
        if (repository.findByUsername(username) != null || password == null)
            return false;
        update(user);
        return true;
    }

    public org.springframework.security.core.userdetails.User getDetail(User user) {
        String name = user.getUsername();
        String pass = user.getPassword();
        String role = roles.getUserRole(user.getId());
        var auth = Arrays.asList(new SimpleGrantedAuthority(role));
        return new org.springframework.security.core.userdetails.User(name, pass, auth);
    }
}

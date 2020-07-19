package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.UserRole;
import com.github.no_maids_cafe.cafe.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepository repository;

    public Iterable<UserRole> list() {
        return repository.findAll();
    }

    public void update(UserRole entity) {
        repository.save(entity);
    }

    public String getUserRole(String user) {
        return repository.findByUser(user).getRole();
    }
}
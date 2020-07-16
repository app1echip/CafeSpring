package com.github.no_maids_cafe.cafe.service;

import java.util.List;

import com.github.no_maids_cafe.cafe.entity.UserRole;
import com.github.no_maids_cafe.cafe.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    public String getRole(String user) {
        UserRole userRole = userRoleRepository.findByUser(user);
        if (userRole == null) {
            return null;
        }
        return userRole.getRole();
    }

    public List<UserRole> list() {
        return userRoleRepository.findAll();
    }

    public String update(UserRole userRole) {
        try {
            userRoleRepository.save(userRole);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }
}
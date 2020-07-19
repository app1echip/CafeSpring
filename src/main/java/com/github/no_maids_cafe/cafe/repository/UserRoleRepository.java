package com.github.no_maids_cafe.cafe.repository;

import com.github.no_maids_cafe.cafe.entity.UserRole;

import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    UserRole findByUser(String user);
}
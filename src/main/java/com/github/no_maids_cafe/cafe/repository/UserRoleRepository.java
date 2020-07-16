package com.github.no_maids_cafe.cafe.repository;

import com.github.no_maids_cafe.cafe.entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findByUser(String user);
}
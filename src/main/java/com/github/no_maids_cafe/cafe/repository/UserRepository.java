package com.github.no_maids_cafe.cafe.repository;

import com.github.no_maids_cafe.cafe.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}

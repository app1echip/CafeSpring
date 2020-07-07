package com.github.no_maids_cafe.cafe.repository;

import com.github.no_maids_cafe.cafe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User getByUsernameAndPassword(String username, String password);

    void deleteById(String id);
}

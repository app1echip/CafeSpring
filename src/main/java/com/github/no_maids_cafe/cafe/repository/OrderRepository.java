package com.github.no_maids_cafe.cafe.repository;

import java.util.List;

import com.github.no_maids_cafe.cafe.entity.Ordre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Ordre, Integer> {
    List<Ordre> findAllByUser(String user);
}

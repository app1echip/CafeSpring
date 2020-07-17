package com.github.no_maids_cafe.cafe.repository;

import java.util.List;

import com.github.no_maids_cafe.cafe.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUser(String user);
}

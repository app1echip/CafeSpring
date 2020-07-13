package com.github.no_maids_cafe.cafe.repository;

import com.github.no_maids_cafe.cafe.entity.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDishRepository extends JpaRepository<OrderDish, Integer> {
}

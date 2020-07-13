package com.github.no_maids_cafe.cafe.repository;

import com.github.no_maids_cafe.cafe.entity.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Integer> {
}

package com.github.no_maids_cafe.cafe.repository;

import com.github.no_maids_cafe.cafe.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Integer> {
}

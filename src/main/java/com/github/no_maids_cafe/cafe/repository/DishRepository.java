package com.github.no_maids_cafe.cafe.repository;

import com.github.no_maids_cafe.cafe.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    List<Dish> findAllByCategory(String category);

    Dish findByName(String name);

    List<Dish> findAllByNameLike(String name);
}

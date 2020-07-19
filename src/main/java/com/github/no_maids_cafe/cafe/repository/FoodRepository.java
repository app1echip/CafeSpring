package com.github.no_maids_cafe.cafe.repository;

import com.github.no_maids_cafe.cafe.entity.Food;

import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Integer> {
    Food findById(String id);
}

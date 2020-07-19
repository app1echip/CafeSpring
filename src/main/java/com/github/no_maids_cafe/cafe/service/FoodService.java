package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.Food;
import com.github.no_maids_cafe.cafe.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Autowired
    private FoodRepository repository;

    public Iterable<Food> list() {
        return repository.findAll();
    }

    public void update(Food entity) {
        repository.save(entity);
    }

    public void delete(Food entity) {
        repository.delete(entity);
    }
}
package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.Food;
import com.github.no_maids_cafe.cafe.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public List<Food> list() {
        return foodRepository.findAll();
    }

    public String update(Food food) {
        if (food.getId() == null) {
            food.setId(new Food().getId());
        }
        try {
            foodRepository.save(food);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }

    public String delete(Food food) {
        try {
            foodRepository.delete(food);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }
}

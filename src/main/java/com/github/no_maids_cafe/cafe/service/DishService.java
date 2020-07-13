package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.Dish;
import com.github.no_maids_cafe.cafe.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    public List<Dish> list() {
        return dishRepository.findAll();
    }

    public String update(Dish dish) {
        if (dish.getId() == null) {
            dish.setId(new Dish().getId());
        }
        try {
            dishRepository.save(dish);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }

    public String delete(Dish dish) {
        try {
            dishRepository.delete(dish);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }
}

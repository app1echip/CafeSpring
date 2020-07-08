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

    public boolean update(Dish dish) {
        if (dish.getId() == null) {
            dish.setId(new Dish().getId());
        }
        try {
            dishRepository.save(dish);
        } catch (DataIntegrityViolationException exception) {
            return false;
        }
        return true;
    }

    public boolean delete(Dish dish) {
        try {
            dishRepository.delete(dish);
        } catch (DataIntegrityViolationException exception) {
            return false;
        }
        return true;
    }

    public List<Dish> listByCategory(String category) {
        return dishRepository.findAllByCategory(category);
    }

    public Dish findByName(String name) {
        return dishRepository.findByName(name);
    }

    public List<Dish> searchByName(String name) {
        return dishRepository.findAllByNameLike('%' + name + '%');
    }
}

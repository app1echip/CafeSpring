package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.Dish;
import com.github.no_maids_cafe.cafe.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private CategoryService categoryService;

    public List<Dish> list() {
        return dishRepository.findAll();
    }

    public void update(Dish dish) {
        dishRepository.save(dish);
    }

    public void deleteById(String id) {
        dishRepository.deleteById(id);
    }

    public List<Dish> listByCategory(String category) {
        return dishRepository.findAllByCategory(categoryService.get(category));
    }

    public List<Dish> findByName(String name) {
        return dishRepository.findAllByNameLike('%' + name + '%');
    }
}

package com.github.no_maids_cafe.cafe.controller;

import java.util.List;

import com.github.no_maids_cafe.cafe.entity.Food;
import com.github.no_maids_cafe.cafe.service.CategoryService;
import com.github.no_maids_cafe.cafe.service.FoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MenuController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/menu")
    public @ResponseBody List<Food> menu() {
        List<Food> foods = foodService.list();
        for (Food food : foods)
            food.setCategory(categoryService.getName(food.getCategory()));
        return foods;
    }
}
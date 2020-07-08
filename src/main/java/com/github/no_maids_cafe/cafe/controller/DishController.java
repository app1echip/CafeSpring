package com.github.no_maids_cafe.cafe.controller;


import com.github.no_maids_cafe.cafe.entity.Dish;
import com.github.no_maids_cafe.cafe.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @GetMapping("/list")
    public @ResponseBody
    Iterable<Dish> list() {
        return dishService.list();
    }


    @PostMapping("/update")
    public @ResponseBody
    boolean update(@RequestBody Dish dish) {
        return dishService.update(dish);
    }


    @PostMapping("/delete")
    public @ResponseBody
    boolean delete(@RequestBody Dish dish) {
        return dishService.delete(dish);
    }

    @PostMapping("/search")
    public @ResponseBody
    List<Dish> search(@RequestBody Dish dish) {
        return dishService.searchByName(dish.getName());
    }
}

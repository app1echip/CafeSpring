package com.github.no_maids_cafe.cafe.controller;

import com.github.no_maids_cafe.cafe.entity.Dish;
import com.github.no_maids_cafe.cafe.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @GetMapping("")
    public @ResponseBody Iterable<Dish> list() {
        return dishService.list();
    }

    @PostMapping("/update")
    public @ResponseBody String update(@RequestBody Dish dish) {
        return dishService.update(dish);
    }

    @PostMapping("/delete")
    public @ResponseBody String delete(@RequestBody Dish dish) {
        return dishService.delete(dish);
    }
}

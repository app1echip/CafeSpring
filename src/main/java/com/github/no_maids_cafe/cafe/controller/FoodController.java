package com.github.no_maids_cafe.cafe.controller;

import com.github.no_maids_cafe.cafe.entity.Food;
import com.github.no_maids_cafe.cafe.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @GetMapping("")
    public @ResponseBody Iterable<Food> list() {
        return foodService.list();
    }
    // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public @ResponseBody String update(@RequestBody Food food) {
        return foodService.update(food);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete")
    public @ResponseBody String delete(@RequestBody Food food) {
        return foodService.delete(food);
    }
}

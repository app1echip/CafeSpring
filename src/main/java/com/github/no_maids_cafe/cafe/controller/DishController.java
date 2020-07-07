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

    @GetMapping("/list")
    public @ResponseBody
    Iterable<Dish> list() {
        return dishService.list();
    }

    @PostMapping("/update")
    public @ResponseBody
    String update(@RequestParam String name,
                  @RequestParam String category,
                  @RequestParam Double price,
                  @RequestParam Integer stock,
                  @RequestParam Integer sales,
                  @RequestParam String description) {
        Dish dish = dishService.findByName(name);
        if (dish == null) dish = new Dish();
        dish.setName(name);
        dish.setCategory(category);
        dish.setPrice(price);
        dish.setStock(stock);
        dish.setSales(sales);
        dish.setDescription(description);
        dishService.update(dish);
        return "Done";
    }

    @PostMapping("/delete")
    public @ResponseBody
    String delete(@RequestParam String id) {
        dishService.deleteById(id);
        return "Done";
    }

    @PostMapping("/search")
    public @ResponseBody
    Iterable<Dish> search(@RequestParam String keyword) {
        return dishService.searchByName(keyword);
    }
}

package com.github.no_maids_cafe.cafe.controller.raw;

import com.github.no_maids_cafe.cafe.entity.Food;
import com.github.no_maids_cafe.cafe.service.FoodService;
import com.github.no_maids_cafe.cafe.util.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FoodController {
    @Autowired
    private FoodService service;

    @GetMapping({ "/admin/food", "/pub/menu" })
    public @ResponseBody Iterable<Food> list() {
        return service.list();
    }

    @PostMapping("/admin/food/update")
    public @ResponseBody ResponseEntity<?> update(@RequestBody Food food) {
        return Query.response(service::update, food);
    }

    @DeleteMapping("/admin/food/delete")
    public @ResponseBody ResponseEntity<?> delete(@RequestBody Food food) {
        return Query.response(service::delete, food);
    }
}
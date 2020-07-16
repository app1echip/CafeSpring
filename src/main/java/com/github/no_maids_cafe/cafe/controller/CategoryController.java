package com.github.no_maids_cafe.cafe.controller;

import com.github.no_maids_cafe.cafe.entity.Category;
import com.github.no_maids_cafe.cafe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public @ResponseBody Iterable<Category> list() {
        return categoryService.list();
    }

    @PostMapping("/update")
    public @ResponseBody String update(@RequestBody Category category) {
        return categoryService.update(category);
    }

    @PostMapping("/delete")
    public @ResponseBody String delete(@RequestBody Category category) {
        return categoryService.delete(category);
    }
}

package com.github.no_maids_cafe.cafe.controller;

import com.github.no_maids_cafe.cafe.entity.Category;
import com.github.no_maids_cafe.cafe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public @ResponseBody
    Iterable<Category> list() {
        return categoryService.list();
    }

    @PostMapping("/add")
    public @ResponseBody
    String addNewCategory(@RequestParam String name) {
        Category category = new Category();
        category.setName(name);
        categoryService.addNewCategory(category);
        return "Done";
    }
}

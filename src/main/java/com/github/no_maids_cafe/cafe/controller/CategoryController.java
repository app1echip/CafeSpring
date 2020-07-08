package com.github.no_maids_cafe.cafe.controller;

import com.github.no_maids_cafe.cafe.entity.Category;
import com.github.no_maids_cafe.cafe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public @ResponseBody
    List<Category> list() {
        return categoryService.list();
    }

    @PostMapping("/update")
    public @ResponseBody
    boolean update(@RequestBody Category category) {
        return categoryService.update(category);
    }

    @PostMapping("/delete")
    public @ResponseBody
    boolean delete(@RequestBody Category category) {
        return categoryService.delete(category);
    }
}

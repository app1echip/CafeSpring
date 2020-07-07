package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.Category;
import com.github.no_maids_cafe.cafe.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> list() {
        return categoryRepository.findAll();
    }

    public Category get(String id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void addNewCategory(Category category) {
        categoryRepository.save(category);
    }
}

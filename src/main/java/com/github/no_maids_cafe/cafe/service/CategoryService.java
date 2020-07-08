package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.Category;
import com.github.no_maids_cafe.cafe.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public boolean update(Category category) {
        if (category.getId() == null)
            category.setId(new Category().getId());
        try {
            categoryRepository.save(category);
        } catch (DataIntegrityViolationException exception) {
            return false;
        }
        return true;
    }

    public boolean delete(Category category) {
        try {
            categoryRepository.delete(category);
        } catch (DataIntegrityViolationException exception) {
            return false;
        }
        return true;
    }
}

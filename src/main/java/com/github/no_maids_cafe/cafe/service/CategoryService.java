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

    public String update(Category category) {
        if (category.getId() == null) {
            category.setId(new Category().getId());
        }
        try {
            categoryRepository.save(category);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }

    public String delete(Category category) {
        try {
            categoryRepository.delete(category);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }
}

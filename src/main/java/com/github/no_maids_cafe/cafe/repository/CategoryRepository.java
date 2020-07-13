package com.github.no_maids_cafe.cafe.repository;

import com.github.no_maids_cafe.cafe.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

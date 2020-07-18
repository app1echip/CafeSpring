package com.github.no_maids_cafe.cafe.repository;

import java.util.List;

import com.github.no_maids_cafe.cafe.entity.OrdreFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepository extends JpaRepository<OrdreFood, Integer> {
    List<OrdreFood> findAllByIdOrdre(String ordre);
}

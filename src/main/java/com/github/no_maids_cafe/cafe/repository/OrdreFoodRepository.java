package com.github.no_maids_cafe.cafe.repository;

import com.github.no_maids_cafe.cafe.entity.OrdreFood;

import org.springframework.data.repository.CrudRepository;

public interface OrdreFoodRepository extends CrudRepository<OrdreFood, Integer> {
    Iterable<OrdreFood> findAllByIdOrdre(String ordre);
}
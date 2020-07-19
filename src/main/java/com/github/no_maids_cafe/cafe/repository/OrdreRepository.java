package com.github.no_maids_cafe.cafe.repository;

import com.github.no_maids_cafe.cafe.entity.Ordre;

import org.springframework.data.repository.CrudRepository;

public interface OrdreRepository extends CrudRepository<Ordre, Integer> {
    Iterable<Ordre> findAllByUser(String user);
}

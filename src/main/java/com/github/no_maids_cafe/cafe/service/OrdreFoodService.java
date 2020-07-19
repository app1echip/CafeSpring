package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.OrdreFood;
import com.github.no_maids_cafe.cafe.repository.OrdreFoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdreFoodService {
    @Autowired
    private OrdreFoodRepository repository;

    public Iterable<OrdreFood> list() {
        return repository.findAll();
    }

    public void update(OrdreFood entity) {
        repository.save(entity);
    }

    public void delete(OrdreFood entity) {
        repository.delete(entity);
    }

    public Iterable<OrdreFood> getContent(String ordre) {
        return repository.findAllByIdOrdre(ordre);
    }
}

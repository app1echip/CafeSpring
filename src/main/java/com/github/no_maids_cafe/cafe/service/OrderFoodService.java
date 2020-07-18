package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.OrdreFood;
import com.github.no_maids_cafe.cafe.repository.OrderFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFoodService {
    @Autowired
    private OrderFoodRepository orderfoodRepository;

    public List<OrdreFood> list() {
        return orderfoodRepository.findAll();
    }

    public String update(OrdreFood orderfood) {
        try {
            orderfoodRepository.save(orderfood);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }

    public String delete(OrdreFood orderfood) {
        try {
            orderfoodRepository.delete(orderfood);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }

    public List<OrdreFood> getContent(String order) {
        return orderfoodRepository.findAllByIdOrdre(order);
    }
}

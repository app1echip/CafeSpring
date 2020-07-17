package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.OrderFood;
import com.github.no_maids_cafe.cafe.repository.OrderFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFoodService {
    @Autowired
    private OrderFoodRepository orderfoodRepository;

    public List<OrderFood> list() {
        return orderfoodRepository.findAll();
    }

    public String update(OrderFood orderfood) {
        try {
            orderfoodRepository.save(orderfood);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }

    public String delete(OrderFood orderfood) {
        try {
            orderfoodRepository.delete(orderfood);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }

    public List<OrderFood> getContent(String order) {
        return orderfoodRepository.findAllByIdOrder(order);
    }
}

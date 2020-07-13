package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.OrderDish;
import com.github.no_maids_cafe.cafe.repository.OrderDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDishService {
    @Autowired
    private OrderDishRepository orderdishRepository;

    public List<OrderDish> list() {
        return orderdishRepository.findAll();
    }

    public String update(OrderDish orderdish) {
        try {
            orderdishRepository.save(orderdish);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }

    public String delete(OrderDish orderdish) {
        try {
            orderdishRepository.delete(orderdish);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }
}

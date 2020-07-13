package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.Order;
import com.github.no_maids_cafe.cafe.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> list() {
        return orderRepository.findAll();
    }

    public String update(Order order) {
        if (order.getId() == null) {
            order.setId(new Order().getId());
        }
        try {
            orderRepository.save(order);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }

    public String delete(Order order) {
        try {
            orderRepository.delete(order);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }
}

package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.Order;
import com.github.no_maids_cafe.cafe.entity.OrderFood;
import com.github.no_maids_cafe.cafe.entity.OrderFoodId;
import com.github.no_maids_cafe.cafe.model.OrderContent;
import com.github.no_maids_cafe.cafe.model.OrderContent.Item;
import com.github.no_maids_cafe.cafe.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderFoodService orderFoodService;
    @Autowired
    private FoodService foodService;

    public List<Order> list() {
        return orderRepository.findAll();
    }

    public String update(Order order) {
        // if (order.getId() == null) {
        // order.setId(UUID.randomUUID().toString());
        // }
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

    public String generate(List<Item> items, String user) {
        Order order = new Order();
        order.setUser(user);
        order.setTime(new Date());
        this.update(order);
        String id = order.getId();
        for (Item item : items) {
            OrderFood orderFood = new OrderFood();
            OrderFoodId orderFoodId = new OrderFoodId();
            orderFoodId.setOrder(id);
            orderFoodId.setFood(item.getId());
            orderFood.setId(orderFoodId);
            orderFood.setQty(item.getQty());
            orderFoodService.update(orderFood);
        }
        return id;
    }

    public List<OrderContent> getContentByUser(String user) {
        return orderRepository.findAllByUser(user).stream()
                .map(order -> new OrderContent(order, orderFoodService.getContent(order.getId())))
                .collect(Collectors.toList());
    }
}

package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.Order;
import com.github.no_maids_cafe.cafe.entity.OrderFood;
import com.github.no_maids_cafe.cafe.entity.OrderFoodId;
import com.github.no_maids_cafe.cafe.model.OrderModel;
import com.github.no_maids_cafe.cafe.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        if (order.getId() == null) {
            order.setId(UUID.randomUUID().toString());
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

    public String generate(Map<String, Integer> content, String user) {
        Order order = new Order();
        String id = order.getId();
        for (Map.Entry<String, Integer> entry : content.entrySet()) {
            OrderFood orderFood = new OrderFood();
            OrderFoodId orderFoodId = new OrderFoodId();
            orderFoodId.setOrder(id);
            orderFoodId.setFood(entry.getKey());
            orderFood.setId(orderFoodId);
            orderFood.setQty(entry.getValue());
            orderFoodService.update(orderFood);
        }
        order.setUser(user);
        order.setTime(new Date());
        return id;
    }

    public List<OrderModel> getContentByUser(String user) {
        List<OrderModel> list = new ArrayList<>();
        for (Order orderEntity : orderRepository.findAllByUser(user)) {
            OrderModel orderModel = new OrderModel();
            orderModel.setId(orderEntity.getId());
            orderModel.setTime(orderEntity.getTime());
            Map<String, Integer> content = new HashMap<>();
            for (OrderFood orderFood : orderFoodService.getContent(orderEntity.getId()))
                content.put(foodService.getName(orderFood.getId().getFood()), orderFood.getQty());
            orderModel.setContent(content);
            list.add(orderModel);
        }
        return list;
    }
}

package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.Ordre;
import com.github.no_maids_cafe.cafe.entity.OrdreFood;
import com.github.no_maids_cafe.cafe.entity.OrdreFood.Id;
import com.github.no_maids_cafe.cafe.model.OrderContent;
import com.github.no_maids_cafe.cafe.model.OrderContent.Item;
import com.github.no_maids_cafe.cafe.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderFoodService orderFoodService;

    public List<Ordre> list() {
        return orderRepository.findAll();
    }

    public String update(Ordre order) {
        try {
            orderRepository.save(order);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }

    public String delete(Ordre order) {
        try {
            orderRepository.delete(order);
        } catch (DataIntegrityViolationException exception) {
            return "failure: " + exception.getMessage();
        }
        return "success";
    }

    public String generate(List<Item> items, String user) {
        Ordre order = new Ordre();
        order.setUser(user);
        order.setTime(new Date());
        this.update(order);
        String id = order.getId();
        for (Item item : items) {
            OrdreFood orderFood = new OrdreFood();
            Id ofId = new Id();
            ofId.setOrdre(id);
            ofId.setFood(item.getId());
            orderFood.setId(ofId);
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

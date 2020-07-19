package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.Ordre;
import com.github.no_maids_cafe.cafe.entity.OrdreFood;
import com.github.no_maids_cafe.cafe.model.OrderDetails;
import com.github.no_maids_cafe.cafe.repository.OrdreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrdreService {
    @Autowired
    private OrdreRepository orderRepository;
    @Autowired
    private OrdreFoodService orderFoodService;

    public Iterable<Ordre> list() {
        return orderRepository.findAll();
    }

    public void update(Ordre order) {
        orderRepository.save(order);
    }

    public void delete(Ordre order) {
        orderRepository.delete(order);
    }

    public String createFromItemsAndUserId(Iterable<OrderDetails.Item> items, String user) {
        Ordre ordre = new Ordre();
        ordre.setUser(user);
        ordre.setTime(new Date());
        this.update(ordre);
        String id = ordre.getId();
        for (OrderDetails.Item item : items) {
            OrdreFood orderfood = new OrdreFood();
            OrdreFood.Id orderfoodId = new OrdreFood.Id();
            orderfoodId.setOrdre(id);
            orderfoodId.setFood(item.getId());
            orderfood.setId(orderfoodId);
            orderfood.setQty(item.getQty());
            orderFoodService.update(orderfood);
        }
        return id;
    }

    public Iterable<OrderDetails> getDetailsByUserId(String user) {
        return StreamSupport.stream(orderRepository.findAllByUser(user).spliterator(), false)
                .map(order -> new OrderDetails(order, orderFoodService.getContent(order.getId())))
                .collect(Collectors.toList());
    }
}
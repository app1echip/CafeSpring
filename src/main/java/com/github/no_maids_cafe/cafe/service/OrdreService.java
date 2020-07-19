package com.github.no_maids_cafe.cafe.service;

import com.github.no_maids_cafe.cafe.entity.Ordre;
import com.github.no_maids_cafe.cafe.entity.OrdreFood;
import com.github.no_maids_cafe.cafe.entity.OrdreFood.Id;
import com.github.no_maids_cafe.cafe.model.Content;
import com.github.no_maids_cafe.cafe.model.Content.Item;
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

    public String createFromItems(Iterable<Item> items, String user) {
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

    public Iterable<Content> getContentByUser(String user) {
        return StreamSupport.stream(orderRepository.findAllByUser(user).spliterator(), false)
                .map(order -> new Content(order, orderFoodService.getContent(order.getId())))
                .collect(Collectors.toList());
    }
}

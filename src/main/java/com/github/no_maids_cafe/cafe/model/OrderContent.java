package com.github.no_maids_cafe.cafe.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.github.no_maids_cafe.cafe.entity.Ordre;
import com.github.no_maids_cafe.cafe.entity.OrdreFood;

public class OrderContent {
    private String id;
    private List<Item> content;
    private Date time;

    public OrderContent() {
    };

    public OrderContent(Ordre order, List<OrdreFood> orderFoods) {
        this.id = order.getId();
        this.time = order.getTime();
        this.content = orderFoods.stream().map(orderFood -> new Item(orderFood)).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public List<Item> getContent() {
        return content;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public static class Item {
        private String id;
        private Integer qty;

        public Item() {
        }

        public Item(OrdreFood orderFood) {
            this.id = orderFood.getId().getFood();
            this.qty = orderFood.getQty();
        }

        public String getId() {
            return id;
        }

        public Integer getQty() {
            return qty;
        }
    }
}
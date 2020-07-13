package com.github.no_maids_cafe.cafe.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_dish")
public class OrderDish {
    @EmbeddedId
    private OrderDishId id;

    private Integer qty;

    public OrderDishId getId() {
        return id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setId(OrderDishId id) {
        this.id = id;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}

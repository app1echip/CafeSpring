package com.github.no_maids_cafe.cafe.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_dish")
public class OrderDish {
    @EmbeddedId
    OrderDishKey id;
    @ManyToOne
    @MapsId("order")
    @JoinColumn(name = "order")
    Order order;

    @ManyToOne
    @MapsId("dish")
    @JoinColumn(name = "dish")
    Dish dish;


    private Integer qty;

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}

package com.github.no_maids_cafe.cafe.entity;

import javax.persistence.*;

@Entity
public class OrderFood {
    @EmbeddedId
    private OrderFoodId id;
    private Integer qty;

    public OrderFoodId getId() {
        return id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setId(OrderFoodId id) {
        this.id = id;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}

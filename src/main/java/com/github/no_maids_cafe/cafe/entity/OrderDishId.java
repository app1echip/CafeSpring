package com.github.no_maids_cafe.cafe.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderDishId implements Serializable {
    @Column(name = "`order`")
    private String order;
    @Column(name = "dish")
    private String dish;

    public String getOrder() {
        return order;
    }

    public String getDish() {
        return dish;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }
}

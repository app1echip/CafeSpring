package com.github.no_maids_cafe.cafe.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderFoodId implements Serializable {
    @Column(name = "`order`")
    private String order;
    private String food;

    public String getOrder() {
        return order;
    }

    public String getFood() {
        return food;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setFood(String food) {
        this.food = food;
    }
}

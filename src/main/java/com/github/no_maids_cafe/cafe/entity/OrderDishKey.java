package com.github.no_maids_cafe.cafe.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@SuppressWarnings("serial")
@Embeddable
public class OrderDishKey implements Serializable {
    @Column(name = "order")
    private String order;
    @Column(name = "dish")
    private String dish;
}

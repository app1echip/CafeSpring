package com.github.no_maids_cafe.cafe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Food {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private @Getter @Setter String id;
    private @Getter @Setter String name;
    private @Getter @Setter String cate;
    private @Getter @Setter Double price;
    private @Getter @Setter Integer sales;
    private @Getter @Setter String des;
    private @Getter @Setter String img;
}

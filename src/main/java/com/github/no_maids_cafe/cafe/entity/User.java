package com.github.no_maids_cafe.cafe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private @Getter @Setter String id;
    private @Getter @Setter String username;
    private @Getter @Setter String email;
    private @Getter @Setter String password;
    private @Getter @Setter String phone;
}

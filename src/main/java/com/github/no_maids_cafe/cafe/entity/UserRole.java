package com.github.no_maids_cafe.cafe.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class UserRole {
    @Id
    private @Getter @Setter String user;
    private @Getter @Setter String role;
}
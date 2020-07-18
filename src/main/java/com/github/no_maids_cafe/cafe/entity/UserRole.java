package com.github.no_maids_cafe.cafe.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class UserRole {
    @Id
    private String user;
    private String role;
}
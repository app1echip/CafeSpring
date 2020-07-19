package com.github.no_maids_cafe.cafe.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class OrdreFood {
    @EmbeddedId
    private Id id;
    private Integer qty;

    @Data
    @Embeddable
    public static class Id implements Serializable {
        private String ordre;
        private String food;
    }
}
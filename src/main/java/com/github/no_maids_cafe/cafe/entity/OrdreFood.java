package com.github.no_maids_cafe.cafe.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class OrdreFood {
    @EmbeddedId
    private @Getter @Setter Id id;
    private @Getter @Setter Integer qty;

    @Embeddable
    public static class Id implements Serializable {
        private @Getter @Setter String ordre;
        private @Getter @Setter String food;
    }
}

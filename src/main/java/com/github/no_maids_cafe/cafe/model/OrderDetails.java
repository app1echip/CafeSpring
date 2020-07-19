package com.github.no_maids_cafe.cafe.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.github.no_maids_cafe.cafe.entity.Ordre;
import com.github.no_maids_cafe.cafe.entity.OrdreFood;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrderDetails {
    private @Getter String id;
    private @Getter List<Item> content;
    private @Getter Date time;

    public OrderDetails(Ordre order, Iterable<OrdreFood> orderFoods) {
        this.id = order.getId();
        this.time = order.getTime();
        this.content = StreamSupport.stream(orderFoods.spliterator(), false).map(Item::new)
                .collect(Collectors.toList());
    }

    @NoArgsConstructor
    public static class Item {
        private @Getter String id;
        private @Getter Integer qty;

        public Item(OrdreFood orderFood) {
            this.id = orderFood.getId().getFood();
            this.qty = orderFood.getQty();
        }
    }
}
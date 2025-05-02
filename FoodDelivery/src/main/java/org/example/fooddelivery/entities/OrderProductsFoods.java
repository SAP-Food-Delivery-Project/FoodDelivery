package org.example.fooddelivery.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_products_foods")
@Entity
public class OrderProductsFoods extends BaseEntity {

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    private Food food;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Builder
    public OrderProductsFoods(int quantity,
                                  Order order,
                                  Food food) {
        this.quantity = quantity;
        this.order = order;
        this.food = food;
        this.isActive = true;
    }
}

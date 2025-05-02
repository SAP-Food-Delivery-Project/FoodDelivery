package org.example.fooddelivery.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_products_beverages")
@Entity
public class OrderProductsBeverages extends BaseEntity{

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    private Beverage beverage;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Builder
    public OrderProductsBeverages(int quantity,
                                  Order order,
                                  Beverage beverage) {
        this.quantity = quantity;
        this.order = order;
        this.beverage = beverage;
        this.isActive = true;
    }

}

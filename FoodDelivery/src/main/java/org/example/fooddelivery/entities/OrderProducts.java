package org.example.fooddelivery.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_products")
@Entity
public class OrderProducts extends BaseEntity{

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    @OneToMany
    private Set<Food> foods;

    @OneToMany
    private Set<Beverage> beverages;

    @Builder
    public OrderProducts(int quantity,
                         Order order,
                         Set<Food> foods,
                         Set<Beverage> beverages){
        this.quantity = quantity;
        this.order = order;
        this.foods = foods;
        this.beverages = beverages;
    }

}

package org.example.fooddelivery.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
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

}

package org.example.fooddelivery.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "beverages")
@Entity
public class Beverage extends BaseEntity {

    @Column(name = "beverages_name", nullable = false)
    private String name;

    @Column(name = "liters", nullable = false)
    private float liters;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    private FoodProducer foodProducer;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

}

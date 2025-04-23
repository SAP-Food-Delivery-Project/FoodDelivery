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
@Table(name = "foods")
@Entity
public class Food extends BaseEntity {

    @Column(name = "food_name", nullable = false)
    private String name;

    @Column(name = "weight", nullable = false)
    private float weight;

    @Column(name = "calories", nullable = false)
    private float calories;

    @Column(name = "fats", nullable = false)
    private float fats;

    @Column(name = "carbohydrates", nullable = false)
    private float carbohydrates;

    @Column(name = "protein", nullable = false)
    private float protein;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "products", nullable = false)
    private String products;

    @ManyToOne(fetch = FetchType.LAZY)
    private FoodProducer foodProducer;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}

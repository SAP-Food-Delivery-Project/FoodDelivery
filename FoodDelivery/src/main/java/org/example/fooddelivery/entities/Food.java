package org.example.fooddelivery.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private double weight;

    @Column(name = "calories", nullable = false)
    private double calories;

    @Column(name = "fats", nullable = false)
    private double fats;

    @Column(name = "carbohydrates", nullable = false)
    private double carbohydrates;

    @Column(name = "protein", nullable = false)
    private double protein;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "products", nullable = false)
    private String products;

    @ManyToOne(fetch = FetchType.LAZY)
    private FoodProducer foodProducer;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Builder
    public Food(String name,
                float weight,
                float calories,
                float fats,
                float carbohydrates,
                float protein,
                BigDecimal price,
                String products,
                FoodProducer foodProducer){
        this.name = name;
        this.weight = weight;
        this.calories = calories;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.price = price;
        this.products = products;
        this.foodProducer = foodProducer;
        this.isActive = true;
    }
}

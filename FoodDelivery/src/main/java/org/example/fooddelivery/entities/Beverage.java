package org.example.fooddelivery.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private double liters;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    private FoodProducer foodProducer;

    @Column(name = "beverage_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private BeverageType beverageType;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Builder
    public Beverage(String name,
                    float liters,
                    BigDecimal price,
                    FoodProducer foodProducer,
                    BeverageType beverageType){
        this.name = name;
        this.liters = liters;
        this.price = price;
        this.foodProducer = foodProducer;
        this.beverageType = beverageType;
        this.isActive = true;
    }

}

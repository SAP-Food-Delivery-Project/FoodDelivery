package org.example.fooddelivery.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "food_producers")
@Entity
public class FoodProducer extends BaseEntity {

    @Column(name = "producer_name", nullable = false)
    private String name;

    @Column(name = "telephone_number",unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "rate", nullable = false)
    private double rate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "FoodProducer")
    private Set<Food> foods;

    @OneToMany(mappedBy = "FoodProducer")
    private Set<Beverage> beverages;

    @OneToMany(mappedBy = "FoodProducer")
    private Set<Order> orders;

}

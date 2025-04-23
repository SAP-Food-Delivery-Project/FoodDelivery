package org.example.fooddelivery.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "food_producers")
@Entity
@Builder
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
    private float rate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "foodProducer")
    private Set<Food> foods;

    @OneToMany(mappedBy = "foodProducer")
    private Set<Beverage> beverages;

    @OneToMany(mappedBy = "foodProducer")
    private Set<Order> orders;

    @Builder
    public FoodProducer(String name,
                        String phoneNumber,
                        String address,
                        String city,
                        float rate){

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.rate = rate;
        this.isActive = true;
    }
}

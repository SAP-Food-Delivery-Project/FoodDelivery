package org.example.fooddelivery.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "deliveries")
@Entity
public class Delivery extends BaseEntity {

    @Column(name = "delivery_time")
    private int deliveryTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}

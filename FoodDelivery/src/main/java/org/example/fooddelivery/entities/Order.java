package org.example.fooddelivery.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "telephone_number",unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime order_date;

    @Column(name = "order_cancelled", nullable = false)
    private boolean order_cancelled;

    @ManyToOne(fetch = FetchType.LAZY)
    private FoodProducer foodProducer;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToOne(mappedBy = "Order")
    private Delivery delivery;
}

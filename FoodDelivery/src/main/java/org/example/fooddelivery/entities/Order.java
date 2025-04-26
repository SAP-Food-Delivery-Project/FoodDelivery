package org.example.fooddelivery.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
@Entity
public class Order extends BaseEntity{

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

//    @Column(name = "telephone_number",unique = true, nullable = false)
//    private String phoneNumber;

//    @Column(name = "price", nullable = false)
//    private BigDecimal price;

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

    @OneToOne(mappedBy = "order")
    private Delivery delivery;

    @Builder
    public Order(String address,
                 String city,
                 LocalDateTime order_date,
                 FoodProducer foodProducer,
                 User user,
                 Delivery delivery){
        this.address = address;
        this.city = city;
        this.order_date = order_date;
        this.foodProducer = foodProducer;
        this.user = user;
        this.delivery = delivery;
        this.order_cancelled = false;
        this.isActive = true;
    }
}

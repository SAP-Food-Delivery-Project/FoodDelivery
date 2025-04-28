package org.example.fooddelivery.entities.dtos.OrderDtos;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fooddelivery.entities.Delivery;
import org.example.fooddelivery.entities.FoodProducer;
import org.example.fooddelivery.entities.dtos.UserDtos.UserDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String address;

    private String city;

    private BigDecimal price;

    private LocalDateTime order_date;

    private boolean order_cancelled;

    private FoodProducer foodProducer;

    private UserDto user;

    private Delivery delivery;

}

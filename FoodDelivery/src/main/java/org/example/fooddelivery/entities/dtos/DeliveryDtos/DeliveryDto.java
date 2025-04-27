package org.example.fooddelivery.entities.dtos.DeliveryDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fooddelivery.entities.Supplier;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {

    private int deliveryTime;

    private Supplier supplier;

    private OrderDto order;

}

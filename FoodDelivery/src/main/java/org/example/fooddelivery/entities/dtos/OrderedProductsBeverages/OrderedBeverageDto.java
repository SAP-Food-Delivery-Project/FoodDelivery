package org.example.fooddelivery.entities.dtos.OrderedProductsBeverages;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedBeverageDto {

    private int quantity;

    private BeverageDto beverage;

    private OrderDto order;
}

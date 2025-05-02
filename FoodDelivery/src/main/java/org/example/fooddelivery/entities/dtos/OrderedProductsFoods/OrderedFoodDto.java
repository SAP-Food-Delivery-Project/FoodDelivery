package org.example.fooddelivery.entities.dtos.OrderedProductsFoods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDto;
import org.example.fooddelivery.entities.dtos.FoodDtos.FoodDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedFoodDto {

    private int quantity;

    private FoodDto food;

    private OrderDto order;

}

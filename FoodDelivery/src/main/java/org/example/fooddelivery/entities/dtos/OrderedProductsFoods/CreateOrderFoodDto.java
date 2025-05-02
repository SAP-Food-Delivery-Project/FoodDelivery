package org.example.fooddelivery.entities.dtos.OrderedProductsFoods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderFoodDto {

    private int quantity;

    private int foodId;

    private int orderId;

}

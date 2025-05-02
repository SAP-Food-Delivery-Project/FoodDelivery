package org.example.fooddelivery.entities.dtos.OrderedProductsBeverages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderBeverageDto {

    private int quantity;

    private int beverageId;

    private int orderId;
}

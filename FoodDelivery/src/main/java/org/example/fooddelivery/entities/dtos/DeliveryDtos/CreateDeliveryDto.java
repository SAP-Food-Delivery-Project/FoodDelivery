package org.example.fooddelivery.entities.dtos.DeliveryDtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDeliveryDto {

    @NotNull
    @Min(0)
    private int deliveryTime;

    @NotNull
    private int supplierId;

    @NotNull
    private int orderId;

}

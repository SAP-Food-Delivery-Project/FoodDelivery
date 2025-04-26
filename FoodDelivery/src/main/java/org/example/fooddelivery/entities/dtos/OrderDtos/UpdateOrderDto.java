package org.example.fooddelivery.entities.dtos.OrderDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderDto {

    @NotNull
    @Size(min = 2, max = 45, message = "Address must be between 2 and 45 symbols!")
    private String address;

    @NotNull
    @Size(min = 2, max = 45, message = "City must be between 2 and 45 symbols!")
    private String city;
}

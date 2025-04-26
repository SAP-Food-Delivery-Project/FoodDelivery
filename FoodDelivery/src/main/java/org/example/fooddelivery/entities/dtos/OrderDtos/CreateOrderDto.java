package org.example.fooddelivery.entities.dtos.OrderDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {

    @NotNull
    @Size(min = 2, max = 45, message = "Address must be between 2 and 45 symbols!")
    private String address;

    @NotNull
    @Size(min = 2, max = 45, message = "City must be between 2 and 45 symbols!")
    private String city;

    @NotNull
    private LocalDateTime orderDate;

    @NotNull
    private int foodProducerId;

    @NotNull
    private int userId;
}

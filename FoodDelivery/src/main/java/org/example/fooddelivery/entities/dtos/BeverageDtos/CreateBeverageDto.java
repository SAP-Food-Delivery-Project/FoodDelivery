package org.example.fooddelivery.entities.dtos.BeverageDtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBeverageDto {

    @NotNull
    @Size(min = 2, max = 25, message = "Beverage name should be between 2 and 25 symbols!")
    private String name;

    @NotNull
    @DecimalMin(value = "0.1", message = "Beverage liters cannot be less than 0.1 liters!")
    private float liters;

    @NotNull
    @DecimalMin(value = "0.1", message = "Beverage price cannot be less than 0.1 ")
    private BigDecimal price;
}

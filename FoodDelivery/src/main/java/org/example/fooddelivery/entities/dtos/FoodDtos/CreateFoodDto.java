package org.example.fooddelivery.entities.dtos.FoodDtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fooddelivery.entities.FoodType;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDto;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFoodDto {

    @NotNull
    @Size(min = 2, max = 30, message = "Food name should be between 2 and 30 symbols!")
    private String name;

    @NotNull
    @DecimalMin(value = "1.0", message = "Food weight cannot be less than 1.0")
    private float weight;

    @NotNull
    @DecimalMin(value = "0.0", message = "Food calories cannot be less than 0.0")
    private float calories;

    @NotNull
    @DecimalMin(value = "0.0", message = "Food fats cannot be less than 0.0")
    private float fats;

    @NotNull
    @DecimalMin(value = "0.0", message = "Food carbohydrates cannot be less than 0.0")
    private float carbohydrates;

    @NotNull
    @DecimalMin(value = "0.0", message = "Food protein cannot be less than 0.0")
    private float protein;

    @NotNull
    @DecimalMin(value = "0.0", message = "Food price cannot be less than 0.0")
    private BigDecimal price;

    @NotNull
    private FoodType foodType;

    @NotNull
    @Size(min = 2, message = "Food product cannot be less than one product")
    private String foodProducts;
}

package org.example.fooddelivery.entities.dtos.FoodDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fooddelivery.entities.FoodType;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodDto {

    private String name;

    private float weight;

    private float calories;

    private float fats;

    private float carbohydrates;

    private float protein;

    private BigDecimal price;

    private FoodType foodType;

    private String foodProducts;
}

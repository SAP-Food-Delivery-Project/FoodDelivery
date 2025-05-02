package org.example.fooddelivery.entities.dtos.BeverageDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fooddelivery.entities.BeverageType;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeverageDto {

    private String name;

    private float liters;

    private BigDecimal price;

    private BeverageType beverageType;

}

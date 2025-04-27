package org.example.fooddelivery.entities.dtos.FoodProductDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDto;
import org.example.fooddelivery.entities.dtos.FoodDtos.FoodDto;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodProducerDto {

    private String name;

    private String phoneNumber;

    private String address;

    private String city;

    private double rate;
}

package org.example.fooddelivery.entities.dtos.FoodProductDtos;

import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDto;
import org.example.fooddelivery.entities.dtos.FoodDtos.FoodDto;

import java.util.Set;

public class FoodProducerDto {

    private String name;

    private String telephoneNumber;

    private String address;

    private String city;

    private double rate;

    private Set<FoodDto> foods;

    private Set<BeverageDto> beverages;
}

package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.Food;
import org.example.fooddelivery.entities.FoodType;
import org.example.fooddelivery.entities.dtos.FoodDtos.CreateFoodDto;
import org.example.fooddelivery.entities.dtos.FoodDtos.FoodDto;
import org.example.fooddelivery.entities.dtos.FoodDtos.FoodDtoWithId;
import org.example.fooddelivery.entities.dtos.FoodDtos.UpdateFoodDto;

import java.util.List;

public interface FoodService {

    FoodDto getSingleFood(int id);

    Food getSingleFoodEntity(int id);

    FoodDto getSingleFood(String name);

    List<FoodDto> getAllFoodsFromFoodProducer(int foodProducerId);

    List<FoodDto> getAllFilteredFood(int id, FoodType foodType);

    FoodDtoWithId createFood(CreateFoodDto createFoodDto);

    FoodDto updateFood(UpdateFoodDto updateFoodDto, int id);

    void deactivateFood(int id);
}

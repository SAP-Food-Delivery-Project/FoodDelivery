package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.BeverageType;
import org.example.fooddelivery.entities.FoodType;
import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDto;
import org.example.fooddelivery.entities.dtos.FoodDtos.FoodDto;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDto;

import java.util.List;

public interface UserOperationService {

    List<FoodProducerDto> getAllFoodProducers();

    FoodProducerDto getSingleFoodProducer(int id);

    List<FoodDto> getAllFoodFromFoodProducer(int foodProducerId);

    List<BeverageDto> getAllBeverageFromFoodProducer(int foodProducerId);

    List<FoodDto> getAllFilteredFood(int foodProducer, String foodType);

    List<BeverageDto> getAllFilteredBeverages(int foodProducer, String beverageType);

    OrderDto getOrderInformation(int id);
}

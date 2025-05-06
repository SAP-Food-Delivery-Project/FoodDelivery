package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.BeverageType;
import org.example.fooddelivery.entities.Food;
import org.example.fooddelivery.entities.FoodType;
import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDto;
import org.example.fooddelivery.entities.dtos.FoodDtos.FoodDto;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDto;
import org.example.fooddelivery.repositories.BeverageRepository;
import org.example.fooddelivery.repositories.FoodProducerRepository;
import org.example.fooddelivery.repositories.FoodRepository;
import org.example.fooddelivery.services.contracts.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserOperationServiceImpl implements UserOperationService {

    private final FoodProducerService foodProducerService;
    private final FoodService foodService;
    private final BeverageService beverageService;
    private final OrderService orderService;

    @Override
    public List<FoodProducerDto> getAllFoodProducers() {
        return foodProducerService.getAllFoodProducers();
    }

    @Override
    public FoodProducerDto getSingleFoodProducer(int id) {
        return foodProducerService.getSingleFoodProducer(id);
    }

    @Override
    public List<FoodDto> getAllFoodFromFoodProducer(int foodProducerId) {
        return foodService.getAllFoodsFromFoodProducer(foodProducerId);
    }

    @Override
    public List<BeverageDto> getAllBeverageFromFoodProducer(int foodProducerId) {
        return beverageService.getAllBeverageFromFoodProducer(foodProducerId);
    }

    @Override
    public List<FoodDto> getAllFilteredFood(int foodProducer, String foodType) {

        FoodType foodTypeEnum = FoodType.valueOf(foodType);
        return foodService.getAllFilteredFood(foodProducer, foodTypeEnum);
    }

    @Override
    public List<BeverageDto> getAllFilteredBeverages(int foodProducer, String beverageType) {

        BeverageType beverageTypeEnum = BeverageType.valueOf(beverageType);
        return beverageService.getAllFilteredBeverage(foodProducer, beverageTypeEnum);
    }

    @Override
    public OrderDto getOrderInformation(int id) {
        return orderService.getOrderById(id);
    }
}

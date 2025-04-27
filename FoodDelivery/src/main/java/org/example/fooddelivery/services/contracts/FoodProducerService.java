package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.FoodProducer;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.CreateFoodProducerDto;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDto;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDtoWithId;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.UpdateFoodProducerDto;

import java.util.List;

public interface FoodProducerService {

    FoodProducerDto getSingleFoodProducer(int id);

    FoodProducer getSingleFoodProducerEntity(int id);

    FoodProducerDto getSingleFoodProducer(String name);

    FoodProducerDto getSingleFoodProducer(String city, String address);

    List<FoodProducerDto> getAllFoodProducers();

    FoodProducerDtoWithId createFoodProducer(CreateFoodProducerDto createFoodProducerDto);

    FoodProducerDto updateFoodProducer(UpdateFoodProducerDto updateFoodProducerDto, int id);

    void deactivateFoodProducer(int id);
}

package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.Beverage;
import org.example.fooddelivery.entities.BeverageType;
import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDto;
import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDtoWithId;
import org.example.fooddelivery.entities.dtos.BeverageDtos.CreateBeverageDto;
import org.example.fooddelivery.entities.dtos.BeverageDtos.UpdateBeverageDto;

import java.util.List;

public interface BeverageService {

    BeverageDto getSingleBeverageById(int id);

    Beverage getSingleBeverageEntityById(int id);

    BeverageDto getSingleBeverageByName(String name);

    List<BeverageDto> getAllBeverageFromFoodProducer(int foodProducerId);

    List<BeverageDto> getAllFilteredBeverage(int id, BeverageType beverageType);

    BeverageDtoWithId createBeverage(CreateBeverageDto createBeverageDto);

    BeverageDto updateBeverage(UpdateBeverageDto updateBeverageDto, int id);

    void deactivateBeverage(int id);
}

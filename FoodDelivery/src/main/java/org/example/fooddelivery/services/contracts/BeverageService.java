package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDto;
import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDtoWithId;
import org.example.fooddelivery.entities.dtos.BeverageDtos.CreateBeverageDto;
import org.example.fooddelivery.entities.dtos.BeverageDtos.UpdateBeverageDto;

public interface BeverageService {

    BeverageDto getSingleBeverageById(int id);

    BeverageDto getSingleBeverageByName(String name);

    BeverageDtoWithId createBeverage(CreateBeverageDto createBeverageDto);

    BeverageDto updateBeverage(UpdateBeverageDto updateBeverageDto, int id);

    void deactivateBeverage(int id);
}

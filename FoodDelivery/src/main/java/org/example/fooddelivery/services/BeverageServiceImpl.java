package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.Beverage;
import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDto;
import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDtoWithId;
import org.example.fooddelivery.entities.dtos.BeverageDtos.CreateBeverageDto;
import org.example.fooddelivery.entities.dtos.BeverageDtos.UpdateBeverageDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.repositories.BeverageRepository;
import org.example.fooddelivery.services.contracts.BeverageService;
import org.example.fooddelivery.util.MapperUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeverageServiceImpl implements BeverageService {

    private final BeverageRepository beverageRepository;
    private final MapperUtil mapperUtil;

    @Override
    public BeverageDto getSingleBeverageById(int id) {
        return mapperUtil.getModelMapper()
                .map(beverageRepository.findBeverageByIdAndIsActiveTrue(id), BeverageDto.class);
    }

    @Override
    public Beverage getSingleBeverageEntityById(int id) {
        return (beverageRepository.findBeverageByIdAndIsActiveTrue(id).orElseThrow(
                () -> new EntityNotFoundException("Beverage not found with id " + id)));
    }

    @Override
    public BeverageDto getSingleBeverageByName(String name) {
        return mapperUtil.getModelMapper()
                .map(beverageRepository.findBeverageByNameAndIsActiveTrue(name), BeverageDto.class);
    }

    @Override
    public BeverageDtoWithId createBeverage(CreateBeverageDto createBeverageDto) {

        Beverage beverage = Beverage.builder()
                            .name(createBeverageDto.getName())
                            .liters(createBeverageDto.getLiters())
                            .price(createBeverageDto.getPrice())
                            .build();

        beverageRepository.save(beverage);

        return mapperUtil.getModelMapper().map(beverage, BeverageDtoWithId.class);

    }

    @Override
    public BeverageDto updateBeverage(UpdateBeverageDto updateBeverageDto, int id) {

        Beverage beverage = beverageRepository.findBeverageByIdAndIsActiveTrue(id)
                            .orElseThrow(() -> new EntityNotFoundException("Beverage", id));

        beverage.setName(updateBeverageDto.getName());
        beverage.setLiters(updateBeverageDto.getLiters());
        beverage.setPrice(updateBeverageDto.getPrice());

        beverageRepository.save(beverage);

        return mapperUtil.getModelMapper().map(beverage, BeverageDto.class);
    }

    @Override
    public void deactivateBeverage(int id) {

        Beverage beverage = beverageRepository.findBeverageByIdAndIsActiveTrue(id)
                            .orElseThrow(() -> new EntityNotFoundException("Beverage", id));

        beverage.setActive(false);
        beverageRepository.save(beverage);
    }
}

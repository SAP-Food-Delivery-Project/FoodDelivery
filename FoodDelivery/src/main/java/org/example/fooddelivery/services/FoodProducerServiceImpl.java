package org.example.fooddelivery.services;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.FoodProducer;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.CreateFoodProducerDto;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDto;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDtoWithId;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.UpdateFoodProducerDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.repositories.FoodProducerRepository;
import org.example.fooddelivery.services.contracts.FoodProducerService;
import org.example.fooddelivery.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodProducerServiceImpl implements FoodProducerService {

    private final FoodProducerRepository foodProducerRepository;
    private final MapperUtil mapperUtil;

    @Override
    public FoodProducerDto getSingleFoodProducer(int id){
        return mapperUtil.getModelMapper()
                        .map(foodProducerRepository.findFoodProducerByIdAndIsActiveTrue(id),
                            FoodProducerDto.class);
    }

    @Override
    public FoodProducerDto getSingleFoodProducer(String name) {
        return mapperUtil.getModelMapper()
                        .map(foodProducerRepository.findFoodProducerByNameAndIsActiveTrue(name),
                            FoodProducerDto.class);
    }

    @Override
    public FoodProducerDto getSingleFoodProducer(String city, String address) {
        return mapperUtil.getModelMapper()
                .map(foodProducerRepository.findFoodProducerByCityAndAddressAndIsActiveTrue(city, address),
                        FoodProducerDto.class);
    }

    @Override
    public List<FoodProducerDto> getAllFoodProducers() {
        return mapperUtil.mapList(foodProducerRepository.findAllFoodProducerByIsActiveTrue(),
                                FoodProducerDto.class);
    }

    @Override
    public FoodProducerDtoWithId createFoodProducer( CreateFoodProducerDto createFoodProducerDto){

        FoodProducer foodProducer = FoodProducer.builder()
                                    .name(createFoodProducerDto.getName())
                                    .phoneNumber(createFoodProducerDto.getTelephoneNumber())
                                    .address(createFoodProducerDto.getAddress())
                                    .city(createFoodProducerDto.getCity())
                                    .build();

        foodProducerRepository.save(foodProducer);

        return mapperUtil.getModelMapper().map(foodProducer, FoodProducerDtoWithId.class);
    }

    @Override
    public FoodProducerDto updateFoodProducer( UpdateFoodProducerDto updateFoodProducerDto, int id){

        FoodProducer foodProducer = foodProducerRepository.findFoodProducerByIdAndIsActiveTrue(id)
                                    .orElseThrow(
                                    () -> new EntityNotFoundException("FoodProducer", id));

        foodProducer.setName(updateFoodProducerDto.getName());
        foodProducer.setPhoneNumber(updateFoodProducerDto.getTelephoneNumber());
        foodProducer.setAddress(updateFoodProducerDto.getAddress());
        foodProducer.setCity(updateFoodProducerDto.getCity());

        foodProducerRepository.save(foodProducer);

        return mapperUtil.getModelMapper().map(foodProducer, FoodProducerDto.class);
    }

    public void deactivateFoodProducer(int id){

        FoodProducer foodProducer = foodProducerRepository.findFoodProducerByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("FoodProducer", id));

        foodProducer.setActive(false);
        foodProducerRepository.save(foodProducer);
    }
}

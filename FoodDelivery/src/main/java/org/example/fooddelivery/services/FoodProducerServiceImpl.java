package org.example.fooddelivery.services;

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
        FoodProducer producer = foodProducerRepository.findFoodProducerByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("No FoodProducer found with id: " + id));

        return mapperUtil.getModelMapper()
                .map(producer, FoodProducerDto.class);
    }

    @Override
    public FoodProducer getSingleFoodProducerEntity(int id){
        return foodProducerRepository.findFoodProducerByIdAndIsActiveTrue(id).orElseThrow(
                () -> new EntityNotFoundException("No FoodProducer found with id: " + id)
        );
    }

    @Override
    public FoodProducerDto getSingleFoodProducer(String name) {
        FoodProducer producer = foodProducerRepository.findFoodProducerByNameAndIsActiveTrue(name)
                .orElseThrow(() -> new EntityNotFoundException("No FoodProducer found with name: " + name));

        return mapperUtil.getModelMapper()
                .map(producer, FoodProducerDto.class);
    }

    @Override
    public FoodProducerDto getSingleFoodProducer(String city, String address) {
        FoodProducer producer = foodProducerRepository.findFoodProducerByCityAndAddressAndIsActiveTrue(city, address)
                .orElseThrow(() -> new EntityNotFoundException("No FoodProducer found with city and address: " + city + ", " + address));

        return mapperUtil.getModelMapper()
                .map(producer, FoodProducerDto.class);
    }

    @Override
    public List<FoodProducerDto> getAllFoodProducers() {
        return mapperUtil.mapList(foodProducerRepository.findAllFoodProducerByIsActiveTrue(),
                                FoodProducerDto.class);
    }

    @Override
    public FoodProducerDtoWithId createFoodProducer(CreateFoodProducerDto createFoodProducerDto){

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

    @Override
    public void deactivateFoodProducer(int id){

        FoodProducer foodProducer = foodProducerRepository.findFoodProducerByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("FoodProducer", id));

        foodProducer.setActive(false);
        foodProducerRepository.save(foodProducer);
    }
}

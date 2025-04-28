package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.Food;
import org.example.fooddelivery.entities.FoodProducer;
import org.example.fooddelivery.entities.dtos.FoodDtos.CreateFoodDto;
import org.example.fooddelivery.entities.dtos.FoodDtos.FoodDto;
import org.example.fooddelivery.entities.dtos.FoodDtos.FoodDtoWithId;
import org.example.fooddelivery.entities.dtos.FoodDtos.UpdateFoodDto;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDtoWithId;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.repositories.FoodRepository;
import org.example.fooddelivery.services.contracts.FoodProducerService;
import org.example.fooddelivery.services.contracts.FoodService;
import org.example.fooddelivery.util.MapperUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final MapperUtil mapperUtil;

    @Override
    public FoodDto getSingleFood(int id) {
        return mapperUtil.getModelMapper()
                .map(foodRepository.findFoodByIdAndIsActiveTrue(id), FoodDto.class);
    }

    @Override
    public FoodDto getSingleFood(String name) {

        return mapperUtil.getModelMapper()
                .map(foodRepository.findFoodByNameAndIsActiveTrue(name), FoodDto.class);
    }

    @Override
    public FoodDtoWithId createFood(CreateFoodDto createFoodDto){

        Food food = Food.builder()
                .name(createFoodDto.getName())
                .weight(createFoodDto.getWeight())
                .calories(createFoodDto.getCalories())
                .fats(createFoodDto.getFats())
                .carbohydrates(createFoodDto.getCarbohydrates())
                .protein(createFoodDto.getProtein())
                .price(createFoodDto.getPrice())
                .products(createFoodDto.getFoodProducts())
                .build();

        foodRepository.save(food);

        return mapperUtil.getModelMapper().map(food, FoodDtoWithId.class);
    }

    @Override
    public FoodDto updateFood(UpdateFoodDto updateFoodDto, int id){

        Food food = foodRepository.findFoodByIdAndIsActiveTrue(id)
                            .orElseThrow(() -> new EntityNotFoundException("Food", id));

        food.setName(updateFoodDto.getName());
        food.setWeight(updateFoodDto.getWeight());
        food.setCalories(updateFoodDto.getCalories());
        food.setFats(updateFoodDto.getFats());
        food.setCarbohydrates(updateFoodDto.getCarbohydrates());
        food.setProtein(updateFoodDto.getProtein());
        food.setPrice(updateFoodDto.getPrice());

        foodRepository.save(food);

        return mapperUtil.getModelMapper().map(food, FoodDto.class);
    }

    @Override
    public void deactivateFood(int id){

        Food food = foodRepository.findFoodByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Food", id));

        food.setActive(false);
        foodRepository.save(food);
    }
}

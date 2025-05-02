package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.dtos.OrderedProductsFoods.CreateOrderFoodDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsFoods.OrderedFoodDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsFoods.OrderedFoodDtoWithId;
import org.example.fooddelivery.entities.dtos.OrderedProductsFoods.UpdateOrderFoodDto;

import java.util.List;

public interface OrderProductsFoodsService {
    OrderedFoodDto getOrderProductFoodById(int id);

    List<OrderedFoodDto> getAllOrderedfoods();

    OrderedFoodDtoWithId createOrderFood(CreateOrderFoodDto createOrderFoodDto);

    OrderedFoodDto updateOrderedFood(UpdateOrderFoodDto updateOrderFoodDto, int id);

    void deactivateOrderedFood(int id);
}

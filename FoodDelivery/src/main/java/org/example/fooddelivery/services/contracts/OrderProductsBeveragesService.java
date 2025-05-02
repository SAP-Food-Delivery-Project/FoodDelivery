package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.CreateOrderBeverageDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.OrderedBeverageDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.OrderedBeverageDtoWithId;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.UpdateOrderBeverageDto;

import java.util.List;

public interface OrderProductsBeveragesService {

    OrderedBeverageDto getOrderProductBeverageById(int id);

    List<OrderedBeverageDto> getAllOrderedBeverages();

    OrderedBeverageDtoWithId createOrderBeverage(CreateOrderBeverageDto createOrderBeverageDto);

    OrderedBeverageDto updateOrderBeverage(UpdateOrderBeverageDto updateOrderBeverageDto, int id);

    void deactivateOrderedBeverage(int id);
}

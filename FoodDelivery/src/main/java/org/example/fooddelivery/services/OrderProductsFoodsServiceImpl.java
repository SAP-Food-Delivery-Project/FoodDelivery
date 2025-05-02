package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.Order;
import org.example.fooddelivery.entities.OrderProductsFoods;
import org.example.fooddelivery.entities.Status;
import org.example.fooddelivery.entities.dtos.OrderedProductsFoods.CreateOrderFoodDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsFoods.OrderedFoodDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsFoods.OrderedFoodDtoWithId;
import org.example.fooddelivery.entities.dtos.OrderedProductsFoods.UpdateOrderFoodDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.exceptions.OperationNotSupportedException;
import org.example.fooddelivery.repositories.OrderProductsFoodsRepository;
import org.example.fooddelivery.services.contracts.FoodService;
import org.example.fooddelivery.services.contracts.OrderProductsFoodsService;
import org.example.fooddelivery.services.contracts.OrderService;
import org.example.fooddelivery.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderProductsFoodsServiceImpl implements OrderProductsFoodsService {

    private final OrderProductsFoodsRepository orderProductsFoodsRepository;
    private final OrderService orderService;
    private final FoodService foodService;
    private final MapperUtil mapperUtil;


    @Override
    public OrderedFoodDto getOrderProductFoodById(int id) {
        return mapperUtil.getModelMapper().map(orderProductsFoodsRepository.findByIdAndIsActiveTrue(id),
                OrderedFoodDto.class);
    }

    @Override
    public List<OrderedFoodDto> getAllOrderedfoods() {
        return mapperUtil.mapList(orderProductsFoodsRepository.findAllByIsActiveTrue(), OrderedFoodDto.class);
    }

    @Override
    public OrderedFoodDtoWithId createOrderFood(CreateOrderFoodDto createOrderFoodDto) {

        OrderProductsFoods orderProductsFoods = OrderProductsFoods.builder()
                .food(foodService.getSingleFoodEntity(createOrderFoodDto.getFoodId()))
                .order(orderService.getOrderEntityById(createOrderFoodDto.getOrderId()))
                .quantity(createOrderFoodDto.getQuantity())
                .build();

        orderProductsFoodsRepository.save(orderProductsFoods);

        return mapperUtil.getModelMapper().map(orderProductsFoods, OrderedFoodDtoWithId.class);
    }


    @Override
    public OrderedFoodDto updateOrderedFood(UpdateOrderFoodDto updateOrderFoodDto, int id) {


        OrderProductsFoods orderProductsFoods = orderProductsFoodsRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found"));

        Order order = orderService.getOrderEntityById(orderProductsFoods.getOrder().getId());

        if (order.getStatus().equals(Status.not_taken)) {
            orderProductsFoods.setQuantity(updateOrderFoodDto.getQuantity());
            orderProductsFoods.setFood(foodService.getSingleFoodEntity(updateOrderFoodDto.getFoodId()));
        } else {
            throw new OperationNotSupportedException("The order is already taken and you can not modify it!");
        }

        orderProductsFoodsRepository.save(orderProductsFoods);

        return mapperUtil.getModelMapper().map(orderProductsFoods, OrderedFoodDto.class);
    }

    @Override
    public void deactivateOrderedFood(int id) {

        OrderProductsFoods orderProductsFoods = orderProductsFoodsRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderFood with id " + id + " not found"));

        orderProductsFoods.setActive(false);
        orderProductsFoodsRepository.save(orderProductsFoods);
    }

}

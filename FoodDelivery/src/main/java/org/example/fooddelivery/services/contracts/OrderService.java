package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.Order;
import org.example.fooddelivery.entities.dtos.OrderDtos.CreateOrderDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDtoWithId;
import org.example.fooddelivery.entities.dtos.OrderDtos.UpdateOrderDto;

import java.util.List;

public interface OrderService {

    OrderDto getOrderById(int id);

    Order getOrderEntityById(int id);

    List<OrderDto> getAllOrders();

    OrderDtoWithId createOrder(CreateOrderDto createOrderDto);

    OrderDto updateOrder(UpdateOrderDto updateOrderDto, int id);

    void deactivateOrder(int id);

}

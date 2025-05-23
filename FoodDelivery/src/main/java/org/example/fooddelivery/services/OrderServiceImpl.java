package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.Order;
import org.example.fooddelivery.entities.dtos.OrderDtos.CreateOrderDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDtoWithId;
import org.example.fooddelivery.entities.dtos.OrderDtos.UpdateOrderDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.exceptions.OperationNotSupportedException;
import org.example.fooddelivery.repositories.OrderRepository;
import org.example.fooddelivery.services.contracts.FoodProducerService;
import org.example.fooddelivery.services.contracts.OrderService;
import org.example.fooddelivery.services.contracts.UserService;
import org.example.fooddelivery.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;
    private final FoodProducerService foodProducerService;

    @Override
    public OrderDto getOrderById(int id) {
        Order order = orderRepository.findOrderByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with " + id + " not found"));

        return mapperUtil.getModelMapper().map(order, OrderDto.class);
    }

    @Override
    public Order getOrderEntityById(int id) {
        return orderRepository.findOrderByIdAndIsActiveTrue(id).orElseThrow(
                () -> new EntityNotFoundException("Order not found with id " + id)
        );
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return mapperUtil.mapList(orderRepository.findAllByIsActiveTrue(), OrderDto.class);
    }

    @Override
    public OrderDtoWithId createOrder(CreateOrderDto createOrderDto) {

        Order order = Order.builder()
                .address(createOrderDto.getAddress())
                .city(createOrderDto.getCity())
                .order_date(createOrderDto.getOrderDate())
                .status(createOrderDto.getStatus())
                .foodProducer(foodProducerService.getSingleFoodProducerEntity(createOrderDto.getFoodProducerId()))
                .user(userService.getSingleUserEntity(createOrderDto.getUserId()))
                .build();

        orderRepository.save(order);

        return mapperUtil.getModelMapper().map(order, OrderDtoWithId.class);
    }


    @Override
    public OrderDto updateOrder(UpdateOrderDto updateOrderDto, int id) {

        Order order = orderRepository.findOrderByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if(order.getDelivery() != null){
            order.setAddress(updateOrderDto.getAddress());
            order.setCity(updateOrderDto.getCity());
            order.setStatus(updateOrderDto.getStatus());
        }else {
            throw new OperationNotSupportedException("Order will be delivered and the location can not be modified!");
        }

        orderRepository.save(order);

        return mapperUtil.getModelMapper().map(order, OrderDto.class);
    }

    @Override
    public void deactivateOrder(int id) {

        Order order = orderRepository.findOrderByIdAndIsActiveTrue(id)
                        .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        order.setActive(false);
        orderRepository.save(order);
    }
}

package org.example.fooddelivery.services.unitTests;

import org.example.fooddelivery.entities.*;
import org.example.fooddelivery.entities.dtos.OrderDtos.CreateOrderDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDtoWithId;
import org.example.fooddelivery.entities.dtos.OrderDtos.UpdateOrderDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.exceptions.OperationNotSupportedException;
import org.example.fooddelivery.repositories.OrderRepository;
import org.example.fooddelivery.services.OrderServiceImpl;
import org.example.fooddelivery.services.contracts.FoodProducerService;
import org.example.fooddelivery.services.contracts.UserService;
import org.example.fooddelivery.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Configuration
public class OrderServiceImplTests {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MapperUtil mapperUtil;

    @Mock
    private UserService userService;

    @Mock
    private FoodProducerService foodProducerService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setup() {
        when(mapperUtil.getModelMapper()).thenReturn(modelMapper);
    }

    @Test
    void testGetOrderById() {
        int id = 1;
        Order order = new Order();
        OrderDto expectedDto = new OrderDto();

        when(orderRepository.findOrderByIdAndIsActiveTrue(id)).thenReturn(Optional.of(order));
        when(modelMapper.map(order, OrderDto.class)).thenReturn(expectedDto);

        OrderDto result = orderService.getOrderById(id);

        assertEquals(expectedDto, result);
    }

    @Test
    void testGetOrderEntityById() {
        int id = 1;
        Order order = new Order();

        when(orderRepository.findOrderByIdAndIsActiveTrue(id)).thenReturn(Optional.of(order));

        Order result = orderService.getOrderEntityById(id);

        assertEquals(order, result);
    }

    @Test
    void testGetOrderEntityById_NotFound() {
        when(orderRepository.findOrderByIdAndIsActiveTrue(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderService.getOrderEntityById(99));
    }

    @Test
    void testGetAllOrders() {
        List<Order> orders = List.of(new Order(), new Order());
        List<OrderDto> expectedDtos = List.of(new OrderDto(), new OrderDto());

        when(orderRepository.findAllByIsActiveTrue()).thenReturn(orders);
        when(mapperUtil.mapList(orders, OrderDto.class)).thenReturn(expectedDtos);

        List<OrderDto> result = orderService.getAllOrders();

        assertEquals(expectedDtos, result);
    }

    @Test
    void testCreateOrder() {
        CreateOrderDto createDto = new CreateOrderDto();
        createDto.setAddress("123 Street");
        createDto.setCity("Sofia");
        createDto.setOrderDate(LocalDateTime.now());
        createDto.setStatus(Status.not_taken);
        createDto.setUserId(10);
        createDto.setFoodProducerId(5);

        Order order = Order.builder()
                .address("123 Street")
                .city("Sofia")
                .order_date(LocalDateTime.now())
                .status(Status.not_taken)
                .build();

        OrderDtoWithId expectedDto = new OrderDtoWithId();

        when(foodProducerService.getSingleFoodProducerEntity(5)).thenReturn(new FoodProducer());
        when(userService.getSingleUserEntity(10)).thenReturn(new User());
        when(modelMapper.map(any(Order.class), eq(OrderDtoWithId.class))).thenReturn(expectedDto);

        OrderDtoWithId result = orderService.createOrder(createDto);

        assertEquals(expectedDto, result);
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void testUpdateOrder_WithDelivery() {
        int id = 1;
        UpdateOrderDto updateDto = new UpdateOrderDto();
        updateDto.setAddress("New Address");
        updateDto.setCity("New City");
        updateDto.setStatus(Status.not_taken);

        Order existingOrder = new Order();
        existingOrder.setDelivery(new Delivery());

        OrderDto expectedDto = new OrderDto();

        when(orderRepository.findOrderByIdAndIsActiveTrue(id)).thenReturn(Optional.of(existingOrder));
        when(modelMapper.map(existingOrder, OrderDto.class)).thenReturn(expectedDto);

        OrderDto result = orderService.updateOrder(updateDto, id);

        assertEquals(expectedDto, result);
        verify(orderRepository).save(existingOrder);
    }

    @Test
    void testUpdateOrder_WithoutDelivery_ShouldThrow() {
        int id = 1;
        UpdateOrderDto updateDto = new UpdateOrderDto();

        Order existingOrder = new Order();

        when(orderRepository.findOrderByIdAndIsActiveTrue(id)).thenReturn(Optional.of(existingOrder));

        assertThrows(OperationNotSupportedException.class, () -> orderService.updateOrder(updateDto, id));
    }

    @Test
    void testDeactivateOrder() {
        int id = 1;
        Order order = new Order();
        order.setActive(true);

        when(orderRepository.findOrderByIdAndIsActiveTrue(id)).thenReturn(Optional.of(order));

        orderService.deactivateOrder(id);

        assertFalse(order.isActive());
        verify(orderRepository).save(order);
    }

    @Test
    void testDeactivateOrder_NotFound() {
        when(orderRepository.findOrderByIdAndIsActiveTrue(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderService.deactivateOrder(99));
    }

}

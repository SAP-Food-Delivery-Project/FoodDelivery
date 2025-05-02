package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.Order;
import org.example.fooddelivery.entities.OrderProductsBeverages;
import org.example.fooddelivery.entities.Status;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.CreateOrderBeverageDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.OrderedBeverageDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.OrderedBeverageDtoWithId;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.UpdateOrderBeverageDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.exceptions.OperationNotSupportedException;
import org.example.fooddelivery.repositories.OrderProductsBeveragesRepository;
import org.example.fooddelivery.services.contracts.BeverageService;
import org.example.fooddelivery.services.contracts.OrderProductsBeveragesService;
import org.example.fooddelivery.services.contracts.OrderService;
import org.example.fooddelivery.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderProductsBeveragesServiceImpl implements OrderProductsBeveragesService {

    private final OrderProductsBeveragesRepository orderProductsBeveragesRepository;
    private final OrderService orderService;
    private final BeverageService beverageService;
    private final MapperUtil mapperUtil;


    @Override
    public OrderedBeverageDto getOrderProductBeverageById(int id) {
        return mapperUtil.getModelMapper().map(orderProductsBeveragesRepository.findByIdAndIsActiveTrue(id),
                OrderedBeverageDto.class);
    }

    @Override
    public List<OrderedBeverageDto> getAllOrderedBeverages() {
        return mapperUtil.mapList(orderProductsBeveragesRepository.findAllByIsActiveTrue(), OrderedBeverageDto.class);
    }

    @Override
    public OrderedBeverageDtoWithId createOrderBeverage(CreateOrderBeverageDto createOrderBeverageDto) {

        OrderProductsBeverages orderProductsBeverages = OrderProductsBeverages.builder()
                .beverage(beverageService.getSingleBeverageEntityById(createOrderBeverageDto.getBeverageId()))
                .order(orderService.getOrderEntityById(createOrderBeverageDto.getOrderId()))
                .quantity(createOrderBeverageDto.getQuantity())
                .build();

        orderProductsBeveragesRepository.save(orderProductsBeverages);

        return mapperUtil.getModelMapper().map(orderProductsBeverages, OrderedBeverageDtoWithId.class);
    }


    @Override
    public OrderedBeverageDto updateOrderBeverage(UpdateOrderBeverageDto updateOrderBeverageDto, int id) {


        OrderProductsBeverages orderProductsBeverages = orderProductsBeveragesRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found"));

        Order order = orderService.getOrderEntityById(orderProductsBeverages.getOrder().getId());

        if(order.getStatus().equals(Status.not_taken)){
            orderProductsBeverages.setQuantity(updateOrderBeverageDto.getQuantity());
            orderProductsBeverages.setBeverage(beverageService.getSingleBeverageEntityById(updateOrderBeverageDto.getBeverageId()));
        }else {
            throw new OperationNotSupportedException("The order is already taken and you can not modify it!");
        }

        orderProductsBeveragesRepository.save(orderProductsBeverages);

        return mapperUtil.getModelMapper().map(orderProductsBeverages, OrderedBeverageDto.class);
    }

    @Override
    public void deactivateOrderedBeverage(int id) {

        OrderProductsBeverages orderProductsBeverages = orderProductsBeveragesRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderBeverage with id " + id + " not found"));

        orderProductsBeverages.setActive(false);
        orderProductsBeveragesRepository.save(orderProductsBeverages);
    }

}

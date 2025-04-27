package org.example.fooddelivery.services;

import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.Delivery;
import org.example.fooddelivery.entities.dtos.DeliveryDtos.DeliveryDto;
import org.example.fooddelivery.entities.dtos.DeliveryDtos.UpdateDeliveryDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.repositories.DeliveryRepository;
import org.example.fooddelivery.services.contracts.DeliveryService;
import org.example.fooddelivery.services.contracts.OrderService;
import org.example.fooddelivery.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final MapperUtil mapperUtil;
    private final OrderService orderService;

    @Override
    public DeliveryDto getDeliveryById(int id) {
        return mapperUtil.getModelMapper().map(deliveryRepository.findDeliveryByIdAndIsActiveTrue(id), DeliveryDto.class);
    }

    @Override
    public List<DeliveryDto> getAllDeliveries() {
        return mapperUtil.mapList(deliveryRepository.findAllByIsActiveTrue(), DeliveryDto.class);
    }

//    @Override
//    public DeliveryDtoWithId createDelivery(CreateDeliveryDto createDeliveryDto) {
//
//        Delivery delivery = Delivery.builder()
//                .deliveryTime(createDeliveryDto.getDeliveryTime())
//                .supplier(createDeliveryDto.getSupplierId())
//                .deliveryTime(orderService.getOrderEntityById(createDeliveryDto.getOrderId()))
//                .build();
//
//        deliveryRepository.save(delivery);
//
//        return mapperUtil.getModelMapper().map(delivery, DeliveryDtoWithId.class);
//    }


    @Override
    public DeliveryDto updateDelivery(UpdateDeliveryDto updateDeliveryDto, int id) {

        Delivery delivery = deliveryRepository.findDeliveryByIdAndIsActiveTrue(id).orElseThrow(
                () -> new EntityNotFoundException("Delivery with id " + id + " not found"));

        delivery.setDeliveryTime(updateDeliveryDto.getDeliveryTime());

        deliveryRepository.save(delivery);

        return mapperUtil.getModelMapper().map(delivery, DeliveryDto.class);
    }

    @Override
    public void deactivateDelivery(int id) {

        Delivery delivery = deliveryRepository.findDeliveryByIdAndIsActiveTrue(id).orElseThrow(
                () -> new EntityNotFoundException("Delivery with id " + id + " not found"));

        delivery.setActive(false);
        deliveryRepository.save(delivery);
    }

}

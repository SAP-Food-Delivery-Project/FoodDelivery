package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.dtos.DeliveryDtos.CreateDeliveryDto;
import org.example.fooddelivery.entities.dtos.DeliveryDtos.DeliveryDto;
import org.example.fooddelivery.entities.dtos.DeliveryDtos.DeliveryDtoWithId;
import org.example.fooddelivery.entities.dtos.DeliveryDtos.UpdateDeliveryDto;

import java.util.List;

public interface DeliveryService {
    DeliveryDto getDeliveryById(int id);

    List<DeliveryDto> getAllDeliveries();

    DeliveryDto updateDelivery(UpdateDeliveryDto updateDeliveryDto, int id);

    void deactivateDelivery(int id);

    // DeliveryDtoWithId createDelivery(CreateDeliveryDto createDeliveryDto);
}

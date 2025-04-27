package org.example.fooddelivery.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.dtos.DeliveryDtos.DeliveryDto;
import org.example.fooddelivery.entities.dtos.DeliveryDtos.UpdateDeliveryDto;
import org.example.fooddelivery.services.contracts.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;


    @GetMapping("/{id}")
    public DeliveryDto getDeliveryById(@PathVariable int id) {
        return deliveryService.getDeliveryById(id);
    }

    @GetMapping
    public List<DeliveryDto> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

//    @PostMapping
//    public DeliveryDtoWithId createDelivery(@RequestBody CreateDeliveryDto createDeliveryDto) {
//        return deliveryService.createDelivery(createDeliveryDto);
//    }


    @PutMapping("/{id}")
    public DeliveryDto updateDelivery(@PathVariable int id, @RequestBody UpdateDeliveryDto updateDeliveryDto) {
        return deliveryService.updateDelivery(updateDeliveryDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateDelivery(@Valid @PathVariable int id){
        deliveryService.deactivateDelivery(id);
        return ResponseEntity.ok("Delivery deactivated successfully!");
    }

}

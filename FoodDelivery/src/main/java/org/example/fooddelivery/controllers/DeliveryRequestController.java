package org.example.fooddelivery.controllers;
import org.example.fooddelivery.entities.Order;
import org.springframework.web.bind.annotation.*;
import org.example.fooddelivery.services.DeliveryRequestService;
import java.util.List;


@RestController
@RequestMapping("/requests")
public class DeliveryRequestController {
    private final DeliveryRequestService service;

    public DeliveryRequestController(DeliveryRequestService service) {
        this.service = service;
    }

    @GetMapping("/not-taken")
    public List<Order> getNotTakenRequests() {
        return service.getNotTakenRequests();
    }
}
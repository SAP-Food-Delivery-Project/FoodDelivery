package org.example.fooddelivery.services;
import java.util.List;
import org.example.fooddelivery.entities.Order;
import org.example.fooddelivery.entities.Status;
import org.springframework.stereotype.Service;
import org.example.fooddelivery.repositories.DeliveryRequestRepository;


@Service
public class DeliveryRequestService {
    private final DeliveryRequestRepository repository;

    public DeliveryRequestService(DeliveryRequestRepository repository) {
        this.repository = repository;
    }

    public List<Order> getNotTakenRequests() {
        return repository.findByStatus(Status.not_taken);
    }
}
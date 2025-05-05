package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.Order;
import org.example.fooddelivery.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface DeliveryRequestRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(Status status);
}
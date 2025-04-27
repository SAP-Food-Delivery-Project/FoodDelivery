package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    Optional<Delivery> findDeliveryByIdAndIsActiveTrue(int id);

    List<Delivery> findAllByIsActiveTrue();
}

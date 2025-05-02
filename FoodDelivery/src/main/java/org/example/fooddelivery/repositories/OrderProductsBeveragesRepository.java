package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.OrderProductsBeverages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderProductsBeveragesRepository extends JpaRepository<OrderProductsBeverages, Integer> {


    Optional<OrderProductsBeverages> findByIdAndIsActiveTrue(int id);

    List<OrderProductsBeverages> findAllByIsActiveTrue();

}

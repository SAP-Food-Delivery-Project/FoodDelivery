package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findOrderByIdAndIsActiveTrue(int id);

    List<Order> findAllByIsActiveTrue();
}

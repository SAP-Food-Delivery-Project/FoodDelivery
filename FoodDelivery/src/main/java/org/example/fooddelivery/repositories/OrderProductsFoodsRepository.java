package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.OrderProductsBeverages;
import org.example.fooddelivery.entities.OrderProductsFoods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderProductsFoodsRepository extends JpaRepository<OrderProductsFoods, Integer> {

    Optional<OrderProductsFoods> findByIdAndIsActiveTrue(int id);

    List<OrderProductsFoods> findAllByIsActiveTrue();

}

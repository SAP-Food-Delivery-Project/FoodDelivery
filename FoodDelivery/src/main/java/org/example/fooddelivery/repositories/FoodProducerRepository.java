package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.FoodProducer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodProducerRepository extends JpaRepository<FoodProducer, Integer> {

    Optional<FoodProducer> findFoodProducerByIdAndIsActiveTrue(int id);

    Optional<FoodProducer> findFoodProducerByNameAndIsActiveTrue(String name);

    Optional<FoodProducer> findFoodProducerByCityAndAddressAndIsActiveTrue(String city, String address);

    List<FoodProducer> findAllFoodProducerByIsActiveTrue();
}

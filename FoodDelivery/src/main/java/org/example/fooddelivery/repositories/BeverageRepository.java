package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.Beverage;
import org.example.fooddelivery.entities.BeverageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BeverageRepository extends JpaRepository<Beverage, Integer> {

    Optional<Beverage> findBeverageByIdAndIsActiveTrue(int id);

    Optional<Beverage> findBeverageByNameAndIsActiveTrue(String name);

    List<Beverage> findAllBeverageByFoodProducerIdAndIsActiveTrue(int foodProducerId);

    List<Beverage> findAllBeverageByBeverageTypeAndFoodProducerIdAndIsActiveTrue
            (int id, BeverageType beverageType);
}

package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeverageRepository extends JpaRepository<Beverage, Integer> {

    Optional<Beverage> findBeverageByIdAndIsActiveTrue(int id);

    Optional<Beverage> findBeverageByNameAndIsActiveTrue(String name);
}

package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.Food;
import org.example.fooddelivery.entities.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Integer> {

    Optional<Food> findFoodByIdAndIsActiveTrue(int id);

    Optional<Food> findFoodByNameAndIsActiveTrue(String name);

    List<Food> findAllFoodByFoodProducerIdAndIsActiveTrue(int id);

    List<Food> findAllByFoodTypeAndFoodProducerIdAndIsActiveTrue(FoodType foodType, int id);


}

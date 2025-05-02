package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.SupplierBonus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierBonusRepository extends JpaRepository<SupplierBonus, Integer> {

    Optional<SupplierBonus> findSupplierBonusByIdAndIsActiveTrue(int id);

    List<SupplierBonus> findAllByIsActiveTrue();
}

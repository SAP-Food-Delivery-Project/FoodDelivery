package org.example.fooddelivery.repositories;

import jakarta.validation.constraints.NotNull;
import org.example.fooddelivery.entities.SupplierBonus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SupplierBonusRepository extends JpaRepository<SupplierBonus, Integer> {

    Optional<SupplierBonus> findSupplierBonusByIdAndIsActiveTrue(int id);

    SupplierBonus findSupplierBonusByCurrentDayAndIsActiveTrue(LocalDate day);

    List<SupplierBonus> findAllByIsActiveTrue();
}

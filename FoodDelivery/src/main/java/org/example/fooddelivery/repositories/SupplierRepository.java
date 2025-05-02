package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Optional<Supplier> findSupplierByIdAndIsActiveTrue(Integer integer);

    List<Supplier> findSupplierByNameAndIsActiveTrue(String name);

    List<Supplier> findAllAndIsActiveTrue();


}

package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByIdAndIsActiveTrue(int id);

    List<Employee> findAllByIsActiveTrue();

}

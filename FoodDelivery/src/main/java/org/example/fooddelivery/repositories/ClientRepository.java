package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByIdAndIsActiveTrue(int id);

    List<Client> findAllByIsActiveTrue();

}

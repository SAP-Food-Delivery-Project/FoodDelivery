package org.example.fooddelivery.repositories;


import org.example.fooddelivery.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByIdAndIsActiveTrue(int id);

    Optional<User> findUserByEmailAndIsActiveTrue(String email);

    Optional<User> findUserByPhoneNumberAndIsActiveTrue(String phoneNumber);

    List<User> findAllByIsActiveTrue();
}

package org.example.fooddelivery.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employees")
@Entity
public class Employee extends User {

    @OneToOne(fetch = FetchType.LAZY)
    private FoodProducer foodProducer;


    @Builder
    public Employee(String firstName,
                String lastName,
                String email,
                String password,
                String phoneNumber,
                LocalDate birthDate,
                FoodProducer foodProducer){
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setPhoneNumber(phoneNumber);
        setBirthDate(birthDate);
        setRole(Role.employee);
        setCreationDate(LocalDate.now());
        setActive(true);
        this.foodProducer = foodProducer;
    }

}

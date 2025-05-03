package org.example.fooddelivery.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "clients")
@Entity
public class Client extends User {

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Builder
    public Client(String firstName,
                String lastName,
                String email,
                String password,
                String phoneNumber,
                LocalDate birthDate,
                String address,
                String city,
                BigDecimal balance) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setPhoneNumber(phoneNumber);
        setBirthDate(birthDate);
        setRole(Role.client);
        setCreationDate(LocalDate.now());
        setActive(true);
        this.address = address;
        this.city = city;
        this.balance = balance;
    }
}

package org.example.fooddelivery.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "suppliers")
@Entity
public class Supplier extends User {

    @Column(name = "rate", nullable = false)
    private double rate;

    @OneToMany(mappedBy = "supplier")
    private Set<SupplierBonus> supplierBonuses;

    @OneToMany(mappedBy = "supplier")
    private Set<Delivery> delivery;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Builder
    public Supplier(String firstName,
                    String lastName,
                    String email,
                    String password,
                    String phoneNumber,
                    LocalDate birthDate){
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setPhoneNumber(phoneNumber);
        setBirthDate(birthDate);
        setRole(Role.supplier);
        setCreationDate(LocalDate.now());
        setActive(true);
        this.rate = 0.0;
    }
}

package org.example.fooddelivery.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email",unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "telephone_number",unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthDate;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;

    @Builder
    public User(String firstName,
                String lastName,
                String email,
                String password,
                String phoneNumber,
                String address,
                String city,
                BigDecimal balance,
                Role role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.balance = balance;
        this.role = role;
        this.creationDate = LocalDate.now();
        this.isActive = true;
    }

}

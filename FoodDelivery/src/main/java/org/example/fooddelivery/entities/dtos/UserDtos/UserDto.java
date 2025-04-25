package org.example.fooddelivery.entities.dtos.UserDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fooddelivery.entities.Role;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate birthDate;

    private String address;

    private String city;

    private Role role;
}

package org.example.fooddelivery.entities.dtos.EmployeeDtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fooddelivery.entities.Role;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeDto {

    @NotNull
    @Size(min = 2, max = 20, message="Name should be between 2 and 20 symbols!")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 20, message="Name should be between 2 and 20 symbols!")
    private String lastName;

    @NotNull
    @Size(min = 2, max = 20, message="Email should be between 2 and 20 symbols!")
    private String email;

    @NotNull
    @Size(min = 2, max = 50, message="Password should be between 2 and 50 symbols!")
    private String password;

    @NotNull
    @Size(min = 9, max = 10, message="Phone number should be 9 symbols!")
    private String phoneNumber;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private Role role;

    @NotNull
    @Min(1)
    private int foodProducerId;
}

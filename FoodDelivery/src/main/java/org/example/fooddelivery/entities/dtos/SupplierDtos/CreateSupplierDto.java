package org.example.fooddelivery.entities.dtos.SupplierDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fooddelivery.entities.Role;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSupplierDto {

    @NotNull
    @Size(min = 2, max = 25, message = "Supplier first name should be between 2 and 25 symbols!")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 25, message = "Supplier last name should be between 2 and 25 symbols!")
    private String lastName;

    @NotNull
    @Size(min = 10, max = 10, message = "Supplier telephone number should be exactly 10 symbols!")
    @Pattern(regexp = "\\d{10}", message = "Phone number must contain only digits.")
    private String phoneNumber;

    @NotNull
    @Size(min = 2, max = 20, message="Email should be between 2 and 20 symbols!")
    private String email;

    @NotNull
    @Size(min = 2, max = 50, message="Password should be between 2 and 50 symbols!")
    private String password;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private Role role;
}

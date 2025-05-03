package org.example.fooddelivery.entities.dtos.EmployeeDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeDto {

    @NotNull
    @Size(min = 2, max = 20, message="Email should be between 2 and 20 symbols!")
    private String email;

    @NotNull
    @Size(min = 2, max = 50, message="Password should be between 2 and 50 symbols!")
    private String password;

    @NotNull
    @Size(min = 9, max = 10, message="Phone number should be 9 symbols!")
    private String phoneNumber;

}

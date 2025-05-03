package org.example.fooddelivery.entities.dtos.EmployeeDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fooddelivery.entities.Role;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDto;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate birthDate;

    private Role role;

    private FoodProducerDto foodProducer;

}

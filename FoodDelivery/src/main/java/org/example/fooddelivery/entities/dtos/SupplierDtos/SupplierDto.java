package org.example.fooddelivery.entities.dtos.SupplierDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

}

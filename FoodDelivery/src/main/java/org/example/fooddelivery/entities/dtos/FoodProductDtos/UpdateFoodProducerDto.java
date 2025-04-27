package org.example.fooddelivery.entities.dtos.FoodProductDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFoodProducerDto {

    @NotNull
    @Size(min=2, max=60, message="Name should be between 2 and 20 symbols!")
    private String name;

    @NotNull
    @Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits.")
    @Pattern(regexp = "\\d{10}", message = "Phone number must contain only digits.")
    private String telephoneNumber;

    @NotNull
    @Size(min = 2, max = 45, message = "Address should be between 2 and 45 symbols!")
    private String address;

    @NotNull
    @Size(min = 2, max = 45, message="City should be between 2 and 45 symbols!")
    private String city;
}

package org.example.fooddelivery.entities.dtos.SupplierBonusDtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fooddelivery.entities.Supplier;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSupplierBonusDto {

    @NotNull
    @DecimalMin(value = "0.1", message = "Bonus cannot be less than 0.1")
    private BigDecimal bonus;

    @NotNull
    private LocalDate currentDay;

    @NotNull
    private int supplierId;
}

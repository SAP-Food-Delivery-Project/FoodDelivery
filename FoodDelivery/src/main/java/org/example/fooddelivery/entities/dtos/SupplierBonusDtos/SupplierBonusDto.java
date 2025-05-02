package org.example.fooddelivery.entities.dtos.SupplierBonusDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fooddelivery.entities.Supplier;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierBonusDto {

    private BigDecimal bonus;

    private LocalDate currentDay;

    private Supplier supplier;


}

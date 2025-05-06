package org.example.fooddelivery.entities.dtos.SupplierDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class SupplierIncomeDto {

    private String email;

    private BigDecimal income;

    public SupplierIncomeDto(String email, Number income) {
        this.email = email;
        this.income = income != null ? new BigDecimal(income.toString()) : BigDecimal.ZERO;
    }
}

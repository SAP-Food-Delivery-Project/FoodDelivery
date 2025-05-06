package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.dtos.SupplierDtos.SupplierIncomeDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SupplierOperationsService {
    List<SupplierIncomeDto> getSupplierIncomeDto(LocalDateTime startDate, LocalDateTime endDate);
}

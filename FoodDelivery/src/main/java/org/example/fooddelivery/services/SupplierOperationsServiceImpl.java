package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.dtos.SupplierDtos.SupplierIncomeDto;
import org.example.fooddelivery.repositories.SupplierRepository;
import org.example.fooddelivery.services.contracts.SupplierOperationsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierOperationsServiceImpl implements SupplierOperationsService {

    private final SupplierRepository supplierRepository;

    @Override
    public List<SupplierIncomeDto> getSupplierIncomeDto(LocalDateTime startDate, LocalDateTime endDate) {
        return supplierRepository.getSupplierIncomes(startDate, endDate);
    }

}

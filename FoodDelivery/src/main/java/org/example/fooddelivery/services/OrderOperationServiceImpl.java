package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.repositories.OrderRepository;
import org.example.fooddelivery.services.contracts.OrderOperationsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrderOperationServiceImpl implements OrderOperationsService {

    private final OrderRepository orderRepository;

    @Override
    public BigDecimal getTurnoverOfTheCompanyInGivenPeriod(LocalDate from, LocalDate to) {
        return orderRepository.turnoverOfTheCompanyInGivenPeriod(from, to);
    }

}

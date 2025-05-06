package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.repositories.FoodProducerRepository;
import org.example.fooddelivery.repositories.FoodRepository;
import org.example.fooddelivery.services.contracts.FoodProducerOperationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class FoodProducerOperationServiceImpl implements FoodProducerOperationService {

    private final FoodProducerRepository producerRepository;



}

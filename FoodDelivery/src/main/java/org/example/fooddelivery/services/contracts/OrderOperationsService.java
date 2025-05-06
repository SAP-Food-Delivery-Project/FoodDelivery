package org.example.fooddelivery.services.contracts;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OrderOperationsService {
    BigDecimal getTurnoverOfTheCompanyInGivenPeriod(LocalDate from, LocalDate to);
}

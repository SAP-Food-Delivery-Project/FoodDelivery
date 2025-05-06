package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findOrderByIdAndIsActiveTrue(int id);

    List<Order> findAllByIsActiveTrue();

    @Query(value = """
                SELECT SUM(total) FROM (
                SELECT (f.price * opf.quantity) AS total
                FROM order_products_foods opf
                JOIN foods f ON opf.food_id = f.id
                JOIN orders o ON opf.order_id = o.id
                WHERE o.status = 'delivered'
                  AND o.is_active = 1
                  AND o.order_cancelled = 0
                  AND f.is_active = 1
                  AND opf.is_active = 1
                  AND o.order_date BETWEEN :startDate AND :endDate
            
                UNION ALL
            
                SELECT (b.price * opb.quantity) AS total
                FROM order_products_beverages opb
                JOIN beverages b ON opb.beverage_id = b.id
                JOIN orders o ON opb.order_id = o.id
                WHERE o.status = 'delivered'
                  AND o.is_active = 1
                  AND o.order_cancelled = 0
                  AND b.is_active = 1
                  AND opb.is_active = 1
                  AND o.order_date BETWEEN :startDate AND :endDate
            ) AS combined_totals
            """, nativeQuery = true)
    BigDecimal turnoverOfTheCompanyInGivenPeriod(@Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);

}

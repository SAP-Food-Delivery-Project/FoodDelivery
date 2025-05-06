package org.example.fooddelivery.repositories;

import org.example.fooddelivery.entities.Supplier;
import org.example.fooddelivery.entities.dtos.SupplierDtos.SupplierIncomeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Optional<Supplier> findSupplierByIdAndIsActiveTrue(Integer integer);

    List<Supplier> findSupplierByEmailAndIsActiveTrue(String email);

    List<Supplier> findAllByIsActiveTrue();

    @Query("""
                SELECT new org.example.fooddelivery.entities.dtos.SupplierDtos.SupplierIncomeDto(
                    s.user.email,
                    SUM(
                        COALESCE(f.price * opf.quantity, 0) +
                        COALESCE(b.price * opb.quantity, 0)
                    )
                )
                FROM Delivery d
                JOIN d.order o
                JOIN d.supplier s
                LEFT JOIN OrderProductsFoods opf ON opf.order.id = o.id AND opf.isActive = true
                LEFT JOIN Food f ON f.id = opf.food.id AND f.isActive = true
                LEFT JOIN OrderProductsBeverages opb ON opb.order.id = o.id AND opb.isActive = true
                LEFT JOIN Beverage b ON b.id = opb.beverage.id AND b.isActive = true
                WHERE d.isActive = true
                  AND o.status = 'delivered'
                  AND o.order_cancelled = false
                  AND o.order_date BETWEEN :startDate AND :endDate
                GROUP BY s.user.email
            """)
    List<SupplierIncomeDto> getSupplierIncomes(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT COUNT(d) FROM Delivery d " +
            "WHERE d.supplier.id = :supplierId " +
            "AND d.isActive = true " +
            "AND DATE(d.order.order_date) = :date")
    int countDeliveriesBySupplierAndDate(@Param("supplierId") int supplierId,
                                          @Param("date") LocalDate date);



}

package org.example.fooddelivery.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "supplier_bonuses")
@Entity
public class SupplierBonus extends BaseEntity{

    @Column(name = "bonus", nullable = false)
    private BigDecimal bonus;

    @Column(name = "current_day", nullable = false)
    private LocalDate currentDay;

    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;


    @Builder
    public SupplierBonus(BigDecimal bonus,
                         Supplier supplier){
        this.bonus = bonus;
        this.supplier = supplier;
        this.currentDay = LocalDate.now();
        this.isActive = true;
    }
}

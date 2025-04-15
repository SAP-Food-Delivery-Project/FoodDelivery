package org.example.fooddelivery.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private LocalDate current_day;

    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

}

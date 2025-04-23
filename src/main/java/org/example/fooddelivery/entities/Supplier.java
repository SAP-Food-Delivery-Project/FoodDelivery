package org.example.fooddelivery.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "suppliers")
@Entity
public class Supplier extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "telephone_number",unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "rate", nullable = false)
    private float rate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "supplier")
    private Set<SupplierBonus> supplierBonuses;

    @OneToMany(mappedBy = "supplier")
    private Set<Delivery> delivery;

    @Builder
    public Supplier(String firstName,
                    String lastName,
                    String phoneNumber,
                    float rate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.rate = rate;
        this.isActive = true;
    }
}

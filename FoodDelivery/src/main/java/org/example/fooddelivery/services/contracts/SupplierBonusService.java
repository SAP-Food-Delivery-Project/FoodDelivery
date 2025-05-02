package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.SupplierBonus;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.CreateSupplierBonusDto;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.SupplierBonusDto;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.SupplierBonusWithIdDto;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.UpdateSupplierBonusDto;
import org.example.fooddelivery.entities.dtos.SupplierDtos.CreateSupplierDto;

import java.util.List;

public interface SupplierBonusService {

    SupplierBonusDto getSingleSupplierBonus(int id);

    SupplierBonus getSingleSupplierBonusEntity(int id);

    List<SupplierBonusDto> getAllSupplierBonuses();

    SupplierBonusWithIdDto createSupplierBonus(CreateSupplierBonusDto createSupplierBonusDto);

    SupplierBonusDto updateSupplierBonus(UpdateSupplierBonusDto updateSupplierBonusDto, int id);

    void deactivateSupplierBonus(int id);
}

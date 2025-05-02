package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.SupplierBonus;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.CreateSupplierBonusDto;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.SupplierBonusDto;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.SupplierBonusWithIdDto;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.UpdateSupplierBonusDto;
import org.example.fooddelivery.entities.dtos.SupplierDtos.CreateSupplierDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.repositories.SupplierBonusRepository;
import org.example.fooddelivery.services.contracts.SupplierBonusService;
import org.example.fooddelivery.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierBonusServiceImpl implements SupplierBonusService {

    private final SupplierBonusRepository supplierBonusRepository;
    private final MapperUtil mapperUtil;


    @Override
    public SupplierBonusDto getSingleSupplierBonus(int id) {
        return mapperUtil.getModelMapper()
                .map(supplierBonusRepository.findSupplierBonusByIdAndIsActiveTrue(id),
                        SupplierBonusDto.class);
    }

    @Override
    public SupplierBonus getSingleSupplierBonusEntity(int id) {
        return supplierBonusRepository.findSupplierBonusByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Bonus with id + " + id + "not found"));
    }

    @Override
    public List<SupplierBonusDto> getAllSupplierBonuses() {
        return mapperUtil.mapList(supplierBonusRepository.findAllByIsActiveTrue(),
                                SupplierBonusDto.class);
    }

    @Override
    public SupplierBonusWithIdDto createSupplierBonus(CreateSupplierBonusDto createSupplierBonusDto) {

        SupplierBonus supplierBonus = SupplierBonus.builder()
                .bonus(createSupplierBonusDto.getBonus())
                .supplier(createSupplierBonusDto.getSupplier()).build();

        supplierBonusRepository.save(supplierBonus);

        return mapperUtil.getModelMapper().map(supplierBonus, SupplierBonusWithIdDto.class);
    }

    @Override
    public SupplierBonusDto updateSupplierBonus(UpdateSupplierBonusDto updateSupplierBonusDto, int id) {

        SupplierBonus supplierBonus = supplierBonusRepository.findSupplierBonusByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Bonus", id));

        supplierBonus.setBonus(updateSupplierBonusDto.getBonus());

        supplierBonusRepository.save(supplierBonus);

        return mapperUtil.getModelMapper().map(supplierBonus, SupplierBonusDto.class);
    }

    @Override
    public void deactivateSupplierBonus(int id) {

        SupplierBonus supplierBonus = supplierBonusRepository.findSupplierBonusByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Bonus", id));

        supplierBonus.setActive(false);
        supplierBonusRepository.save(supplierBonus);
    }
}

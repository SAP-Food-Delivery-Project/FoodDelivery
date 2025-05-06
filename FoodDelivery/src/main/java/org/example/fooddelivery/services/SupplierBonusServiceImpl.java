package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.Supplier;
import org.example.fooddelivery.entities.SupplierBonus;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.CreateSupplierBonusDto;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.SupplierBonusDto;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.SupplierBonusWithIdDto;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.UpdateSupplierBonusDto;
import org.example.fooddelivery.entities.dtos.SupplierDtos.CreateSupplierDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.exceptions.OperationNotSupportedException;
import org.example.fooddelivery.repositories.SupplierBonusRepository;
import org.example.fooddelivery.repositories.SupplierRepository;
import org.example.fooddelivery.services.contracts.SupplierBonusService;
import org.example.fooddelivery.services.contracts.SupplierService;
import org.example.fooddelivery.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierBonusServiceImpl implements SupplierBonusService {

    private final SupplierBonusRepository supplierBonusRepository;
    private final SupplierRepository supplierRepository;
    private final SupplierService supplierService;
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

        SupplierBonus supplierBonusForThisDay = supplierBonusRepository.findSupplierBonusByCurrentDayAndIsActiveTrue(
                createSupplierBonusDto.getCurrentDay());

        if(supplierBonusForThisDay != null){
            throw new OperationNotSupportedException("Supplier bonus already exists for today");
        }

        int countOfDeliveriesForToday = supplierRepository
                .countDeliveriesBySupplierAndDate(createSupplierBonusDto.getSupplierId(),
                createSupplierBonusDto.getCurrentDay());
        Supplier supplier = supplierService.getSingleSupplierEntity(createSupplierBonusDto.getSupplierId());

        if(countOfDeliveriesForToday >= 5){
            SupplierBonus supplierBonus = SupplierBonus.builder()
                    .bonus(createSupplierBonusDto.getBonus())
                    .supplier(supplier).build();

            supplierBonusRepository.save(supplierBonus);

            return mapperUtil.getModelMapper().map(supplierBonus, SupplierBonusWithIdDto.class);
        }else {
            throw new OperationNotSupportedException("User has not reached 5 deliveries");
        }

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

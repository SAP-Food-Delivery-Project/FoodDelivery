package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.Supplier;
import org.example.fooddelivery.entities.SupplierBonus;
import org.example.fooddelivery.entities.dtos.SupplierDtos.CreateSupplierDto;
import org.example.fooddelivery.entities.dtos.SupplierDtos.SupplierDto;
import org.example.fooddelivery.entities.dtos.SupplierDtos.SupplierDtoWithId;
import org.example.fooddelivery.entities.dtos.SupplierDtos.UpdateSupplierDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.repositories.SupplierRepository;
import org.example.fooddelivery.services.contracts.SupplierService;
import org.example.fooddelivery.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final MapperUtil mapperUtil;

    @Override
    public SupplierDto getSingleSupplier(int id) {
        return mapperUtil.getModelMapper()
                        .map(supplierRepository.findSupplierByIdAndIsActiveTrue(id), SupplierDto.class);
    }

    @Override
    public Supplier getSingleSupplierEntity(int id) {
        return supplierRepository.findSupplierByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Supplier with id " + id + " not found"));
    }

    @Override
    public List<SupplierDto> getAllSupplierByName(String name) {
        return mapperUtil
                .mapList(supplierRepository.findSupplierByNameAndIsActiveTrue(name), SupplierDto.class);
    }

    @Override
    public List<SupplierDto> getAllSupplier() {
        return mapperUtil
                .mapList(supplierRepository.findAllAndIsActiveTrue(), SupplierDto.class);
    }

    @Override
    public SupplierDtoWithId createSupplier(CreateSupplierDto createSupplierDto) {

        Supplier supplier = Supplier.builder()
                .firstName(createSupplierDto.getFirstName())
                .lastName(createSupplierDto.getLastName())
                .phoneNumber(createSupplierDto.getPhoneNumber())
                .build();

        supplierRepository.save(supplier);

        return mapperUtil.getModelMapper().map(supplier, SupplierDtoWithId.class);
    }

    @Override
    public SupplierDto updateSupplier(UpdateSupplierDto updateSupplierDto, int id) {

        Supplier supplier = supplierRepository.findSupplierByIdAndIsActiveTrue(id)
                .orElseThrow(()-> new EntityNotFoundException("Supplier", id));

        supplier.setFirstName(updateSupplierDto.getFirstName());
        supplier.setLastName(updateSupplierDto.getLastName());
        supplier.setPhoneNumber(updateSupplierDto.getPhoneNumber());

        supplierRepository.save(supplier);

        return mapperUtil.getModelMapper().map(supplier, SupplierDto.class);
    }

    @Override
    public void deactivateSupplier(int id) {

        Supplier supplier = supplierRepository.findSupplierByIdAndIsActiveTrue(id)
                .orElseThrow(()-> new EntityNotFoundException("Supplier", id));

        supplier.setActive(false);
        supplierRepository.save(supplier);
    }
}

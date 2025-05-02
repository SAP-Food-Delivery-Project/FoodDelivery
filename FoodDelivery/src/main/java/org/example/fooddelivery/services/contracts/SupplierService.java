package org.example.fooddelivery.services.contracts;


import org.example.fooddelivery.entities.Supplier;
import org.example.fooddelivery.entities.User;
import org.example.fooddelivery.entities.dtos.SupplierDtos.CreateSupplierDto;
import org.example.fooddelivery.entities.dtos.SupplierDtos.SupplierDto;
import org.example.fooddelivery.entities.dtos.SupplierDtos.SupplierDtoWithId;
import org.example.fooddelivery.entities.dtos.SupplierDtos.UpdateSupplierDto;

import java.util.List;

public interface SupplierService {

    SupplierDto getSingleSupplier(int id);

    Supplier getSingleSupplierEntity(int id);

    List<SupplierDto> getAllSupplierByName(String name);

    List<SupplierDto> getAllSupplier();

    SupplierDtoWithId createSupplier(CreateSupplierDto createSupplierDto);

    SupplierDto updateSupplier(UpdateSupplierDto updateSupplierDto, int id);

    void deactivateSupplier(int id);
}

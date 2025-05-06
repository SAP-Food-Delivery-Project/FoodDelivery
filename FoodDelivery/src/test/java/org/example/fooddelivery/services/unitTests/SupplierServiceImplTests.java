package org.example.fooddelivery.services.unitTests;

import org.example.fooddelivery.entities.Supplier;
import org.example.fooddelivery.entities.dtos.SupplierDtos.SupplierDto;
import org.example.fooddelivery.entities.dtos.SupplierDtos.UpdateSupplierDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.repositories.SupplierRepository;
import org.example.fooddelivery.services.SupplierServiceImpl;
import org.example.fooddelivery.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Configuration
public class SupplierServiceImplTests {

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private MapperUtil mapperUtil;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @BeforeEach
    void setup() {
        when(mapperUtil.getModelMapper()).thenReturn(modelMapper);
    }

    @Test
    void testGetSingleSupplier() {
        int id = 1;
        Supplier supplier = new Supplier();
        SupplierDto expectedDto = new SupplierDto();

        when(supplierRepository.findSupplierByIdAndIsActiveTrue(id)).thenReturn(Optional.of(supplier));
        when(modelMapper.map(supplier, SupplierDto.class)).thenReturn(expectedDto);

        SupplierDto result = supplierService.getSingleSupplier(id);

        assertEquals(expectedDto, result);
    }

    @Test
    void testGetSingleSupplierEntity() {
        int id = 1;
        Supplier supplier = new Supplier();

        when(supplierRepository.findSupplierByIdAndIsActiveTrue(id)).thenReturn(Optional.of(supplier));

        Supplier result = supplierService.getSingleSupplierEntity(id);

        assertEquals(supplier, result);
    }

    @Test
    void testGetSingleSupplierEntity_NotFound() {
        when(supplierRepository.findSupplierByIdAndIsActiveTrue(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> supplierService.getSingleSupplierEntity(99));
    }

    @Test
    void testGetAllSupplierByName() {
        String name = "test@example.com";
        List<Supplier> suppliers = List.of(new Supplier(), new Supplier());
        List<SupplierDto> expectedDtos = List.of(new SupplierDto(), new SupplierDto());

        when(supplierRepository.findSupplierByEmailAndIsActiveTrue(name)).thenReturn(suppliers);
        when(mapperUtil.mapList(suppliers, SupplierDto.class)).thenReturn(expectedDtos);

        List<SupplierDto> result = supplierService.getAllSupplierByName(name);

        assertEquals(expectedDtos, result);
    }

    @Test
    void testGetAllSupplier() {
        List<Supplier> suppliers = List.of(new Supplier(), new Supplier());
        List<SupplierDto> expectedDtos = List.of(new SupplierDto(), new SupplierDto());

        when(supplierRepository.findAllByIsActiveTrue()).thenReturn(suppliers);
        when(mapperUtil.mapList(suppliers, SupplierDto.class)).thenReturn(expectedDtos);

        List<SupplierDto> result = supplierService.getAllSupplier();

        assertEquals(expectedDtos, result);
    }

    @Test
    void testUpdateSupplier() {
        int id = 1;
        UpdateSupplierDto updateDto = new UpdateSupplierDto();
        updateDto.setFirstName("Updated");
        updateDto.setLastName("Name");
        updateDto.setPhoneNumber("0888999888");

        Supplier existingSupplier = new Supplier();
        SupplierDto expectedDto = new SupplierDto();

        when(supplierRepository.findSupplierByIdAndIsActiveTrue(id)).thenReturn(Optional.of(existingSupplier));
        when(modelMapper.map(existingSupplier, SupplierDto.class)).thenReturn(expectedDto);

        SupplierDto result = supplierService.updateSupplier(updateDto, id);

        assertEquals(expectedDto, result);
        verify(supplierRepository).save(existingSupplier);
        assertEquals("Updated", existingSupplier.getFirstName());
        assertEquals("Name", existingSupplier.getLastName());
        assertEquals("0888999888", existingSupplier.getPhoneNumber());
    }

    @Test
    void testUpdateSupplier_NotFound() {
        int id = 999;
        UpdateSupplierDto updateDto = new UpdateSupplierDto();

        when(supplierRepository.findSupplierByIdAndIsActiveTrue(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> supplierService.updateSupplier(updateDto, id));
    }

    @Test
    void testDeactivateSupplier() {
        int id = 1;
        Supplier supplier = new Supplier();
        supplier.setActive(true);

        when(supplierRepository.findSupplierByIdAndIsActiveTrue(id)).thenReturn(Optional.of(supplier));

        supplierService.deactivateSupplier(id);

        assertFalse(supplier.isActive());
        verify(supplierRepository).save(supplier);
    }

    @Test
    void testDeactivateSupplier_NotFound() {
        int id = 999;

        when(supplierRepository.findSupplierByIdAndIsActiveTrue(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> supplierService.deactivateSupplier(id));
    }
}
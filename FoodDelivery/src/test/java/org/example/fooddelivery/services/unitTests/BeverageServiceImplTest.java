package org.example.fooddelivery.services.unitTests;

import org.example.fooddelivery.entities.Beverage;
import org.example.fooddelivery.entities.BeverageType;
import org.example.fooddelivery.entities.dtos.BeverageDtos.*;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.repositories.BeverageRepository;
import org.example.fooddelivery.services.BeverageServiceImpl;
import org.example.fooddelivery.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BeverageServiceImplTest {

    private BeverageRepository beverageRepository;
    private MapperUtil mapperUtil;
    private BeverageServiceImpl beverageService;

    private final ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void setUp() {
        beverageRepository = mock(BeverageRepository.class);
        mapperUtil = mock(MapperUtil.class);
        beverageService = new BeverageServiceImpl(beverageRepository, mapperUtil);

        when(mapperUtil.getModelMapper()).thenReturn(modelMapper);
    }

    @Test
    void testGetSingleBeverageById_returnsDto() {
        Beverage beverage = new Beverage();
        beverage.setName("Cola");

        when(beverageRepository.findBeverageByIdAndIsActiveTrue(1))
                .thenReturn(Optional.of(beverage));

        BeverageDto dto = beverageService.getSingleBeverageById(1);
        assertEquals("Cola", dto.getName());
    }

    @Test
    void testGetSingleBeverageEntityById_throwsIfNotFound() {
        when(beverageRepository.findBeverageByIdAndIsActiveTrue(5))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            beverageService.getSingleBeverageEntityById(5);
        });
    }

    @Test
    void testCreateBeverage_savesCorrectly() {
        CreateBeverageDto dto = new CreateBeverageDto();
        dto.setName("Sprite");
        dto.setPrice(new BigDecimal(2.5));
        dto.setLiters(1.5f);
        dto.setBeverageType(BeverageType.carbonated);

        Beverage saved = Beverage.builder()
                .name("Sprite")
                .price(new BigDecimal(2.5))
                .liters(1.5f)
                .beverageType(BeverageType.carbonated)
                .build();

        when(beverageRepository.save(any(Beverage.class))).thenReturn(saved);

        BeverageDtoWithId result = beverageService.createBeverage(dto);
        assertEquals(1 , result.getId());
    }

    @Test
    void testUpdateBeverage_updatesFields() {
        Beverage existing = new Beverage();
        existing.setName("Old");
        existing.setLiters(0.5);
        existing.setPrice(new BigDecimal(1.5));

        UpdateBeverageDto updateDto = new UpdateBeverageDto();
        updateDto.setName("New");
        updateDto.setLiters(1.0f);
        updateDto.setPrice(new BigDecimal(2.5));

        when(beverageRepository.findBeverageByIdAndIsActiveTrue(2))
                .thenReturn(Optional.of(existing));

        BeverageDto updated = beverageService.updateBeverage(updateDto, 2);

        assertEquals("New", updated.getName());
        assertEquals(1.0, updated.getLiters());
        assertEquals(2.0, updated.getPrice());
    }

    @Test
    void testDeactivateBeverage_setsActiveFalse() {
        Beverage beverage = new Beverage();
        beverage.setActive(true);

        when(beverageRepository.findBeverageByIdAndIsActiveTrue(3))
                .thenReturn(Optional.of(beverage));

        beverageService.deactivateBeverage(3);

        assertFalse(beverage.isActive());
        verify(beverageRepository).save(beverage);
    }
}
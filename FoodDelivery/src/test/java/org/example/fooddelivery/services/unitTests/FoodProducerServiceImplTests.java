package org.example.fooddelivery.services.unitTests;

import org.example.fooddelivery.entities.FoodProducer;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.CreateFoodProducerDto;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDto;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDtoWithId;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.UpdateFoodProducerDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.repositories.FoodProducerRepository;
import org.example.fooddelivery.services.FoodProducerServiceImpl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Configuration
public class FoodProducerServiceImplTests {

    @Mock
    private FoodProducerRepository foodProducerRepository;

    @Mock
    private MapperUtil mapperUtil;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FoodProducerServiceImpl foodProducerService;

    @BeforeEach
    void setUp() {
        when(mapperUtil.getModelMapper()).thenReturn(modelMapper);
    }


    @Test
    void testGetSingleFoodProducerEntity_Found() {
        FoodProducer producer = new FoodProducer();
        when(foodProducerRepository.findFoodProducerByIdAndIsActiveTrue(1)).thenReturn(Optional.of(producer));

        FoodProducer result = foodProducerService.getSingleFoodProducerEntity(1);
        assertEquals(producer, result);
    }

    @Test
    void testGetSingleFoodProducerEntity_NotFound() {
        when(foodProducerRepository.findFoodProducerByIdAndIsActiveTrue(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> foodProducerService.getSingleFoodProducerEntity(1));
    }


    @Test
    void testGetAllFoodProducers() {
        List<FoodProducer> producers = List.of(new FoodProducer(), new FoodProducer());
        List<FoodProducerDto> dtos = List.of(new FoodProducerDto(), new FoodProducerDto());

        when(foodProducerRepository.findAllFoodProducerByIsActiveTrue()).thenReturn(producers);
        when(mapperUtil.mapList(producers, FoodProducerDto.class)).thenReturn(dtos);

        List<FoodProducerDto> result = foodProducerService.getAllFoodProducers();
        assertEquals(dtos, result);
    }


    @Test
    void testUpdateFoodProducer() {
        UpdateFoodProducerDto updateDto = new UpdateFoodProducerDto("NewName", "654321", "NewAddress", "NewCity");
        FoodProducer producer = new FoodProducer();
        FoodProducerDto dto = new FoodProducerDto();

        when(foodProducerRepository.findFoodProducerByIdAndIsActiveTrue(1)).thenReturn(Optional.of(producer));
        when(foodProducerRepository.save(producer)).thenReturn(producer);
        when(modelMapper.map(producer, FoodProducerDto.class)).thenReturn(dto);

        FoodProducerDto result = foodProducerService.updateFoodProducer(updateDto, 1);
        assertEquals(dto, result);
    }

    @Test
    void testDeactivateFoodProducer() {
        FoodProducer producer = new FoodProducer();
        producer.setActive(true);

        when(foodProducerRepository.findFoodProducerByIdAndIsActiveTrue(1)).thenReturn(Optional.of(producer));

        foodProducerService.deactivateFoodProducer(1);
        assertFalse(producer.isActive());
        verify(foodProducerRepository).save(producer);
    }


    @Test
    void testGetSingleFoodProducerById() {
        int id = 1;
        FoodProducer foodProducer = new FoodProducer(); // или можеш да му зададеш стойности
        FoodProducerDto expectedDto = new FoodProducerDto();

        when(foodProducerRepository.findFoodProducerByIdAndIsActiveTrue(id)).thenReturn(Optional.of(foodProducer));
        when(modelMapper.map(foodProducer, FoodProducerDto.class)).thenReturn(expectedDto);

        FoodProducerDto result = foodProducerService.getSingleFoodProducer(id);

        assertEquals(expectedDto, result);
        verify(foodProducerRepository).findFoodProducerByIdAndIsActiveTrue(id);
        verify(modelMapper).map(foodProducer, FoodProducerDto.class);
    }

    @Test
    void testGetSingleFoodProducerByName() {
        String name = "TestName";
        FoodProducer foodProducer = new FoodProducer();
        FoodProducerDto expectedDto = new FoodProducerDto();

        when(foodProducerRepository.findFoodProducerByNameAndIsActiveTrue(name)).thenReturn(Optional.of(foodProducer));
        when(modelMapper.map(foodProducer, FoodProducerDto.class)).thenReturn(expectedDto);

        FoodProducerDto result = foodProducerService.getSingleFoodProducer(name);

        assertEquals(expectedDto, result);
        verify(foodProducerRepository).findFoodProducerByNameAndIsActiveTrue(name);
        verify(modelMapper).map(foodProducer, FoodProducerDto.class);
    }

    @Test
    void testGetSingleFoodProducerByCityAndAddress() {
        String city = "Sofia";
        String address = "Test Street";
        FoodProducer foodProducer = new FoodProducer();
        FoodProducerDto expectedDto = new FoodProducerDto();

        when(foodProducerRepository.findFoodProducerByCityAndAddressAndIsActiveTrue(city, address))
                .thenReturn(Optional.of(foodProducer));
        when(modelMapper.map(foodProducer, FoodProducerDto.class)).thenReturn(expectedDto);

        FoodProducerDto result = foodProducerService.getSingleFoodProducer(city, address);

        assertEquals(expectedDto, result);
        verify(foodProducerRepository).findFoodProducerByCityAndAddressAndIsActiveTrue(city, address);
        verify(modelMapper).map(foodProducer, FoodProducerDto.class);
    }

}

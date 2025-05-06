package org.example.fooddelivery.services.unitTests;

import org.example.fooddelivery.entities.Employee;
import org.example.fooddelivery.entities.FoodProducer;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.CreateEmployeeDto;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.EmployeeDto;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.EmployeeDtoWithId;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.UpdateEmployeeDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.repositories.EmployeeRepository;
import org.example.fooddelivery.services.EmployeeServiceImpl;
import org.example.fooddelivery.services.contracts.FoodProducerService;
import org.example.fooddelivery.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Configuration
public class EmployeeServiceImplTests {


    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private MapperUtil mapperUtil;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private FoodProducerService foodProducerService;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setup() {
        when(mapperUtil.getModelMapper()).thenReturn(modelMapper);
    }

    @Test
    void testGetSingleEmployee() {
        int id = 1;
        Employee employee = new Employee();
        EmployeeDto expectedDto = new EmployeeDto();

        when(employeeRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.of(employee));
        when(modelMapper.map(employee, EmployeeDto.class)).thenReturn(expectedDto);

        EmployeeDto result = employeeService.getSingleEmployee(id);

        assertEquals(expectedDto, result);
    }

    @Test
    void testGetSingleEmployeeThrowsEntityNotFoundException() {
        int id = 1;
        Employee employee = new Employee();
        EmployeeDto expectedDto = new EmployeeDto();

        when(employeeRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.of(employee));
        when(employeeRepository.findById(2)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> employeeService.getSingleEmployee(2));

    }

    @Test
    void testGetSingleEmployeeEntity() {
        int id = 1;
        Employee employee = new Employee();

        when(employeeRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getSingleEmployeeEntity(id);

        assertEquals(employee, result);
    }

    @Test
    void testGetSingleEmployeeEntity_NotFound() {
        when(employeeRepository.findByIdAndIsActiveTrue(42)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> employeeService.getSingleEmployeeEntity(42));
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = List.of(new Employee(), new Employee());
        List<EmployeeDto> expectedDtos = List.of(new EmployeeDto(), new EmployeeDto());

        when(employeeRepository.findAllByIsActiveTrue()).thenReturn(employees);
        when(mapperUtil.mapList(employees, EmployeeDto.class)).thenReturn(expectedDtos);

        List<EmployeeDto> result = employeeService.getAllEmployees();

        assertEquals(expectedDtos, result);
    }

    @Test
    void testCreateEmployee() {
        CreateEmployeeDto createDto = new CreateEmployeeDto();
        createDto.setFirstName("John");
        createDto.setLastName("Doe");
        createDto.setEmail("john@example.com");
        createDto.setPassword("secret");
        createDto.setPhoneNumber("0888123456");
        createDto.setBirthDate(LocalDate.of(1990, 1, 1));
        createDto.setFoodProducerId(100);

        FoodProducer foodProducer = new FoodProducer();

        when(foodProducerService.getSingleFoodProducerEntity(100)).thenReturn(foodProducer);

        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("secret")
                .phoneNumber("0888123456")
                .birthDate(LocalDate.of(1990, 1, 1))
                .foodProducer(foodProducer)
                .build();

        EmployeeDtoWithId expectedDto = new EmployeeDtoWithId();

        when(modelMapper.map(any(Employee.class), eq(EmployeeDtoWithId.class))).thenReturn(expectedDto);

        EmployeeDtoWithId result = employeeService.createEmployee(createDto);

        assertEquals(expectedDto, result);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void testUpdateEmployee() {
        int id = 1;
        UpdateEmployeeDto updateDto = new UpdateEmployeeDto();
        updateDto.setEmail("new@email.com");
        updateDto.setPassword("newpass");
        updateDto.setPhoneNumber("0888999999");

        Employee employee = new Employee();
        EmployeeDto expectedDto = new EmployeeDto();

        when(employeeRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.of(employee));
        when(modelMapper.map(employee, EmployeeDto.class)).thenReturn(expectedDto);

        EmployeeDto result = employeeService.updateEmployee(updateDto, id);

        assertEquals(expectedDto, result);
        assertEquals("new@email.com", employee.getEmail());
        assertEquals("newpass", employee.getPassword());
        assertEquals("0888999999", employee.getPhoneNumber());
        verify(employeeRepository).save(employee);
    }

    @Test
    void testUpdateEmployee_NotFound() {
        int id = 123;
        UpdateEmployeeDto updateDto = new UpdateEmployeeDto();

        when(employeeRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> employeeService.updateEmployee(updateDto, id));
    }

    @Test
    void testDeactivateEmployee() {
        int id = 1;
        Employee employee = new Employee();
        employee.setActive(true);

        when(employeeRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.of(employee));

        employeeService.deactivateEmployee(id);

        assertFalse(employee.isActive());
        verify(employeeRepository).save(employee);
    }

    @Test
    void testDeactivateEmployee_NotFound() {
        int id = 42;
        when(employeeRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> employeeService.deactivateEmployee(id));
    }



}

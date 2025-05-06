package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.Employee;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.CreateEmployeeDto;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.EmployeeDto;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.EmployeeDtoWithId;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.UpdateEmployeeDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.repositories.EmployeeRepository;
import org.example.fooddelivery.services.contracts.EmployeeService;
import org.example.fooddelivery.services.contracts.FoodProducerService;
import org.example.fooddelivery.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MapperUtil mapperUtil;
    private final FoodProducerService foodProducerService;

    @Override
    public EmployeeDto getSingleEmployee(int id) {

        Employee employee = employeeRepository.findByIdAndIsActiveTrue(id).orElseThrow(
                () -> new EntityNotFoundException("Employee with id " + id + " not found"));

        return mapperUtil.getModelMapper()
                .map(employee, EmployeeDto.class);
    }

    @Override
    public Employee getSingleEmployeeEntity(int id) {
        return employeeRepository.findByIdAndIsActiveTrue(id).orElseThrow(
                () -> new EntityNotFoundException("Employee with id " + id + " not found")
        );
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return mapperUtil.mapList(employeeRepository.findAllByIsActiveTrue(), EmployeeDto.class);
    }

    @Override
    public EmployeeDtoWithId createEmployee(CreateEmployeeDto createEmployeeDto) {

        Employee employee = Employee.builder()
                .firstName(createEmployeeDto.getFirstName())
                .lastName(createEmployeeDto.getLastName())
                .email(createEmployeeDto.getEmail())
                .password(createEmployeeDto.getPassword())
                .phoneNumber(createEmployeeDto.getPhoneNumber())
                .birthDate(createEmployeeDto.getBirthDate())
                .foodProducer(foodProducerService.getSingleFoodProducerEntity(createEmployeeDto.getFoodProducerId()))
                .build();

        employeeRepository.save(employee);

        return mapperUtil.getModelMapper().map(employee, EmployeeDtoWithId.class);
    }

    @Override
    public EmployeeDto updateEmployee(UpdateEmployeeDto updateEmployeeDto, int id) {

        Employee employee = employeeRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee", id));


        employee.setEmail(updateEmployeeDto.getEmail());
        employee.setPassword(updateEmployeeDto.getPassword());
        employee.setPhoneNumber(updateEmployeeDto.getPhoneNumber());

        employeeRepository.save(employee);

        return mapperUtil.getModelMapper().map(employee, EmployeeDto.class);
    }

    @Override
    public void deactivateEmployee(int id) {

        Employee employee = employeeRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee", id));

        employee.setActive(false);
        employeeRepository.save(employee);
    }

}

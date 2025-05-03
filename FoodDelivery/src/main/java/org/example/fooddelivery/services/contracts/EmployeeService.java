package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.Employee;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.CreateEmployeeDto;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.EmployeeDto;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.EmployeeDtoWithId;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.UpdateEmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getSingleEmployee(int id);

    Employee getSingleEmployeeEntity(int id);

    List<EmployeeDto> getAllEmployees();

    EmployeeDtoWithId createEmployee(CreateEmployeeDto createEmployeeDto);

    EmployeeDto updateEmployee(UpdateEmployeeDto updateEmployeeDto, int id);

    void deactivateEmployee(int id);
}

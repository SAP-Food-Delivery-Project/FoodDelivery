package org.example.fooddelivery.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.CreateEmployeeDto;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.EmployeeDto;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.EmployeeDtoWithId;
import org.example.fooddelivery.entities.dtos.EmployeeDtos.UpdateEmployeeDto;
import org.example.fooddelivery.services.contracts.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable int id) {
        return employeeService.getSingleEmployee(id);
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDtoWithId createEmployee(@RequestBody CreateEmployeeDto createEmployeeDto) {
        return employeeService.createEmployee(createEmployeeDto);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable int id, @RequestBody UpdateEmployeeDto updateEmployeeDto) {
        return employeeService.updateEmployee(updateEmployeeDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateEmployee(@Valid @PathVariable int id){
        employeeService.deactivateEmployee(id);
        return ResponseEntity.ok("Employee deactivated successfully!");
    }
}

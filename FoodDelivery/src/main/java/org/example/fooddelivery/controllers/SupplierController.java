package org.example.fooddelivery.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.dtos.SupplierDtos.*;
import org.example.fooddelivery.services.contracts.SupplierOperationsService;
import org.example.fooddelivery.services.contracts.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierOperationsService supplierOperationsService;

    @GetMapping("/{id}")
    public SupplierDto getSupplierById(int id){
        return supplierService.getSingleSupplier(id);
    }

    @GetMapping("/name/{name}")
    public List<SupplierDto> getSuppliersByName(String name){
        return supplierService.getAllSupplierByName(name);
    }

    @GetMapping
    public List<SupplierDto> getAllSuppliers(){
        return supplierService.getAllSupplier();
    }

    @PostMapping
    public SupplierDtoWithId createSupplier(@RequestBody CreateSupplierDto createSupplierDto){
        return supplierService.createSupplier(createSupplierDto);
    }

    @PutMapping("/{id}")
    public SupplierDto updateSupplier(@PathVariable int id, @RequestBody UpdateSupplierDto updateSupplierDto){
        return supplierService.updateSupplier(updateSupplierDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateSupplier(@Valid @PathVariable int id){
        supplierService.deactivateSupplier(id);
        return ResponseEntity.ok("Supplier deactivated successfully!");
    }

    @GetMapping("/incomes")
    public List<SupplierIncomeDto> getAllSupplierIncome(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate){
        return supplierOperationsService.getSupplierIncomeDto(startDate, endDate);
    }
}

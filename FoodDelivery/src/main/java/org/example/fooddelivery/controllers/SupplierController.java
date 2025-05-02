package org.example.fooddelivery.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.dtos.SupplierDtos.CreateSupplierDto;
import org.example.fooddelivery.entities.dtos.SupplierDtos.SupplierDto;
import org.example.fooddelivery.entities.dtos.SupplierDtos.SupplierDtoWithId;
import org.example.fooddelivery.entities.dtos.SupplierDtos.UpdateSupplierDto;
import org.example.fooddelivery.services.contracts.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

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
}

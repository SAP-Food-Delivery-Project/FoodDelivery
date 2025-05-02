package org.example.fooddelivery.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.CreateSupplierBonusDto;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.SupplierBonusDto;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.SupplierBonusWithIdDto;
import org.example.fooddelivery.entities.dtos.SupplierBonusDtos.UpdateSupplierBonusDto;
import org.example.fooddelivery.entities.dtos.SupplierDtos.UpdateSupplierDto;
import org.example.fooddelivery.services.contracts.SupplierBonusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/supplier/bonuses")
public class SupplierBonusController {

    private final SupplierBonusService supplierBonusService;

    @GetMapping("/{id}")
    public SupplierBonusDto getSupplierBonusById(@PathVariable int id){
        return supplierBonusService.getSingleSupplierBonus(id);
    }

    @GetMapping
    public List<SupplierBonusDto> getAllSupplierBonuses(){
        return supplierBonusService.getAllSupplierBonuses();
    }

    @PostMapping
    public SupplierBonusWithIdDto createSupplierBonus(@RequestBody CreateSupplierBonusDto createSupplierBonusDto){
        return supplierBonusService.createSupplierBonus(createSupplierBonusDto);
    }

    @PutMapping("/{id}")
    public SupplierBonusDto updateSupplierBonus
            (@PathVariable int id, @RequestBody UpdateSupplierBonusDto updateSupplierBonusDto){
        return supplierBonusService.updateSupplierBonus(updateSupplierBonusDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateSupplierBonus(@Valid @PathVariable int id){
         supplierBonusService.deactivateSupplierBonus(id);

         return ResponseEntity.ok("User deactivated successfully!");
    }
}

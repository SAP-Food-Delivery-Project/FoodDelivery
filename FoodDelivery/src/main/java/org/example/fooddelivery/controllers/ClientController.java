package org.example.fooddelivery.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.dtos.ClientDtos.ClientDto;
import org.example.fooddelivery.entities.dtos.ClientDtos.ClientDtoWithId;
import org.example.fooddelivery.entities.dtos.ClientDtos.CreateClientDto;
import org.example.fooddelivery.entities.dtos.ClientDtos.UpdateClientDto;
import org.example.fooddelivery.services.contracts.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable int id) {
        return clientService.getSingleClient(id);
    }

    @GetMapping
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping
    public ClientDtoWithId createClient(@RequestBody CreateClientDto createClientDto) {
        return clientService.createClient(createClientDto);
    }

    @PutMapping("/{id}")
    public ClientDto updateClient(@PathVariable int id, @RequestBody UpdateClientDto updateClientDto) {
        return clientService.updateClient(updateClientDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateClient(@Valid @PathVariable int id){
        clientService.deactivateClient(id);
        return ResponseEntity.ok("Client deactivated successfully!");
    }
}

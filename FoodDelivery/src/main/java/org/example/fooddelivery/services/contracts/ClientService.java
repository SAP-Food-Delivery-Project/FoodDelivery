package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.Client;
import org.example.fooddelivery.entities.dtos.ClientDtos.ClientDto;
import org.example.fooddelivery.entities.dtos.ClientDtos.ClientDtoWithId;
import org.example.fooddelivery.entities.dtos.ClientDtos.CreateClientDto;
import org.example.fooddelivery.entities.dtos.ClientDtos.UpdateClientDto;

import java.util.List;

public interface ClientService {
    ClientDto getSingleClient(int id);

    Client getSingleClientEntity(int id);

    List<ClientDto> getAllClients();

    ClientDtoWithId createClient(CreateClientDto createClientDto);

    ClientDto updateClient(UpdateClientDto updateClientDto, int id);

    void deactivateClient(int id);
}

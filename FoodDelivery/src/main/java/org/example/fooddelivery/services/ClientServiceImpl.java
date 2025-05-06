package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.Client;
import org.example.fooddelivery.entities.dtos.ClientDtos.ClientDto;
import org.example.fooddelivery.entities.dtos.ClientDtos.ClientDtoWithId;
import org.example.fooddelivery.entities.dtos.ClientDtos.CreateClientDto;
import org.example.fooddelivery.entities.dtos.ClientDtos.UpdateClientDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.repositories.ClientRepository;
import org.example.fooddelivery.services.contracts.ClientService;
import org.example.fooddelivery.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final MapperUtil mapperUtil;

    @Override
    public ClientDto getSingleClient(int id) {

        Client client = clientRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Client with id " + id + " not found"));

        return mapperUtil.getModelMapper()
                .map(client, ClientDto.class);
    }

    @Override
    public Client getSingleClientEntity(int id) {
        return clientRepository.findByIdAndIsActiveTrue(id).orElseThrow(
                () -> new EntityNotFoundException("Client with id " + id + " not found")
        );
    }

    @Override
    public List<ClientDto> getAllClients() {
        return mapperUtil.mapList(clientRepository.findAllByIsActiveTrue(), ClientDto.class);
    }

    @Override
    public ClientDtoWithId createClient(CreateClientDto createClientDto) {

        Client client = Client.builder()
                .firstName(createClientDto.getFirstName())
                .lastName(createClientDto.getLastName())
                .email(createClientDto.getEmail())
                .password(createClientDto.getPassword())
                .phoneNumber(createClientDto.getPhoneNumber())
                .birthDate(createClientDto.getBirthDate())
                .address(createClientDto.getAddress())
                .city(createClientDto.getCity())
                .balance(createClientDto.getBalance())
                .build();

        clientRepository.save(client);

        return mapperUtil.getModelMapper().map(client, ClientDtoWithId.class);
    }

    @Override
    public ClientDto updateClient(UpdateClientDto updateClientDto, int id) {

        Client client = clientRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Client", id));


        client.setEmail(updateClientDto.getEmail());
        client.setPassword(updateClientDto.getPassword());
        client.setPhoneNumber(updateClientDto.getPhoneNumber());
        client.setBalance(updateClientDto.getBalance());
        client.setAddress(updateClientDto.getAddress());
        client.setCity(updateClientDto.getCity());

        clientRepository.save(client);

        return mapperUtil.getModelMapper().map(client, ClientDto.class);
    }

    @Override
    public void deactivateClient(int id) {

        Client client = clientRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Client", id));

        client.setActive(false);
        clientRepository.save(client);
    }

}

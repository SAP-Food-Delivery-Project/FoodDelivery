package org.example.fooddelivery.services.unitTests;

import org.example.fooddelivery.entities.Client;
import org.example.fooddelivery.entities.dtos.ClientDtos.ClientDto;
import org.example.fooddelivery.entities.dtos.ClientDtos.ClientDtoWithId;
import org.example.fooddelivery.entities.dtos.ClientDtos.CreateClientDto;
import org.example.fooddelivery.entities.dtos.ClientDtos.UpdateClientDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.repositories.ClientRepository;
import org.example.fooddelivery.services.ClientServiceImpl;
import org.example.fooddelivery.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Configuration
public class ClientServiceImplTests {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private MapperUtil mapperUtil;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setup() {
        when(mapperUtil.getModelMapper()).thenReturn(modelMapper);
    }

    @Test
    void testGetSingleClient() {
        int id = 1;
        Client client = new Client();
        ClientDto expectedDto = new ClientDto();

        when(clientRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.of(client));
        when(modelMapper.map(client, ClientDto.class)).thenReturn(expectedDto);

        ClientDto result = clientService.getSingleClient(id);

        assertEquals(expectedDto, result);
    }

    @Test
    void testGetSingleClientEntity() {
        int id = 1;
        Client client = new Client();

        when(clientRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.of(client));

        Client result = clientService.getSingleClientEntity(id);

        assertEquals(client, result);
    }

    @Test
    void testGetSingleClientEntityNotFound() {
        int id = 999;

        when(clientRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> clientService.getSingleClientEntity(id));
    }

    @Test
    void testGetAllClients() {
        List<Client> clients = List.of(new Client(), new Client());
        List<ClientDto> expectedDtos = List.of(new ClientDto(), new ClientDto());

        when(clientRepository.findAllByIsActiveTrue()).thenReturn(clients);
        when(mapperUtil.mapList(clients, ClientDto.class)).thenReturn(expectedDtos);

        List<ClientDto> result = clientService.getAllClients();

        assertEquals(expectedDtos, result);
    }

    @Test
    void testUpdateClient() {
        int id = 1;
        UpdateClientDto updateDto = new UpdateClientDto();
        updateDto.setEmail("new@example.com");
        updateDto.setPassword("newpass");
        updateDto.setPhoneNumber("99999");
        updateDto.setBalance(BigDecimal.valueOf(50));
        updateDto.setAddress("New Address");
        updateDto.setCity("Plovdiv");

        Client existingClient = new Client();
        ClientDto expectedDto = new ClientDto();

        when(clientRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.of(existingClient));
        when(modelMapper.map(existingClient, ClientDto.class)).thenReturn(expectedDto);

        ClientDto result = clientService.updateClient(updateDto, id);

        assertEquals(expectedDto, result);
        verify(clientRepository).save(existingClient);
    }

    @Test
    void testDeactivateClient() {
        int id = 1;
        Client client = new Client();
        client.setActive(true);

        when(clientRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.of(client));

        clientService.deactivateClient(id);

        assertFalse(client.isActive());
        verify(clientRepository).save(client);
    }

}

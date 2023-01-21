package com.example.ex1.service;

import com.example.ex1.model.Client;
import com.example.ex1.repository.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    /**
     * @InjectMock
     * @Mock
     */
    @InjectMocks
    private ClientManagementService clientManagementService;

    @Mock
    private ClientRepository clientRepository;

    /**
     * @BeforeEach @AfterEach, @BeforeAll, @AfterAll
     */

    /**
     * getBy save delete
     */
    @Test
    @DisplayName("Save client successfully")
    void saveClientSuccessfully() {
        Client client = Client.builder()
                .firstName("abc")
                .lastName("def")
                .build();
        when(clientRepository.save(client)).thenReturn(client);
        Client result = clientManagementService.save(client);
        assertEquals(result.getFirstName(), client.getFirstName());
    }

    @Test
    @DisplayName("Get client by lastName")
    void getClientByLastName() {
        Client client1 = Client.builder()
                .firstName("123")
                .lastName("found")
                .build();
        when(clientRepository.save(client1)).thenReturn(client1);
        Client res1 = clientManagementService.save(client1);
        when(clientRepository.findAll()).thenReturn(List.of(client1));
        List<Client> result = clientManagementService.getBy(null, "found");
        assertEquals(result, List.of(res1));
    }
    @Test
    @DisplayName("Get all clients")
    void getAllClients() {
        Client client1 = Client.builder()
                .firstName("123")
                .lastName("found")
                .build();
        Client client2 = Client.builder()
                .firstName("456")
                .lastName("notfound")
                .build();
        when(clientRepository.save(client1)).thenReturn(client1);
        when(clientRepository.save(client2)).thenReturn(client2);
        Client res1 = clientManagementService.save(client1);
        Client res2 = clientManagementService.save(client2);
        when(clientRepository.findAll()).thenReturn(List.of(res1, res2));
        List<Client> result = clientManagementService.getBy(null, null);
        assertEquals(List.of(res1, res2), result);
    }
}

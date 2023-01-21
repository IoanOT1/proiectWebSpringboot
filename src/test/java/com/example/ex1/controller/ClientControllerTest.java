package com.example.ex1.controller;

import com.example.ex1.model.Client;
import com.example.ex1.repository.*;
import com.example.ex1.service.ClientManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = ClientManagerController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private ContractRepository contractRepository;
    @MockBean
    private DeviceRepository deviceRepository;
    @MockBean
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @MockBean
    private SignatureRepository signatureRepository;
    @MockBean
    private WarrantyRepository warrantyRepository;

    @MockBean
    private ClientManagementService clientManagementService;


    @Test
    public void createClient() throws Exception {
        Client client = Client.builder()
                .clientId(10)
                .firstName("abc")
                .lastName("def")
                .build();
        when(clientManagementService.save(any())).thenReturn(client);
        System.out.println(objectMapper.writeValueAsString(client));
        mockMvc.perform(post("/clients")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.firstName").value(client.getFirstName()));
    }


    @Test
    public void getClientByLastName() throws Exception {
        Client client = Client.builder()
                .clientId(10)
                .firstName("abc")
                .lastName("def")
                .build();
        when(clientManagementService.getBy(any(), eq("def"))).thenReturn(List.of(client));
        mockMvc.perform(get("/clients")
                        .contentType("application/json")
                        .param("lastName", "def"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].firstName").value("abc"));
    }
}

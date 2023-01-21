package com.example.ex1.controller;

import com.example.ex1.model.Client;
import com.example.ex1.model.SubscriptionPlan;
import com.example.ex1.repository.*;
import com.example.ex1.service.ClientManagementService;
import com.example.ex1.service.ContractManagementService;
import com.example.ex1.service.PlanStatisticsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PlanStatisticsController.class)
public class PlanStatisticsControllerTest {
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
    private PlanStatisticsService planStatisticsService;
    @MockBean
    private ClientManagementService clientManagementService;
    @MockBean
    private ContractManagementService contractManagementService;


    @Test
    public void getSubscriptionPlanPopularity() throws Exception {
        when(planStatisticsService.getNMostPopularSubscriptionPlan(any())).thenReturn(List.of());
        mockMvc.perform(get("/statistics")
                        .contentType("application/json")
                        .param("n", "1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("[]"));
    }
}

package com.example.ex1.controller;

import com.example.ex1.model.Contract;
import com.example.ex1.model.SubscriptionPlan;
import com.example.ex1.repository.*;
import com.example.ex1.service.ClientManagementService;
import com.example.ex1.service.ContractManagementService;
import com.example.ex1.service.PlanManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PlanManagementController.class)
public class PlanMangementControllerTest {

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
    private ContractManagementService contractManagementService;
    @MockBean
    private ClientManagementService clientManagementService;
    @MockBean
    private PlanManagementService planManagementService;

    @Test
    public void createPlan() throws Exception {
        SubscriptionPlan subscriptionPlan = SubscriptionPlan.builder()
                .details(List.of("abc", "def"))
                .name("name")
                .subscriptionPlanId(1)
                .pricePerYear(BigDecimal.valueOf(1))
                .build();

        when(planManagementService.addSubscriptionPlan(any())).thenReturn(subscriptionPlan);

        mockMvc.perform(MockMvcRequestBuilders.post("/plans")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(subscriptionPlan)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value(subscriptionPlan.getName()));
    }
}

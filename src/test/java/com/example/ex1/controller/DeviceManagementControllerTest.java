package com.example.ex1.controller;

import com.example.ex1.model.Client;
import com.example.ex1.model.Contract;
import com.example.ex1.repository.*;
import com.example.ex1.service.ClientManagementService;
import com.example.ex1.service.ContractManagementService;
import com.example.ex1.service.DeviceManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ContractDeviceController.class)
public class DeviceManagementControllerTest {
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
    private DeviceManagementService deviceManagementService;


    @Test
    public void createClient() throws Exception {
        Contract contract = Contract.builder()
                .contractId(1)
                .billingInterval(6)
                .date(new Date())
                .duration(12)
                .build();
        when(deviceManagementService.updateDeviceInContract(any(), any())).thenReturn(contract);

        List<String> values = Arrays.asList("1", "1", "1");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.addAll("devicesId", values);
        params.add("contractId", "1");

        mockMvc.perform(post("/devices")
                        .contentType("application/json")
                        .params(params))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.duration").value(contract.getDuration()));
    }
}

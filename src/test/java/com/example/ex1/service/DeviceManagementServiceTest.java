package com.example.ex1.service;

import com.example.ex1.model.*;
import com.example.ex1.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeviceManagementServiceTest {
    @InjectMocks
    private DeviceManagementService deviceManagementService;

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @Mock
    private ContractRepository contractRepository;
    @Mock
    private DeviceRepository deviceRepository;
    @Mock
    private SignatureRepository signatureRepository;

    @Test
    @DisplayName("Update contract devices successfully")
    void updateContractDevicesSuccessfully() {
        Contract contract = Contract.builder()
                .contractId(1)
                .billingInterval(6)
                .date(new Date())
                .duration(12)
                .build();

        Device device = new Device();
        contract.setDevices(List.of(device));

        when(contractRepository.findById(1)).thenReturn(Optional.of(contract));
        when(deviceRepository.findAllById(any())).thenReturn(List.of(device));
        when(contractRepository.save(contract)).thenReturn(contract);

        Contract result = deviceManagementService.updateDeviceInContract(contract.getContractId(), List.of());

        assertEquals(result.getDevices(), List.of(device));
    }
}

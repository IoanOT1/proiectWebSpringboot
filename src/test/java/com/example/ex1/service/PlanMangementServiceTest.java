package com.example.ex1.service;

import com.example.ex1.model.Client;
import com.example.ex1.model.Contract;
import com.example.ex1.model.Signature;
import com.example.ex1.model.SubscriptionPlan;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlanMangementServiceTest {
    /**
     * @InjectMock
     * @Mock
     */
    @InjectMocks
    private PlanManagementService planManagementService;

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
    @DisplayName("Create a subscription plan successfully")
    void createSubscriptionPlanSuccessfully() {
        SubscriptionPlan subscriptionPlan = SubscriptionPlan.builder()
                .details(List.of("abc", "def"))
                .name("name")
                .subscriptionPlanId(1)
                .pricePerYear(BigDecimal.valueOf(1))
                .build();

        when(subscriptionPlanRepository.save(any())).thenReturn(subscriptionPlan);

        SubscriptionPlan result = planManagementService.addSubscriptionPlan(subscriptionPlan);

        assertEquals(result.getName(), subscriptionPlan.getName());
    }
}

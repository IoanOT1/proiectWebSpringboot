package com.example.ex1.service;

import com.example.ex1.model.Client;
import com.example.ex1.model.SubscriptionPlan;
import com.example.ex1.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class PlanStatisticsServiceTest {
    @ExtendWith(MockitoExtension.class)

    @InjectMocks
    private PlanStatisticsService planStatisticsService;

    @Mock
    private ContractRepository contractRepository;

    @Test
    @DisplayName("Get plan statistics")
    void getPlanStatistics() {
        SubscriptionPlan subscriptionPlan = SubscriptionPlan.builder()
                .details(List.of("abc", "def"))
                .name("name")
                .subscriptionPlanId(1)
                .pricePerYear(BigDecimal.valueOf(1))
                .build();
        when(contractRepository.getNMostCommonSubscriptionPlans()).thenReturn(List.of(subscriptionPlan));
        List<SubscriptionPlan> result = planStatisticsService.getNMostPopularSubscriptionPlan(1);
        assertEquals(result.get(0), subscriptionPlan);
    }

}

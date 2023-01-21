package com.example.ex1.service;

import com.example.ex1.model.SubscriptionPlan;
import com.example.ex1.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanStatisticsService {
    @Autowired
    ContractRepository contractRepository;

    public List<SubscriptionPlan> getNMostPopularSubscriptionPlan(Integer n){
        return contractRepository.getNMostCommonSubscriptionPlans()
                .stream()
                .limit(n)
                .collect(Collectors.toList());
    }
}

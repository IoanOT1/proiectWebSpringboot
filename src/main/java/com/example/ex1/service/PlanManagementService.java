package com.example.ex1.service;

import com.example.ex1.model.SubscriptionPlan;
import com.example.ex1.repository.SubscriptionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO throw errors for bad input in service

@Service
public class PlanManagementService {
    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    public SubscriptionPlan addSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanRepository.save(subscriptionPlan);
    }
}

package com.example.ex1.controller;

import com.example.ex1.model.SubscriptionPlan;
import com.example.ex1.service.PlanManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/plans")
@Api(value = "/plans", description = "Operations related to subscription plans")
public class PlanManagementController {
    private final PlanManagementService planManagementService;
    public PlanManagementController(PlanManagementService planManagementService) {
        this.planManagementService = planManagementService;
    }

    @PostMapping
    @ApiOperation(value = "Operation which creates a new subscription plan", response = SubscriptionPlan.class)
    public ResponseEntity<SubscriptionPlan> create( @RequestBody SubscriptionPlan subscriptionPlan) {
        SubscriptionPlan createdSubscriptionPlan = planManagementService.addSubscriptionPlan(subscriptionPlan);
        return ResponseEntity
                .created(URI.create("/plans/" + createdSubscriptionPlan.getSubscriptionPlanId()))
                .body(createdSubscriptionPlan);
    }
}
